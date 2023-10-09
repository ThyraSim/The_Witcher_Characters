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

        //Création du répository complet (tous les DAO)
        baseRepository = new BaseRepository(getApplication());

        RecyclerView listPersos = findViewById(R.id.listPerso);

        listPersos.setLayoutManager(new LinearLayoutManager(this));

        //Send data to adapter
        adapter = new personnageAdapter(this, persosArray, new personnageAdapter.OnItemClickListener() {
            //Create onClickListener and send data to PersonnageActivity
            @Override
            public void onItemClick(int position) {
                PersonnageDetails clickedPost = persosArray.get(position);
                Intent intent = new Intent(AccountActivity.this, PersonnageActivity.class);
                Log.d("DEBUG", "Clicked on item at position " + position + ": " + clickedPost.getPersonnage().getPersonnageId());
                intent.putExtra("persoId", clickedPost.getPersonnage().getPersonnageId());
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

