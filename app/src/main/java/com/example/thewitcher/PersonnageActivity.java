package com.example.thewitcher;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.thewitcher.Entity.OwnedSkill;
import com.example.thewitcher.Entity.PersonnageDetails;
import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.Entity.gear.Armor;
import com.example.thewitcher.Entity.gear.Weapon;
import com.example.thewitcher.adapter.personnageAdapter;
import com.example.thewitcher.connection.WitcherRoomDatabase;
import com.example.thewitcher.repository.BaseRepository;
import com.example.thewitcher.viewModels.PersonnageViewModel;

import java.util.ArrayList;
import java.util.List;

public class PersonnageActivity extends AppCompatActivity {
//ceci vas etre le detail de accountactivity lorsqu'il vas cliquer sur une fiche de perso
private PersonnageViewModel viewModel;
    private personnageAdapter adapter;
    private List<PersonnageDetails> persosArray = new ArrayList<>();
    private BaseRepository baseRepository;

    // Views définies dans votre fichier XML
    private ImageView imageView;
    private TextView tvClass;
    private TextView tvRace;
    private TextView tvSkills;
    private TextView tvGear;
    private ScrollView skillScrollView;
    private ScrollView gearScrollView;
    private ScrollView backgroundScrollView;
    private LinearLayout skillLayout;
    private LinearLayout gearLayout;
    private LinearLayout backgroundLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnage);
        imageView = findViewById(R.id.imageView);
        tvClass = findViewById(R.id.tvClass);
        tvRace = findViewById(R.id.tvRace);
        skillScrollView = findViewById(R.id.svSkill);
        gearScrollView = findViewById(R.id.svGear);
        backgroundScrollView = findViewById(R.id.svBackground);
        
//... et ainsi de suite pour toutes les autres vues

        //Connection à la database
        WitcherRoomDatabase database = WitcherRoomDatabase.getDatabase(this);

        //Création du répository complet (tous les DAO)
        baseRepository = new BaseRepository(getApplication());

        // Initialisation du viewModel
        viewModel = new PersonnageViewModel(getApplication());

        int id = getIntent().getIntExtra("persoId", 0);

        // Récupérer et afficher les détails du personnage
        loadCharacterDetails(id);
    }

    private void loadCharacterDetails(int id) {
        LiveData<PersonnageDetails> personnageDetails = viewModel.getPersonnageDetailsById(id);
        personnageDetails.observe(this, persoDetails -> {
            // Afficher les détails du personnage
            tvClass.setText(persoDetails.getClasse().getName());
            // Afficher l'armure
            Armor armor = persoDetails.getArmor();
            TextView tvArmor = new TextView(this);
            tvArmor.setText(armor.getName());
//            gearScrollView.addView(tvArmor);
            //Afficher l'arme
            Weapon weapon = persoDetails.getWeapon();
            TextView tvWeapon = new TextView(this);
            tvWeapon.setText(weapon.getName());
//            gearScrollView.addView(tvWeapon);
            // Afficher l'histoire
//            TextView tvBackground = new TextView(this);
//            tvBackground.setText(persoDetails.getPersonnage().getBackground());
//            backgroundLayout.addView(tvBackground);
        });
        //Afficher les skills
        LiveData<List<OwnedSkill>> oSkills = viewModel.getPersonnageSkillsById(id);
        oSkills.observe(this, ownedSkills -> {
            for (OwnedSkill oSkill : ownedSkills) {
                TextView tvSkill = new TextView(this);
                //Vérification skillid de oSkill
                Log.d("DEBUG", "Skill id: " + oSkill.getSkillId());
                LiveData<Skill> skill = baseRepository.findSkillById(oSkill.getSkillId());
                skill.observe(this, skill1 -> {
                    tvSkill.setText(skill1.getNomSkill());
                });
//                skillScrollView.addView(tvSkill);
            }
        });
    }
}