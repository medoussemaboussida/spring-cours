package tn.esprit.tp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tp2.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation r join Chambre c on (r member of c.reservations) where c.bloc.idBloc = :idBloc and year (r .dateReservation)= year(current date ) and r.estValide = true order by r.idReservation limit 1")
    Reservation getForReservation(@Param("idBloc") long idBloc);
}
