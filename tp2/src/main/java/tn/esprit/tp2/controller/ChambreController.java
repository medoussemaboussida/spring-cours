package tn.esprit.tp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.tp2.entity.Reservation;
import tn.esprit.tp2.service.IChambreService;

import java.util.List;

@RestController
@RequestMapping("/chambres")
public class ChambreController {
    @Autowired
    IChambreService chambreService;
    @GetMapping("/reservations/above/{number}")
    public List<Reservation> getReservationsAboveNumber(@PathVariable Long number) {
        return chambreService.getReservationForChambreWithNumberGraterThan(number);
    }
}
