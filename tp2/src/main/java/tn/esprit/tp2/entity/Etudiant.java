package tn.esprit.tp2.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
@Builder
@ToString(includeFieldNames = false)
@EqualsAndHashCode(exclude = "idEtudiant")

public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private long idEtudiant;

    private String nomEt;

    private String prenomEt;
    private long cin;
    private String ecole;
    private String dateNaissance;

    @ManyToMany(cascade = CascadeType.ALL ,mappedBy = "etudiants")
    private Set<Reservation> reservations;
}
