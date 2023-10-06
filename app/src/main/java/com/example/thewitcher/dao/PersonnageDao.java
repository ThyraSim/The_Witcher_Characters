package com.example.thewitcher.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.thewitcher.Entity.Personnage;
import com.example.thewitcher.Entity.PersonnageDetails;

import java.util.List;

@Dao
public interface PersonnageDao extends BaseDao<Personnage> {

    @Transaction
    @Query("SELECT * FROM personnage WHERE id = :searchId")
    LiveData<Personnage> findById(int searchId);

    @Query("SELECT personnage.*, " +
            "race.id AS race_id, race.name AS race_name, " +
            "classe.id AS classe_id, classe.name AS classe_name, " +
            "weapon.id AS weapon_id, weapon.name AS weapon_name, " +
            "armor.id AS armor_id, armor.name AS armor_name " +
            "FROM personnage " +
            "JOIN race ON personnage.raceId = race.id " +
            "JOIN classe ON personnage.classeId = classe.id " +
            "JOIN weapon ON personnage.weaponId = weapon.id " +
            "JOIN armor ON personnage.armorId = armor.id " +
            "WHERE personnage.id = :searchId")
    LiveData<PersonnageDetails> findPersonnageDetails(int searchId);

    @Query("SELECT personnage.*, " +
            "race.id AS race_id, race.name AS race_name, " +
            "classe.id AS classe_id, classe.name AS classe_name, " +
            "weapon.id AS weapon_id, weapon.name AS weapon_name, " +
            "armor.id AS armor_id, armor.name AS armor_name " +
            "FROM personnage " +
            "JOIN race ON personnage.raceId = race.id " +
            "JOIN classe ON personnage.classeId = classe.id " +
            "JOIN weapon ON personnage.weaponId = weapon.id " +
            "JOIN armor ON personnage.armorId = armor.id")
    LiveData<List<PersonnageDetails>> findAllPersonnageDetails();


    @Query("SELECT * FROM personnage")
    LiveData<List<Personnage>> findAll();
}
