package br.com.bookcheck.mapper;

import br.com.bookcheck.domain.entity.Usuario;
import br.com.bookcheck.domain.view.UsuarioDetailView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioDetailViewMapper {

    @Mapping(source = "id", target = "id")
    UsuarioDetailView toView(Usuario userSystem);


}
