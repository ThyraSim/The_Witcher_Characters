package com.example.thewitcher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.OwnedSkill;
import com.example.thewitcher.Entity.Personnage;
import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.Entity.classe.Classe;
import com.example.thewitcher.Entity.gear.Armor;
import com.example.thewitcher.Entity.gear.Weapon;
import com.example.thewitcher.Entity.race.Race;
import com.example.thewitcher.adapter.ArmorAdapter;
import com.example.thewitcher.adapter.SkillSelectionAdapter;
import com.example.thewitcher.adapter.WeaponAdapter;
import com.example.thewitcher.connection.WitcherRoomDatabase;
import com.example.thewitcher.contracts.ArmorResultContract;
import com.example.thewitcher.contracts.ClassResultContract;
import com.example.thewitcher.contracts.RaceResultContract;
import com.example.thewitcher.contracts.WeaponResultContract;
import com.example.thewitcher.repository.BaseRepository;
import com.example.thewitcher.viewModels.SkillViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class CreationActivity extends AppCompatActivity {

    EditText txtNameInput, txtAge, txtBackground;
    Button btnSave, btnRace, btnClass, btnArmor, btnWeapon;
    Race race = null;
    Classe classe = null;
    Armor armor = null;
    Weapon weapon = null;
    Map<Skill, Integer> skillLevels;
    BaseRepository baseRepository;
    SkillSelectionAdapter adapter;
    SkillViewModel viewModel;
    List<Skill> skillArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        initializeViews();
        setupOnClickListerners();
        //Initialize database
        WitcherRoomDatabase database = WitcherRoomDatabase.getDatabase(this);
        //Initialize baseRepository
        baseRepository = new BaseRepository(getApplication());
        //Set activity title
        setTitle("New Character");

    }

    private void setupOnClickListerners() {
        btnSave.setOnClickListener(v -> saveCharacter());

        ActivityResultLauncher<Integer> raceResultLauncher = registerForActivityResult(new RaceResultContract(), this::onRaceActivityResult);
        btnRace.setOnClickListener(v -> raceResultLauncher.launch(1));

        ActivityResultLauncher<Integer> classResultLauncher = registerForActivityResult(new ClassResultContract(), this::onClassActivityResult);
        btnClass.setOnClickListener(v -> classResultLauncher.launch(1));

        ActivityResultLauncher<Integer> armorResultLauncher = registerForActivityResult(new ArmorResultContract(), this::onArmorActivityResult);
        btnArmor.setOnClickListener(v -> armorResultLauncher.launch(1));

        ActivityResultLauncher<Integer> weaponResultLauncher = registerForActivityResult(new WeaponResultContract(), this::onWeaponActivityResult);
        btnWeapon.setOnClickListener(v -> weaponResultLauncher.launch(1));
    }

    private void onRaceActivityResult(Integer result) {
        if (result != null && result != -1) {
            LiveData<Race> liveRace = baseRepository.findRaceById(result);
            liveRace.observe(getLifecycleOwner(), selectedRace -> {
                race = selectedRace;
                btnRace.setText(race.getName());
            });
        }
    }

    private void saveCharacter() {
        String name = txtNameInput.getText().toString();
        int age = Integer.parseInt(txtAge.getText().toString());
        String background = txtBackground.getText().toString();

        CompletableFuture.supplyAsync(() -> {
            // Insert personnage and get its ID
            int personnageId = baseRepository.insertPersonnage(
                    new Personnage(name, age, race.getRaceId(), classe.getClassId(),
                            armor.getArmorId(), weapon.getWeaponId(),
                            background));
            return personnageId;
        }).thenAccept(personnageId -> {
            // Use the personnageId to insert owned skills
            for (Map.Entry<Skill, Integer> entry : skillLevels.entrySet()) {
                baseRepository.insertOwnedSkill(new OwnedSkill(entry.getKey().getSkillId(),
                        personnageId,
                        entry.getValue()));
            }
        });
        Intent intent = new Intent(CreationActivity.this, AccountActivity.class);
        startActivity(intent);
    }

    private void onClassActivityResult(Integer result) {
        if (result != null && result != -1) {
            LiveData<Classe> liveClasse = baseRepository.findClasseById(result);
            liveClasse.observe(getLifecycleOwner(), selectedClasse -> {
                classe = selectedClasse;
                btnClass.setText(classe.getName());
                RecyclerView listCreationSkill = findViewById(R.id.listCreationSkills);
                listCreationSkill.setLayoutManager(new LinearLayoutManager(getApplication()));
                viewModel = new SkillViewModel(getApplication(), classe.getClassId());
                adapter = new SkillSelectionAdapter(getApplicationContext(), skillArray);
                adapter.setOnTotalSkillLevelChangeListener(totalSkillLevel -> {
                    int availableSkillPoints = 20 - totalSkillLevel;
                    if (availableSkillPoints < 0) availableSkillPoints = 0;
                    TextView txtSkillPoints = findViewById(R.id.txtSkillPoints);
                    txtSkillPoints.setText(String.valueOf(availableSkillPoints));
                    skillLevels = adapter.getSkillLevels();
                });
                listCreationSkill.setAdapter(adapter);
                viewModel.getAllSkills().observe(getLifecycleOwner(), skill -> {
                    skillArray.clear();
                    skillArray.addAll(skill);
                    adapter.notifyDataSetChanged();
                });
            });
        }
    }

    private void onArmorActivityResult(Integer result) {
        if (result != null && result != -1) {
            LiveData<Armor> liveArmor = baseRepository.findArmorById(result);
            liveArmor.observe(getLifecycleOwner(), selectedArmor -> {
                armor = selectedArmor;
                btnArmor.setText(armor.getName());
            });
        }
    }

    private void onWeaponActivityResult(Integer result) {
        if (result != null && result != -1) {
            LiveData<Weapon> liveWeapon = baseRepository.findWeaponById(result);
            liveWeapon.observe(getLifecycleOwner(), selectedWeapon -> {
                weapon = selectedWeapon;
                btnWeapon.setText(weapon.getName());
            });
        }
    }

    private void initializeViews() {
        txtNameInput = findViewById(R.id.txtNameInput);
        txtAge = findViewById(R.id.txtAge);
        txtBackground = findViewById(R.id.txtBackground);
        btnSave = findViewById(R.id.btnSave);
        btnRace = findViewById(R.id.btnRace);
        btnClass = findViewById(R.id.btnClass);
        btnArmor = findViewById(R.id.btnArmor);
        btnWeapon = findViewById(R.id.btnWeapon);
    }

    private LifecycleOwner getLifecycleOwner() {
        return this;
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}