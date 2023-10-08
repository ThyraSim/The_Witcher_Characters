package com.example.thewitcher.utils;

import android.os.AsyncTask;

import com.example.thewitcher.dao.BaseDao;
import com.example.thewitcher.repository.BaseRepository;

/**
 *
 * @param <T> mettre la classe de l'objet à insérer
 */
public class InsertEntityAsyncTask<T> extends AsyncTask<T, Void, Void> {
    private BaseRepository<T> nbaseRepository;

    //myDao = DAO de l'objet à insérer
    public InsertEntityAsyncTask(BaseDao myDao){
        this.nbaseRepository = new BaseRepository<>(myDao);
    }

    @SafeVarargs
    @Override
    protected final Void doInBackground(T... entities){
        nbaseRepository.insertEntity(entities[0]);
        return null;
    }
}
