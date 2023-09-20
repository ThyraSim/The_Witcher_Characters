package com.example.thewitcher.dao.race;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.thewitcher.Entity.race.Race;
import com.example.thewitcher.dao.BaseDao;

import java.util.List;

@Dao
public interface RaceDao extends BaseDao<Race> {
    @Query("SELECT * FROM race WHERE id = :searchId")
    LiveData<Race> findById(int searchId);
    @Query("SELECT * FROM race")
    LiveData<List<Race>> findAll();
}
