package moderna.rest.service;

import lombok.RequiredArgsConstructor;
import moderna.rest.exception.DataIntegratyViolationException;
import moderna.rest.exception.ObjectNotFoundException;
import moderna.rest.mapper.PessoaMapper;
import moderna.rest.model.PessoaEntity;
import moderna.rest.repository.PessoaRepository;
import moderna.rest.requests.PessoaPostRequestBody;
import moderna.rest.requests.PessoaPutRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {


    private final PessoaRepository pessoaRepository;

    public Page<PessoaEntity> listAll(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public PessoaEntity findByIdOrThrowBadRequestException(long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PessoaEntity.class.getName()));
    }

    public List<PessoaEntity> findBynome(String nome) {
        return pessoaRepository.findBynome(nome);
    }


    public PessoaEntity create(PessoaPostRequestBody pessoaPostRequestBody) {
        if (findByCPF(pessoaPostRequestBody) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!!!");
        }
        PessoaEntity newObjPessoa = new PessoaEntity(null, pessoaPostRequestBody.getNome(),
                pessoaPostRequestBody.getCpf(), pessoaPostRequestBody.getGenero());

        return pessoaRepository.save(PessoaMapper.INSTANCE.toPessoaEntity(pessoaPostRequestBody));
    }

    public void delete(long id) {
        pessoaRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(PessoaPutRequestBody pessoaPutRequestBody) {
        PessoaEntity savedPessoa = findByIdOrThrowBadRequestException(pessoaPutRequestBody.getId());
        PessoaEntity pessoaEntity = PessoaMapper.INSTANCE.toPessoaEntity(pessoaPutRequestBody);
        pessoaEntity.setId(savedPessoa.getId());
        pessoaRepository.save(pessoaEntity);
    }

    private PessoaEntity findByCPF(PessoaPostRequestBody objDTO) {
        PessoaEntity obj = pessoaRepository.findByCPF(objDTO.getCpf());
        if (obj != null) {
            return obj;
        }
        return null;
    }


}
