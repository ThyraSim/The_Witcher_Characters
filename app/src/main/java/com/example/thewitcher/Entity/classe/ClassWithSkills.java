package com.example.thewitcher.Entity.classe;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.thewitcher.Entity.Skill;

import java.util.List;

public class ClassWithSkills {
    @Embedded(prefix = "classe_") public Classe classe;
    @Relation(
            parentColumn = "classe_id",
            entityColumn = "id",
            associateBy = @Junction(ClasseSkillCrossRef.class)
    )
    public List<Skill> skills;

    public ClassWithSkills(Classe classe, List<Skill> skills){
        this.classe = classe;
        this.skills = skills;
    }

    public ClassWithSkills(){

    }
}
