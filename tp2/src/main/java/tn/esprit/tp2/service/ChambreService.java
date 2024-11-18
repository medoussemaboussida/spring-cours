package tn.esprit.tp2.service;

import org.hibernate.annotations.DialectOverride;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.tp2.entity.*;
import tn.esprit.tp2.repository.ChambreRepository;
import tn.esprit.tp2.repository.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ChambreService implements IChambreService {
    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    private UniversiteRepository universiteRepository;


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

}

