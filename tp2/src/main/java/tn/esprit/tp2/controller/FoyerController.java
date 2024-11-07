package tn.esprit.tp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp2.entity.Foyer;
import tn.esprit.tp2.service.IFoyerService;

import java.util.List;

@RestController
@RequestMapping("/Foyer")
public class FoyerController {
    @Autowired
    IFoyerService foyerService;

    @PostMapping("add")
    public Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.addFoyer(foyer);
    }

    @GetMapping("getAll")
    public List<Foyer> getAllFoyers() {
        return foyerService.getAllFoyer();
    }

    @PutMapping("update")
    public Foyer updateEtudiant(@RequestBody Foyer foyer) {
        return foyerService.updateFoyer(foyer);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteFoyer(@PathVariable long id) {
        foyerService.deleteFoyer(id);
    }

    @GetMapping("/{id}")
    public Foyer getFoyerById(@PathVariable long id) {
        return foyerService.getFoyerById(id);
    }

}
