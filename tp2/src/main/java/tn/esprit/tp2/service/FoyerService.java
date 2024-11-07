package tn.esprit.tp2.service;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.tp2.entity.Chambre;
import tn.esprit.tp2.entity.Foyer;
import tn.esprit.tp2.repository.FoyerRepository;

import java.util.List;

@Repository
public class FoyerService implements IFoyerService{
    @Autowired
    FoyerRepository foyerRepository;
    @Override
    public Foyer addFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public List<Foyer> getAllFoyer() {
        return foyerRepository.findAll();
    }


    @Override
    public Foyer updateFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public void deleteFoyer(long id) {
        foyerRepository.deleteById(id);
    }

    @Override
    public Foyer getFoyerById(long id) {
        return foyerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("foyer not found with id: " + id));
    }
}
