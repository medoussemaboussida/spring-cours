package tn.esprit.tp2.service;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tp2.entity.Foyer;
import tn.esprit.tp2.entity.Universite;
import tn.esprit.tp2.repository.FoyerRepository;
import tn.esprit.tp2.repository.UniversiteRepository;

import java.util.List;

@Service
public class UniversiteService implements IUniversiteService {
    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    private FoyerRepository foyerRepository;
    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public List<Universite> getAllUniversite() {
        return universiteRepository.findAll();
    }


    @Override
    public Universite updateUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public void deleteUniversite(long id) {
        universiteRepository.deleteById(id);
    }
    @Override
    public Universite getUniversiteById(long id) {
        return universiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Universite not found with id: " + id));
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        // Trouver l'université par nom
        Universite universite = universiteRepository.findByUniversiteName(nomUniversite)
                .orElseThrow(() -> new RuntimeException("Université non trouvée avec le nom : " + nomUniversite));

        // Trouver le foyer par ID
        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer non trouvé avec l'ID : " + idFoyer));

        // Affecter le foyer à l'université
        universite.setFoyer(foyer);

        // Mettre à jour l'entité université
        return universiteRepository.save(universite);
    }
    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        // Trouver l'université par ID
        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new RuntimeException("Université non trouvée avec l'ID : " + idUniversite));

        // Supprimer la référence au foyer dans l'université
        universite.setFoyer(null);

        // Sauvegarder les modifications dans la base de données
        return universiteRepository.save(universite);
    }

}
