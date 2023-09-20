package com.example.thewitcher.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Skill {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int skillId;
    private String nomSkill;
    private int cost;

    @Ignore
    public Skill() {
    }

    public Skill(int skillId, String nomSkill, int cost) {
        this.skillId = skillId;
        this.nomSkill = nomSkill;
        this.cost = cost;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getNomSkill() {
        return nomSkill;
    }

    public void setNomSkill(String nomSkill) {
        this.nomSkill = nomSkill;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
