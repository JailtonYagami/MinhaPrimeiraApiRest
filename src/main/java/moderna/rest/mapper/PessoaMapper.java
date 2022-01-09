package moderna.rest.mapper;

import moderna.rest.model.PessoaEntity;
import moderna.rest.requests.PessoaPostRequestBody;
import moderna.rest.requests.PessoaPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class PessoaMapper {

    public static final PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    public abstract PessoaEntity toPessoaEntity(PessoaPostRequestBody pessoaPostRequestBody);

    public abstract PessoaEntity toPessoaEntity(PessoaPutRequestBody pessoaPutRequestBody);

}
