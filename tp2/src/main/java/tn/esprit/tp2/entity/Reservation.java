package tn.esprit.tp2.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(includeFieldNames = false)
@EqualsAndHashCode(exclude = "idReservation")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private long idReservation;
    // Nouveau champ pour stocker le numéro de réservation
    private String numReservation;
    private Date dateReservation;

    private boolean estValide;

    @ManyToMany(cascade = CascadeType.ALL )
    private Set<Etudiant> etudiants;

    public long getIdReservation() {
        return idReservation;
    }

    public void setEtudiants(Set<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public void setIdReservation(long idReservation) {
        this.idReservation = idReservation;
    }

    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }
}
