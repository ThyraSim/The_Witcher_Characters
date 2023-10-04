package com.example.thewitcher.Entity.gear;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Armor {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int armorId;
    private String name;
    private Integer sp;

    public Armor(int armorId, String name, Integer sp) {
        this.armorId = armorId;
        this.name = name;
        this.sp = sp;
    }

    public int getArmorId() {
        return armorId;
    }

    public void setArmorId(int armorId) {
        this.armorId = armorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSp() {
        return sp;
    }

    public void setSp(Integer sp) {
        this.sp = sp;
    }
}
