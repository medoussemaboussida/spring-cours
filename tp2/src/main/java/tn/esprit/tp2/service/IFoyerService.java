package tn.esprit.tp2.service;

import tn.esprit.tp2.entity.Foyer;

import java.util.List;

public interface IFoyerService {
     Foyer addFoyer(Foyer foyer);

     List<Foyer> getAllFoyer();

     Foyer getFoyerById(long id);

     Foyer updateFoyer(Foyer foyer);

     void deleteFoyer(long id);
}
