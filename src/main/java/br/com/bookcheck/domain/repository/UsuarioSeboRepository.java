package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Usuario.Usuario;
import br.com.bookcheck.domain.entity.Usuario.UsuarioSebo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioSeboRepository extends JpaRepository<UsuarioSebo, Long>, JpaSpecificationExecutor<Usuario>{
    Optional<UsuarioSebo> findByCnpj(String cnpj);



}
