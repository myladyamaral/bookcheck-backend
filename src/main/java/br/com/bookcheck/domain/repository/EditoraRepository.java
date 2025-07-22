package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Livro.Editora;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long>, JpaSpecificationExecutor<Usuario>{

    Optional<Editora> findById(Long id);


}
