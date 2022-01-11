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


    @Query("SELECT obj FROM PessoaEntity obj WHERE obj.cpf =:cpf")
    PessoaEntity findByCPF(@Param("cpf") String cpf);
}
