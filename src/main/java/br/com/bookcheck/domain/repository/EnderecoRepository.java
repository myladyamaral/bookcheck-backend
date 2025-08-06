package br.com.bookcheck.domain.repository;

import br.com.bookcheck.domain.entity.Usuario.Endereco;
import br.com.bookcheck.domain.entity.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>, JpaSpecificationExecutor<Endereco>{

    Optional<Endereco> findById(Long id);


}
