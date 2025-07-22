package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Usuario.Usuario;
import br.com.bookcheck.domain.entity.Usuario.UsuarioLeitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioLeitorRepository extends JpaRepository<UsuarioLeitor, Long>, JpaSpecificationExecutor<Usuario>{

    Optional<UsuarioLeitor>findByCpf(String cpf);



}
