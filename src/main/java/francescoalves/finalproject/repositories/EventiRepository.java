package francescoalves.finalproject.repositories;

import francescoalves.finalproject.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventiRepository extends JpaRepository<Evento, UUID> {
}