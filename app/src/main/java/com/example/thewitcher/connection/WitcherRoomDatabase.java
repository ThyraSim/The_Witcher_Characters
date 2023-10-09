package com.example.thewitcher.connection;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.thewitcher.Entity.OwnedSkill;
import com.example.thewitcher.Entity.Personnage;
import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.Entity.classe.Classe;
import com.example.thewitcher.Entity.classe.ClasseSkillCrossRef;
import com.example.thewitcher.Entity.gear.Armor;
import com.example.thewitcher.Entity.gear.Weapon;
import com.example.thewitcher.Entity.race.Race;
import com.example.thewitcher.dao.OwnedSkillDao;
import com.example.thewitcher.dao.PersonnageDao;
import com.example.thewitcher.dao.SkillDao;
import com.example.thewitcher.dao.classe.ClasseDao;
import com.example.thewitcher.dao.classe.ClasseSkillCrossRefDao;
import com.example.thewitcher.dao.gear.ArmorDao;
import com.example.thewitcher.dao.gear.WeaponDao;
import com.example.thewitcher.dao.race.RaceDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Classe.class, Race.class, Weapon.class, Armor.class, OwnedSkill.class,
                    Personnage.class, Skill.class, ClasseSkillCrossRef.class}, version = 30, exportSchema = false)
public abstract class WitcherRoomDatabase extends RoomDatabase {
    public abstract ClasseDao classeDao();
    public abstract ClasseSkillCrossRefDao classeSkillCrossRefDao();
    public abstract RaceDao raceDao();
    public abstract WeaponDao weaponDao();
    public abstract ArmorDao armorDao();
    public abstract OwnedSkillDao ownedSkillDao();
    public abstract PersonnageDao personnageDao();
    public abstract SkillDao skillDao();

    private static volatile WitcherRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static WitcherRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WitcherRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WitcherRoomDatabase.class, "witcher_database")
                            .fallbackToDestructiveMigration()
                            .build();
                    createEntities(INSTANCE);
                }
            }
        }
        return INSTANCE;
    }

    private static void createEntities(WitcherRoomDatabase INSTANCE) {
        databaseWriteExecutor.submit(() -> {
            // Check if the database is empty
            if (INSTANCE.classeDao().count() == 0) {
                // Insert entities into the database
                for (Classe classe : BaseEntities.getClassesToInsert()) {
                    INSTANCE.classeDao().insert(classe);
                }
                for (Skill skill : BaseEntities.getSkillsToInsert()){
                    INSTANCE.skillDao().insert(skill);
                }
                for (ClasseSkillCrossRef classWithSkill : BaseEntities.getClasseWithSkillsToInsert()){
                    INSTANCE.classeSkillCrossRefDao().insert(classWithSkill);
                }
                for(Race race : BaseEntities.getRacesToInsert()){
                    INSTANCE.raceDao().insert(race);
                }
                for(Weapon weapon : BaseEntities.getWeaponsToInsert()){
                    INSTANCE.weaponDao().insert(weapon);
                }
                for(Armor armor : BaseEntities.getArmorsToInsert()){
                    INSTANCE.armorDao().insert(armor);
                }
                for(Personnage personnage : BaseEntities.getPersonnagesToInsert()){
                    INSTANCE.personnageDao().insert(personnage);
                }
                for(OwnedSkill ownedSkill : BaseEntities.getOwnedSkillsToInsert()){
                    INSTANCE.ownedSkillDao().insert(ownedSkill);
                }
            }
            return null;
        });
    }

}
