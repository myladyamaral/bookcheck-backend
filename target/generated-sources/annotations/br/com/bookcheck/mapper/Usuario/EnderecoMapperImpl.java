package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.EnderecoRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.EnderecoResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Endereco;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T21:23:03-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class EnderecoMapperImpl implements EnderecoMapper {

    @Override
    public Endereco toEntity(EnderecoRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Endereco.EnderecoBuilder<?, ?> endereco = Endereco.builder();

        endereco.bairro( dto.getBairro() );
        endereco.cep( dto.getCep() );
        endereco.cidade( dto.getCidade() );
        endereco.complemento( dto.getComplemento() );
        endereco.logradouro( dto.getLogradouro() );
        endereco.numero( dto.getNumero() );
        endereco.tipoLogradouro( dto.getTipoLogradouro() );
        endereco.uf( dto.getUf() );

        return endereco.build();
    }

    @Override
    public EnderecoResponseDto toResponseDto(Endereco entity) {
        if ( entity == null ) {
            return null;
        }

        EnderecoResponseDto.EnderecoResponseDtoBuilder<?, ?> enderecoResponseDto = EnderecoResponseDto.builder();

        enderecoResponseDto.bairro( entity.getBairro() );
        enderecoResponseDto.cep( entity.getCep() );
        enderecoResponseDto.cidade( entity.getCidade() );
        enderecoResponseDto.complemento( entity.getComplemento() );
        enderecoResponseDto.id( entity.getId() );
        enderecoResponseDto.logradouro( entity.getLogradouro() );
        enderecoResponseDto.numero( entity.getNumero() );
        enderecoResponseDto.tipoLogradouro( entity.getTipoLogradouro() );
        enderecoResponseDto.uf( entity.getUf() );

        return enderecoResponseDto.build();
    }

    @Override
    public void updateEnderecoFromDto(EnderecoRequestDto dto, Endereco entity) {
        if ( dto == null ) {
            return;
        }

        entity.setBairro( dto.getBairro() );
        entity.setCep( dto.getCep() );
        entity.setCidade( dto.getCidade() );
        entity.setComplemento( dto.getComplemento() );
        entity.setLogradouro( dto.getLogradouro() );
        entity.setNumero( dto.getNumero() );
        entity.setTipoLogradouro( dto.getTipoLogradouro() );
        entity.setUf( dto.getUf() );
    }
}
