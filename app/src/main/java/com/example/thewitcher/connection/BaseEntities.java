package com.example.thewitcher.connection;

import com.example.thewitcher.Entity.OwnedSkill;
import com.example.thewitcher.Entity.Personnage;
import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.Entity.classe.Classe;
import com.example.thewitcher.Entity.classe.ClasseSkillCrossRef;
import com.example.thewitcher.Entity.gear.Armor;
import com.example.thewitcher.Entity.gear.Weapon;
import com.example.thewitcher.Entity.race.Race;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseEntities {
    public static List<Classe> getClassesToInsert() {
        List<Classe> classes = new ArrayList<>();
        classes.add(new Classe(1, "Bard", "Busking (EMP)", "A Bard is a wonderful thing to have around, " +
                "especially when the party’s low on money. A " +
                "Bard can take an hour and make a Busking" +
                "roll in the nearest town center. The total of " +
                "this roll is the amount of money raked in by " +
                "the Bard while they perform on the street. A " +
                "fumble can lower the roll, and a negative value " +
                "means that not only do you fail to make any " +
                "coin but you are also harrassed by the locals " +
                "for your poor performance, resulting in a -2 " +
                "to Charisma with anyone in the town for the " +
                "rest of the day", 0, "None"));
        classes.add(new Classe(2, "Craftsman", "Patch Job (CRA)", "A skilled craftsman can patch a weapon or " +
                "armor well enough to keep it working and " +
                "keep its wearer/wielder in the fight, whether " +
                "that be by tying a bowstring back together, sharpening the edge of a broken blade or " +
                "nailing a plate over a cracked shield. By taking " +
                "a turn to roll Patch Job at a DC equal to the " +
                "item’s Crafting DC -3 a Craftsman can restore " +
                "a broken shield or armor to 1/2 its full SP or " +
                "restore a broken weapon to 1/2 its durability. " +
                "Until fixed at a forge, a patched weapon does " +
                "half its normal damage.", 0, "None"));
        classes.add(new Classe(3, "Criminal", "Practiced Paranoia (INT)", "Whether they’re an assassin, a thief, a counterfeitter, or a smuggler, criminals all share a practiced paranoia that keeps them out of trouble. " +
                "Whenever a Criminal comes within 10m of a " +
                "trap (this includes experimental traps, Man at " +
                "Arms booby traps, & ambushes) they immediately can make a Practiced Paranoia roll at " +
                "either the DC to spot the trap, the ambushing " +
                "party’s Stealth roll, or a DC set by the GM. " +
                "Even if they don’t succeed in spotting the trap, " +
                "they are still aware that something is wrong.", 0, "None"));
        classes.add(new Classe(4, "Doctor", "Healing Hands (CRA)", "Anyone can apply some ointment and wrap " +
                "a bandage around a cut, but a Doctor has " +
                "true medical training which allows them to " +
                "perform complex surgeries. A Doctor with " +
                "Healing Hands is the only person who can " +
                "heal a critical wound. To heal critical wounds " +
                "a doctor must make a number of successful " +
                "Healing Hands rolls based on the severity of " +
                "the critical wound. The DC of the roll is based " +
                "on the severity of the critical wound as well. " +
                "Healing Hands can also be used for any First " +
                "Aid task", 0, "None"));
        classes.add(new Classe(5, "Mage", "Magic Training (INT)", "To qualify as a Mage a magically adept person must pass through the halls of one of the " +
                "world’s magical academies and learn the fundamentals of the magical arts. A Mage can roll " +
                "Magical Training whenever they encounter " +
                "a magical phenomenon, an unknown spell, " +
                "or a question of magical theory. The DC is set " +
                "by the GM, and a success allows the Mage to " +
                "learn everything there is to know about the " +
                "phenomenon. Magical Training can also " +
                "be rolled as a form of Awareness that detects " +
                "magical beings, spells, and hexes", 5, "5 Novice Spells" +
                "1 Ritual" +
                "1 Hex"));
        classes.add(new Classe(6, "Man At Arms", "Tough As Nails", "True Men At Arms like the Blue Stripes of Temeria and the Impera Brigade of Nilfgaard are " +
                "hardened soldiers who never give in or surrender. When a Man At Arms falls to or below 0 Health, they can roll Tough As Nails at " +
                "a DC equal to the number of negative Health " +
                "times 2 to keep fighting. If they fail, they fall " +
                "into death state as per usual. If they succeed " +
                "they can keep fighting as if they were only at " +
                "their Wound Threshold. Any damage forces " +
                "them to make another roll against a DC based " +
                "on their Health", 0, "None"));
        classes.add(new Classe(7, "Merchant", "Well Traveled (INT)", "Your average merchant makes a living from " +
                "trade and that trade brings in customers " +
                "from all around. But a traveling merchant " +
                "goes to their customers, wandering the roads " +
                "of the world, and learning from it’s people. " +
                "A Merchant can make a Well Traveled roll " +
                "anytime they want to know a fact about a " +
                "specific item, culture, or area. The DC is set " +
                "by the GM and if the roll is successful the " +
                "Merchant remembers the answer to that " +
                "question, calling on memories of the last time " +
                "they travelled through the applicable area.", 0, "None"));
        classes.add(new Classe(8, "Priest", "Initiate of the Gods (EMP)", "The churches of the world are often warm " +
                "and inviting places, helping their communities and welcoming new converts. A Priest " +
                "can roll Initiate of the Gods at a DC set by " +
                "the GM at churches of the same faith to get " +
                "free lodging, healing, and other services at " +
                "the GM’s discretion. Initiate of the Gods also " +
                "works when dealing with members of the " +
                "same faith, though they will likely be able to " +
                "offer less than a fully supplied church. Keep in " +
                "mind that Initiate of the Gods doesn’t work " +
                "with members of other faiths.", 0, "2 Invocations" +
                "2 Rituals" +
                "2 Hexes"));
        classes.add(new Classe(9, "Witcher", "Witcher Training", "Most of a Witcher’s early life is spent within " +
                "the walls of their keep, studying huge, dusty " +
                "tomes and going through hellish combat " +
                "training. Many have argued that the Witcher’s " +
                "greatest weapon is their knowledge of monsters and their adaptability in any situation. " +
                "When in a hostile environment or difficult " +
                "terrain, a Witcher can lessen the penalties by " +
                "half their Witcher Training value (minimum " +
                "1). Witcher Training can also be used in any " +
                "situation that you would normally use Monster Lore for", 0, "All Basic Signs"));
        return classes;
    }

    public static List<Skill> getSkillsToInsert() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill(1, "Awareness", 1));
        skills.add(new Skill(2, "Business", 1));
        skills.add(new Skill(3, "Deduction", 1));
        skills.add(new Skill(4, "Education", 1));
        skills.add(new Skill(5, "Language", 2));
        skills.add(new Skill(6, "Monster Lore", 2));
        skills.add(new Skill(7, "Social Etiquette", 1));
        skills.add(new Skill(8, "Streetwise", 1));
        skills.add(new Skill(9, "Tactics", 2));
        skills.add(new Skill(10, "Teaching", 1));
        skills.add(new Skill(11, "Wilderness Survival", 1));
        skills.add(new Skill(12, "Brawling", 1));
        skills.add(new Skill(13, "Dodge/Escape", 1));
        skills.add(new Skill(14, "Melee", 1));
        skills.add(new Skill(15, "Riding", 1));
        skills.add(new Skill(16, "Sailing", 1));
        skills.add(new Skill(17, "Small Blades", 1));
        skills.add(new Skill(18, "Staff/Spear", 1));
        skills.add(new Skill(19, "Swordsmanship", 1));
        skills.add(new Skill(20, "Archery", 1));
        skills.add(new Skill(21, "Athletics", 1));
        skills.add(new Skill(22, "Crossbow", 1));
        skills.add(new Skill(23, "Sleight of Hand", 1));
        skills.add(new Skill(24, "Stealth", 1));
        skills.add(new Skill(25, "Physique", 1));
        skills.add(new Skill(26, "Endurance", 1));
        skills.add(new Skill(27, "Charisma", 1));
        skills.add(new Skill(28, "Deceit", 1));
        skills.add(new Skill(29, "Fine Arts", 1));
        skills.add(new Skill(30, "Gambling", 1));
        skills.add(new Skill(31, "Grooming and Style", 1));
        skills.add(new Skill(32, "Human Perception", 1));
        skills.add(new Skill(33, "Leadership", 1));
        skills.add(new Skill(34, "Persuasion", 1));
        skills.add(new Skill(35, "Perfomance", 1));
        skills.add(new Skill(36, "Seduction", 1));
        skills.add(new Skill(37, "Alchemy", 2));
        skills.add(new Skill(38, "Crafting", 2));
        skills.add(new Skill(39, "Disguise", 1));
        skills.add(new Skill(40, "First Aid", 1));
        skills.add(new Skill(41, "Forgery", 1));
        skills.add(new Skill(42, "Pick Lock", 1));
        skills.add(new Skill(43, "Trap Crafting", 2));
        skills.add(new Skill(44, "Courage", 1));
        skills.add(new Skill(45, "Hex Weaving", 2));
        skills.add(new Skill(46, "Spell Casting", 2));
        skills.add(new Skill(47, "Resist Magic", 2));
        skills.add(new Skill(48, "Resist Coercion", 1));
        skills.add(new Skill(49, "Intimidation", 1));
        skills.add(new Skill(50, "Ritual Crafting", 2));
        return skills;
    }

    public static List<ClasseSkillCrossRef> getClasseWithSkillsToInsert(){
        List<Classe> classes = getClassesToInsert();
        List<Skill> skills = getSkillsToInsert();
        List<ClasseSkillCrossRef> classWithSkills = new ArrayList<>();
        List<Skill> toAddSkills = Arrays.asList(skills.get(26), skills.get(27), skills.get(34), skills.get(4), skills.get(31), skills.get(33), skills.get(7), skills.get(28), skills.get(36), skills.get(6));
        for(Skill skill:toAddSkills){
            ClasseSkillCrossRef crossRef = new ClasseSkillCrossRef();
            crossRef.classe_id = 1;
            crossRef.id = skill.getSkillId();
            classWithSkills.add(crossRef);
        }

        toAddSkills = Arrays.asList(skills.get(37), skills.get(1), skills.get(20), skills.get(25), skills.get(24), skills.get(7), skills.get(28), skills.get(36), skills.get(3), skills.get(33));
        for(Skill skill:toAddSkills){
            ClasseSkillCrossRef crossRef = new ClasseSkillCrossRef();
            crossRef.classe_id = 2;
            crossRef.id = skill.getSkillId();
            classWithSkills.add(crossRef);
        }

        toAddSkills = Arrays.asList(skills.get(22), skills.get(41), skills.get(7), skills.get(40), skills.get(27), skills.get(23), skills.get(48), skills.get(16), skills.get(20), skills.get(0));
        for(Skill skill:toAddSkills){
            ClasseSkillCrossRef crossRef = new ClasseSkillCrossRef();
            crossRef.classe_id = 3;
            crossRef.id = skill.getSkillId();
            classWithSkills.add(crossRef);
        }

        toAddSkills = Arrays.asList(skills.get(47), skills.get(26), skills.get(6), skills.get(43), skills.get(31), skills.get(10), skills.get(1), skills.get(2), skills.get(16), skills.get(36));
        for(Skill skill:toAddSkills){
            ClasseSkillCrossRef crossRef = new ClasseSkillCrossRef();
            crossRef.classe_id = 4;
            crossRef.id = skill.getSkillId();
            classWithSkills.add(crossRef);
        }

        toAddSkills = Arrays.asList(skills.get(31), skills.get(45), skills.get(44), skills.get(46), skills.get(17),skills.get(3), skills.get(49), skills.get(6), skills.get(35), skills.get(30));
        for(Skill skill:toAddSkills){
            ClasseSkillCrossRef crossRef = new ClasseSkillCrossRef();
            crossRef.classe_id = 5;
            crossRef.id = skill.getSkillId();
            classWithSkills.add(crossRef);
        }

        toAddSkills = Arrays.asList(skills.get(16), skills.get(13), skills.get(19), skills.get(11), skills.get(12), skills.get(10), skills.get(43), skills.get(24), skills.get(48));
        for(Skill skill:toAddSkills){
            ClasseSkillCrossRef crossRef = new ClasseSkillCrossRef();
            crossRef.classe_id = 6;
            crossRef.id = skill.getSkillId();
            classWithSkills.add(crossRef);
        }

        toAddSkills = Arrays.asList(skills.get(26), skills.get(16), skills.get(3), skills.get(4), skills.get(7), skills.get(1), skills.get(33), skills.get(31), skills.get(29), skills.get(47));
        for(Skill skill:toAddSkills){
            ClasseSkillCrossRef crossRef = new ClasseSkillCrossRef();
            crossRef.classe_id = 7;
            crossRef.id = skill.getSkillId();
            classWithSkills.add(crossRef);
        }

        toAddSkills = Arrays.asList(skills.get(49), skills.get(32), skills.get(43), skills.get(31), skills.get(44), skills.get(39), skills.get(26), skills.get(10), skills.get(9), skills.get(45));
        for(Skill skill:toAddSkills){
            ClasseSkillCrossRef crossRef = new ClasseSkillCrossRef();
            crossRef.classe_id = 8;
            crossRef.id = skill.getSkillId();
            classWithSkills.add(crossRef);
        }

        toAddSkills = Arrays.asList(skills.get(0), skills.get(2), skills.get(45), skills.get(36), skills.get(12), skills.get(10), skills.get(18), skills.get(20), skills.get(23), skills.get(14));
        for(Skill skill:toAddSkills){
            ClasseSkillCrossRef crossRef = new ClasseSkillCrossRef();
            crossRef.classe_id = 9;
            crossRef.id = skill.getSkillId();
            classWithSkills.add(crossRef);
        }
        return classWithSkills;
    }

    public static List<Race> getRacesToInsert() {
        List<Race> races = new ArrayList<>();
        races.add(new Race(1, "Witchers", "Enhanced Senses", "Resilient Mutation", "Dulled Emotions", "Lightning Reflexes"));
        races.add(new Race(2, "Elves", "Artistic", "Marksman", "Natural Attuenement"));
        races.add(new Race(3, "Dwarves", "Though", "Strong", "Crafter's Eye"));
        races.add(new Race(4, "Humans", "Trustworthy", "Ingenuity", "Blindly Stubborn"));
        return races;
    }

    public static List<Weapon> getWeaponsToInsert() {
        List<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(1, "Iron Long Sword", "2d6+2", 2));
        weapons.add(new Weapon(2, "Arming Sword", "2d6+4", 1));
        weapons.add(new Weapon(3, "Gleddyf", "3d6+2", 2));
        weapons.add(new Weapon(4, "Hunter’s Falchion", "3d6", 1));
        weapons.add(new Weapon(5, "Krigsverd", "4d6+4", 1));
        weapons.add(new Weapon(6, "Esboda", "5d6", 1));
        weapons.add(new Weapon(7, "Kord", "5d6", 1));
        weapons.add(new Weapon(8, "Vicovarian Blade", "5d6+4", 2));
        weapons.add(new Weapon(9, "Torrwr", "6d6", 2));
        weapons.add(new Weapon(10, "Dagger", "1d6+2", 1));
        weapons.add(new Weapon(11, "Stiletto", "1d6", 1));
        weapons.add(new Weapon(12, "Poniard", "2d6+2", 1));
        weapons.add(new Weapon(13, "Jambiya", "2d6", 1));
        weapons.add(new Weapon(14, "Hand Axe", "2d6+1", 1));
        weapons.add(new Weapon(15, "Battle Axe", "5d6", 1));
        weapons.add(new Weapon(16, "Berserker’s Axe", "6d6", 2));
        weapons.add(new Weapon(17, "Brass Knuckles", "1d6", 1));
        weapons.add(new Weapon(18, "Mace", "5d6", 1));
        weapons.add(new Weapon(19, "Highland Mauler", "6d6+2", 2));
        weapons.add(new Weapon(20, "Spear", "3d6", 2));
        weapons.add(new Weapon(21, "Pole Axe", "4d6+2", 2));
        weapons.add(new Weapon(22, "Red Halberd", "6d6+3", 2));
        return weapons;
    }

    public static List<Armor> getArmorsToInsert() {
        List<Armor> armors = new ArrayList<>();
        armors.add(new Armor(1, "Verden Archer’s Hood", 3));
        armors.add(new Armor(2, "Gambeson", 3));
        armors.add(new Armor(3, "Aedirnian Gambeson", 5));
        armors.add(new Armor(4, "Double Woven Gambeson", 8));
        armors.add(new Armor(5, "Brigandine", 12));
        armors.add(new Armor(6, "Redanian Halberdier’s Armor", 14));
        armors.add(new Armor(7, "Lyrian Leather Jacket", 16));
        armors.add(new Armor(8, "Plate Armor", 20));
        armors.add(new Armor(9, "Hindarsfjall Heavy Armor", 25));
        armors.add(new Armor(10, "Nilfgaardian Plate Armor", 30));
        return armors;
    }

    public static List<Personnage> getPersonnagesToInsert(){
        List<Personnage> personnages = new ArrayList<>();
        personnages.add(new Personnage("George", 20, 1, 1, 1, 1));
        personnages.add(new Personnage("Paulo", 22, 3, 7, 18, 8));
        personnages.add(new Personnage("Yemel", 27, 4, 6, 16, 5));
        personnages.add(new Personnage("Zechiel", 322, 2, 3, 11, 6));
        personnages.add(new Personnage("Remereim", 19, 1, 4, 4, 4));
        return personnages;
    }

    public static List<OwnedSkill> getOwnedSkillsToInsert(){
        List<OwnedSkill> ownedSkills = new ArrayList<>();
        ownedSkills.add(new OwnedSkill(27, 1, 1));
        ownedSkills.add(new OwnedSkill(12, 1, 3));
        ownedSkills.add(new OwnedSkill(35, 1, 5));
        ownedSkills.add(new OwnedSkill(7, 1, 2));
        ownedSkills.add(new OwnedSkill(42, 1, 4));
        ownedSkills.add(new OwnedSkill(14, 2, 2));
        ownedSkills.add(new OwnedSkill(3, 2, 4));
        ownedSkills.add(new OwnedSkill(47, 2, 1));
        ownedSkills.add(new OwnedSkill(19, 2, 5));
        ownedSkills.add(new OwnedSkill(25, 2, 3));
        ownedSkills.add(new OwnedSkill(9, 3, 2));
        ownedSkills.add(new OwnedSkill(50, 3, 1));
        ownedSkills.add(new OwnedSkill(11, 3, 5));
        ownedSkills.add(new OwnedSkill(38, 3, 4));
        ownedSkills.add(new OwnedSkill(29, 3, 3));
        ownedSkills.add(new OwnedSkill(4, 4, 5));
        ownedSkills.add(new OwnedSkill(17, 4, 1));
        ownedSkills.add(new OwnedSkill(31, 4, 2));
        ownedSkills.add(new OwnedSkill(8, 4, 3));
        ownedSkills.add(new OwnedSkill(44, 4, 4));
        ownedSkills.add(new OwnedSkill(23, 5, 5));
        ownedSkills.add(new OwnedSkill(15, 5, 1));
        ownedSkills.add(new OwnedSkill(39, 5, 4));
        ownedSkills.add(new OwnedSkill(10, 5, 2));
        ownedSkills.add(new OwnedSkill(49, 5, 3));
        return ownedSkills;
    }
}
