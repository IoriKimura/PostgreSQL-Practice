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

    @Autowired
    UserRepo userRepo;

    @Autowired
    CharacterRepo characterRepo;

    static private String characterName = null;
    static private Long raceId;
    static private Long classId;

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
        raceId = race_id;
        classId = class_id;
        List<Subraces> subracesList = subracesRepo.findAllSubraces(race_id.intValue());
        model.addAttribute("subraces", subracesList);
        List<Weapons> weaponsList = weaponsRepo.findAllWeapons(class_id.intValue());
        model.addAttribute("weapons", weaponsList);
        List<Armours> armoursList = armourRepo.findAllArmours(class_id.intValue());
        model.addAttribute("armours", armoursList);
        return "creationNext";
    }

    @PostMapping(value = "/saved")
    public String savingCharacter(Model model, Long subrace_id, Long weapon_id, Long armour_id,
                                  Authentication auth){
        String email = auth.getName();
        try{
            characterRepo.saveNewCharacter(userRepo.findUserByEmail(email).getUser_id().intValue(),
                    classId.intValue(), raceId.intValue(), subrace_id.intValue(),
                    weapon_id.intValue(), armour_id.intValue(), characterName);
        }
        catch(Exception e){
            return "redirect:/collection";
        }
        return "redirect:/collection";
    }
}
