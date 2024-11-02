package tn.esprit.tp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.tp2.service.IBlocService;

@Controller
public class BlocController {
    @Autowired
    IBlocService blocService;
}
