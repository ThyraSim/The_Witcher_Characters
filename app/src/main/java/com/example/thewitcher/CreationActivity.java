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
import com.example.thewitcher.Entity.gear.Armor;
import com.example.thewitcher.Entity.gear.Weapon;
import com.example.thewitcher.Entity.race.Race;
import com.example.thewitcher.connection.WitcherRoomDatabase;
import com.example.thewitcher.contracts.ArmorResultContract;
import com.example.thewitcher.contracts.ClassResultContract;
import com.example.thewitcher.contracts.RaceResultContract;
import com.example.thewitcher.contracts.WeaponResultContract;
import com.example.thewitcher.repository.BaseRepository;

public class CreationActivity extends AppCompatActivity {

    EditText txtNameInput;
    Button btnSave, btnRace, btnClass, btnArmor, btnWeapon;
    Race race = null;
    Classe classe = null;
    Armor armor = null;
    Weapon weapon = null;
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
        btnArmor = findViewById(R.id.btnArmor);
        btnWeapon = findViewById(R.id.btnWeapon);

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

        //Create ActivityResultLauncher for btnClasse
        ActivityResultLauncher<Integer> classeResultLauncher =
                registerForActivityResult(new ClassResultContract(), new ActivityResultCallback<Integer>() {
                    @Override
                    public void onActivityResult(Integer result) {
                        if (result != null && result != -1) {
                            LiveData<Classe> liveClasse = baseRepository.findClasseById(result);
                            liveClasse.observe(getLifecycleOwner(), selectedClasse -> {
                                classe = selectedClasse;
                                btnClass.setText(classe.getName());
                            });

                        }
                    }
                });

        //Create ActivityResultLauncher for btnArmor
        ActivityResultLauncher<Integer> armorResultLauncher =
                registerForActivityResult(new ArmorResultContract(), new ActivityResultCallback<Integer>() {
                    @Override
                    public void onActivityResult(Integer result) {
                        if (result != null && result != -1) {
                            LiveData<Armor> liveArmor = baseRepository.findArmorById(result);
                            liveArmor.observe(getLifecycleOwner(), selectedClasse -> {
                                armor = selectedClasse;
                                btnArmor.setText(armor.getName());
                            });
                        }
                    }
                });

        //Create ActivityResultLauncher for btnWeapon
        ActivityResultLauncher<Integer> weaponResultLauncher =
                registerForActivityResult(new WeaponResultContract(), new ActivityResultCallback<Integer>() {
                    @Override
                    public void onActivityResult(Integer result) {
                        if (result != null && result != -1) {
                            LiveData<Weapon> liveWeapon = baseRepository.findWeaponById(result);
                            liveWeapon.observe(getLifecycleOwner(), selectedClasse -> {
                                weapon = selectedClasse;
                                btnWeapon.setText(weapon.getName());
                            });
                        }
                    }
                });

        //Create onClickListener for btnRace sending the user to ChooseRaceActivity
        btnRace.setOnClickListener(v -> {
            raceResultLauncher.launch(1);
        });

        //Create onClickListener for btnClass sending the user to ChooseClassActivity
        btnClass.setOnClickListener(v -> {
            classeResultLauncher.launch(1);
        });

        //Create onClickListener for btnArmor sending the user to ChooseArmorActivity
        btnArmor.setOnClickListener(v -> {
            armorResultLauncher.launch(1);
        });

        //Create onClickListener for btnWeapon sending the user to ChooseWeaponActivity
        btnWeapon.setOnClickListener(v -> {
            weaponResultLauncher.launch(1);
        });
    }

    private LifecycleOwner getLifecycleOwner() {
        return this;
    }
}