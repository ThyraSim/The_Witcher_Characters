package com.example.thewitcher.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.thewitcher.Entity.OwnedGear;

import java.util.List;

@Dao
public interface OwnedGearDao extends BaseDao<OwnedGear> {
    @Query("SELECT * FROM ownedGear WHERE id = :searchId")
    LiveData<OwnedGear> findById(int searchId);
    @Query("SELECT * FROM ownedGear")
    LiveData<List<OwnedGear>> findAll();
}
