package com.example.thewitcher.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class OwnedGear {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int gearId;
//    private Gear gear;
    private int quantite;

    @Ignore
    public OwnedGear() {
    }

    public OwnedGear(int gearId/*, Gear gear*/, int quantite) {
        this.gearId = gearId;
//        this.gear = gear;
        this.quantite = quantite;
    }

//    public Gear getGear() {
//        return gear;
//    }
//
//    public void setGear(Gear gear) {
//        this.gear = gear;
//    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getGearId() {
        return gearId;
    }

    public void setGearId(int gearId) {
        this.gearId = gearId;
    }
}
