package francescoalves.finalproject.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String messaggio) {
        super(messaggio);
    }
}