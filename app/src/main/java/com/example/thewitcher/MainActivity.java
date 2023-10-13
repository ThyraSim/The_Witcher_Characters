package com.example.thewitcher;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.thewitcher.Entity.OwnedSkill;
import com.example.thewitcher.Entity.Personnage;
import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.Entity.classe.ClassWithSkills;
import com.example.thewitcher.Entity.classe.Classe;
import com.example.thewitcher.Entity.race.Race;
import com.example.thewitcher.connection.WitcherRoomDatabase;
import com.example.thewitcher.converters.Converters;
import com.example.thewitcher.dao.OwnedSkillDao;
import com.example.thewitcher.dao.PersonnageDao;
import com.example.thewitcher.dao.SkillDao;
import com.example.thewitcher.dao.classe.ClasseDao;
import com.example.thewitcher.dao.classe.ClasseSkillCrossRefDao;
import com.example.thewitcher.dao.gear.ArmorDao;
import com.example.thewitcher.dao.gear.WeaponDao;
import com.example.thewitcher.dao.race.RaceDao;
import com.example.thewitcher.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {

    //Membres
    private TextView txtName, txtId, txtSkill, txtSkillName, txtSkillId, txtSkillCost;
    private TextView txtPersoName, txtPersoArmorName, txtOwnedSkillName;
    private BaseRepository baseRepository;
    private LiveData<List<Classe>> classeListLiveData;
    private LiveData<List<Race>> raceListLiveData;
    private LiveData<List<OwnedSkill>> ownedSkillListLiveData;
    private LiveData<List<Personnage>> personnageListLiveData;
    private LiveData<List<Skill>> skillListLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidgets();
        setListeners();

        //Connection à la database
        WitcherRoomDatabase database = WitcherRoomDatabase.getDatabase(this);

        //Instanciation des DAO
        ClasseDao classeDao = database.classeDao();
        ClasseSkillCrossRefDao classeSkillCrossRefDao = database.classeSkillCrossRefDao();
        RaceDao raceDao = database.raceDao();
        WeaponDao weaponDao = database.weaponDao();
        ArmorDao armorDao = database.armorDao();
        OwnedSkillDao ownedSkillDao = database.ownedSkillDao();
        PersonnageDao personnageDao = database.personnageDao();
        SkillDao skillDao = database.skillDao();

        //Création du répository complet (tous les DAO)
        baseRepository = new BaseRepository(getApplication());

        Classe classe = new Classe(1, "Bard", "Busking (EMP)", "A Bard is a wonderful thing to have around", 0, "None");

//        new InsertEntityAsyncTask<Classe>(classeDao).execute(classe);
        Skill skill = new Skill(1, "Perception", 2);
//        new InsertEntityAsyncTask<Skill>(skillDao).execute(skill);

        //Récupération des classes dans la BD
        classeListLiveData = new BaseRepository<Classe>(classeDao).findAll();
        
        //Modification de l'affichage lors de changments
//        ObserveEntityListAsyncTask<Classe> observeClasseListAsyncTask = new ObserveEntityListAsyncTask<>(classeListLiveData, new Consumer<Classe>() {
//            @Override
//            public void accept(Classe classe) {
//                updateTextViewsClasse(classe);
//            }
//        });
//        observeClasseListAsyncTask.execute();

        skillListLiveData = new BaseRepository<Skill>(skillDao).findAll();
//        ObserveEntityListAsyncTask<Skill> observeSkillListAsyncTask = new ObserveEntityListAsyncTask<>(skillListLiveData, new Consumer<Skill>() {
//           @Override
//            public void accept(Skill skill) {
//                updateTextViewsSkill(skill);
//            }
//       });
//        observeSkillListAsyncTask.execute();

        ClassWithSkills classWithSkills = new ClassWithSkills();
        classWithSkills.classe = classe;
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);
        classWithSkills.skills = skills;

        Converters converters = new Converters(database, this);
//        new InsertEntityAsyncTask<ClasseSkillCrossRef>(classeSkillCrossRefDao).execute(converters.mapToClasseSkillCrossRef(classWithSkills));

        //Récupération des skills dans la BD
        LiveData<List<Skill>> skillsFromClassLiveData = baseRepository.findSkillsByClassId(1);
        //Modification de l'affichage des skills selon les changements
//        ObserveEntityListAsyncTask<Skill> skillObserveEntityListAsyncTask = new ObserveEntityListAsyncTask<>(skillsFromClassLiveData, new Consumer<Skill>() {
//            @Override
//            public void accept(Skill skills) {
//                updateTextViewsSkill(skills);
//            }
//        });
//        skillObserveEntityListAsyncTask.execute();

//        int personnageId = 1;
//        int ownedSkillId = 1;
//
//        updatePersonnageName(personnageId);
//        updatePersonnageArmorName(personnageId);
//        updateOwnedSkillName(ownedSkillId);


        LiveData<List<Personnage>> personnageListLiveData = baseRepository.findAllPersonnages();
        personnageListLiveData.observe(this, new Observer<List<Personnage>>() {
            @Override
            public void onChanged(List<Personnage> personnageList) {
                if (!personnageList.isEmpty()) {
                    Personnage personnage = personnageList.get(0);
                    txtPersoName.setText(personnage.getName());
                }
            }
        });
    }
    private void setListeners(){

    }

    //Membres UI
    private void setWidgets(){
        txtName = findViewById(R.id.txtName);
        txtId = findViewById(R.id.txtId);
        txtSkill = findViewById(R.id.txtSkill);
        txtSkillName = findViewById(R.id.txtSkillName);
        txtSkillId = findViewById(R.id.txtSkillId);
        txtSkillCost = findViewById(R.id.txtSkillCost);
        txtPersoName = findViewById(R.id.txtPersoName);
        txtPersoArmorName = findViewById(R.id.txtPersoArmorName);
        txtOwnedSkillName = findViewById(R.id.txtOwnedSkillName);
    }

    //Fonction pour créer n'importe quelle entité

//    private <T> void observeEntityList(LiveData<List<T>> entityListLiveData, Consumer<T> entityConsumer) {
//        entityListLiveData.observe(this, new Observer<List<T>>() {
//            @Override
//            public void onChanged(List<T> entities) {
//                if (!entities.isEmpty()) {
//                    T entity = entities.get(0);
//                    entityConsumer.accept(entity);
//                }
//            }
//        });
//    }

    //Modifier l'affichage des attributs de classes
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

    //Modifier l'affichage des attributs de skills
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

    //Modifier l'affichage des attributs de ClasseWithSkills
    private void updateTextViewsClassWithSkill(ClassWithSkills classWithSkills) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtSkillName.setText(classWithSkills.skills.get(0).getNomSkill());
                txtSkillId.setText(String.valueOf(classWithSkills.skills.get(0).getSkillId()));
                txtSkillCost.setText(String.valueOf(classWithSkills.skills.get(0).getCost()));
                txtSkillName.setText(String.valueOf(classWithSkills));
            }
        });
    }

    //Fonction pour observer les changements dans les livedata en background
//    private class ObserveEntityListAsyncTask<T> extends AsyncTask<Void, Void, Void> {
//        private LiveData<List<T>> entityListLiveData;
//        private Consumer<T> entityConsumer;
//
//        public ObserveEntityListAsyncTask(LiveData<List<T>> entityListLiveData, Consumer<T> entityConsumer) {
//            this.entityListLiveData = entityListLiveData;
//            this.entityConsumer = entityConsumer;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Utilitaire.observeEntityList(this, entityListLiveData, entityConsumer);
//                }
//            });
//            return null;
//        }
//    }

    public void updatePersonnageName(int personnageId) {
        LiveData<Personnage> personnageLiveData = baseRepository.findPersonnageById(personnageId);
        observeEntityOnce(personnageLiveData, this::displayPersonnageName);
    }

    private void displayPersonnageName(Personnage personnage) {
        txtPersoName.setText(personnage.getName());
    }

    /*public void updatePersonnageArmorName(int personnageId) {
        LiveData<PersonnageDetails> personnageDetailsLiveData = baseRepository.findPersonnageDetails(personnageId);
        observeEntityOnce(personnageDetailsLiveData, personnageDetails -> txtPersoArmorName.setText(personnageDetails.getArmor().getName()));
    }*/

//    public void updatePersonnageArmorName(int personnageId) {
//        PersonnageDetails personnageDetailsLiveData = baseRepository.findPersonnageDetails(personnageId);
//        observeEntityOnce(personnageDetailsLiveData, personnageDetails -> {
//            if (personnageDetails != null && personnageDetails.getArmor() != null) {
//                txtPersoArmorName.setText(personnageDetails.getArmor().getName());
//            } else {
//                // Handle the null scenario, for example:
//                txtPersoArmorName.setText("No armor details available");
//            }
//        });
//    }


    public void updateOwnedSkillName(int ownedSkillId) {
        LiveData<OwnedSkill> ownedSkillLiveData = baseRepository.findOwnedSkillById(ownedSkillId);
        observeEntityOnce(ownedSkillLiveData, this::displayOwnedSkillName);
    }

    private void displayOwnedSkillName(OwnedSkill ownedSkill) {
        if (ownedSkill != null) {
            LiveData<Skill> skillLiveData = baseRepository.findSkillById(ownedSkill.getSkillId());
            observeEntityOnce(skillLiveData, skill -> {
                if (skill != null) {
                    txtOwnedSkillName.setText(skill.getNomSkill());
                }
            });
        }
    }

    private <T> void observeEntityOnce(LiveData<T> liveData, Consumer<T> action) {
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(T t) {
                action.accept(t);  // Use data here
                liveData.removeObserver(this);  // Stop observing
            }
        };
        liveData.observe(this, observer);
    }



}