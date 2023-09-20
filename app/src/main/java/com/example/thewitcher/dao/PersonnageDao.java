package com.example.thewitcher.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.thewitcher.Entity.Personnage;
import com.example.thewitcher.Entity.PersonnageDetails;
import com.example.thewitcher.Entity.PersonnageSkillCrossRef;

import java.util.List;

@Dao
public interface PersonnageDao extends BaseDao<Personnage> {

    @Transaction
    @Query("SELECT * FROM personnage WHERE id = :searchId")
    LiveData<Personnage> findById(int searchId);

    @Query("SELECT race.*, classe.*, weapon.*, armor.* FROM personnage "+
            "JOIN race ON personnage.raceId = race.id "+
            "JOIN classe ON personnage.classeId = classe.id "+
            "JOIN weapon ON personnage.weaponId = weapon.id "+
            "JOIN armor ON personnage.armorId = armor.id "+
            "WHERE personnage.id = :searchId")
    LiveData<PersonnageDetails> findPersonnageDetails(int searchId);

    @Query("SELECT * FROM personnage")
    LiveData<List<Personnage>> findAll();

    @Insert
    void insertPersonnageSkills(List<PersonnageSkillCrossRef> personnageSkillCrossRefs);
}
