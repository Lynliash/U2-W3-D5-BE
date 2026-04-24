package francescoalves.finalproject.controllers;

import francescoalves.finalproject.entities.Evento;
import francescoalves.finalproject.services.EventiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public Evento save(@RequestBody Evento body) {
        return this.eventiService.save(body);
    }
}