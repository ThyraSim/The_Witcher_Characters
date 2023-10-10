package com.example.thewitcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.race.Race;
import com.example.thewitcher.adapter.RaceAdapter;
import com.example.thewitcher.connection.WitcherRoomDatabase;
import com.example.thewitcher.repository.BaseRepository;
import com.example.thewitcher.viewModels.RaceViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseRaceActivity extends AppCompatActivity {

    private RaceViewModel viewModel;
    private RaceAdapter adapter;
    private List<Race> raceArray = new ArrayList<>();
    private BaseRepository baseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race_list);

        //Set activity title
        setTitle("Choose your Race");

        //Connection à la database
        WitcherRoomDatabase database = WitcherRoomDatabase.getDatabase(this);

        RecyclerView listRaces = findViewById(R.id.listRaces);
        listRaces.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new RaceViewModel(getApplication());
        baseRepository = new BaseRepository(getApplication());

        //Send data to adapter
        adapter = new RaceAdapter(getApplicationContext(), raceArray, new RaceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Race clickedRace = raceArray.get(position);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selected_race_id", clickedRace.getRaceId());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        listRaces.setAdapter(adapter);

        viewModel.getAllRaces().observe(this, race -> {
            raceArray = race;
            adapter.updateData((ArrayList<Race>) race);
            adapter.notifyDataSetChanged();
        });
    }
}