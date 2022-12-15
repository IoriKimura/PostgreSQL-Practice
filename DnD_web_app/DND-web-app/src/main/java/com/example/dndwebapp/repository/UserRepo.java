package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface UserRepo extends JpaRepository<Users, Long> {
    @Procedure(procedureName = "public.findByEmail")
    Boolean findByEmail(String email);
}
