package com.example.thewitcher.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.thewitcher.Entity.OwnedSkill;
import com.example.thewitcher.Entity.OwnedSkillWithSkill;

import java.util.List;

@Dao
public interface OwnedSkillDao extends BaseDao<OwnedSkill> {
    @Query("SELECT * FROM ownedskill WHERE id = :searchId")
    LiveData<OwnedSkill> findById(int searchId);

    @Query("SELECT * FROM ownedskill")
    LiveData<List<OwnedSkill>> findAll();

    @Query("SELECT * FROM OwnedSkill WHERE personnage_id = :personnageId")
    LiveData<List<OwnedSkill>> findSkillsForPersonnage(int personnageId);

    @Query("SELECT * FROM OwnedSkill WHERE personnage_id = :personnageId")
    LiveData<List<OwnedSkillWithSkill>> getOwnedSkillsWithDetailsByPersonnageId(int personnageId);
}
