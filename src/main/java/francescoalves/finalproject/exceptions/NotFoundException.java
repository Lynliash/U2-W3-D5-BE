package francescoalves.finalproject.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String messaggio) {
        super(messaggio);
    }
}