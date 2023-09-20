package com.example.thewitcher.Entity.classe;

import androidx.room.Entity;

@Entity(primaryKeys = {"classe_id", "id"})
public class ClasseSkillCrossRef {
    public int classe_id;
    public int id;
}
