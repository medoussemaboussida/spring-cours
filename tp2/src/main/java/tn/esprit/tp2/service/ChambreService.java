package tn.esprit.tp2.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DialectOverride;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tp2.entity.*;
import tn.esprit.tp2.repository.BlocRepository;
import tn.esprit.tp2.repository.ChambreRepository;
import tn.esprit.tp2.repository.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.hibernate.Hibernate;


@Slf4j
@Service
public class ChambreService implements IChambreService {
    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    private UniversiteRepository universiteRepository;
    @Autowired
    private BlocRepository blocRepository;

    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }


    @Override
    public Chambre updateChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void deleteChambre(long id) {
        chambreRepository.deleteById(id);
    }

    @Override
    public Chambre getChambreById(long id) {
        return chambreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("chambre not found with id: " + id));
    }

    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        // Trouver l'université par son nom
        Universite universite = universiteRepository.findByUniversiteName(nomUniversite)
                .orElseThrow(() -> new RuntimeException("Université non trouvée avec le nom : " + nomUniversite));

        // Récupérer le foyer de l'université
        Foyer foyer = universite.getFoyer();
        if (foyer == null) {
            throw new RuntimeException("Aucun foyer associé à l'université : " + nomUniversite);
        }

        // Récupérer les blocs associés au foyer
        Set<Bloc> blocs = foyer.getBlocs();

        // Récupérer toutes les chambres des blocs
        List<Chambre> chambres = new ArrayList<>();
        for (Bloc bloc : blocs) {
            chambres.addAll(bloc.getChambres());
        }

        // Retourner la liste des chambres
        return chambres;
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        return chambreRepository.findChambresByBlocAndType(idBloc, typeC);
    }



    /*@Override
    public List<Reservation> getReservationForChambreWithNumberGraterThan(Long number)
    {
        List<Chambre> chambres = chambreRepository.findByNumber(number);
        return chambres.stream().flatMap(chambre -> chambre.getReservations().stream()).collect(Collectors.toList());
    }*/

    /*************** partie schedule *************/

    @Transactional
    @Scheduled(cron = "0 * * * * *")
    public void listeChambreParBloc() {
        //recuperer les blocs
        List<Bloc> blocs = blocRepository.findAll();

        for (Bloc b : blocs) {
            log.info("*****************");
            log.info("Bloc => {} ayant une capacité {}", b.getNomBloc(), b.getCapaciteBloc());
            // Accès direct à la collection 'chambres' sans utiliser de variable intermédiaire
            Set<Chambre> chambres = b.getChambres(); // Obtenir les chambres associées
            if (chambres.isEmpty()) {
                log.info("Pas de chambre disponible dans ce bloc");
            } else {
                log.info("La liste des chambres pour ce bloc:");
                for (Chambre chambre : chambres) {
                    log.info("NumChambre: {} type: {}", chambre.getNumeroChambre(), chambre.getTypeC());
                }
            }
        }
            log.info("************************");


        }

    @Scheduled(fixedRate = 300000) // 300000 milliseconds = 5 minutes
    public void pourcentageChambreParTypeChambre() {
        // Récupérer la liste des chambres
        List<Chambre> chambres = chambreRepository.findAll();
        // Afficher toutes les chambres pour vérifier les valeurs
        chambres.forEach(chambre -> System.out.println("Type Chambre: " + chambre.getTypeC()));
        // Total des chambres
        long totalChambres = chambres.size();

        if (totalChambres > 0) {
            long countSimple = chambres.stream()
                    .filter(chambre -> "SIMPLE".equals(chambre.getTypeC().name()))
                    .count();

            long countDouble = chambres.stream()
                    .filter(chambre -> "DOUBLE".equals(chambre.getTypeC().name()))
                    .count();

            long countTriple = chambres.stream()
                    .filter(chambre -> "TRIPLE".equals(chambre.getTypeC().name()))
                    .count();

            // Calculer le pourcentage pour chaque type
            double percentageSimple = (countSimple * 100.0) / totalChambres;
            double percentageDouble = (countDouble * 100.0) / totalChambres;
            double percentageTriple = (countTriple * 100.0) / totalChambres;

            // Afficher les résultats
            log.info("INFO " + totalChambres + " --- Nombre total des chambres: " + totalChambres);
            log.info("INFO " + percentageSimple + " --- Le pourcentage des chambres pour le type SIMPLE est égale à " + percentageSimple);
            log.info("INFO " + percentageDouble + " --- Le pourcentage des chambres pour le type DOUBLE est égale à " + percentageDouble);
            log.info("INFO " + percentageTriple + " --- Le pourcentage des chambres pour le type TRIPLE est égale à " + percentageTriple);
        } else {
            // Cas où il n'y a pas de chambres
            log.info("INFO --- Aucun total de chambres disponible pour calculer les pourcentages.");
        }

    }


    @Transactional
    @Scheduled(fixedRate = 300000)  // 300000 milliseconds = 5 minutes
    public void nbPlacesDisponibleParChambreAnneeEnCours() {
        // Récupérer la liste des chambres
        List<Chambre> chambres = chambreRepository.findAll();

        // Afficher pour chaque chambre son type et le nombre de places disponibles
        for (Chambre chambre : chambres) {
            // Récupérer le type de chambre
            String typeChambre = chambre.getTypeC().toString();
            // Accéder à la capacité via le bloc et calculer les places disponibles
            long capacite = chambre.getBloc().getCapaciteBloc(); // Capacité du bloc
            long occupants = chambre.getReservations().size(); // Nombre d'occupants (nombre de réservations)
            long placesDisponibles = capacite - occupants; // Places disponibles

            // Vérifier si la chambre est complète
            if (placesDisponibles <= 0) {
                log.info("INFO --- La chambre " + typeChambre + " " + chambre.getNumeroChambre() + " est complète");
            } else {
               log.info("INFO --- Le nombre de place disponible pour la chambre " + typeChambre + " " + chambre.getNumeroChambre() + " est " + placesDisponibles);
            }
        }
    }



}



