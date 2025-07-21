package br.com.bookcheck.mapper;

import br.com.bookcheck.controller.dto.request.EditoraRequestDto;
import br.com.bookcheck.controller.dto.request.LivroRequestDto;
import br.com.bookcheck.controller.dto.response.EditoraResponseDto;
import br.com.bookcheck.controller.dto.response.LivroResponseDto;
import br.com.bookcheck.domain.entity.Livro.Editora;
import br.com.bookcheck.domain.entity.Livro.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EditoraMapper {

    EditoraMapper INSTANCE = Mappers.getMapper(EditoraMapper.class);

    @Mapping(target = "id", ignore = true)
    Editora toEntity(EditoraRequestDto dto);

    EditoraResponseDto toResponseDto(Editora entity);


}
