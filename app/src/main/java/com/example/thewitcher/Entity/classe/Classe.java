package com.example.thewitcher.Entity.classe;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Classe {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int classId;
    private String name;
    private String definingName;
    private String definingDescription;
    private int vigor;
    private String magicPerks;

    @Ignore
    public Classe() {
    }

    public Classe(int classId, String name, String definingName, String definingDescription, int vigor, String magicPerks) {
        this.classId = classId;
        this.name = name;
        this.definingName = definingName;
        this.definingDescription = definingDescription;
        this.vigor = vigor;
        this.magicPerks = magicPerks;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getDefiningName() {
        return definingName;
    }

    public void setDefiningName(String definingName) {
        this.definingName = definingName;
    }

    public String getDefiningDescription() {
        return definingDescription;
    }

    public void setDefiningDescription(String definingDescription) {
        this.definingDescription = definingDescription;
    }

    public int getVigor() {
        return vigor;
    }

    public void setVigor(int vigor) {
        this.vigor = vigor;
    }

    public String getMagicPerks() {
        return magicPerks;
    }

    public void setMagicPerks(String magicPerks) {
        this.magicPerks = magicPerks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
