package tn.esprit.tp2.service;

import tn.esprit.tp2.entity.Chambre;
import tn.esprit.tp2.entity.Etudiant;

import java.util.List;

public interface IEtudiantService {
    // Create
    Etudiant addEtudiant(Etudiant etudiant);

    // Read
    List<Etudiant> getAllEtudiants();

    // Update
    Etudiant updateEtudiant(Etudiant etudiant);

    // Delete
    void deleteEtudiant(long id);

}
