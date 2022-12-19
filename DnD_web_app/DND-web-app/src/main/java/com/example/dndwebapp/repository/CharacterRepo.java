package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CharacterRepo extends JpaRepository<Characters, Long> {

    @Query("SELECT character FROM Characters character WHERE character.user_id = :user_id")
    List<Characters> findByUserId(Long user_id);
}
