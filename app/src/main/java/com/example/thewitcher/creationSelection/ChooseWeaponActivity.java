package com.example.thewitcher.creationSelection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.gear.Weapon;
import com.example.thewitcher.R;
import com.example.thewitcher.adapter.WeaponAdapter;
import com.example.thewitcher.repository.BaseRepository;
import com.example.thewitcher.viewModels.WeaponViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseWeaponActivity extends AppCompatActivity {

    private WeaponViewModel viewModel;
    private WeaponAdapter adapter;
    private List<Weapon> weaponArray = new ArrayList<>();
    private BaseRepository baseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weapon_list);

        //Set activity title
        setTitle("Choose your Weapon");

        //Connection à la database
        baseRepository = new BaseRepository(getApplication());

        RecyclerView listWeapons = findViewById(R.id.listWeapons);
        listWeapons.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new WeaponViewModel(getApplication());
        baseRepository = new BaseRepository(getApplication());

        //Send data to adapter
        adapter = new WeaponAdapter(getApplicationContext(), weaponArray, new WeaponAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Weapon clickedWeapon = weaponArray.get(position);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selected_weapon_id", clickedWeapon.getWeaponId());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        listWeapons.setAdapter(adapter);

        viewModel.getAllWeapons().observe(this, weapon -> {
            weaponArray.clear();
            weaponArray.addAll(weapon);
            adapter.notifyDataSetChanged();
        });

    }
}