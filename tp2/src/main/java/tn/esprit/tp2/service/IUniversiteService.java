package tn.esprit.tp2.service;

import tn.esprit.tp2.entity.Universite;

import java.util.List;

public interface IUniversiteService {
    Universite addUniversite(Universite universite);
    Universite updateUniversite(Universite universite);
    public void deleteUniversite(long id);
    public List<Universite> getAllUniversite();
    public Universite getUniversiteById(long id);
}
