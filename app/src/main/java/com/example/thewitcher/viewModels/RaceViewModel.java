package com.example.thewitcher.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.thewitcher.Entity.race.Race;
import com.example.thewitcher.repository.BaseRepository;

import java.util.List;

public class RaceViewModel extends AndroidViewModel {
    private final BaseRepository repository;
    private final LiveData<List<Race>> allRaces;

    public RaceViewModel(Application application) {
        super(application);
        repository = new BaseRepository(application);
        allRaces = repository.findAllRaces();
    }

    public LiveData<List<Race>> getAllRaces() {
        return allRaces;
    }


}
