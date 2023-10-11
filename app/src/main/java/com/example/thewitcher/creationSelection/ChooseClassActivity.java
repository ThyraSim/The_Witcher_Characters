package com.example.thewitcher.creationSelection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.classe.Classe;
import com.example.thewitcher.R;
import com.example.thewitcher.adapter.ClassAdapter;
import com.example.thewitcher.connection.WitcherRoomDatabase;
import com.example.thewitcher.repository.BaseRepository;
import com.example.thewitcher.viewModels.ClassViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseClassActivity extends AppCompatActivity {

    private ClassViewModel viewModel;
    private ClassAdapter adapter;
    private List<Classe> classeArray = new ArrayList<>();
    private BaseRepository baseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_list);

        //Set activity title
        setTitle("Choose your Class");

        //Connection à la database
        WitcherRoomDatabase database = WitcherRoomDatabase.getDatabase(this);

        RecyclerView listClasses = findViewById(R.id.listClasses);
        listClasses.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ClassViewModel(getApplication());
        baseRepository = new BaseRepository(getApplication());

        //Send data to adapter
        adapter = new ClassAdapter(getApplicationContext(), classeArray, new ClassAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Classe clickedClass = classeArray.get(position);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selected_class_id", clickedClass.getClassId());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        listClasses.setAdapter(adapter);

        viewModel.getAllClasses().observe(this, classe -> {
            classeArray.clear();
            classeArray.addAll(classe);
            adapter.notifyDataSetChanged();
        });
    }
}