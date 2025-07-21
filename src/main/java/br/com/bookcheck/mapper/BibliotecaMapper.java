package br.com.bookcheck.mapper;

import br.com.bookcheck.controller.dto.request.BibliotecaRequestDto;
import br.com.bookcheck.controller.dto.response.BibliotecaResponseDto;
import br.com.bookcheck.domain.entity.Leitor.Biblioteca;
import br.com.bookcheck.domain.enums.converter.EstadoConservacaoConverter;
import br.com.bookcheck.domain.enums.converter.StatusLeituraConverter;
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
