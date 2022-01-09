package moderna.rest.repository;

import moderna.rest.model.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity,Long> {


    List<PessoaEntity> findBynome(String nome);

    List<PessoaEntity> findBycpf(String cpf);

    @Query("SELECT obj FROM PessoaEntity obj WHERE obj.cpf =:cpf")
    PessoaEntity buscarPessoaCPF(@Param("cpf") String cpf);
}
