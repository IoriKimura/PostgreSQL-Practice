package com.example.dndwebapp.controllers;

import com.example.dndwebapp.models.Users;
import com.example.dndwebapp.models.Weapons;
import com.example.dndwebapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {

    @Autowired
    UserRepo userRepo;

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
//        Employee employeeFromDb = eRepo.findByEmail(employee.getEmail()); //TODO Совя процедура в БД
//        if(employeeFromDb != null) {
//
//        }
//        else {
//            employee.setPassword(passwordEncoder.encode(employee.getPassword())); //
//            eRepo.save(employee);
//
//        }
//        return "redirect:/login";
        else
            return "redirect:/login";
    }
}
