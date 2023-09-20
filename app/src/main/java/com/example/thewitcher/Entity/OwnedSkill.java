package com.example.thewitcher.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class OwnedSkill {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int ownedSkillId;

//    private Skill skill;
    private int value;

    @Ignore
    public OwnedSkill() {
    }

    public OwnedSkill(int ownedSkillId, /*Skill skill,*/ int value) {
        this.ownedSkillId = ownedSkillId;
//        this.skill = skill;
        this.value = value;
    }

    public int getOwnedSkillId() {
        return ownedSkillId;
    }

    public void setOwnedSkillId(int ownedSkillId) {
        this.ownedSkillId = ownedSkillId;
    }

//    public Skill getSkill() {
//        return skill;
//    }
//
//    public void setSkill(Skill skill) {
//        this.skill = skill;
//    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
