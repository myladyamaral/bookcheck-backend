package br.com.bookcheck.service;

import br.com.bookcheck.controller.dto.request.Livro.LivroRequestDto;
import br.com.bookcheck.controller.dto.response.Search.AutorSearchDto;
import br.com.bookcheck.controller.dto.response.Search.EditoraSearchDto;
import br.com.bookcheck.controller.dto.response.Search.LivroSearchDto;
import br.com.bookcheck.controller.dto.response.Search.SearchResultDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OpenLibraryService {

    private final RestTemplate restTemplate;
    private final String openLibraryUrl;
    private final ObjectMapper objectMapper;

    public OpenLibraryService(RestTemplate restTemplate,
                              @Value("${openlibrary.api.url:https://openlibrary.org}") String openLibraryUrl,
                              ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.openLibraryUrl = openLibraryUrl;
        this.objectMapper = objectMapper;
    }

    public LivroRequestDto searchBookByIsbn(String isbn) {
        String url = UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
                .path("/api/books")
                .queryParam("bibkeys", "ISBN:"+isbn)
                .queryParam("format", "json")
                .queryParam("jscmd", "data")
                .toUriString();

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        if (response == null || response.isEmpty()) {
            return null;
        }
        Map<String, Object> bookData = (Map<String, Object>) response.get("ISBN:" + isbn);

        if (bookData == null) {
            return null;
        }

        return convertToLivroRequestDto(bookData);
    }

    public SearchResultDto search(String query) {
        CompletableFuture<List<LivroSearchDto>> booksFuture = searchBooksAsync(query);
        CompletableFuture<List<AutorSearchDto>> authorsFuture = searchAuthorsAsync(query);
        CompletableFuture<List<EditoraSearchDto>> publishersFuture = searchPublishersAsync(query);

        CompletableFuture.allOf(booksFuture, authorsFuture, publishersFuture).join();

        try {
            return new SearchResultDto(booksFuture.get(), authorsFuture.get(), publishersFuture.get());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar resultados da OpenLibrary", e);
        }
    }

    private CompletableFuture<List<LivroSearchDto>> searchBooksAsync(String query) {
        return CompletableFuture.supplyAsync(() -> {
            String url = UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
                    .path("/search.json")
                    .queryParam("q", query)
                    .queryParam("limit", 5)
                    .queryParam("fields", "key,title,author_name")
                    .toUriString();
            try {
                JsonNode root = objectMapper.readTree(restTemplate.getForObject(url, String.class));
                return StreamSupport.stream(root.path("docs").spliterator(), false)
                        .map(doc -> new LivroSearchDto(
                                doc.path("key").asText(),
                                doc.path("title").asText(),
                                doc.path("author_name").has(0) ? doc.path("author_name").get(0).asText() : "Autor desconhecido"
                        ))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                return new ArrayList<>();
            }
        });
    }

    private CompletableFuture<List<AutorSearchDto>> searchAuthorsAsync(String query) {
        return CompletableFuture.supplyAsync(() -> {
            String url = UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
                    .path("/search/authors.json")
                    .queryParam("q", query)
                    .queryParam("limit", 3)
                    .queryParam("fields", "key,name")
                    .toUriString();
            try {
                JsonNode root = objectMapper.readTree(restTemplate.getForObject(url, String.class));
                return StreamSupport.stream(root.path("docs").spliterator(), false)
                        .map(doc -> new AutorSearchDto(
                                doc.path("key").asText(),
                                doc.path("name").asText()
                        ))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                return new ArrayList<>();
            }
        });
    }

    private CompletableFuture<List<EditoraSearchDto>> searchPublishersAsync(String query) {
        return CompletableFuture.supplyAsync(() -> {
            String url = UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
                    .path("/search.json")
                    .queryParam("publisher", query)
                    .queryParam("limit", 10) // Aumenta o limite para ter mais chance de encontrar editoras únicas
                    .queryParam("fields", "publisher")
                    .toUriString();
            try {
                JsonNode root = objectMapper.readTree(restTemplate.getForObject(url, String.class));
                return StreamSupport.stream(root.path("docs").spliterator(), false)
                        .flatMap(doc -> StreamSupport.stream(doc.path("publisher").spliterator(), false))
                        .map(JsonNode::asText)
                        .distinct()
                        .limit(3) // Limita o resultado final para 3 editoras
                        .map(name -> new EditoraSearchDto(
                                URLEncoder.encode(name.toLowerCase().replace(" ", "-"), StandardCharsets.UTF_8),
                                name
                        ))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                return new ArrayList<>();
            }
        });
    }

    private LivroRequestDto convertToLivroRequestDto(Map<String, Object> bookData) {
        return LivroRequestDto.builder()
                .isbn(extractIsbn(bookData))
                .titulo((String) bookData.get("title"))
                .ano(extractYear(bookData))
                .idioma(extractLanguage(bookData))
                .build();
    }

    private String extractIsbn(Map<String, Object> bookData) {
        return getFirstFromList(bookData, "isbn_13")
                .orElseGet(() -> getFirstFromList(bookData, "isbn_10")
                        .orElse(null));
    }
    private Optional<String> getFirstFromList(Map<String, Object> bookData, String key) {
        if (bookData.containsKey(key)) {
            List<String> values = (List<String>) bookData.get(key);
            if (values != null && !values.isEmpty()) {
                return Optional.of(values.get(0));
            }
        }
        return Optional.empty();
    }

    private Year extractYear(Map<String, Object> bookData) {
        if (bookData.containsKey("publish_date")) {
            String publishDate = (String) bookData.get("publish_date");
            try {
                return Year.parse(publishDate.substring(publishDate.length() - 4));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private String extractLanguage(Map<String, Object> bookData) {
        if (bookData.containsKey("languages")) {
            java.util.List<Map<String, String>> languages = (java.util.List<Map<String, String>>) bookData.get("languages");
            if (!languages.isEmpty()) {
                return languages.get(0).get("key").replace("/languages/", "");
            }
        }
        return null;
    }
}
