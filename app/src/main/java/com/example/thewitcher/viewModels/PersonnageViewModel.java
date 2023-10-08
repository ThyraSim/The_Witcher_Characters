package com.example.thewitcher.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.thewitcher.Entity.PersonnageDetails;
import com.example.thewitcher.repository.BaseRepository;

import java.util.List;

public class PersonnageViewModel extends AndroidViewModel {
    private final BaseRepository repository;
    private final LiveData<List<PersonnageDetails>> allPersonnageDetails;

    public PersonnageViewModel(Application application) {
        super(application);
        repository = new BaseRepository(application);
        allPersonnageDetails = repository.findAllPersonnageDetails();
    }

    public LiveData<List<PersonnageDetails>> getAllPersonnageDetails() {
        return allPersonnageDetails;
    }
}

