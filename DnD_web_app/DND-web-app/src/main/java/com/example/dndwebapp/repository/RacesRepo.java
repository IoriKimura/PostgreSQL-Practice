package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.Races;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacesRepo extends JpaRepository<Races, Long> {
    @Query(value = "SELECT * FROM races", nativeQuery = true)
    List<Races> findAllRaces();
}
