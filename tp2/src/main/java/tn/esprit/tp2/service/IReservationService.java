package tn.esprit.tp2.service;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.tp2.entity.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation addReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Reservation getReservationById(long id);
    void deleteReservation(long id);
    Reservation updateReservation(Reservation reservation);

}
