package com.example.thewitcher.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.thewitcher.Entity.gear.Weapon;
import com.example.thewitcher.repository.BaseRepository;

import java.util.List;

public class WeaponViewModel extends AndroidViewModel {
    private final BaseRepository repository;
    private final LiveData<List<Weapon>> allWeapons;

    public WeaponViewModel(Application application) {
        super(application);
        repository = new BaseRepository(application);
        allWeapons = repository.findAllWeapons();
    }

    public LiveData<List<Weapon>> getAllWeapons() {
        return allWeapons;
    }
}
