package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Sebo.Catalogo;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import br.com.bookcheck.domain.enums.DisponibilidadeCatalogoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Long>, JpaSpecificationExecutor<Catalogo>{

    Optional<Catalogo> findById(Long id);

    Optional<Catalogo> findBySeboIdAndIsbn(Long seboId, String isbn);

    List<Catalogo> findBySeboIdAndStatus(Long seboId, DisponibilidadeCatalogoEnum status);

    Page<Catalogo> findAllBySeboIdAndStatus(Long seboId, DisponibilidadeCatalogoEnum status, Pageable pageable);
    
}
