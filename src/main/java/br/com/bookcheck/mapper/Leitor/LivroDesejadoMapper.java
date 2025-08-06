package br.com.bookcheck.mapper.Leitor;

import br.com.bookcheck.controller.dto.request.Leitor.LivroDesejadoRequestDto;
import br.com.bookcheck.controller.dto.response.Leitor.LivroDesejadoResponseDto;
import br.com.bookcheck.domain.entity.Leitor.LivroDesejado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LivroDesejadoMapper {

    LivroDesejadoMapper INSTANCE = Mappers.getMapper(LivroDesejadoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isbn", source = "isbn")
    @Mapping(target = "leitor.id", source = "leitorId")
    LivroDesejado toEntity(LivroDesejadoRequestDto dto);

    @Mapping(target = "isbn", source = "isbn")
    @Mapping(target = "leitor", source = "leitor")
    LivroDesejadoResponseDto toResponseDto(LivroDesejado entity);

    List<LivroDesejadoResponseDto> toResponseDto(List<LivroDesejado> entities);

    default Page<LivroDesejadoResponseDto> toResponseDto(Page<LivroDesejado> entities){
        return entities.map(this::toResponseDto);
    }

}
