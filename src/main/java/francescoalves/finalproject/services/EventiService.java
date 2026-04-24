package francescoalves.finalproject.services;

import francescoalves.finalproject.entities.Evento;
import francescoalves.finalproject.exceptions.NotFoundException;
import francescoalves.finalproject.repositories.EventiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventiService {
    @Autowired
    private EventiRepository eventiRepo;

    public Evento save(Evento body) {
        return eventiRepo.save(body);
    }

    public List<Evento> findAll() {
        return eventiRepo.findAll();
    }

    public Evento findById(UUID id) {
        return eventiRepo.findById(id).orElseThrow(() -> new NotFoundException(id.toString()));
    }

}