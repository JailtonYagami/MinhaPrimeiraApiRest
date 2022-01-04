package moderna.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_endereco")
@Builder
public class EnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 10)
    private String cep;
    @Column(nullable = false,length = 6)
    private String numero;
    @Column(nullable = false,length = 150)
    private String logradouro;
    @Column(nullable = false,length = 10)
    private String complemento;


}
