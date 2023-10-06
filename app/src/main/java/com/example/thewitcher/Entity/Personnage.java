package com.example.thewitcher.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Personnage {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int personnageId;
    private String name;
    private int age;
    private int raceId;
    private int classeId;
    private int weaponId;
    private int armorId;

    @Ignore
    public Personnage() {
    }

    public Personnage(int personnageId, String name, int age, int raceId, int classeId, int weaponId, int armorId) {
        this.personnageId = personnageId;
        this.name = name;
        this.age = age;
        this.raceId = raceId;
        this.classeId = classeId;
        this.weaponId = weaponId;
        this.armorId = armorId;
    }

    @Ignore
    public Personnage(String name, int age, int raceId, int classeId, int weaponId, int armorId) {
        this.name = name;
        this.age = age;
        this.raceId = raceId;
        this.classeId = classeId;
        this.weaponId = weaponId;
        this.armorId = armorId;
    }

    public int getPersonnageId() {
        return personnageId;
    }

    public void setPersonnageId(int personnageId) {
        this.personnageId = personnageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getClasseId() {
        return classeId;
    }

    public void setClasseId(int classeId) {
        this.classeId = classeId;
    }

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public int getArmorId() {
        return armorId;
    }

    public void setArmorId(int armorId) {
        this.armorId = armorId;
    }
}
