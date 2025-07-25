package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.CurtidaRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.CurtidaResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Curtida;
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
