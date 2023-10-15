package com.example.thewitcher.viewModels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.thewitcher.Entity.OwnedSkill;
import com.example.thewitcher.Entity.OwnedSkillWithSkill;
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

    //Renvoie tous les personnages
    public LiveData<List<PersonnageDetails>> getAllPersonnageDetails() {
        return allPersonnageDetails;
    }

    //Renvoie un personnage par son id
    public LiveData<PersonnageDetails> getPersonnageDetailsById(int id) {
        return repository.findPersonnageDetails(id);
    }

    //Renvoie les skills d'un personnage par son id
    public LiveData<List<OwnedSkillWithSkill>> getPersonnageSkillsById(int id) {
        return repository.findOwnedSkillByPersonnageId(id);
    }

    public LiveData<List<OwnedSkill>> getOwnedSkillById(int id) {
        return repository.findOwnedSkillByPersonnageId(id);
    }

    public void deletePersonnage(final PersonnageDetails personnageDetails) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                repository.deletePersonnage(personnageDetails.getPersonnage());
                return null;
            }
        }.execute();
    }
}

