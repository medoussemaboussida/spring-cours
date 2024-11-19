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

    private int getCapaciteMaximale(TypeChambre typeC) {
        switch (typeC) {
            case SIMPLE:
                return 1;
            case DOUBLE:
                return 2;
            case TRIPLE:
                return 3;
            default:
                throw new IllegalArgumentException("Type de chambre inconnu");
        }
    }

    private boolean isChambreDisponible(Chambre chambre) {
        int capaciteMax = getCapaciteMaximale(chambre.getTypeC());
        return chambre.getReservations().size() < capaciteMax;
    }

    @Override
    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
        // Rechercher le bloc par ID
        Optional<Bloc> blocOpt = blocRepository.findById(idBloc);
        // Rechercher l'étudiant par CIN
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);

        if (blocOpt.isPresent() && etudiant != null) {
            Bloc bloc = blocOpt.get();

            // Parcourir les chambres du bloc pour trouver une chambre disponible
            for (Chambre chambre : bloc.getChambres()) {
                if (isChambreDisponible(chambre)) {
                    // Générer le numéro de réservation dans le format demandé
                    String anneeUniversitaire = "2023-2024"; // Ceci peut être dynamique selon l'année en cours
                    String numReservation = chambre.getNumeroChambre() + "-" + bloc.getNomBloc() + "-" + anneeUniversitaire;

                    // Créer un ensemble d'étudiants et ajouter l'étudiant trouvé
                    Set<Etudiant> etudiants = new HashSet<>();
                    etudiants.add(etudiant);

                    // Créer la réservation
                    Reservation reservation = Reservation.builder()
                            .numReservation(numReservation)
                            .estValide(true)
                            .etudiants(etudiants)   // Ajout de l'ensemble des étudiants
                              // Ajout de la chambre
                            .build();

                    // Sauvegarder la réservation
                    return reservationRepository.save(reservation);
                }
            }
        }
        throw new RuntimeException("Aucune chambre disponible ou étudiant non trouvé");
    }




}
