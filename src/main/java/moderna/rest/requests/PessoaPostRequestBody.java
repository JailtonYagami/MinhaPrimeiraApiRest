package moderna.rest.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PessoaPostRequestBody {

    @NotEmpty(message = "O nome da Pessoa não pode ser Vazio")
    private String nome;
    private String cpf;
    private String genero;
}
