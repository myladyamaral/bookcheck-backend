package br.com.bookcheck.mapper.Usuario;

import br.com.bookcheck.domain.entity.Usuario.Usuario;
import br.com.bookcheck.domain.view.UsuarioDetailView;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T21:06:08-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class UsuarioDetailViewMapperImpl implements UsuarioDetailViewMapper {

    @Override
    public UsuarioDetailView toView(Usuario userSystem) {
        if ( userSystem == null ) {
            return null;
        }

        UsuarioDetailView.UsuarioDetailViewBuilder usuarioDetailView = UsuarioDetailView.builder();

        usuarioDetailView.id( userSystem.getId() );
        usuarioDetailView.descricao( userSystem.getDescricao() );
        usuarioDetailView.email( userSystem.getEmail() );
        usuarioDetailView.endereco( userSystem.getEndereco() );
        usuarioDetailView.senha( userSystem.getSenha() );
        usuarioDetailView.telefone( userSystem.getTelefone() );
        usuarioDetailView.tipoUsuario( userSystem.getTipoUsuario() );

        return usuarioDetailView.build();
    }
}
