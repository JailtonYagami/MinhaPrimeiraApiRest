package moderna.rest.repository;

import moderna.rest.model.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ContatoRepository extends JpaRepository<ContatoEntity,Long> {
}
