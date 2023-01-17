package com.example.dndwebapp.controllers;

import com.example.dndwebapp.models.Users;
import com.example.dndwebapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "/personal")
    public String userInfo(Model model, Authentication auth){
        Users current = userRepo.findUserByEmail(auth.getName());
        model.addAttribute("user", current);
        return "userInfo";
    }

    @PostMapping(value = "/saving")
    public String saveChanges(Model model, Authentication auth, String password){
        Integer userID = userRepo.findIdByEmail(auth.getName());
        password = passwordEncoder.encode(password);
        try{
            userRepo.updatePassword(password, userID);
        }
        catch (Exception e){
            return "redirect:/logout";
        }
        return "redirect:/logout";
    }
}

