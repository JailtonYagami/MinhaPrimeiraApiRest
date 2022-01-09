package moderna.rest.service;

import lombok.RequiredArgsConstructor;
import moderna.rest.exception.BadRequestException;
import moderna.rest.exception.DataIntegratyViolationException;
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
                .orElseThrow(() -> new BadRequestException("Pessoa not found!!!"));
    }

    public List<PessoaEntity> findBynome( String nome) {
        return pessoaRepository.findBynome(nome);
    }

    public List<PessoaEntity> findBycpf( String cpf) {
        return pessoaRepository.findBycpf(cpf);
    }

    public PessoaEntity save(PessoaPostRequestBody pessoaPostRequestBody) {

        if(buscarPessoaCPF(pessoaPostRequestBody) != null){
            throw new DataIntegratyViolationException("CPF já cadastado na base de dados!!");
        }
//        var pessoaEncontrada = buscarPessoa(pessoaPostRequestBody.getCpf());
//
//        if (!pessoaEncontrada.equals(pessoaPostRequestBody.getCpf())){
//            pessoaRepository.save(PessoaMapper.INSTANCE.toPessoaEntity(pessoaPostRequestBody ));
//        } else{
//            System.out.println("Houve um erro ao cadastrar o usuário");
//        }
        return pessoaRepository.save(PessoaMapper.INSTANCE.toPessoaEntity(pessoaPostRequestBody ));
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

    private PessoaEntity buscarPessoaCPF(PessoaPostRequestBody objDTO){
        PessoaEntity obj = pessoaRepository.buscarPessoaCPF(objDTO.getCpf());
        if (obj != null){
            return obj;
        }
        return null;
    }


}
