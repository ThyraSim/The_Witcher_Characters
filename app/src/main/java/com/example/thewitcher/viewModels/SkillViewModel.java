package com.example.thewitcher.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.repository.BaseRepository;

import java.util.List;

public class SkillViewModel extends AndroidViewModel {
    private final BaseRepository repository;
    private final LiveData<List<Skill>> allSkills;

    public SkillViewModel(Application application, int id) {
        super(application);
        repository = new BaseRepository(application);
        allSkills = repository.findSkillsByClassId(id);
    }

    public LiveData<List<Skill>> getAllSkills() {
        return allSkills;
    }
}

