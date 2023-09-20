package com.example.thewitcher.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.thewitcher.Entity.Skill;

import java.util.List;

@Dao
public interface SkillDao extends BaseDao<Skill> {
    @Query("SELECT * FROM skill WHERE id = :searchId")
    LiveData<Skill> findById(int searchId);
    @Query("SELECT * FROM skill")
    LiveData<List<Skill>> findAll();
}
