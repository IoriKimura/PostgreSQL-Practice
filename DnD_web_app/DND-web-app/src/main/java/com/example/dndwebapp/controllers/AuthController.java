package com.example.dndwebapp.controllers;

import com.example.dndwebapp.models.Characters;
import com.example.dndwebapp.models.Users;
import com.example.dndwebapp.models.Weapons;
import com.example.dndwebapp.repository.CharacterRepo;
import com.example.dndwebapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    CharacterRepo characterRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "/login")
    public String loginPage(Model model){
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registrationPage(Model model){
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String addNewUser(Users user, Model model){
        if(userRepo.findByEmail(user.getEmail())) {
            model.addAttribute("notification", "Такой пользователь уже есть!");
            return "registration";
        }
        else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.saveNewUser(user.getUser_name(), user.getNick_name(), user.getEmail(), user.getPassword());
            //ToDo: переписать на свой метод сохранения в БД.
            return "redirect:/login";
        }
    }

    @GetMapping("/")
    public String mainPage(Model model){
        return "/mainPage";
    }
}
