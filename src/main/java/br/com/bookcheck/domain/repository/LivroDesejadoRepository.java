package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Leitor.LivroDesejado;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroDesejadoRepository extends JpaRepository<LivroDesejado, Long>, JpaSpecificationExecutor<LivroDesejado>{

    Optional<LivroDesejado> findById(Long id);

    List<LivroDesejado> findByLeitorId(Long leitorId);
    Page<LivroDesejado> findByLeitorId(Long leitorId, Pageable pageable);

    Optional<LivroDesejado> findByLeitorIdAndIsbn(Long leitorId, String isbn);
}
