package francescoalves.finalproject.services;

import francescoalves.finalproject.entities.Evento;
import francescoalves.finalproject.entities.Prenotazione;
import francescoalves.finalproject.entities.Utente;
import francescoalves.finalproject.exceptions.BadRequestException;
import francescoalves.finalproject.repositories.PrenotazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PrenotazioniService {
    @Autowired
    private PrenotazioniRepository prenotazioniRepo;

    @Autowired
    private EventiService eventiService;

    public Prenotazione prenota(Utente utente, Evento evento) {
        // controllo dei posti
        if (evento.getPostiTotali() <= 0) {
            throw new BadRequestException("non ci sono posti disponibili");
        }

        // crea prenot.
        Prenotazione nuova = new Prenotazione();
        nuova.setUtente(utente);
        nuova.setEvento(evento);
        nuova.setDataPrenotazione(LocalDateTime.now());

        // toglie 1 posto all'evento
        evento.setPostiTotali(evento.getPostiTotali() - 1);
        eventiService.save(evento);

        return prenotazioniRepo.save(nuova);
    }
}