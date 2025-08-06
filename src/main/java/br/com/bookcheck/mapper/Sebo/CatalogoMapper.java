package br.com.bookcheck.mapper.Sebo;

import br.com.bookcheck.controller.dto.request.Leitor.BibliotecaUpdateRequestDto;
import br.com.bookcheck.controller.dto.request.Sebo.CatalogoRequestDto;
import br.com.bookcheck.controller.dto.request.Sebo.CatalogoUpdateRequestDto;
import br.com.bookcheck.controller.dto.response.Sebo.CatalogoResponseDto;
import br.com.bookcheck.domain.entity.Leitor.Biblioteca;
import br.com.bookcheck.domain.entity.Sebo.Catalogo;
import br.com.bookcheck.mapper.Livro.LivroMapperResolver;
import br.com.bookcheck.mapper.Usuario.UsuarioMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LivroMapperResolver.class, UsuarioMapper.class})
public interface CatalogoMapper {

    CatalogoMapper INSTANCE = Mappers.getMapper(CatalogoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sebo.id", source = "seboId")
    Catalogo toEntity(CatalogoRequestDto dto);

    @Mapping(target = "sebo", source = "sebo")
    @Mapping(target = "isbn", source = "isbn")
    CatalogoResponseDto toResponseDto(Catalogo entity);

    void updateEntityFromDto(CatalogoUpdateRequestDto updated, @MappingTarget Catalogo entity);

    List<CatalogoResponseDto> toResponseDto(List<Catalogo> entities);

    default Page<CatalogoResponseDto> toResponseDto(Page<Catalogo> entities){
        return entities.map(this::toResponseDto);
    }

}
