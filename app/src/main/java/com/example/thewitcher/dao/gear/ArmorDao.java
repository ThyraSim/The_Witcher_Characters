package com.example.thewitcher.dao.gear;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.thewitcher.Entity.gear.Armor;
import com.example.thewitcher.dao.BaseDao;

import java.util.List;

@Dao
public interface ArmorDao extends BaseDao<Armor> {

    @Query("SELECT * FROM armor WHERE id = :searchId")
    LiveData<Armor> findById(int searchId);
    @Query("SELECT * FROM armor")
    LiveData<List<Armor>> findAll();
}
