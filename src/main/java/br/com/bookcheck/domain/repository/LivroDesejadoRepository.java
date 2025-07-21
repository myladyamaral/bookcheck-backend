package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Leitor.LivroDesejado;
import br.com.bookcheck.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroDesejadoRepository extends JpaRepository<LivroDesejado, Long>, JpaSpecificationExecutor<Usuario>{

    Optional<LivroDesejado> findById(Long id);


}
