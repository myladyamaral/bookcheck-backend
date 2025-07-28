package br.com.bookcheck.mapper.Livro;

import br.com.bookcheck.controller.dto.request.Livro.LivroRequestDto;
import br.com.bookcheck.controller.dto.response.Livro.AutorResponseDto;
import br.com.bookcheck.controller.dto.response.Livro.EditoraResponseDto;
import br.com.bookcheck.controller.dto.response.Livro.LivroResponseDto;
import br.com.bookcheck.domain.entity.Livro.Autor;
import br.com.bookcheck.domain.entity.Livro.Editora;
import br.com.bookcheck.domain.entity.Livro.Livro;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-28T12:16:56-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class LivroMapperImpl implements LivroMapper {

    @Autowired
    private AutorMapperResolver autorMapperResolver;
    @Autowired
    private EditoraMapperResolver editoraMapperResolver;

    @Override
    public Livro toEntity(LivroRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Livro.LivroBuilder<?, ?> livro = Livro.builder();

        livro.autor( autorMapperResolver.toAutor( dto.getAutorId() ) );
        livro.editora( editoraMapperResolver.toEditora( dto.getEditoraId() ) );
        livro.ano( dto.getAno() );
        livro.genero( dto.getGenero() );
        livro.idioma( dto.getIdioma() );
        livro.isbn( dto.getIsbn() );
        livro.titulo( dto.getTitulo() );

        return livro.build();
    }

    @Override
    public LivroResponseDto toResponseDto(Livro entity) {
        if ( entity == null ) {
            return null;
        }

        LivroResponseDto.LivroResponseDtoBuilder livroResponseDto = LivroResponseDto.builder();

        livroResponseDto.autor( autorToAutorResponseDto( entity.getAutor() ) );
        livroResponseDto.editora( editoraToEditoraResponseDto( entity.getEditora() ) );
        livroResponseDto.ano( entity.getAno() );
        livroResponseDto.genero( entity.getGenero() );
        livroResponseDto.id( entity.getId() );
        livroResponseDto.idioma( entity.getIdioma() );
        livroResponseDto.isbn( entity.getIsbn() );
        livroResponseDto.titulo( entity.getTitulo() );

        return livroResponseDto.build();
    }

    @Override
    public void updateLivroFromDto(LivroRequestDto dto, Livro entity) {
        if ( dto == null ) {
            return;
        }

        entity.setAno( dto.getAno() );
        entity.setGenero( dto.getGenero() );
        entity.setIdioma( dto.getIdioma() );
        entity.setIsbn( dto.getIsbn() );
        entity.setTitulo( dto.getTitulo() );
    }

    protected AutorResponseDto autorToAutorResponseDto(Autor autor) {
        if ( autor == null ) {
            return null;
        }

        AutorResponseDto.AutorResponseDtoBuilder autorResponseDto = AutorResponseDto.builder();

        autorResponseDto.id( autor.getId() );
        autorResponseDto.nome( autor.getNome() );

        return autorResponseDto.build();
    }

    protected EditoraResponseDto editoraToEditoraResponseDto(Editora editora) {
        if ( editora == null ) {
            return null;
        }

        EditoraResponseDto.EditoraResponseDtoBuilder editoraResponseDto = EditoraResponseDto.builder();

        editoraResponseDto.cnpj( editora.getCnpj() );
        editoraResponseDto.id( editora.getId() );
        editoraResponseDto.nome( editora.getNome() );

        return editoraResponseDto.build();
    }
}
