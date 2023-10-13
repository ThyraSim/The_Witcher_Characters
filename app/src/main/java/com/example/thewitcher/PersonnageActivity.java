package com.example.thewitcher;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.OwnedSkill;
import com.example.thewitcher.Entity.OwnedSkillWithSkill;
import com.example.thewitcher.Entity.PersonnageDetails;
import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.Entity.gear.Armor;
import com.example.thewitcher.Entity.gear.Weapon;
import com.example.thewitcher.adapter.SkillsAdapter;
import com.example.thewitcher.adapter.personnageAdapter;
import com.example.thewitcher.connection.WitcherRoomDatabase;
import com.example.thewitcher.repository.BaseRepository;
import com.example.thewitcher.viewModels.PersonnageViewModel;
import com.example.thewitcher.adapter.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PersonnageActivity extends AppCompatActivity {
//ceci vas etre le detail de accountactivity lorsqu'il vas cliquer sur une fiche de perso
private PersonnageViewModel viewModel;
    private personnageAdapter adapter;
    private List<PersonnageDetails> persosArray = new ArrayList<>();
    private SkillsAdapter skillsAdapter;
    private ArmorAdapter armorAdapter;
    private WeaponAdapter weaponAdapter;
    private BaseRepository baseRepository;
    // Views définies dans votre fichier XML
    private ImageView imageView;
    private TextView tvClass,tvRace,tvDescription;
    private RecyclerView skillsRecyclerView,armorRecyclerView,gearRecyclerView;
    private ScrollView backgroundScrollView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnage);
        initViews();
        setupRecyclerViews();

        //Connection à la database
        WitcherRoomDatabase database = WitcherRoomDatabase.getDatabase(this);
        baseRepository = new BaseRepository(getApplication());
        viewModel = new PersonnageViewModel(getApplication());

        int id = getIntent().getIntExtra("persoId", 0);
        loadCharacterDetails(id);
    }

    private void setupRecyclerViews() {

        skillsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        armorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        gearRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        gearRecyclerView.setNestedScrollingEnabled(false);
        skillsAdapter = new SkillsAdapter(new ArrayList<>());
        skillsRecyclerView.setAdapter(skillsAdapter);
        gearRecyclerView.setAdapter(weaponAdapter);
        armorRecyclerView.setAdapter(armorAdapter);
    }

    private void initViews() {
        imageView = findViewById(R.id.imageView);
        tvClass = findViewById(R.id.tvClass);
        tvRace = findViewById(R.id.tvRace);
        skillsRecyclerView = findViewById(R.id.skillsRecyclerView);
        armorRecyclerView = findViewById(R.id.armorRecyclerView);
        gearRecyclerView = findViewById(R.id.gearRecyclerView);
        gearRecyclerView.setNestedScrollingEnabled(false);
        backgroundScrollView = findViewById(R.id.svBackground);
        weaponAdapter = new WeaponAdapter(this, new ArrayList<Weapon>(), null);
        armorAdapter = new ArmorAdapter(this, new ArrayList<Armor>(), null);

    }


    private void loadCharacterDetails(int id) {
        imageView.setImageResource(R.drawable.aorus_chibi3);
        LiveData<PersonnageDetails> personnageDetails = viewModel.getPersonnageDetailsById(id);
        personnageDetails.observe(this, persoDetails -> {
            // Afficher les détails du personnage
            tvClass.setText(persoDetails.getClasse().getName());
            tvRace.setText(persoDetails.getRace().getName());

            // Afficher l'armure
            Armor armor = persoDetails.getArmor();
            if (armor != null) {
                armorAdapter.updateData(Collections.singletonList(armor));
            }

            //Afficher l'arme
            Weapon weapon = persoDetails.getWeapon();
            if (weapon != null) {
                Log.d("Test", "Name: "+weapon.getName()+" Damage: "+weapon.getDamage()+" Hands: "+weapon.getHands());
                weaponAdapter.updateData(Collections.singletonList(weapon));
            }
            // Afficher l'histoire
//            TextView tvBackground = new TextView(this);
//            tvBackground.setText(persoDetails.getPersonnage().getBackground());
//            backgroundLayout.addView(tvBackground);
        });

        //Afficher les skills
        LiveData<List<OwnedSkill>> oSkills = viewModel.getOwnedSkillById(id);
        oSkills.observe(this, ownedSkills -> {
            List<OwnedSkillWithSkill> ownedSkillWithSkills = new ArrayList<>();

            // Utilisez un compteur pour savoir quand toutes les compétences ont été récupérées
            AtomicInteger counter = new AtomicInteger(0);

            for (OwnedSkill oSkill : ownedSkills) {
                LiveData<Skill> skill = baseRepository.findSkillById(oSkill.getSkillId());
                skill.observe(this, skill1 -> {
                    OwnedSkillWithSkill ownedSkillWithSkill = new OwnedSkillWithSkill();
                    ownedSkillWithSkill.ownedSkill = oSkill;
                    ownedSkillWithSkill.skill = skill1;
                    ownedSkillWithSkills.add(ownedSkillWithSkill);

                    // Si toutes les compétences ont été récupérées, mettez à jour l'adaptateur
                    if (counter.incrementAndGet() == ownedSkills.size()) {
                        Log.d("Data: ", "Updating skills adapter");
                        skillsAdapter = new SkillsAdapter(ownedSkillWithSkills);
                        skillsRecyclerView.setAdapter(skillsAdapter);
                    }
                });
            }
        });




    }
}