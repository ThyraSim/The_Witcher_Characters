package com.example.thewitcher.repository;

import androidx.lifecycle.LiveData;

import com.example.thewitcher.Entity.OwnedSkill;
import com.example.thewitcher.Entity.Personnage;
import com.example.thewitcher.Entity.PersonnageDetails;
import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.Entity.classe.Classe;
import com.example.thewitcher.Entity.classe.ClasseSkillCrossRef;
import com.example.thewitcher.Entity.race.Race;
import com.example.thewitcher.dao.BaseDao;
import com.example.thewitcher.dao.OwnedSkillDao;
import com.example.thewitcher.dao.PersonnageDao;
import com.example.thewitcher.dao.SkillDao;
import com.example.thewitcher.dao.classe.ClasseDao;
import com.example.thewitcher.dao.classe.ClasseSkillCrossRefDao;
import com.example.thewitcher.dao.gear.ArmorDao;
import com.example.thewitcher.dao.gear.WeaponDao;
import com.example.thewitcher.dao.race.RaceDao;

import java.util.List;

public class BaseRepository<T> {

    private BaseDao myDao;
    private ClasseDao classeDao;
    private ClasseSkillCrossRefDao classeSkillCrossRefDao;
    private RaceDao raceDao;
    private WeaponDao weaponDao;
    private ArmorDao armorDao;
    private OwnedSkillDao ownedSkillDao;
    private PersonnageDao personnageDao;
    private SkillDao skillDao;

    public BaseRepository(ClasseDao classeDao, ClasseSkillCrossRefDao classeSkillCrossRefDao, RaceDao raceDao, WeaponDao weaponDao, ArmorDao armorDao, OwnedSkillDao ownedSkillDao, PersonnageDao personnageDao, SkillDao skillDao) {
        this.classeDao = classeDao;
        this.classeSkillCrossRefDao = classeSkillCrossRefDao;
        this.raceDao = raceDao;
        this.weaponDao = weaponDao;
        this.armorDao = armorDao;
        this.ownedSkillDao = ownedSkillDao;
        this.personnageDao = personnageDao;
        this.skillDao = skillDao;
    }

    public BaseRepository(BaseDao myDao){
        this.myDao = myDao;
    }

    public void insertEntity(T entity) {
        myDao.insert(entity);
    }

    public void updateEntity(T entity) {
        myDao.update(entity);
    }

    public void deleteEntity(T entity) {
        myDao.delete(entity);
    }

    public LiveData<List<T>> findAll(){ return myDao.findAll();}

    public void insertClasse(Classe classe) {
        classeDao.insert(classe);
    }

    public void deleteClasse(Classe classe) {
        classeDao.delete(classe);
    }

    public void updateClasse(Classe classe) {
        classeDao.update(classe);
    }

    public LiveData<Classe> findClasseById(int id) {
        return classeDao.findById(id);
    }

    public LiveData<List<Classe>> findAllClasses() {
        return classeDao.findAll();
    }

    public LiveData<ClasseSkillCrossRef> findSkillByClassId(int id) { return classeSkillCrossRefDao.findById(id);}

    public LiveData<List<Skill>> findSkillsByClassId(int classId) { return classeSkillCrossRefDao.findSkillsByClassId(classId); }

    public void insertRace(Race race) {
        raceDao.insert(race);
    }

    public void deleteRace(Race race) {
        raceDao.delete(race);
    }

    public void updateRace(Race race) {
        raceDao.update(race);
    }

    public LiveData<Race> findRaceById(int id) {
        return raceDao.findById(id);
    }

    public LiveData<List<Race>> findAllRaces() {
        return raceDao.findAll();
    }

    public void insertOwnedSkill(OwnedSkill ownedSkill) {
        ownedSkillDao.insert(ownedSkill);
    }

    public void deleteOwnedSkill(OwnedSkill ownedSkill) {
        ownedSkillDao.delete(ownedSkill);
    }

    public void updateOwnedSkill(OwnedSkill ownedSkill) {
        ownedSkillDao.update(ownedSkill);
    }

    public LiveData<OwnedSkill> findOwnedSkillById(int id) {
        return ownedSkillDao.findById(id);
    }

    public LiveData<List<OwnedSkill>> findAllOwnedSkills() {
        return ownedSkillDao.findAll();
    }

    public void insertPersonnage(Personnage personnage) {
        personnageDao.insert(personnage);
    }

    public void deletePersonnage(Personnage personnage) {
        personnageDao.delete(personnage);
    }

    public void updatePersonnage(Personnage personnage) {
        personnageDao.update(personnage);
    }

    public LiveData<Personnage> findPersonnageById(int id) {
        return personnageDao.findById(id);
    }

    public LiveData<List<Personnage>> findAllPersonnages() {
        return personnageDao.findAll();
    }

    public LiveData<PersonnageDetails> findPersonnageDetails(int searchId){ return personnageDao.findPersonnageDetails(searchId); }

    public void insertSkill(Skill skill) {
        skillDao.insert(skill);
    }

    public void deleteSkill(Skill skill) {
        skillDao.delete(skill);
    }

    public void updateSkill(Skill skill) {
        skillDao.update(skill);
    }

    public LiveData<Skill> findSkillById(int id) {
        return skillDao.findById(id);
    }

    public LiveData<List<Skill>> findAllSkills() {
        return skillDao.findAll();
    }
}
