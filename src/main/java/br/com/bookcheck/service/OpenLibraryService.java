package br.com.bookcheck.service;

import br.com.bookcheck.controller.dto.request.Livro.LivroRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OpenLibraryService {

    private final RestTemplate restTemplate;
    private final String openLibraryUrl;

    public OpenLibraryService(RestTemplate restTemplate,
                              @Value("${openlibrary.api.url:https://openlibrary.org}") String openLibraryUrl) {
        this.restTemplate = restTemplate;
        this.openLibraryUrl = openLibraryUrl;
    }

    public LivroRequestDto searchBookByIsbn(String isbn) {
        String url = UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
                .path("/api/books")
                .queryParam("bibkeys", "ISBN:"+isbn)
                .queryParam("format", "json")
                .queryParam("jscmd", "data")
                .toUriString();

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        Map<String, Object> bookData = (Map<String, Object>) response.get("ISBN:" + isbn);

        if (bookData == null) {
            return null;
        }

        return convertToLivroRequestDto(bookData);
    }

    private LivroRequestDto convertToLivroRequestDto(Map<String, Object> bookData) {
        return LivroRequestDto.builder()
                .isbn(extractIsbn(bookData))
                .titulo((String) bookData.get("title"))
                .ano(extractYear(bookData))
                .idioma(extractLanguage(bookData))// Você precisará criar/mapear a editora separadamente
                .build();
    }

    private String extractIsbn(Map<String, Object> bookData) {
        // Prefere ISBN-13, fallback para ISBN-10
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