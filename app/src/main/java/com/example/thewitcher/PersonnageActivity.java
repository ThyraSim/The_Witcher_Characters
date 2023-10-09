package com.example.thewitcher;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.PersonnageDetails;
import com.example.thewitcher.Entity.Skill;
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
        ImageView imageView = findViewById(R.id.imageView);
        TextView tvClass = findViewById(R.id.tvClass);
        TextView tvRace = findViewById(R.id.tvRace);
        ScrollView skillsScrollView = findViewById(R.id.svSkill);
        ScrollView gearScrollView = findViewById(R.id.svGear);
        ScrollView backgroundScrollView = findViewById(R.id.svBackground);
        
//... et ainsi de suite pour toutes les autres vues

        //Connection à la database
        WitcherRoomDatabase database = WitcherRoomDatabase.getDatabase(this);

        //Création du répository complet (tous les DAO)
        baseRepository = new BaseRepository(getApplication());

        // Initialisation du viewModel
        //viewModel = new PersonnageViewModel();

        // Récupérer et afficher les détails du personnage
        loadCharacterDetails();



    }

    private void loadCharacterDetails() {

    }
}