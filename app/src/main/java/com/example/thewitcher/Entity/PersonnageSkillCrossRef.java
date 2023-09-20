package com.example.thewitcher.Entity;

import androidx.room.Entity;

@Entity(tableName = "personnage_skill_cross_ref",
    primaryKeys = { "personnageId", "skillId" })
public class PersonnageSkillCrossRef {
    private int personnageId;
    private int skillId;
    private int level;

    public PersonnageSkillCrossRef(int personnageId, int skillId, int level) {
        this.personnageId = personnageId;
        this.skillId = skillId;
        this.level = level;
    }

    public int getPersonnageId() {
        return personnageId;
    }

    public void setPersonnageId(int personnageId) {
        this.personnageId = personnageId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
