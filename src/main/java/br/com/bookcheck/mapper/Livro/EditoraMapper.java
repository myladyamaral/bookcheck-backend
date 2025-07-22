package br.com.bookcheck.mapper.Livro;

import br.com.bookcheck.controller.dto.request.Livro.EditoraRequestDto;
import br.com.bookcheck.controller.dto.response.Livro.EditoraResponseDto;
import br.com.bookcheck.domain.entity.Livro.Editora;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EditoraMapper {

    EditoraMapper INSTANCE = Mappers.getMapper(EditoraMapper.class);

    @Mapping(target = "id", ignore = true)
    Editora toEntity(EditoraRequestDto dto);

    EditoraResponseDto toResponseDto(Editora entity);


}
