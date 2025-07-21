package br.com.bookcheck.mapper;

import br.com.bookcheck.controller.dto.request.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.response.CatalogoResponseDto;
import br.com.bookcheck.domain.entity.Sebo.Catalogo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CatalogoMapper {

    CatalogoMapper INSTANCE = Mappers.getMapper(CatalogoMapper.class);

    @Mapping(target = "id", ignore = true)
    Catalogo toEntity(CatalogoRequestDto dto);

    CatalogoResponseDto toResponseDto(Catalogo entity);


}
