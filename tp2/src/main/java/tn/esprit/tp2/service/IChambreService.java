package tn.esprit.tp2.service;

import tn.esprit.tp2.entity.Chambre;
import tn.esprit.tp2.entity.Reservation;
import tn.esprit.tp2.entity.TypeChambre;

import java.util.List;

public interface IChambreService {

    // Create
    Chambre addChambre(Chambre chambre);

    // Read
    List<Chambre> getAllChambres();

    // Update
    Chambre updateChambre(Chambre chambre);

    // Delete
    void deleteChambre(long id);
    Chambre getChambreById(long id);

    public List<Chambre> getChambresParNomUniversite(String nomUniversite);
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC);
    //to do
    //reservation pour chambre avec nombre sup a entier donnee
    //List<Reservation> getReservationForChambreWithNumberGraterThan(Long number);
}
