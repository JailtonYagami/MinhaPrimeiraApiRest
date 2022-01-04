package moderna.rest.requests;

import lombok.Data;

@Data
public class PessoaPutRequestBody {
    private Long id;
    private String nome;
    private String cpf;
    private String genero;
}
