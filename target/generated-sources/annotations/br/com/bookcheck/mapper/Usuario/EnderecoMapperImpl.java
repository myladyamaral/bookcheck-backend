package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.EnderecoRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.EnderecoResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Endereco;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-28T12:16:55-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
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

        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto();

        enderecoResponseDto.setBairro( entity.getBairro() );
        enderecoResponseDto.setCep( entity.getCep() );
        enderecoResponseDto.setCidade( entity.getCidade() );
        enderecoResponseDto.setComplemento( entity.getComplemento() );
        enderecoResponseDto.setId( entity.getId() );
        enderecoResponseDto.setLogradouro( entity.getLogradouro() );
        enderecoResponseDto.setNumero( entity.getNumero() );
        enderecoResponseDto.setTipoLogradouro( entity.getTipoLogradouro() );
        enderecoResponseDto.setUf( entity.getUf() );

        return enderecoResponseDto;
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
