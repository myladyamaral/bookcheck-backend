package br.com.bookcheck.mapper.Leitor;

import br.com.bookcheck.controller.dto.request.Leitor.BibliotecaRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.BibliotecaResponseDto;
import br.com.bookcheck.domain.entity.Leitor.Biblioteca;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BibliotecaMapper {

    BibliotecaMapper INSTANCE = Mappers.getMapper(BibliotecaMapper.class);

    @Mapping(target = "id", ignore = true)
    Biblioteca toEntity(BibliotecaRequestDto dto);

    BibliotecaResponseDto toResponseDto(Biblioteca entity);


}
