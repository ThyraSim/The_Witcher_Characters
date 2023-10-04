package com.example.thewitcher.Entity;

import androidx.room.Embedded;

import com.example.thewitcher.Entity.classe.Classe;
import com.example.thewitcher.Entity.gear.Armor;
import com.example.thewitcher.Entity.gear.Weapon;
import com.example.thewitcher.Entity.race.Race;

public class PersonnageDetails {

    @Embedded
    public Personnage personnage;

    @Embedded(prefix = "race_")
    public Race race;

    @Embedded(prefix = "classe_")
    public Classe classe;

    @Embedded(prefix = "weapon_")
    public Weapon weapon;

    @Embedded(prefix = "armor_")
    public Armor armor;
}


