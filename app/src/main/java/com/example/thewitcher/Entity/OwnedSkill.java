package com.example.thewitcher.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
        entity = Personnage.class,
        parentColumns = "id",
        childColumns = "personnage_id",
        onDelete = ForeignKey.CASCADE))
public class OwnedSkill {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int ownedSkillId;

    @ColumnInfo(name = "skill_id")
    private int skillId;

    @ColumnInfo(name = "personnage_id")
    private int personnageId;  // This column is a foreign key.

    private int value;

    @Ignore
    public OwnedSkill() {
    }

    public OwnedSkill(int ownedSkillId, int skillId, int personnageId, int value) {
        this.ownedSkillId = ownedSkillId;
        this.skillId = skillId;
        this.personnageId = personnageId;
        this.value = value;
    }

    @Ignore
    public OwnedSkill(int skillId, int personnageId, int value) {
        this.skillId = skillId;
        this.personnageId = personnageId;
        this.value = value;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getPersonnageId() {
        return personnageId;
    }

    public void setPersonnageId(int personnageId) {
        this.personnageId = personnageId;
    }

    public int getOwnedSkillId() {
        return ownedSkillId;
    }

    public void setOwnedSkillId(int ownedSkillId) {
        this.ownedSkillId = ownedSkillId;
    }



    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
