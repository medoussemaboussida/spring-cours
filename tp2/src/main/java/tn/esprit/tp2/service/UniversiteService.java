package tn.esprit.tp2.service;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.tp2.repository.UniversiteRepository;

@Repository
public class UniversiteService implements IUniversiteService {
    @Autowired
    UniversiteRepository universiteRepository;
}
