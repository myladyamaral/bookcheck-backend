package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.MensagemRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.MensagemResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Mensagem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MensagemMapper {

    MensagemMapper INSTANCE = Mappers.getMapper(MensagemMapper.class);

    @Mapping(target = "id", ignore = true)
    Mensagem toEntity(MensagemRequestDto dto);

    MensagemResponseDto toResponseDto(Mensagem entity);


}
