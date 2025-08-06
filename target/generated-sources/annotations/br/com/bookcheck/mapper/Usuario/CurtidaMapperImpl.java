package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.CurtidaRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.CurtidaResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Curtida;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T21:06:08-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class CurtidaMapperImpl implements CurtidaMapper {

    @Override
    public Curtida toEntity(CurtidaRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Curtida.CurtidaBuilder<?, ?> curtida = Curtida.builder();

        curtida.dataHora( dto.getDataHora() );

        return curtida.build();
    }

    @Override
    public CurtidaResponseDto toResponseDto(Curtida entity) {
        if ( entity == null ) {
            return null;
        }

        CurtidaResponseDto.CurtidaResponseDtoBuilder curtidaResponseDto = CurtidaResponseDto.builder();

        curtidaResponseDto.dataHora( entity.getDataHora() );
        curtidaResponseDto.id( entity.getId() );

        return curtidaResponseDto.build();
    }
}
