package com.example.thewitcher.Entity.gear;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Weapon {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int weaponId;
    private String name;
    private String damage;
    private Integer hands;

    public Weapon(int weaponId, String name, String damage, Integer hands) {
        this.weaponId = weaponId;
        this.name = name;
        this.damage = damage;
        this.hands = hands;
    }

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public Integer getHands() {
        return hands;
    }

    public void setHands(Integer hands) {
        this.hands = hands;
    }
}
