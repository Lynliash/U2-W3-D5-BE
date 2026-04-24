package francescoalves.finalproject.controllers;

import francescoalves.finalproject.entities.Evento;
import francescoalves.finalproject.entities.Utente;
import francescoalves.finalproject.services.EventiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventiController {

    @Autowired
    private EventiService eventiService;

    @GetMapping
    public List<Evento> getAll() {
        return this.eventiService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public Evento save(@RequestBody Evento body, @AuthenticationPrincipal Utente organizzatore) {
        body.setOrganizzatore(organizzatore);
        return this.eventiService.save(body);
    }
}