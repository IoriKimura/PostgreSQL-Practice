package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.Characters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepo extends JpaRepository<Characters, Long> {
}
