package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.Weapons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeaponsRepo extends JpaRepository<Weapons, Long> {
    @Query(value = "SELECT * FROM weapons WHERE weapon_class_id = ?1", nativeQuery = true)
    List<Weapons> findAllWeapons(Integer class_id);
}