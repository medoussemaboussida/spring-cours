package tn.esprit.tp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp2.entity.Etudiant;
import tn.esprit.tp2.service.IEtudiantService;

import java.util.List;

@RestController
@RequestMapping("/Etudiants")
public class EtudiantController {
    @Autowired
    IEtudiantService etudiantService;
    @PostMapping("add")
    public Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.addEtudiant(etudiant);
    }

    @GetMapping("getAll")
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    @PutMapping("update")
    public Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.updateEtudiant(etudiant);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteEtudiant(@PathVariable long id) {
        etudiantService.deleteEtudiant(id);
    }

    @GetMapping("/{id}")
    public Etudiant getEtudiantById(@PathVariable long id) {
        return etudiantService.getEtudiantById(id);
    }

}
