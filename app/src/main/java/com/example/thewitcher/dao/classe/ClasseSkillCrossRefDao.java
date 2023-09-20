package com.example.thewitcher.dao.classe;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.Entity.classe.ClasseSkillCrossRef;
import com.example.thewitcher.dao.BaseDao;

import java.util.List;

@Dao
public interface ClasseSkillCrossRefDao extends BaseDao<ClasseSkillCrossRef> {

    @Override
    @Transaction
    @Query("SELECT ClasseSkillCrossRef.classe_id, ClasseSkillCrossRef.id " +
            "FROM ClasseSkillCrossRef " +
            "INNER JOIN classe ON ClasseSkillCrossRef.classe_id = classe.id " +
            "INNER JOIN skill ON ClasseSkillCrossRef.id = skill.id")
    LiveData<List<ClasseSkillCrossRef>> findAll();

    @Transaction
    @Query("SELECT ClasseSkillCrossRef.classe_id, ClasseSkillCrossRef.id " +
            "FROM ClasseSkillCrossRef " +
            "INNER JOIN classe ON ClasseSkillCrossRef.classe_id = classe.id " +
            "INNER JOIN skill ON ClasseSkillCrossRef.id = skill.id WHERE classe.id = :searchId")
    LiveData<ClasseSkillCrossRef> findById(int searchId);

//    @RawQuery(observedEntities = {Classe.class, Skill.class})
//    LiveData<List<ClassWithSkills>> getAllClassesWithSkills(SupportSQLiteQuery query);

    @Query("Select Skill.id, Skill.nomSkill, Skill.cost from ClasseSkillCrossRef " +
            "INNER JOIN Skill ON ClasseSkillCrossRef.id = Skill.id where ClasseSkillCrossRef.classe_id = :searchId")
    LiveData<List<Skill>> findSkillsByClassId(int searchId);
}
