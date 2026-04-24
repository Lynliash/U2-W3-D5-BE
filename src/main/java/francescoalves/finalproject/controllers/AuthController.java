package francescoalves.finalproject.controllers;

import francescoalves.finalproject.entities.Utente;
import francescoalves.finalproject.payloads.LoginDTO;
import francescoalves.finalproject.payloads.LoginResponseDTO;
import francescoalves.finalproject.payloads.UtenteDTO;
import francescoalves.finalproject.services.AuthService;
import francescoalves.finalproject.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body) {
        return new LoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente register(@RequestBody UtenteDTO body) {
        return this.utenteService.save(body);
    }
}