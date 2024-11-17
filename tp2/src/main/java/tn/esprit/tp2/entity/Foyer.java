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
@Builder
@ToString(includeFieldNames = false)
@EqualsAndHashCode(exclude = "idFoyer")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private long idFoyer;

    private String nomFoyer;
    private long capaciteFoyer;

    @OneToOne (mappedBy = "foyer")
    private Universite universite;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "foyer")
    private Set<Bloc> blocs;

    public long getIdFoyer() {
        return idFoyer;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }
}
