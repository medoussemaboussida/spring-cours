package tn.esprit.tp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp2.entity.Bloc;
import tn.esprit.tp2.service.IBlocService;

import java.util.List;

@RestController
@RequestMapping ("/Blocs")
public class BlocController {
    @Autowired
    IBlocService blocService;

    @PostMapping("add")
    public Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addBloc(bloc);
    }

    // Obtenir la liste de tous les blocs
    @GetMapping("getAll")
    public List<Bloc> getAllBlocs() {
        return blocService.getAllBlocs();
    }

    // Mettre à jour un bloc
    @PutMapping("update")
    public Bloc updateBloc(@RequestBody Bloc bloc) {
        return blocService.updateBloc(bloc);
    }


    // Supprimer un bloc par son id
    @DeleteMapping("/delete/{id}")
    public void deleteBloc(@PathVariable long id) {
        blocService.deleteBloc(id);
    }

    // Récupérer un bloc spécifique par son id (optionnel)
    @GetMapping("/{id}")
    public Bloc getBlocById(@PathVariable long id) {
        return blocService.getBlocById(id);
    }

    @PutMapping("/affecterChambres/{idBloc}")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numChambre, @PathVariable long idBloc) {
        return blocService.affecterChambresABloc(numChambre, idBloc);
    }
}
