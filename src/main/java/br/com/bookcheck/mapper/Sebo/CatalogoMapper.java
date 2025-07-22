package br.com.bookcheck.mapper.Sebo;

import br.com.bookcheck.controller.dto.request.Sebo.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.response.Sebo.CatalogoResponseDto;
import br.com.bookcheck.domain.entity.Sebo.Catalogo;
import br.com.bookcheck.mapper.Livro.LivroMapperResolver;
import br.com.bookcheck.mapper.Usuario.UsuarioMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LivroMapperResolver.class, UsuarioMapper.class})
public interface CatalogoMapper {

    CatalogoMapper INSTANCE = Mappers.getMapper(CatalogoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "livro.id", source = "livroId")
    @Mapping(target = "sebo.id", source = "seboId")
    Catalogo toEntity(CatalogoRequestDto dto);

    @Mapping(target = "livro", source = "livro")
    @Mapping(target = "sebo", source = "sebo")
    CatalogoResponseDto toResponseDto(Catalogo entity);

    List<CatalogoResponseDto> toResponseDto(List<Catalogo> entities);

    default Page<CatalogoResponseDto> toResponseDto(Page<Catalogo> entities){
        return entities.map(this::toResponseDto);
    }

}
