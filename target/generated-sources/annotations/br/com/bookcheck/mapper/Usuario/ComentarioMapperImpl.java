package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.ComentarioRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.ComentarioResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Comentario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T21:31:17-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ComentarioMapperImpl implements ComentarioMapper {

    @Override
    public Comentario toEntity(ComentarioRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Comentario.ComentarioBuilder<?, ?> comentario = Comentario.builder();

        comentario.dataHora( dto.getDataHora() );
        comentario.texto( dto.getTexto() );

        return comentario.build();
    }

    @Override
    public ComentarioResponseDto toResponseDto(Comentario entity) {
        if ( entity == null ) {
            return null;
        }

        ComentarioResponseDto.ComentarioResponseDtoBuilder comentarioResponseDto = ComentarioResponseDto.builder();

        comentarioResponseDto.dataHora( entity.getDataHora() );
        comentarioResponseDto.id( entity.getId() );
        comentarioResponseDto.texto( entity.getTexto() );

        return comentarioResponseDto.build();
    }

    @Override
    public List<ComentarioResponseDto> toResponseDto(List<Comentario> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ComentarioResponseDto> list = new ArrayList<ComentarioResponseDto>( entities.size() );
        for ( Comentario comentario : entities ) {
            list.add( toResponseDto( comentario ) );
        }

        return list;
    }
}
