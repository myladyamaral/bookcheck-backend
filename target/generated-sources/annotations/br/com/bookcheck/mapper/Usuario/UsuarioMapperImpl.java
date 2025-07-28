package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.EnderecoRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorUpdateRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.EnderecoResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioResumeResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioSeboResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Endereco;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import br.com.bookcheck.domain.entity.Usuario.UsuarioLeitor;
import br.com.bookcheck.domain.entity.Usuario.UsuarioSebo;
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
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioLeitor toEntity(UsuarioLeitorRequestDto request) {
        if ( request == null ) {
            return null;
        }

        UsuarioLeitor.UsuarioLeitorBuilder<?, ?> usuarioLeitor = UsuarioLeitor.builder();

        usuarioLeitor.descricao( request.getDescricao() );
        usuarioLeitor.email( request.getEmail() );
        usuarioLeitor.endereco( enderecoRequestDtoToEndereco( request.getEndereco() ) );
        usuarioLeitor.nome( request.getNome() );
        usuarioLeitor.senha( request.getSenha() );
        usuarioLeitor.telefone( request.getTelefone() );
        usuarioLeitor.cpf( request.getCpf() );
        usuarioLeitor.dataNascimento( request.getDataNascimento() );

        return usuarioLeitor.build();
    }

    @Override
    public UsuarioLeitorResponseDto toResponseDto(UsuarioLeitor entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioLeitorResponseDto.UsuarioLeitorResponseDtoBuilder<?, ?> usuarioLeitorResponseDto = UsuarioLeitorResponseDto.builder();

        usuarioLeitorResponseDto.descricao( entity.getDescricao() );
        usuarioLeitorResponseDto.email( entity.getEmail() );
        usuarioLeitorResponseDto.endereco( enderecoToEnderecoResponseDto( entity.getEndereco() ) );
        usuarioLeitorResponseDto.id( entity.getId() );
        usuarioLeitorResponseDto.nome( entity.getNome() );
        usuarioLeitorResponseDto.telefone( entity.getTelefone() );
        usuarioLeitorResponseDto.tipoUsuario( entity.getTipoUsuario() );
        usuarioLeitorResponseDto.cpf( entity.getCpf() );
        usuarioLeitorResponseDto.dataNascimento( entity.getDataNascimento() );

        return usuarioLeitorResponseDto.build();
    }

    @Override
    public void updateEntityFromDto(UsuarioLeitorUpdateRequestDto updated, UsuarioLeitor entity) {
        if ( updated == null ) {
            return;
        }

        entity.setDescricao( updated.getDescricao() );
        entity.setEmail( updated.getEmail() );
        if ( updated.getEndereco() != null ) {
            if ( entity.getEndereco() == null ) {
                entity.setEndereco( Endereco.builder().build() );
            }
            enderecoRequestDtoToEndereco1( updated.getEndereco(), entity.getEndereco() );
        }
        else {
            entity.setEndereco( null );
        }
        entity.setNome( updated.getNome() );
        entity.setSenha( updated.getSenha() );
        entity.setTelefone( updated.getTelefone() );
        entity.setDataNascimento( updated.getDataNascimento() );
    }

    @Override
    public UsuarioSebo toEntity(UsuarioSeboRequestDto request) {
        if ( request == null ) {
            return null;
        }

        UsuarioSebo.UsuarioSeboBuilder<?, ?> usuarioSebo = UsuarioSebo.builder();

        usuarioSebo.descricao( request.getDescricao() );
        usuarioSebo.email( request.getEmail() );
        usuarioSebo.endereco( enderecoRequestDtoToEndereco( request.getEndereco() ) );
        usuarioSebo.nome( request.getNome() );
        usuarioSebo.senha( request.getSenha() );
        usuarioSebo.telefone( request.getTelefone() );
        usuarioSebo.cnpj( request.getCnpj() );
        usuarioSebo.horario( request.getHorario() );

        return usuarioSebo.build();
    }

    @Override
    public UsuarioSeboResponseDto toResponseDto(UsuarioSebo entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioSeboResponseDto.UsuarioSeboResponseDtoBuilder<?, ?> usuarioSeboResponseDto = UsuarioSeboResponseDto.builder();

        usuarioSeboResponseDto.descricao( entity.getDescricao() );
        usuarioSeboResponseDto.email( entity.getEmail() );
        usuarioSeboResponseDto.endereco( enderecoToEnderecoResponseDto( entity.getEndereco() ) );
        usuarioSeboResponseDto.id( entity.getId() );
        usuarioSeboResponseDto.nome( entity.getNome() );
        usuarioSeboResponseDto.telefone( entity.getTelefone() );
        usuarioSeboResponseDto.tipoUsuario( entity.getTipoUsuario() );
        usuarioSeboResponseDto.cnpj( entity.getCnpj() );
        usuarioSeboResponseDto.horario( entity.getHorario() );

        return usuarioSeboResponseDto.build();
    }

    @Override
    public void updateEntityFromDto(UsuarioSeboRequestDto updated, UsuarioSebo entity) {
        if ( updated == null ) {
            return;
        }

        entity.setDescricao( updated.getDescricao() );
        entity.setEmail( updated.getEmail() );
        if ( updated.getEndereco() != null ) {
            if ( entity.getEndereco() == null ) {
                entity.setEndereco( Endereco.builder().build() );
            }
            enderecoRequestDtoToEndereco1( updated.getEndereco(), entity.getEndereco() );
        }
        else {
            entity.setEndereco( null );
        }
        entity.setNome( updated.getNome() );
        entity.setSenha( updated.getSenha() );
        entity.setTelefone( updated.getTelefone() );
        entity.setCnpj( updated.getCnpj() );
        entity.setHorario( updated.getHorario() );
    }

    @Override
    public UsuarioResumeResponseDto toResumeResponseDto(Usuario entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioResumeResponseDto.UsuarioResumeResponseDtoBuilder<?, ?> usuarioResumeResponseDto = UsuarioResumeResponseDto.builder();

        usuarioResumeResponseDto.descricao( entity.getDescricao() );
        usuarioResumeResponseDto.id( entity.getId() );
        usuarioResumeResponseDto.nome( entity.getNome() );

        return usuarioResumeResponseDto.build();
    }

    @Override
    public List<UsuarioResumeResponseDto> toResponseDto(List<Usuario> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UsuarioResumeResponseDto> list = new ArrayList<UsuarioResumeResponseDto>( entities.size() );
        for ( Usuario usuario : entities ) {
            list.add( toResumeResponseDto( usuario ) );
        }

        return list;
    }

    protected Endereco enderecoRequestDtoToEndereco(EnderecoRequestDto enderecoRequestDto) {
        if ( enderecoRequestDto == null ) {
            return null;
        }

        Endereco.EnderecoBuilder<?, ?> endereco = Endereco.builder();

        endereco.bairro( enderecoRequestDto.getBairro() );
        endereco.cep( enderecoRequestDto.getCep() );
        endereco.cidade( enderecoRequestDto.getCidade() );
        endereco.complemento( enderecoRequestDto.getComplemento() );
        endereco.logradouro( enderecoRequestDto.getLogradouro() );
        endereco.numero( enderecoRequestDto.getNumero() );
        endereco.tipoLogradouro( enderecoRequestDto.getTipoLogradouro() );
        endereco.uf( enderecoRequestDto.getUf() );

        return endereco.build();
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

    protected void enderecoRequestDtoToEndereco1(EnderecoRequestDto enderecoRequestDto, Endereco mappingTarget) {
        if ( enderecoRequestDto == null ) {
            return;
        }

        mappingTarget.setBairro( enderecoRequestDto.getBairro() );
        mappingTarget.setCep( enderecoRequestDto.getCep() );
        mappingTarget.setCidade( enderecoRequestDto.getCidade() );
        mappingTarget.setComplemento( enderecoRequestDto.getComplemento() );
        mappingTarget.setLogradouro( enderecoRequestDto.getLogradouro() );
        mappingTarget.setNumero( enderecoRequestDto.getNumero() );
        mappingTarget.setTipoLogradouro( enderecoRequestDto.getTipoLogradouro() );
        mappingTarget.setUf( enderecoRequestDto.getUf() );
    }
}
