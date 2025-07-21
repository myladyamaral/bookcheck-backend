package br.com.bookcheck.mapper;

import br.com.bookcheck.controller.dto.request.CurtidaRequestDto;
import br.com.bookcheck.controller.dto.response.CurtidaResponseDto;
import br.com.bookcheck.domain.entity.Curtida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CurtidaMapper {

    CurtidaMapper INSTANCE = Mappers.getMapper(CurtidaMapper.class);

    @Mapping(target = "id", ignore = true)
    Curtida toEntity(CurtidaRequestDto dto);

    CurtidaResponseDto toResponseDto(Curtida entity);


}
