package br.com.bookcheck.mapper;

import br.com.bookcheck.controller.dto.request.EnderecoRequestDto;
import br.com.bookcheck.controller.dto.response.EnderecoResponseDto;
import br.com.bookcheck.domain.entity.Endereco;
import br.com.bookcheck.domain.entity.Usuario;
import br.com.bookcheck.domain.view.UsuarioDetailView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    Endereco toEntity(EnderecoRequestDto dto);

    EnderecoResponseDto toResponseDto(Endereco entity);

    void updateEnderecoFromDto(EnderecoRequestDto dto, @MappingTarget Endereco entity);


}
