package com.example.thewitcher.dao.classe;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.thewitcher.Entity.classe.Classe;
import com.example.thewitcher.dao.BaseDao;

import java.util.List;

@Dao
public interface ClasseDao extends BaseDao<Classe> {
    @Query("SELECT * FROM classe WHERE id = :searchId")
    LiveData<Classe> findById(int searchId);

    @Override
    @Query("SELECT * FROM classe")
    LiveData<List<Classe>> findAll();
}
