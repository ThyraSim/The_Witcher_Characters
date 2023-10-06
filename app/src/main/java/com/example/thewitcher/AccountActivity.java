package com.example.thewitcher;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.PersonnageDetails;
import com.example.thewitcher.connection.WitcherRoomDatabase;
import com.example.thewitcher.dao.OwnedSkillDao;
import com.example.thewitcher.dao.PersonnageDao;
import com.example.thewitcher.dao.SkillDao;
import com.example.thewitcher.dao.classe.ClasseDao;
import com.example.thewitcher.dao.classe.ClasseSkillCrossRefDao;
import com.example.thewitcher.dao.gear.ArmorDao;
import com.example.thewitcher.dao.gear.WeaponDao;
import com.example.thewitcher.dao.race.RaceDao;
import com.example.thewitcher.repository.BaseRepository;
import com.example.thewitcher.viewModels.PersonnageViewModel;

import java.util.ArrayList;
import java.util.List;

//ici va etre la liste des personnage lier au compte
public class AccountActivity extends AppCompatActivity {
    private PersonnageViewModel viewModel;
    private personnageAdapter adapter;
    private List<PersonnageDetails> persosArray = new ArrayList<>();
    private BaseRepository baseRepository;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //Connection à la database
        WitcherRoomDatabase database = WitcherRoomDatabase.getDatabase(this);

        //Instanciation des DAO
        ClasseDao classeDao = database.classeDao();
        ClasseSkillCrossRefDao classeSkillCrossRefDao = database.classeSkillCrossRefDao();
        RaceDao raceDao = database.raceDao();
        WeaponDao weaponDao = database.weaponDao();
        ArmorDao armorDao = database.armorDao();
        OwnedSkillDao ownedSkillDao = database.ownedSkillDao();
        PersonnageDao personnageDao = database.personnageDao();
        SkillDao skillDao = database.skillDao();

        //Création du répository complet (tous les DAO)
        baseRepository = new BaseRepository(getApplication());

        RecyclerView listPersos = findViewById(R.id.listPersos);

        listPersos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new personnageAdapter(this, persosArray, new personnageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                PersonnageDetails clickedPost = persosArray.get(position);
                Intent intent = new Intent(AccountActivity.this, PersonnageActivity.class);
                intent.putExtra("titre", clickedPost.getPersonnage().getName());
                startActivity(intent);
            }
        });
        listPersos.setAdapter(adapter);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(PersonnageViewModel.class);

        // Observe LiveData
        viewModel.getAllPersonnageDetails().observe(this, personnageDetails -> {
            Log.d("DEBUG", "Observer received " + personnageDetails.size() + " items");
            persosArray = personnageDetails;
            adapter.updateData((ArrayList<PersonnageDetails>) personnageDetails);
            adapter.notifyDataSetChanged();
        });

        registerForContextMenu(listPersos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.itemAdd) {
            Toast.makeText(this, "Add new post", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.itemConfig) {
            Toast.makeText(this, "Enter into config", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.itemLogout) {
            finish();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;

    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.list_popup_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


}

