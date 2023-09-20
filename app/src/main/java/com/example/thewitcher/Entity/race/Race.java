package com.example.thewitcher.Entity.race;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Race {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int raceId;
    private String name;
    private String perk1;
    private String perk2;
    private String perk3;
    private String perk4;

    @Ignore
    public Race() {
    }

    @Ignore
    public Race(int raceId, String name, String perk1, String perk2, String perk3) {
        this.raceId = raceId;
        this.name = name;
        this.perk1 = perk1;
        this.perk2 = perk2;
        this.perk3 = perk3;
    }

    public Race(int raceId, String name, String perk1, String perk2, String perk3, String perk4) {
        this.raceId = raceId;
        this.name = name;
        this.perk1 = perk1;
        this.perk2 = perk2;
        this.perk3 = perk3;
        this.perk4 = perk4;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerk1() {
        return perk1;
    }

    public void setPerk1(String perk1) {
        this.perk1 = perk1;
    }

    public String getPerk2() {
        return perk2;
    }

    public void setPerk2(String perk2) {
        this.perk2 = perk2;
    }

    public String getPerk3() {
        return perk3;
    }

    public void setPerk3(String perk3) {
        this.perk3 = perk3;
    }

    public String getPerk4() {
        return perk4;
    }

    public void setPerk4(String perk4) {
        this.perk4 = perk4;
    }
}
