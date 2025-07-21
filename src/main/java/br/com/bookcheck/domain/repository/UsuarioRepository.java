package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Usuario;
import br.com.bookcheck.domain.entity.UsuarioLeitor;
import br.com.bookcheck.domain.entity.UsuarioSebo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario>{

    Optional<Usuario>findByEmail(String email);

    @Query("SELECT u FROM UsuarioLeitor u")
    List<UsuarioLeitor> findAllLeitores();

    @Query("SELECT u FROM UsuarioSebo u")
    List<UsuarioSebo> findAllSebos();


}
