package tn.esprit.tp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tp2.entity.Bloc;
import tn.esprit.tp2.entity.Chambre;
import tn.esprit.tp2.entity.TypeChambre;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    //List<Chambre> findByNumber(Long numberChambre);
    Optional <Chambre> findByNumeroChambre(Long numeroChambre);
    List<Chambre> findByBlocIn(Set<Bloc> blocs);
    // Recherche des chambres par bloc et type de chambre avec JPQL
    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
    List<Chambre> findChambresByBlocAndType(@Param("idBloc") long idBloc, @Param("typeC") TypeChambre typeC);


    @Query("select c from Chambre c join Reservation r on (r member of c.reservations ) where c.bloc.idBloc = :idBloc and  year(r.dateReservation) != year(current date) ")
    Chambre getChambreForReservation(long idBloc);

    @Query("select c from Chambre c join Reservation r on (r member of c.reservations) where r.idReservation= :idReservation")
    Chambre findByIdReservation(Long idReservation);
}
