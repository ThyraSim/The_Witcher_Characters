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

    public Personnage getPersonnage() {
        return personnage;
    }

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}


