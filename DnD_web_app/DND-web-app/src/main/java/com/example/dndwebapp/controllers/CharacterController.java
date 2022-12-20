package com.example.dndwebapp.controllers;

import com.example.dndwebapp.models.Classes;
import com.example.dndwebapp.models.Races;
import com.example.dndwebapp.models.Subraces;
import com.example.dndwebapp.models.Weapons;
import com.example.dndwebapp.repository.ClassesRepo;
import com.example.dndwebapp.repository.RacesRepo;
import com.example.dndwebapp.repository.SubracesRepo;
import com.example.dndwebapp.repository.WeaponsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class CharacterController {

    @Autowired
    ClassesRepo classesrepo;
    @Autowired
    RacesRepo racesRepo;

    @Autowired
    SubracesRepo subracesRepo;

    @Autowired
    WeaponsRepo weaponsRepo;

    @GetMapping(value = "/creation")
    public String showCreation(Model model, Authentication auth){
        List<Classes> classesList = classesrepo.findAllClasses();
        model.addAttribute("classes", classesList);

        List<Races> racesList = racesRepo.findAllRaces();
        model.addAttribute("races", racesList);

        return "creation";
    }

    @PostMapping(value = "/creation/continue")
    public String continueCreate(Model model, String character_name,  Long race_id, Long class_id){
        List<Subraces> subracesList = subracesRepo.findAllSubraces(race_id.intValue());
        model.addAttribute("subraces", subracesList);
        List<Weapons> weaponsList = weaponsRepo.findAllWeapons(class_id.intValue());
        model.addAttribute("weapons", weaponsList);
        System.out.println(character_name + race_id + class_id);
        return "creationNext";
    }
}
