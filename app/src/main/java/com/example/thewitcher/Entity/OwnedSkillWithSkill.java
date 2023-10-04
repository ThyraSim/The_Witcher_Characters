package com.example.thewitcher.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

public class OwnedSkillWithSkill {
    @Embedded
    public OwnedSkill ownedSkill;

    @Relation(
            parentColumn = "skill_id",
            entityColumn = "skillId"
    )
    public Skill skill;
}

