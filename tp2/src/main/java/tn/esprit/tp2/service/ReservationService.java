package tn.esprit.tp2.service;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.tp2.entity.*;
import tn.esprit.tp2.repository.BlocRepository;
import tn.esprit.tp2.repository.ChambreRepository;
import tn.esprit.tp2.repository.EtudiantRepository;
import tn.esprit.tp2.repository.ReservationRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class ReservationService implements IReservationService{
    @Autowired
     ReservationRepository reservationRepository;
    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private BlocRepository blocRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;
    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }


    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(long id) {
        reservationRepository.deleteById(id);
    }
    @Override
    public Reservation getReservationById(long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
    }


    @Override
    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
        // Rechercher l'étudiant par CIN
        Etudiant e = etudiantRepository.findByCin(cinEtudiant);
        if(e!=null)
        {
            Reservation reservation = reservationRepository.getForReservation(idBloc);
            if(reservation==null)
            {
                reservation.getEtudiants().add(e);
                Chambre c =chambreRepository.findByIdReservation(reservation.getIdReservation());
                if(c.getTypeC() == TypeChambre.DOUBLE ||  c.getTypeC() == TypeChambre.TRIPLE && reservation.getEtudiants().size()==3)
                {
                    reservation.setEstValide(false);
                }
               return reservationRepository.save(reservation);

            }
            else {
                Chambre c =chambreRepository.getChambreForReservation(idBloc);
                Reservation r1= new Reservation(); //Reservation.builder().idReservation(c.getNumeroChambre()+"-"+c.getBloc().getNomBloc()).build();
                r1.getEtudiants().add(e);
                if(c.getTypeC()==TypeChambre.SIMPLE)

                    r1.setEstValide(false);
                    chambreRepository.save(c);

                return reservationRepository.save(r1);
            }
        }


        throw new RuntimeException("Aucune chambre disponible ou étudiant non trouvé");
    }




}
