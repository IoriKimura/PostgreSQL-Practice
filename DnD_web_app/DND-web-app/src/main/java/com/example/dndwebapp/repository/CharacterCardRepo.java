package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.ViewCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterCardRepo extends JpaRepository<ViewCharacter, Long> {

    @Query(value = "SELECT id, character_name, class_name, race_name, subrace_name, user_id FROM charactercard WHERE user_id = :userID", nativeQuery = true)
    List<ViewCharacter> findCharacterByUserId(Integer userID);
}
