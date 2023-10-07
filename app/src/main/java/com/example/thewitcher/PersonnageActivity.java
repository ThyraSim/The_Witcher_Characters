package com.example.thewitcher;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.thewitcher.Entity.PersonnageDetails;
import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.repository.BaseRepository;

public class PersonnageActivity extends AppCompatActivity {
//ceci vas etre le detail de accountactivity lorsqu'il vas cliquer sur une fiche de perso
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnage);

        //Création du répository complet (tous les DAO)
        BaseRepository baseRepository = new BaseRepository(getApplication());

        //Recuperation de personnageId envoyé par l'activité précédente
        int personnageId = getIntent().getIntExtra("persoId", 0);

        //Recuperation du personnage avec le id
        LiveData<PersonnageDetails> personnageDetails = baseRepository.findPersonnageDetails(personnageId);

        //Observer pour le personnage
        personnageDetails.observe(this, new Observer<PersonnageDetails>() {
            @Override
            public void onChanged(PersonnageDetails personnageDetails) {
                Log.d("DEBUG", "Personnage details value is: " + personnageDetails);
                setTitle(personnageDetails.getPersonnage().getName());
            }
        });


        LiveData<Skill> skill = baseRepository.findSkillById(4);
        skill.observe(this, new Observer<Skill>() {
            @Override
            public void onChanged(Skill skill) {
                Log.d("DEBUG", "Skill value is: " + skill);
            }
        });
    }
}