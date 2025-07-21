package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Livro.Livro;
import br.com.bookcheck.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>, JpaSpecificationExecutor<Usuario>{

    Optional<Livro> findById(Long id);




}
