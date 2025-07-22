package br.com.bookcheck.mapper.Livro;

import br.com.bookcheck.controller.dto.request.Livro.AutorRequestDto;
import br.com.bookcheck.controller.dto.response.Livro.AutorResponseDto;
import br.com.bookcheck.domain.entity.Livro.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    AutorMapper INSTANCE = Mappers.getMapper(AutorMapper.class);

    @Mapping(target = "id", ignore = true)
    Autor toEntity(AutorRequestDto dto);

    AutorResponseDto toResponseDto(Autor entity);


}
