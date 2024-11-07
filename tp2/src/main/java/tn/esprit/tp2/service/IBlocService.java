package tn.esprit.tp2.service;

import tn.esprit.tp2.entity.Bloc;
import tn.esprit.tp2.entity.Chambre;

import java.util.List;

public interface IBlocService {
    // Create
    Bloc addBloc(Bloc bloc);

    // Read
    List<Bloc> getAllBlocs();

    // Update
    Bloc updateBloc(Bloc bloc);

    // Delete
    void deleteBloc(long id);

     Bloc getBlocById(long id);
}
