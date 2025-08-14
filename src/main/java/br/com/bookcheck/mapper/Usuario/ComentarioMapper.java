package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.controller.dto.request.Usuario.ComentarioRequestDto;
import br.com.bookcheck.controller.dto.response.Usuario.ComentarioResponseDto;
import br.com.bookcheck.domain.entity.Usuario.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, PublicacaoMapper.class})
public interface ComentarioMapper {

    ComentarioMapper INSTANCE = Mappers.getMapper(ComentarioMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publicacao.id", source = "publicacaoId")
    Comentario toEntity(ComentarioRequestDto dto);

    @Mapping(target = "autor", source = "autor")
    @Mapping(target = "publicacao", source = "publicacao")
    ComentarioResponseDto toResponseDto(Comentario entity);

    List<ComentarioResponseDto> toResponseDto(List<Comentario> entities);

    default Page<ComentarioResponseDto> toResponseDto(Page<Comentario> entities){
        return entities.map(this::toResponseDto);
    }
}