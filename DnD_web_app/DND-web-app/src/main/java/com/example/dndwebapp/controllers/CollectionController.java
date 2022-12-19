package com.example.dndwebapp.controllers;
import com.example.dndwebapp.models.ViewCharacter;
import com.example.dndwebapp.models.Users;
import com.example.dndwebapp.repository.CharacterCardRepo;
import com.example.dndwebapp.repository.CharacterRepo;
import com.example.dndwebapp.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CollectionController {

    @Autowired
    CharacterRepo characterRepo;

    @Autowired
    CharacterCardRepo characterCardRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping(value = "/collection")
    @Transactional
    public String showCollection(Model model, Authentication auth){
        String userEmail = auth.getName();
        Users user = userRepo.findByEmailForSecurity(userEmail);
        List<ViewCharacter> characters = characterCardRepo.findCharacterByUserId(user.getUser_id().intValue());
        List<ViewCharacter> viewCharacters = characters;
        model.addAttribute("characters", viewCharacters);

        return "collection";
    }
}
