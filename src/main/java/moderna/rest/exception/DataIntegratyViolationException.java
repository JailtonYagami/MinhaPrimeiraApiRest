package moderna.rest.exception;

public class DataIntegratyViolationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataIntegratyViolationException(String message, Throwable cause){
        super(message,cause);
    }
    public DataIntegratyViolationException(String messege){
        super(messege);
    }
}
