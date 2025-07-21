package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Sebo.Catalogo;
import br.com.bookcheck.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Long>, JpaSpecificationExecutor<Usuario>{

    Optional<Catalogo> findById(Long id);


}
