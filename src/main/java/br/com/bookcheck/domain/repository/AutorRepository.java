package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Livro.Autor;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>, JpaSpecificationExecutor<Autor>{

    Optional<Autor> findById(Long id);


}
