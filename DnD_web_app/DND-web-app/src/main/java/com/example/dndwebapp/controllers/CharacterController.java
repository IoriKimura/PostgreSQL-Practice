package com.example.dndwebapp.controllers;

import com.example.dndwebapp.models.Classes;
import com.example.dndwebapp.models.Races;
import com.example.dndwebapp.repository.ClassesRepo;
import com.example.dndwebapp.repository.RacesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class CharacterController {

    @Autowired
    ClassesRepo classesrepo;
    @Autowired
    RacesRepo racesRepo;

    @GetMapping(value = "/creation")
    public String showCreation(Model model, Authentication auth){
        List<Classes> classesList = classesrepo.findAllClasses();
        model.addAttribute("classes", classesList);

        List<Races> racesList = racesRepo.findAllRaces();
        model.addAttribute("races", racesList);

        return "creation";
    }
}
