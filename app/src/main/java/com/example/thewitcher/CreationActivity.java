package com.example.thewitcher;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.thewitcher.Entity.classe.Classe;
import com.example.thewitcher.Entity.race.Race;
import com.example.thewitcher.connection.WitcherRoomDatabase;
import com.example.thewitcher.contracts.RaceResultContract;
import com.example.thewitcher.repository.BaseRepository;

public class CreationActivity extends AppCompatActivity {

    EditText txtNameInput;
    Button btnSave, btnRace, btnClass;
    Race race = null;
    Classe classe = null;
    BaseRepository baseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        //Initialize database
        WitcherRoomDatabase database = WitcherRoomDatabase.getDatabase(this);

        //Initialize baseRepository
        baseRepository = new BaseRepository(getApplication());

        //Set activity title
        setTitle("New Character");

        txtNameInput = findViewById(R.id.txtNameInput);
        btnSave = findViewById(R.id.btnSave);
        btnRace = findViewById(R.id.btnRace);
        btnClass = findViewById(R.id.btnClass);


        //Create onClickListener for btnSave that will save the name in String name
        btnSave.setOnClickListener(v -> {
            String name = txtNameInput.getText().toString();
            //Vérification de la valeur de name dans debug
            Log.d("DEBUG", "Name is: " + name);
        });

        //Create ActivityResultLauncher for btnRace
        ActivityResultLauncher<Integer> raceResultLauncher =
                registerForActivityResult(new RaceResultContract(), new ActivityResultCallback<Integer>() {
                    @Override
                    public void onActivityResult(Integer result) {
                        if (result != null && result != -1) {
                            LiveData<Race> liveRace = baseRepository.findRaceById(result);
                            liveRace.observe(getLifecycleOwner(), selectedRace -> {
                                race = selectedRace;
                                btnRace.setText(race.getName());
                            });

                        }
                    }
                });

        //Create onClickListener for btnRace sending the user to ChooseRaceActivity
        btnRace.setOnClickListener(v -> {
            raceResultLauncher.launch(1);
        });
    }

    private LifecycleOwner getLifecycleOwner() {
        return this;
    }
}