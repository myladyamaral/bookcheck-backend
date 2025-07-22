package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.PublicacaoRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.PublicacaoResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Publicacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PublicacaoMapper {

    PublicacaoMapper INSTANCE = Mappers.getMapper(PublicacaoMapper.class);

    @Mapping(target = "id", ignore = true)
    Publicacao toEntity(PublicacaoRequestDto dto);

    PublicacaoResponseDto toResponseDto(Publicacao entity);


}
