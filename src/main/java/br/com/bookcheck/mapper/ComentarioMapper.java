package br.com.bookcheck.mapper;

import br.com.bookcheck.controller.dto.request.ComentarioRequestDto;
import br.com.bookcheck.controller.dto.response.ComentarioResponseDto;
import br.com.bookcheck.domain.entity.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    ComentarioMapper INSTANCE = Mappers.getMapper(ComentarioMapper.class);

    @Mapping(target = "id", ignore = true)
    Comentario toEntity(ComentarioRequestDto dto);

    ComentarioResponseDto toResponseDto(Comentario entity);


}
