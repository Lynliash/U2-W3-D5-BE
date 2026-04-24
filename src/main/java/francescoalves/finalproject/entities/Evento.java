package francescoalves.finalproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "eventi")
@Getter
@Setter
public class Evento {
    @Id
    @GeneratedValue
    private UUID id;

    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int postiTotali;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private Utente organizzatore;

    @OneToMany(mappedBy = "evento")
    private List<Prenotazione> prenotazioni;

    public Evento() {
    }
}