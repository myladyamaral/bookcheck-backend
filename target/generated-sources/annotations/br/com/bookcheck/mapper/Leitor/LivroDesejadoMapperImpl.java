package br.com.bookcheck.mapper.Leitor;

import br.com.bookcheck.controller.dto.request.Leitor.LivroDesejadoRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.LivroDesejadoResponseDto;
import br.com.bookcheck.controller.dto.response.Livro.AutorResponseDto;
import br.com.bookcheck.controller.dto.response.Livro.EditoraResponseDto;
import br.com.bookcheck.controller.dto.response.Livro.LivroResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.EnderecoResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
import br.com.bookcheck.domain.entity.Leitor.LivroDesejado;
import br.com.bookcheck.domain.entity.Livro.Autor;
import br.com.bookcheck.domain.entity.Livro.Editora;
import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.entity.Usuario.Endereco;
import br.com.bookcheck.domain.entity.Usuario.UsuarioLeitor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-28T12:16:55-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class LivroDesejadoMapperImpl implements LivroDesejadoMapper {

    @Override
    public LivroDesejado toEntity(LivroDesejadoRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        LivroDesejado.LivroDesejadoBuilder<?, ?> livroDesejado = LivroDesejado.builder();

        livroDesejado.livro( livroDesejadoRequestDtoToLivro( dto ) );
        livroDesejado.leitor( livroDesejadoRequestDtoToUsuarioLeitor( dto ) );

        return livroDesejado.build();
    }

    @Override
    public LivroDesejadoResponseDto toResponseDto(LivroDesejado entity) {
        if ( entity == null ) {
            return null;
        }

        LivroDesejadoResponseDto.LivroDesejadoResponseDtoBuilder livroDesejadoResponseDto = LivroDesejadoResponseDto.builder();

        livroDesejadoResponseDto.livro( livroToLivroResponseDto( entity.getLivro() ) );
        livroDesejadoResponseDto.leitor( usuarioLeitorToUsuarioLeitorResponseDto( entity.getLeitor() ) );
        livroDesejadoResponseDto.id( entity.getId() );

        return livroDesejadoResponseDto.build();
    }

    @Override
    public List<LivroDesejadoResponseDto> toResponseDto(List<LivroDesejado> entities) {
        if ( entities == null ) {
            return null;
        }

        List<LivroDesejadoResponseDto> list = new ArrayList<LivroDesejadoResponseDto>( entities.size() );
        for ( LivroDesejado livroDesejado : entities ) {
            list.add( toResponseDto( livroDesejado ) );
        }

        return list;
    }

    protected Livro livroDesejadoRequestDtoToLivro(LivroDesejadoRequestDto livroDesejadoRequestDto) {
        if ( livroDesejadoRequestDto == null ) {
            return null;
        }

        Livro.LivroBuilder<?, ?> livro = Livro.builder();

        livro.id( livroDesejadoRequestDto.getLivroId() );

        return livro.build();
    }

    protected UsuarioLeitor livroDesejadoRequestDtoToUsuarioLeitor(LivroDesejadoRequestDto livroDesejadoRequestDto) {
        if ( livroDesejadoRequestDto == null ) {
            return null;
        }

        UsuarioLeitor.UsuarioLeitorBuilder<?, ?> usuarioLeitor = UsuarioLeitor.builder();

        usuarioLeitor.id( livroDesejadoRequestDto.getLeitorId() );

        return usuarioLeitor.build();
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

    protected EnderecoResponseDto enderecoToEnderecoResponseDto(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto();

        enderecoResponseDto.setBairro( endereco.getBairro() );
        enderecoResponseDto.setCep( endereco.getCep() );
        enderecoResponseDto.setCidade( endereco.getCidade() );
        enderecoResponseDto.setComplemento( endereco.getComplemento() );
        enderecoResponseDto.setId( endereco.getId() );
        enderecoResponseDto.setLogradouro( endereco.getLogradouro() );
        enderecoResponseDto.setNumero( endereco.getNumero() );
        enderecoResponseDto.setTipoLogradouro( endereco.getTipoLogradouro() );
        enderecoResponseDto.setUf( endereco.getUf() );

        return enderecoResponseDto;
    }

    protected UsuarioLeitorResponseDto usuarioLeitorToUsuarioLeitorResponseDto(UsuarioLeitor usuarioLeitor) {
        if ( usuarioLeitor == null ) {
            return null;
        }

        UsuarioLeitorResponseDto.UsuarioLeitorResponseDtoBuilder<?, ?> usuarioLeitorResponseDto = UsuarioLeitorResponseDto.builder();

        usuarioLeitorResponseDto.descricao( usuarioLeitor.getDescricao() );
        usuarioLeitorResponseDto.email( usuarioLeitor.getEmail() );
        usuarioLeitorResponseDto.endereco( enderecoToEnderecoResponseDto( usuarioLeitor.getEndereco() ) );
        usuarioLeitorResponseDto.id( usuarioLeitor.getId() );
        usuarioLeitorResponseDto.nome( usuarioLeitor.getNome() );
        usuarioLeitorResponseDto.telefone( usuarioLeitor.getTelefone() );
        usuarioLeitorResponseDto.tipoUsuario( usuarioLeitor.getTipoUsuario() );
        usuarioLeitorResponseDto.cpf( usuarioLeitor.getCpf() );
        usuarioLeitorResponseDto.dataNascimento( usuarioLeitor.getDataNascimento() );

        return usuarioLeitorResponseDto.build();
    }
}
