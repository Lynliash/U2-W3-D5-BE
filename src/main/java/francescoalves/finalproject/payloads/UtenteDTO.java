package francescoalves.finalproject.payloads;

public record UtenteDTO(
        String username,
        String email,
        String password,
        String ruolo
) {
}