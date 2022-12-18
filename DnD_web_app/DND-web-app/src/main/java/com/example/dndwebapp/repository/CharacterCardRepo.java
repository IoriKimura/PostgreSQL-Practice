package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.ViewCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharacterCardRepo extends JpaRepository<ViewCharacter, Long> {

    @Query(value = "SELECT id, character_name, class_name, race_name, subrace_name FROM charactercard", nativeQuery = true)
    List<ViewCharacter> takeInfo();
}
