package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    //ToDo: В репозиториях стараемся использовать только функции и процедуры, что существуют в самой БД.
    @Procedure(procedureName = "public.findByEmail")
    Boolean findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE users.email = ?1", nativeQuery = true)
    Users findUserByEmail(String email);

    @Query(value = "CALL public.saveNewUser(:name, :nickName, :email, :password)", nativeQuery = true)
    void saveNewUser(String name, String nickName, String email, String password);
}
