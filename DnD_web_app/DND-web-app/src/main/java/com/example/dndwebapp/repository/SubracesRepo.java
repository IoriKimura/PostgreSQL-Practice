package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.Subraces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubracesRepo extends JpaRepository<Subraces, Long> {
    @Query(value = "SELECT * FROM subraces WHERE race_id = ?1", nativeQuery = true)
    List<Subraces> findAllSubraces(Integer race_id);
}

