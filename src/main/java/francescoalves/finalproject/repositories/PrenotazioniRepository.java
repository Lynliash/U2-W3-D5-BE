package francescoalves.finalproject.repositories;

import francescoalves.finalproject.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {
}