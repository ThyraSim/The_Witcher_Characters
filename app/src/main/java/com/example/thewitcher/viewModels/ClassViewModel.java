package com.example.thewitcher.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.thewitcher.Entity.classe.Classe;
import com.example.thewitcher.repository.BaseRepository;

import java.util.List;

public class ClassViewModel extends AndroidViewModel {
    private final BaseRepository repository;
    private final LiveData<List<Classe>> allClasses;

    public ClassViewModel(Application application) {
        super(application);
        repository = new BaseRepository(application);
        allClasses = repository.findAllClasses();
    }

    public LiveData<List<Classe>> getAllClasses() {
        return allClasses;
    }
}
