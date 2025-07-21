package br.com.bookcheck.mapper;

import br.com.bookcheck.controller.dto.request.UsuarioLeitorRequestDto;
import br.com.bookcheck.controller.dto.request.UsuarioSeboRequestDto;
import br.com.bookcheck.controller.dto.response.UsuarioLeitorResponseDto;
import br.com.bookcheck.controller.dto.response.UsuarioSeboResponseDto;
import br.com.bookcheck.domain.entity.UsuarioLeitor;
import br.com.bookcheck.domain.entity.UsuarioSebo;
import br.com.bookcheck.domain.enums.converter.TipoUsuarioConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {TipoUsuarioConverter.class,
                EnderecoMapperResolver.class})
public interface UsuarioMapper {

    // Leitor
    UsuarioLeitor toEntity(UsuarioLeitorRequestDto request);

    @Mapping(source = "endereco", target = "endereco")
    UsuarioLeitorResponseDto toResponseDto(UsuarioLeitor entity);

    // Sebo
    UsuarioSebo toEntity(UsuarioSeboRequestDto request);

    UsuarioSeboResponseDto toResponseDto(UsuarioSebo entity);


}