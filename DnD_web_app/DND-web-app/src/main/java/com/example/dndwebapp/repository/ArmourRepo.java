package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.Armours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmourRepo extends JpaRepository<Armours, Long> {
    @Query(value = "SELECT * FROM armours WHERE armour_class_id = ?1", nativeQuery = true)
    List<Armours> findAllArmours(Integer class_id);

}
