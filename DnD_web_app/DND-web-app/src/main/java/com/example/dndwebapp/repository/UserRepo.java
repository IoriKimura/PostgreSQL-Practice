package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface UserRepo extends JpaRepository<Users, Long> {
    //ToDo: В репозиториях стараемся использовать только функции и процедуры, что существуют в самой БД.
    @Procedure(procedureName = "public.findByEmail")
    Boolean findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE users.email = ?1", nativeQuery = true)
    Users findByEmailForSecurity(String email);

    @Query(value = "SELECT user_name FROM users WHERE users.user_id = ?1", nativeQuery = true)
    String findNameById(Integer id);
}
