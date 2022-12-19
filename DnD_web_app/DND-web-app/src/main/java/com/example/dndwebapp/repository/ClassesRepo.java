package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesRepo extends JpaRepository<Classes, Long> {

    @Query(value = "SELECT * FROM classes", nativeQuery = true)
    List<Classes> findAllClasses();
}
