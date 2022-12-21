package com.example.dndwebapp.repository;

import com.example.dndwebapp.models.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface CharacterRepo extends JpaRepository<Characters, Long> {

    @Query(value = "CALL public.savenewcharacter(:userID, :classID, :raceID, :subraceID, " +
            ":weaponID, :armourID, :characterName)", nativeQuery = true)
    void saveNewCharacter(Integer userID, Integer classID, Integer raceID, Integer subraceID,
                     Integer weaponID, Integer armourID, String characterName);

    @Query(value = "CALL public.deletecharacter(:characterID)", nativeQuery = true)
    void deleteCharacter(Integer characterID);
}
