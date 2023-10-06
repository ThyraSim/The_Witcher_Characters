package com.example.thewitcher.utils;

import android.os.AsyncTask;

import com.example.thewitcher.dao.BaseDao;
import com.example.thewitcher.repository.BaseRepository;

public class InsertAllEntityAsyncTask<T> extends AsyncTask<T, Void, Void> {
    private BaseRepository<T> nbaseRepository;

    public InsertAllEntityAsyncTask(BaseDao<T> myDao){
        this.nbaseRepository = new BaseRepository<>(myDao);
    }

    @SafeVarargs
    @Override
    protected final Void doInBackground(T... entities){
        nbaseRepository.insertAllEntities(entities);
        return null;
    }
}
