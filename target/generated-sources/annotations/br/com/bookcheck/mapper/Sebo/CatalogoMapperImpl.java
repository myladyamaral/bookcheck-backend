package br.com.bookcheck.mapper.Sebo;

import br.com.bookcheck.controller.dto.request.Sebo.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.response.Livro.AutorResponseDto;
import br.com.bookcheck.controller.dto.response.Livro.EditoraResponseDto;
import br.com.bookcheck.controller.dto.response.Livro.LivroResponseDto;
import br.com.bookcheck.controller.dto.response.Sebo.CatalogoResponseDto;
import br.com.bookcheck.domain.entity.Livro.Autor;
import br.com.bookcheck.domain.entity.Livro.Editora;
import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.entity.Sebo.Catalogo;
import br.com.bookcheck.domain.entity.Usuario.UsuarioSebo;
import br.com.bookcheck.mapper.Usuario.UsuarioMapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T21:05:35-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class CatalogoMapperImpl implements CatalogoMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public Catalogo toEntity(CatalogoRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Catalogo.CatalogoBuilder<?, ?> catalogo = Catalogo.builder();

        catalogo.livro( catalogoRequestDtoToLivro( dto ) );
        catalogo.sebo( catalogoRequestDtoToUsuarioSebo( dto ) );
        catalogo.estadoConservacao( dto.getEstadoConservacao() );
        catalogo.preco( dto.getPreco() );
        catalogo.quantidade( dto.getQuantidade() );
        catalogo.status( dto.getStatus() );

        return catalogo.build();
    }

    @Override
    public CatalogoResponseDto toResponseDto(Catalogo entity) {
        if ( entity == null ) {
            return null;
        }

        CatalogoResponseDto.CatalogoResponseDtoBuilder catalogoResponseDto = CatalogoResponseDto.builder();

        catalogoResponseDto.livro( livroToLivroResponseDto( entity.getLivro() ) );
        catalogoResponseDto.sebo( usuarioMapper.toResponseDto( entity.getSebo() ) );
        catalogoResponseDto.estadoConservacao( entity.getEstadoConservacao() );
        catalogoResponseDto.id( entity.getId() );
        catalogoResponseDto.preco( entity.getPreco() );
        catalogoResponseDto.quantidade( entity.getQuantidade() );
        catalogoResponseDto.status( entity.getStatus() );

        return catalogoResponseDto.build();
    }

    @Override
    public List<CatalogoResponseDto> toResponseDto(List<Catalogo> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CatalogoResponseDto> list = new ArrayList<CatalogoResponseDto>( entities.size() );
        for ( Catalogo catalogo : entities ) {
            list.add( toResponseDto( catalogo ) );
        }

        return list;
    }

    protected Livro catalogoRequestDtoToLivro(CatalogoRequestDto catalogoRequestDto) {
        if ( catalogoRequestDto == null ) {
            return null;
        }

        Livro.LivroBuilder<?, ?> livro = Livro.builder();

        livro.id( catalogoRequestDto.getLivroId() );

        return livro.build();
    }

    protected UsuarioSebo catalogoRequestDtoToUsuarioSebo(CatalogoRequestDto catalogoRequestDto) {
        if ( catalogoRequestDto == null ) {
            return null;
        }

        UsuarioSebo.UsuarioSeboBuilder<?, ?> usuarioSebo = UsuarioSebo.builder();

        usuarioSebo.id( catalogoRequestDto.getSeboId() );

        return usuarioSebo.build();
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

    protected LivroResponseDto livroToLivroResponseDto(Livro livro) {
        if ( livro == null ) {
            return null;
        }

        LivroResponseDto.LivroResponseDtoBuilder livroResponseDto = LivroResponseDto.builder();

        livroResponseDto.ano( livro.getAno() );
        livroResponseDto.autor( autorToAutorResponseDto( livro.getAutor() ) );
        livroResponseDto.editora( editoraToEditoraResponseDto( livro.getEditora() ) );
        livroResponseDto.genero( livro.getGenero() );
        livroResponseDto.id( livro.getId() );
        livroResponseDto.idioma( livro.getIdioma() );
        livroResponseDto.isbn( livro.getIsbn() );
        livroResponseDto.titulo( livro.getTitulo() );

        return livroResponseDto.build();
    }
}
