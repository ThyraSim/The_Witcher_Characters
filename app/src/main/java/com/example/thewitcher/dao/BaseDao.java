package com.example.thewitcher.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;


public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(T entity);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(T... entities);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<T> entities);

    @Delete
    void delete(T entity);

    @Update
    void update(T entity);

    LiveData<List<T>> findAll();
}
