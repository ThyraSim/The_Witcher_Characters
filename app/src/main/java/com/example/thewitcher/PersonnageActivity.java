package com.example.thewitcher;

import android.content.SharedPreferences;
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
import com.example.thewitcher.adapter.ArmorAdapter;
import com.example.thewitcher.adapter.SkillsAdapter;
import com.example.thewitcher.adapter.WeaponAdapter;
import com.example.thewitcher.adapter.personnageAdapter;
import com.example.thewitcher.connection.WitcherRoomDatabase;
import com.example.thewitcher.repository.BaseRepository;
import com.example.thewitcher.viewModels.PersonnageViewModel;

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
    private PersonnageDetails personnageDetails;
    private BaseRepository baseRepository;
    // Views définies dans votre fichier XML
    private ImageView imageView;
    private TextView tvClass,tvRace,tvBackground, tvLevel,tvWeaponName,tvArmorName,tvTitle;
    private RecyclerView skillsRecyclerView,armorRecyclerView,gearRecyclerView;
    private ScrollView backgroundScrollView;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Connection à la database
        WitcherRoomDatabase database = WitcherRoomDatabase.getDatabase(this);
        baseRepository = new BaseRepository(getApplication());
        viewModel = new PersonnageViewModel(getApplication());

        int theme = 0;

        int id = getIntent().getIntExtra("persoId", 0);

        String selectedTheme = getIntent().getStringExtra("persoClass");

        switch (selectedTheme) {
            case "Elves":
                theme = R.style.Theme_TheWitcher_Elf;
                break;
            case "Witchers":
                theme = R.style.Theme_TheWitcher_Witchers;
                break;
            case "Humans":
                theme = R.style.Theme_TheWitcher_Human;
                break;
            case "Dwarves":
                theme = R.style.Theme_TheWitcher_Dwarf;
            default:
                // thème par défaut
                break;
        }

        setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnage);
        initViews();
        setupRecyclerViews();
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
        tvBackground = findViewById(R.id.tvDescription);
        tvArmorName = findViewById(R.id.tvArmorName);
        tvWeaponName = findViewById(R.id.tvWeaponName);
        tvTitle = findViewById(R.id.tvTitle);

        //tvLevel = findViewById(R.id.)
        weaponAdapter = new WeaponAdapter(this, new ArrayList<Weapon>(), null, false);
        armorAdapter = new ArmorAdapter(this, new ArrayList<Armor>(), null,false);

    }



    private void loadCharacterDetails(int id) {
        imageView.setImageResource(R.drawable.aorus_chibi3);
        LiveData<PersonnageDetails> personnageDetails = viewModel.getPersonnageDetailsById(id);

        personnageDetails.observe(this, persoDetails -> {
            setTitle(persoDetails.getPersonnage().getName());
            // Afficher les détails du personnage
            tvClass.setText(persoDetails.getClasse().getName());
            tvRace.setText(persoDetails.getRace().getName());


            // Afficher l'armure
            Armor armor = persoDetails.getArmor();
            if (armor != null) {
                armorAdapter.updateData(Collections.singletonList(armor));
                tvArmorName.setText(persoDetails.getArmor().getName());
            }

            //Afficher l'arme
            Weapon weapon = persoDetails.getWeapon();
            if (weapon != null) {
                Log.d("Test", "Name: "+weapon.getName()+" Damage: "+weapon.getDamage()+" Hands: "+weapon.getHands());
                weaponAdapter.updateData(Collections.singletonList(weapon));
                tvWeaponName.setText(persoDetails.getWeapon().getName());
            }
            // Afficher l'histoire
            String background = persoDetails.getPersonnage().getBackground();
            if(background !=null) {
                tvBackground.setText(persoDetails.getPersonnage().getBackground());

            }else if(background == null && background.isEmpty()){
                String texte = ("il n'y pas de background, Sad >uwu<");
                persoDetails.getPersonnage().setBackground(texte);
                tvBackground.setText(texte);
            }
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