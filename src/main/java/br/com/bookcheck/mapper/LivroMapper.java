package br.com.bookcheck.mapper;

import br.com.bookcheck.controller.dto.request.LivroRequestDto;
import br.com.bookcheck.controller.dto.response.LivroResponseDto;
import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.enums.converter.GeneroLiterarioConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {AutorMapperResolver.class,
                EditoraMapperResolver.class,})
public interface LivroMapper {

    LivroMapper INSTANCE = Mappers.getMapper(LivroMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "autor", source = "autorId")
    @Mapping(target = "editora", source = "editoraId")
    Livro toEntity(LivroRequestDto dto);

    @Mapping(target = "autor", source = "autor")
    @Mapping(target = "editora", source = "editora")
    LivroResponseDto toResponseDto(Livro entity);

    void updateLivroFromDto(LivroRequestDto dto, @MappingTarget Livro entity);


}
