package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioLeitorUpdateRequestDto;
import br.com.bookcheck.controller.dto.request.Usuario.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioLeitorResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioResumeResponseDto;
import br.com.bookcheck.controller.dto.response.Usuario.UsuarioSeboResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import br.com.bookcheck.domain.entity.Usuario.UsuarioLeitor;
import br.com.bookcheck.domain.entity.Usuario.UsuarioSebo;
import br.com.bookcheck.domain.enums.converter.TipoUsuarioConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {TipoUsuarioConverter.class,
                EnderecoMapperResolver.class})
public interface UsuarioMapper {

    // Leitor
    UsuarioLeitor toEntity(UsuarioLeitorRequestDto request);
    UsuarioLeitorResponseDto toResponseDto(UsuarioLeitor entity);
    void updateEntityFromDto(UsuarioLeitorUpdateRequestDto updated, @MappingTarget UsuarioLeitor entity);

    // Sebo
    UsuarioSebo toEntity(UsuarioSeboRequestDto request);
    UsuarioSeboResponseDto toResponseDto(UsuarioSebo entity);
    void updateEntityFromDto(UsuarioSeboRequestDto updated, @MappingTarget UsuarioSebo entity);



    UsuarioResumeResponseDto toResumeResponseDto(Usuario entity);
    List<UsuarioResumeResponseDto> toResponseDto(List<Usuario> entities);

    default Page<UsuarioResumeResponseDto> toResponseDto(Page<Usuario> entities){
        return entities.map(this::toResumeResponseDto);
    }


}