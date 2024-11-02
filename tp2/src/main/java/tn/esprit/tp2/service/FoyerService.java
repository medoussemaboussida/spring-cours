package tn.esprit.tp2.service;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.tp2.repository.FoyerRepository;

@Repository
public class FoyerService implements IFoyerService{
    @Autowired
    FoyerRepository foyerRepository;
}
