package francescoalves.finalproject.controllers;

import francescoalves.finalproject.entities.Prenotazione;
import francescoalves.finalproject.entities.Utente;
import francescoalves.finalproject.services.EventiService;
import francescoalves.finalproject.services.PrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

    @Autowired
    private PrenotazioniService prenotazioniService;

    @Autowired
    private EventiService eventiService;

    @PostMapping("/{eventoId}")
    public Prenotazione createPrenotazione(
            @AuthenticationPrincipal Utente currentUtente,
            @PathVariable UUID eventoId) {
        return this.prenotazioniService.prenota(currentUtente, eventiService.findById(eventoId));
    }

}