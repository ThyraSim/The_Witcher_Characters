package com.example.thewitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PersonnageActivity extends AppCompatActivity {
//ceci vas etre le detail de accountactivity lorsqu'il vas cliquer sur une fiche de perso
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnage);
    }
}