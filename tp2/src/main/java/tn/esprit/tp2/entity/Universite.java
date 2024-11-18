package tn.esprit.tp2.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(includeFieldNames = false)
@EqualsAndHashCode(exclude = "idUniversite")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Universite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private long idUniversite;

    private String universiteName;
    private String universiteAddress;

    @OneToOne
    private Foyer foyer;

    public long getIdUniversite() {
        return idUniversite;
    }

    public void setFoyer(Foyer foyer) {
        this.foyer = foyer;
    }

    public String getUniversiteName() {
        return universiteName;
    }

    public Foyer getFoyer() {
        return foyer;
    }

}
