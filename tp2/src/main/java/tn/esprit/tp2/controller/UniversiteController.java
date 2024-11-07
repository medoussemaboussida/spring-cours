package tn.esprit.tp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp2.entity.Universite;
import tn.esprit.tp2.service.IUniversiteService;

import java.util.List;

@RestController
@RequestMapping("/Universite")
public class UniversiteController {
    @Autowired
    IUniversiteService universiteService;
    @PostMapping("add")
    public Universite addUniversite(@RequestBody Universite universite) {
        return universiteService.addUniversite(universite);
    }

    @GetMapping("getAll")
    public List<Universite> getAllUniversite() {
        return universiteService.getAllUniversite();
    }

    @PutMapping("update")
    public Universite updateUniversite(@RequestBody Universite universite) {
        return universiteService.updateUniversite(universite);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteUniversite(@PathVariable long id) {
       universiteService.deleteUniversite(id);
    }

    @GetMapping("/{id}")
    public Universite getUniversiteById(@PathVariable long id) {
        return universiteService.getUniversiteById(id);
    }
}
