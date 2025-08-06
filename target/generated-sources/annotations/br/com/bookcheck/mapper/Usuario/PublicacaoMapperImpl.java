package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.PublicacaoRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.PublicacaoResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Publicacao;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T21:06:07-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class PublicacaoMapperImpl implements PublicacaoMapper {

    @Override
    public Publicacao toEntity(PublicacaoRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Publicacao.PublicacaoBuilder<?, ?> publicacao = Publicacao.builder();

        publicacao.dataHora( dto.getDataHora() );
        publicacao.texto( dto.getTexto() );

        return publicacao.build();
    }

    @Override
    public PublicacaoResponseDto toResponseDto(Publicacao entity) {
        if ( entity == null ) {
            return null;
        }

        PublicacaoResponseDto.PublicacaoResponseDtoBuilder publicacaoResponseDto = PublicacaoResponseDto.builder();

        publicacaoResponseDto.dataHora( entity.getDataHora() );
        publicacaoResponseDto.id( entity.getId() );
        publicacaoResponseDto.texto( entity.getTexto() );

        return publicacaoResponseDto.build();
    }
}
