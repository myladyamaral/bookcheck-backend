package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.MensagemRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.MensagemResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Mensagem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MensagemMapper {

    MensagemMapper INSTANCE = Mappers.getMapper(MensagemMapper.class);

    @Mapping(target = "id", ignore = true)
    Mensagem toEntity(MensagemRequestDto dto);

    MensagemResponseDto toResponseDto(Mensagem entity);

    List<MensagemResponseDto> toResponseDto(List<Mensagem> entities);

    default Page<MensagemResponseDto> toResponseDto(Page<Mensagem> entities){
        return entities.map(this::toResponseDto);
    }
}
