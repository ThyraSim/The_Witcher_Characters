package com.example.thewitcher.creationSelection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.gear.Armor;
import com.example.thewitcher.R;
import com.example.thewitcher.adapter.ArmorAdapter;
import com.example.thewitcher.repository.BaseRepository;
import com.example.thewitcher.viewModels.ArmorViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseArmorActivity extends AppCompatActivity {

    private ArmorViewModel viewModel;
    private ArmorAdapter adapter;
    private List<Armor> armorArray = new ArrayList<>();
    private BaseRepository baseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.armor_list);

        //Set activity title
        setTitle("Choose your Armor");

        //Connection à la database#

        RecyclerView listArmors = findViewById(R.id.listArmors);
        listArmors.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ArmorViewModel(getApplication());
        baseRepository = new BaseRepository(getApplication());

        //Send data to adapter
        adapter = new ArmorAdapter(getApplicationContext(), armorArray, new ArmorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Armor clickedArmor = armorArray.get(position);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selected_armor_id", clickedArmor.getArmorId());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        listArmors.setAdapter(adapter);

        viewModel.getAllArmors().observe(this, armor -> {
            armorArray.clear();
            armorArray.addAll(armor);
            adapter.notifyDataSetChanged();
        });
    }
}