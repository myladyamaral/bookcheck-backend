package br.com.bookcheck.mapper.Leitor;

import br.com.bookcheck.controller.dto.request.Leitor.BibliotecaRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.BibliotecaResponseDto;
import br.com.bookcheck.domain.entity.Leitor.Biblioteca;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-28T12:16:55-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class BibliotecaMapperImpl implements BibliotecaMapper {

    @Override
    public Biblioteca toEntity(BibliotecaRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Biblioteca.BibliotecaBuilder<?, ?> biblioteca = Biblioteca.builder();

        biblioteca.estadoConservacao( dto.getEstadoConservacao() );
        biblioteca.statusLeitura( dto.getStatusLeitura() );

        return biblioteca.build();
    }

    @Override
    public BibliotecaResponseDto toResponseDto(Biblioteca entity) {
        if ( entity == null ) {
            return null;
        }

        BibliotecaResponseDto.BibliotecaResponseDtoBuilder bibliotecaResponseDto = BibliotecaResponseDto.builder();

        bibliotecaResponseDto.estadoConservacao( entity.getEstadoConservacao() );
        bibliotecaResponseDto.id( entity.getId() );
        bibliotecaResponseDto.statusLeitura( entity.getStatusLeitura() );

        return bibliotecaResponseDto.build();
    }
}
