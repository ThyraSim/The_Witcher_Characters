package com.example.thewitcher.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;


public interface BaseDao<T> {
    @Insert
    void insert(T entity);

    @Delete
    void delete(T entity);

    @Update
    void update(T entity);

    LiveData<List<T>> findAll();
}
