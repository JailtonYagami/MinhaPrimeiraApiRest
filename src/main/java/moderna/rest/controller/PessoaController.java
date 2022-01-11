package moderna.rest.controller;


import lombok.RequiredArgsConstructor;
import moderna.rest.model.PessoaEntity;
import moderna.rest.requests.PessoaPostRequestBody;
import moderna.rest.requests.PessoaPutRequestBody;
import moderna.rest.service.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/pessoas")
@RequiredArgsConstructor
public class PessoaController {


    private final PessoaService pessoaService;

    @GetMapping(path = "listar-todos")
    public ResponseEntity<Page<PessoaEntity>> list(Pageable pageable) {
        return ResponseEntity.ok(pessoaService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PessoaEntity> findById(@PathVariable long id) {
        return ResponseEntity.ok(pessoaService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<PessoaEntity>> findBynome(@RequestParam String nome) {
        return ResponseEntity.ok((pessoaService.findBynome(nome)));
    }

    @PostMapping("/cadastrar-pessoa")
    public ResponseEntity<PessoaEntity> create(@RequestBody @Valid PessoaPostRequestBody pessoaPostRequestBody) {
        return new ResponseEntity<>(pessoaService.create(pessoaPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody PessoaPutRequestBody pessoaPutRequestBody) {
        pessoaService.replace(pessoaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
