package francescoalves.finalproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
public class Prenotazione {
    @Id
    @GeneratedValue
    private UUID id;

    private LocalDateTime dataPrenotazione;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    public Prenotazione() {
    }
}