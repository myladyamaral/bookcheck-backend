package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Leitor.Biblioteca;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long>, JpaSpecificationExecutor<Usuario>{

    Optional<Biblioteca> findById(Long id);
    List<Biblioteca> findByLeitorId(Long leitorId);

    Page<Biblioteca> findByLeitorId(Long leitorId, Pageable pageable);
}
