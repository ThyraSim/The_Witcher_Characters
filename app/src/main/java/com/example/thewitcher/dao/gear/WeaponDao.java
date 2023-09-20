package com.example.thewitcher.dao.gear;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.thewitcher.Entity.gear.Weapon;
import com.example.thewitcher.dao.BaseDao;

import java.util.List;

@Dao
public interface WeaponDao extends BaseDao<Weapon> {
    @Query("SELECT * FROM weapon WHERE id = :searchId")
    LiveData<Weapon> findById(int searchId);
    @Query("SELECT * FROM weapon")
    LiveData<List<Weapon>> findAll();
}
