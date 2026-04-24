package francescoalves.finalproject.services;

import francescoalves.finalproject.entities.Utente;
import francescoalves.finalproject.exceptions.UnauthorizedException;
import francescoalves.finalproject.payloads.LoginDTO;
import francescoalves.finalproject.repositories.UtentiRepository;
import francescoalves.finalproject.security.TokenTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private UtentiRepository utentiRepo;

    @Autowired
    private TokenTools tokenTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        Utente found = this.utentiRepo.findByEmail(body.email())
                .orElseThrow(() -> new UnauthorizedException("credenziali errate"));

        if (bcrypt.matches(body.password(), found.getPassword())) {
            return tokenTools.generateToken(found);
        } else {
            throw new UnauthorizedException("credenziali errate");
        }
    }
}