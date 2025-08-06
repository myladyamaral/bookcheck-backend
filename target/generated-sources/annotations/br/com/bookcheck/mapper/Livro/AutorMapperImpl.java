package br.com.bookcheck.mapper.Livro;

import br.com.bookcheck.controller.dto.request.Livro.AutorRequestDto;
import br.com.bookcheck.controller.dto.response.Livro.AutorResponseDto;
import br.com.bookcheck.domain.entity.Livro.Autor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T21:05:53-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class AutorMapperImpl implements AutorMapper {

    @Override
    public Autor toEntity(AutorRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Autor.AutorBuilder<?, ?> autor = Autor.builder();

        autor.nome( dto.getNome() );

        return autor.build();
    }

    @Override
    public AutorResponseDto toResponseDto(Autor entity) {
        if ( entity == null ) {
            return null;
        }

        AutorResponseDto.AutorResponseDtoBuilder autorResponseDto = AutorResponseDto.builder();

        autorResponseDto.id( entity.getId() );
        autorResponseDto.nome( entity.getNome() );

        return autorResponseDto.build();
    }
}
