package com.example.thewitcher.converters;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.Entity.classe.ClassWithSkills;
import com.example.thewitcher.Entity.classe.Classe;
import com.example.thewitcher.Entity.classe.ClasseSkillCrossRef;
import com.example.thewitcher.connection.WitcherRoomDatabase;
import com.example.thewitcher.dao.SkillDao;
import com.example.thewitcher.dao.classe.ClasseDao;
import com.example.thewitcher.repository.BaseRepository;

public class Converters {
    WitcherRoomDatabase database;
    LifecycleOwner lifecycleOwner;
    ClasseDao classeDao;
    SkillDao skillDao;
    BaseRepository classeRepository;
    BaseRepository skillRepository;

    public Converters(WitcherRoomDatabase database, LifecycleOwner lifecycleOwner){
        this.database = database;
        this.lifecycleOwner = lifecycleOwner;
        classeDao = database.classeDao();
        skillDao = database.skillDao();
        classeRepository = new BaseRepository(classeDao);
        skillRepository = new BaseRepository(skillDao);
    }

    public Converters(WitcherRoomDatabase database){
        this.database = database;
        classeDao = database.classeDao();
        skillDao = database.skillDao();
        classeRepository = new BaseRepository(classeDao);
        skillRepository = new BaseRepository(skillDao);
    }

    public ClassWithSkills mapToClassWithSkills(ClasseSkillCrossRef crossRef) {
        ClassWithSkills classWithSkills = new ClassWithSkills();

        LiveData<Classe> classeLiveData = classeDao.findById(crossRef.classe_id);
        classeLiveData.observe(lifecycleOwner, new Observer<Classe>() {
            @Override
            public void onChanged(Classe classe) {
                // Update your UI or perform any necessary actions with the classe object
                if (classe != null) {
                    classWithSkills.classe = classe;
                }
            }
        });
        LiveData<Skill> skillLiveData = skillDao.findById(crossRef.id);
        classeLiveData.observe(lifecycleOwner, new Observer<Classe>() {
            @Override
            public void onChanged(Classe classe) {
                // Update your UI or perform any necessary actions with the classe object
                if (classe != null) {
                    classWithSkills.classe = classe;
                }
            }
        });

        return classWithSkills;
    }

    public ClasseSkillCrossRef mapToClasseSkillCrossRef(ClassWithSkills classWithSkills) {
        ClasseSkillCrossRef crossRef = new ClasseSkillCrossRef();

        // Map the properties from the ClassWithSkills to the ClasseSkillCrossRef object
        crossRef.classe_id = classWithSkills.classe.getClassId(); // Set the corresponding property from classWithSkills
        crossRef.id = classWithSkills.skills.get(0).getSkillId(); // Set the corresponding property from classWithSkills

        return crossRef;
    }
}
