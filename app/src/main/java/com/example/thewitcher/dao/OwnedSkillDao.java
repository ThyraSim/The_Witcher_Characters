package com.example.thewitcher.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.thewitcher.Entity.OwnedSkill;

import java.util.List;

@Dao
public interface OwnedSkillDao extends BaseDao<OwnedSkill> {
    @Query("SELECT * FROM ownedskill WHERE id = :searchId")
    LiveData<OwnedSkill> findById(int searchId);

    @Query("SELECT * FROM ownedskill")
    LiveData<List<OwnedSkill>> findAll();
}
