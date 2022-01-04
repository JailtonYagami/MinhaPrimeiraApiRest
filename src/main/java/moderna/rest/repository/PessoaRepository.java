package moderna.rest.repository;

import moderna.rest.model.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity,Long> {


    List<PessoaEntity> findBynome(String nome);

    List<PessoaEntity> findBycpf(String cpf);
}
