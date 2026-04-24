package francescoalves.finalproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "utenti")
@Getter
@Setter
public class Utente {
    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni;

    public Utente() {
    }
}