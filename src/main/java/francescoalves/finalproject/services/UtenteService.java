package francescoalves.finalproject.services;

import francescoalves.finalproject.entities.Ruolo;
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

        if (body.ruolo() == null) {
            nuovoUtente.setRuolo(Ruolo.UTENTE_NORMALE);
        } else {
            try {
                nuovoUtente.setRuolo(Ruolo.valueOf(body.ruolo().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("ruolo non valido! Usa UTENTE_NORMALE o ORGANIZZATORE_EVENTI");
            }
        }
        return this.utentiRepo.save(nuovoUtente);
    }

    public Utente findById(UUID id) {
        return this.utentiRepo.findById(id).orElseThrow(() -> new NotFoundException("utente non trovato"));
    }
}