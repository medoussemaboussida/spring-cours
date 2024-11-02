package tn.esprit.tp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.tp2.service.IReservationService;

@Controller
public class ReservationController {
    @Autowired
    IReservationService reservationService;
}
