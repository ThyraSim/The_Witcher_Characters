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
    @ColumnInfo(name = "ownedId")
    private int ownedSkillId;

    @ColumnInfo(name = "skill_id")
    private Integer skillId;

    @ColumnInfo(name = "personnage_id")
    private Integer personnageId;  // This column is a foreign key.

    private Integer value;

    @Ignore
    public OwnedSkill() {
    }

    public OwnedSkill(int ownedSkillId, Integer skillId, Integer personnageId, Integer value) {
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

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public Integer getPersonnageId() {
        return personnageId;
    }

    public void setPersonnageId(Integer personnageId) {
        this.personnageId = personnageId;
    }

    public int getOwnedSkillId() {
        return ownedSkillId;
    }

    public void setOwnedSkillId(int ownedSkillId) {
        this.ownedSkillId = ownedSkillId;
    }



    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
