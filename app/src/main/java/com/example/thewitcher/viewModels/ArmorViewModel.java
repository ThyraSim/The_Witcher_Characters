package com.example.thewitcher.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.thewitcher.Entity.gear.Armor;
import com.example.thewitcher.repository.BaseRepository;

import java.util.List;

public class ArmorViewModel extends AndroidViewModel {
    private final BaseRepository repository;
    private final LiveData<List<Armor>> allArmors;

    public ArmorViewModel(Application application) {
        super(application);
        repository = new BaseRepository(application);
        allArmors = repository.findAllArmors();
    }

    public LiveData<List<Armor>> getAllArmors() {
        return allArmors;
    }
}
