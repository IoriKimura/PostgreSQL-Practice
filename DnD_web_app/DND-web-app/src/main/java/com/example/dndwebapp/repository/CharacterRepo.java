package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharacterRepo extends JpaRepository<Characters, Long> {

    @Query("SELECT character FROM Characters character WHERE character.user_id = :user_id")
    List<Characters> findByUserId(Long user_id);
}
