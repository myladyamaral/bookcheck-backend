package br.com.bookcheck.mapper;

import br.com.bookcheck.controller.dto.request.AutorRequestDto;
import br.com.bookcheck.controller.dto.request.LivroRequestDto;
import br.com.bookcheck.controller.dto.response.AutorResponseDto;
import br.com.bookcheck.controller.dto.response.LivroResponseDto;
import br.com.bookcheck.domain.entity.Livro.Autor;
import br.com.bookcheck.domain.entity.Livro.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    AutorMapper INSTANCE = Mappers.getMapper(AutorMapper.class);

    @Mapping(target = "id", ignore = true)
    Autor toEntity(AutorRequestDto dto);

    AutorResponseDto toResponseDto(Autor entity);


}
