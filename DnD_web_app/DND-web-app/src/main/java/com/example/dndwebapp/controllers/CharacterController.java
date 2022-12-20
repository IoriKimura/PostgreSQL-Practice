package com.example.dndwebapp.controllers;

import com.example.dndwebapp.models.*;
import com.example.dndwebapp.repository.*;
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

    @Autowired
    ArmourRepo armourRepo;

    static String characterName = null;

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
        characterName = character_name;
        List<Subraces> subracesList = subracesRepo.findAllSubraces(race_id.intValue());
        model.addAttribute("subraces", subracesList);
        List<Weapons> weaponsList = weaponsRepo.findAllWeapons(class_id.intValue());
        model.addAttribute("weapons", weaponsList);
        List<Armours> armoursList = armourRepo.findAllArmours(class_id.intValue());
        model.addAttribute("armours", armoursList);
        return "creationNext";
    }

    @PostMapping(value = "/saved")
    public String savingCharacter(Model model){
        System.out.println(characterName);
        return "collection";
    }
}
