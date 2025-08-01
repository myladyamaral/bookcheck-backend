package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.PublicacaoRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.PublicacaoResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Publicacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublicacaoMapper {

    PublicacaoMapper INSTANCE = Mappers.getMapper(PublicacaoMapper.class);

    @Mapping(target = "id", ignore = true)
    Publicacao toEntity(PublicacaoRequestDto dto);

    PublicacaoResponseDto toResponseDto(Publicacao entity);

    List<PublicacaoResponseDto> toResponseDto(List<Publicacao> entities);

    default Page<PublicacaoResponseDto> toResponseDto(Page<Publicacao> entities){
        return entities.map(this::toResponseDto);
    }


}
