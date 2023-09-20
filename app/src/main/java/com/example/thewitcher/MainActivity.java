package com.example.thewitcher;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.thewitcher.Entity.OwnedGear;
import com.example.thewitcher.Entity.OwnedSkill;
import com.example.thewitcher.Entity.Personnage;
import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.Entity.classe.ClassWithSkills;
import com.example.thewitcher.Entity.classe.Classe;
import com.example.thewitcher.Entity.race.Race;
import com.example.thewitcher.connection.WitcherRoomDatabase;
import com.example.thewitcher.dao.BaseDao;
import com.example.thewitcher.dao.OwnedGearDao;
import com.example.thewitcher.dao.OwnedSkillDao;
import com.example.thewitcher.dao.PersonnageDao;
import com.example.thewitcher.dao.SkillDao;
import com.example.thewitcher.dao.classe.ClasseDao;
import com.example.thewitcher.dao.classe.ClasseSkillCrossRefDao;
import com.example.thewitcher.dao.gear.ArmorDao;
import com.example.thewitcher.dao.gear.WeaponDao;
import com.example.thewitcher.dao.race.RaceDao;
import com.example.thewitcher.repository.BaseRepository;

import java.util.List;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {

    private TextView txtName, txtId, txtSkill, txtSkillName, txtSkillId, txtSkillCost;

    private BaseRepository baseRepository;
    private LiveData<List<Classe>> classeListLiveData;
    private LiveData<List<Race>> raceListLiveData;
    private LiveData<List<OwnedGear>> ownedGearListLiveData;
    private LiveData<List<OwnedSkill>> ownedSkillListLiveData;
    private LiveData<List<Personnage>> personnageListLiveData;
    private LiveData<List<Skill>> skillListLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidgets();
        setListeners();

        WitcherRoomDatabase database = WitcherRoomDatabase.getDatabase(this);

        ClasseDao classeDao = database.classeDao();
        ClasseSkillCrossRefDao classeSkillCrossRefDao = database.classeSkillCrossRefDao();
        RaceDao raceDao = database.raceDao();
        WeaponDao weaponDao = database.weaponDao();
        ArmorDao armorDao = database.armorDao();
        OwnedGearDao ownedGearDao = database.ownedGearDao();
        OwnedSkillDao ownedSkillDao = database.ownedSkillDao();
        PersonnageDao personnageDao = database.personnageDao();
        SkillDao skillDao = database.skillDao();

        baseRepository = new BaseRepository(classeDao, classeSkillCrossRefDao, raceDao, weaponDao, armorDao, ownedGearDao, ownedSkillDao, personnageDao, skillDao);

        Classe classe = new Classe(1, "Bard", "Busking (EMP)", "A Bard is a wonderful thing to have around", 0, "None");

//        new InsertEntityAsyncTask<Classe>(classeDao).execute(classe);
        Skill skill = new Skill(1, "Perception", 2);
//        new InsertEntityAsyncTask<Skill>(skillDao).execute(skill);

        classeListLiveData = new BaseRepository<Classe>(classeDao).findAll();
        ObserveEntityListAsyncTask<Classe> observeClasseListAsyncTask = new ObserveEntityListAsyncTask<>(classeListLiveData, new Consumer<Classe>() {
            @Override
            public void accept(Classe classe) {
                updateTextViewsClasse(classe);
            }
        });
        observeClasseListAsyncTask.execute();

//        skillListLiveData = new BaseRepository<Skill>(skillDao).findAll();
//        ObserveEntityListAsyncTask<Skill> observeSkillListAsyncTask = new ObserveEntityListAsyncTask<>(skillListLiveData, new Consumer<Skill>() {
//            @Override
//            public void accept(Skill skill) {
//                updateTextViewsSkill(skill);
//            }
//        });
//        observeSkillListAsyncTask.execute();

//        ClassWithSkills classWithSkills = new ClassWithSkills();
//        classWithSkills.classe = classe;
//        List<Skill> skills = new ArrayList<>();
//        skills.add(skill);
//        classWithSkills.skills = skills;

//        Converters converters = new Converters(database, this);
//        new InsertEntityAsyncTask<ClasseSkillCrossRef>(classeSkillCrossRefDao).execute(converters.mapToClasseSkillCrossRef(classWithSkills));

        LiveData<List<Skill>> skillsFromClassLiveData = baseRepository.findSkillsByClassId(1);
        ObserveEntityListAsyncTask<Skill> skillObserveEntityListAsyncTask = new ObserveEntityListAsyncTask<>(skillsFromClassLiveData, new Consumer<Skill>() {
            @Override
            public void accept(Skill skills) {
                updateTextViewsSkill(skills);
            }
        });
        skillObserveEntityListAsyncTask.execute();

    }
    private void setListeners(){

    }

    private void setWidgets(){
        txtName = findViewById(R.id.txtName);
        txtId = findViewById(R.id.txtId);
        txtSkill = findViewById(R.id.txtSkill);
        txtSkillName = findViewById(R.id.txtSkillName);
        txtSkillId = findViewById(R.id.txtSkillId);
        txtSkillCost = findViewById(R.id.txtSkillCost);
    }

    private class InsertEntityAsyncTask<T> extends AsyncTask<T, Void, Void>{
        private BaseRepository<T> nbaseRepository;

        public InsertEntityAsyncTask(BaseDao myDao){
            this.nbaseRepository = new BaseRepository<>(myDao);
        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(T... entities){
            nbaseRepository.insertEntity(entities[0]);
            return null;
        }
    }

    private <T> void observeEntityList(LiveData<List<T>> entityListLiveData, Consumer<T> entityConsumer) {
        entityListLiveData.observe(this, new Observer<List<T>>() {
            @Override
            public void onChanged(List<T> entities) {
                if (!entities.isEmpty()) {
                    T entity = entities.get(0);
                    entityConsumer.accept(entity);
                }
            }
        });
    }

    private void updateTextViewsClasse(Classe classe) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtName.setText(classe.getDefiningName());
                txtId.setText(String.valueOf(classe.getClassId()));
                txtSkill.setText(classe.getDefiningDescription());
            }
        });
    }
    private void updateTextViewsSkill(Skill skill) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtSkillName.setText(skill.getNomSkill());
                txtSkillId.setText(String.valueOf(skill.getSkillId()));
                txtSkillCost.setText(String.valueOf(skill.getCost()));
            }
        });
    }

    private void updateTextViewsClassWithSkill(ClassWithSkills classWithSkills) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                txtSkillName.setText(classWithSkills.skills.get(0).getNomSkill());
//                txtSkillId.setText(String.valueOf(classWithSkills.skills.get(0).getSkillId()));
//                txtSkillCost.setText(String.valueOf(classWithSkills.skills.get(0).getCost()));
                txtSkillName.setText(String.valueOf(classWithSkills));
            }
        });
    }

    private class ObserveEntityListAsyncTask<T> extends AsyncTask<Void, Void, Void> {
        private LiveData<List<T>> entityListLiveData;
        private Consumer<T> entityConsumer;

        public ObserveEntityListAsyncTask(LiveData<List<T>> entityListLiveData, Consumer<T> entityConsumer) {
            this.entityListLiveData = entityListLiveData;
            this.entityConsumer = entityConsumer;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    observeEntityList(entityListLiveData, entityConsumer);
                }
            });
            return null;
        }
    }
}