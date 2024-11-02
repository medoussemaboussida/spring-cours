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

    private Date dateReservation;

    private boolean estValide;

    @ManyToMany(cascade = CascadeType.ALL )
    private Set<Etudiant> etudiants;
}
