package tn.esprit.tp2.service;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.tp2.entity.Chambre;
import tn.esprit.tp2.entity.Reservation;
import tn.esprit.tp2.repository.ChambreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ChambreService implements IChambreService {
    @Autowired
    ChambreRepository chambreRepository;
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
    public List<Reservation> getReservationForChambreWithNumberGraterThan(Long number)
    {
        List<Chambre> chambres = chambreRepository.findByNumber(number);
        return chambres.stream().flatMap(chambre -> chambre.getReservations().stream()).collect(Collectors.toList());
    }

}

