package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.EnderecoRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.EnderecoResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    Endereco toEntity(EnderecoRequestDto dto);

    EnderecoResponseDto toResponseDto(Endereco entity);

    void updateEnderecoFromDto(EnderecoRequestDto dto, @MappingTarget Endereco entity);


}
