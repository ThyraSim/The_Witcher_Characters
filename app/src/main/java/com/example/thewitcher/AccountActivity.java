package com.example.thewitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thewitcher.connection.WitcherRoomDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

//ici va etre la liste des personnage lier au compte
public class AccountActivity extends AppCompatActivity {
    private ListView listPersos;
    private ArrayList<AccountPerso> persosArray = new ArrayList<>();
    private personnageAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        WitcherRoomDatabase database = WitcherRoomDatabase.getDatabase(this);
        // 1: Retrieve the email sent by MainActivity
        String email = getIntent().getStringExtra("email");
        // 2: Display the email in tvHello
        listPersos = findViewById(R.id.listPersos);

        // Populating the postsArray
        persosArray.add(new AccountPerso("Post 1", "Ceci est une image de Aorus Chibi", R.drawable.aorus_chibi3));
        persosArray.add(new AccountPerso("Post 2", "Ceci est une image d'une potion d'arcane", R.drawable.raid_shadow_arcane_potion));
        persosArray.add(new AccountPerso("Post 3", "Ceci est une image d'une potion de force'", R.drawable.raid_shadow_force_potion));
        persosArray.add(new AccountPerso("Post 4", "Ceci est une image d'une moyenne potion d'arcane", R.drawable.raid_shadow_medium_arcane_potion));
        persosArray.add(new AccountPerso("Post 5", "Ceci est une image de silver", R.drawable.silver_raid_shadow));

        adapter = new personnageAdapter(this, R.layout.account_personnage, persosArray);
        listPersos.setAdapter(adapter);

        listPersos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AccountPerso clickedPost = persosArray.get(position);
                Intent intent = new Intent(AccountActivity.this, PersonnageActivity.class);
                intent.putExtra("titre", clickedPost.getTitre());
                startActivity(intent);
            }
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

