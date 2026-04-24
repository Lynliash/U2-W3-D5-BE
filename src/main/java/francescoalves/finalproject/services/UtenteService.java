package francescoalves.finalproject.services;

import francescoalves.finalproject.entities.Utente;
import francescoalves.finalproject.exceptions.BadRequestException;
import francescoalves.finalproject.exceptions.NotFoundException;
import francescoalves.finalproject.payloads.UtenteDTO;
import francescoalves.finalproject.repositories.UtentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private UtentiRepository utentiRepo;

    @Autowired
    private PasswordEncoder bcrypt;

    public Utente save(UtenteDTO body) {
        this.utentiRepo.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("l'email " + body.email() + " è già in uso");
        });
        Utente nuovoUtente = new Utente();
        nuovoUtente.setUsername(body.username());
        nuovoUtente.setEmail(body.email());
        nuovoUtente.setPassword(bcrypt.encode(body.password()));
        return utentiRepo.save(nuovoUtente);
    }

    public Utente findById(UUID id) {
        return utentiRepo.findById(id).orElseThrow(() -> new NotFoundException(id.toString()));
    }

    public Utente findByEmail(String email) {
        return utentiRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("utente con email " + email + " non trovato"));
    }
}