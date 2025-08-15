package br.com.bookcheck.mapper.Leitor;

import br.com.bookcheck.controller.dto.request.Leitor.BibliotecaRequestDto;
import br.com.bookcheck.controller.dto.request.Leitor.BibliotecaUpdateRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.BibliotecaResponseDto;
import br.com.bookcheck.domain.entity.Leitor.Biblioteca;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BibliotecaMapper {

    BibliotecaMapper INSTANCE = Mappers.getMapper(BibliotecaMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "workId", source = "workId")
    @Mapping(target = "leitor.id", source = "leitorId")
    Biblioteca toEntity(BibliotecaRequestDto dto);

    @Mapping(target = "workId", source = "workId")
    @Mapping(target = "leitor", source = "leitor")
    BibliotecaResponseDto toResponseDto(Biblioteca entity);

    void updateEntityFromDto(BibliotecaUpdateRequestDto updated, @MappingTarget Biblioteca entity);


    List<BibliotecaResponseDto> toResponseDto(List<Biblioteca> entities);

    default Page<BibliotecaResponseDto> toResponseDto(Page<Biblioteca> entities){
        return entities.map(this::toResponseDto);
    }

}
