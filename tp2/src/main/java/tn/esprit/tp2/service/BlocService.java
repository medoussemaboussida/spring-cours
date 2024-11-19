package tn.esprit.tp2.service;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.tp2.entity.Bloc;
import tn.esprit.tp2.entity.Chambre;
import tn.esprit.tp2.repository.BlocRepository;
import tn.esprit.tp2.repository.ChambreRepository;

import java.util.List;

@Repository
public class BlocService implements IBlocService{
    @Autowired
    BlocRepository blocRepository;
    @Autowired
    private ChambreRepository chambreRepository;
    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }


    @Override
    public Bloc updateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void deleteBloc(long id) {
        blocRepository.deleteById(id);
    }
    @Override
    public Bloc getBlocById(long id) {
        return blocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bloc not found with id: " + id));
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
        // Trouver le bloc par son ID
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc non trouvé avec l'ID : " + idBloc));

        // Parcourir les numéros de chambre fournis
        for (Long num : numChambre) {
            // Vérifier si une chambre avec ce numéro existe déjà
            Chambre chambre = chambreRepository.findByNumeroChambre(num)
                    .orElseGet(() -> {
                        // Si la chambre n'existe pas, créer une nouvelle instance
                        Chambre nouvelleChambre = new Chambre();
                        nouvelleChambre.setNumeroChambre(num);
                        return nouvelleChambre;
                    });

            // Associer la chambre au bloc
            chambre.setBloc(bloc);

            // Sauvegarder la chambre
            chambreRepository.save(chambre);
        }

        // Sauvegarder le bloc après modification (si nécessaire)
        return blocRepository.save(bloc);
    }
}
