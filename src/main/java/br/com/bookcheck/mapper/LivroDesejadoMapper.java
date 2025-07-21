package br.com.bookcheck.mapper;

import br.com.bookcheck.controller.dto.request.LivroDesejadoRequestDto;
import br.com.bookcheck.controller.dto.response.LivroDesejadoResponseDto;
import br.com.bookcheck.domain.entity.Leitor.LivroDesejado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LivroDesejadoMapper {

    LivroDesejadoMapper INSTANCE = Mappers.getMapper(LivroDesejadoMapper.class);

    @Mapping(target = "id", ignore = true)
    LivroDesejado toEntity(LivroDesejadoRequestDto dto);

    LivroDesejadoResponseDto toResponseDto(LivroDesejado entity);


}
