package wof.warfare;

import wof.warfare.abilities.Ability;

import java.util.List;

import static wof.warfare.abilities.Abilities.*;

public enum Troop {
    CAVALRY     (300, 5, 10, 30, 3200, 1, true, false, UnitType.HORSEMAN, CAVALRY_ABILITIES),
    LANDS        (80, 10, 40, 50, 2000, 1, true, false, UnitType.INFANTRY, LANDS_ABILITIES),
    SWORDSMAN   (135, 40, 70, 90, 2800, 1,true, false, UnitType.INFANTRY, SWORDSMAN_ABILITIES),
    HALBERD     (135, 35, 70, 110, 3500, 2, true, false, UnitType.INFANTRY, HALBERD_ABILITIES),
    SPEARMAN    (125, 25, 70,90, 1800, 3, true, false, UnitType.INFANTRY, SPEARMAN_ABILITIES),
    KNIGHT      (190, 50, 60, 70, 4500, 1, true, false, UnitType.HORSEMAN, KNIGHT_ABILITIES),
    CROSSBOWMAN (60, 15, 100, 90, 2000, 7, false,true, UnitType.ARCHER, CROSSBOWMAN_ABILITIES),
    BOWMAN      (25, 5, 100, 40, 1200, 11, false, true, UnitType.ARCHER, BOWMAN_ABILITIES),
    RAM         (5,5,0,0,0,1,true,false, UnitType.SUPPORT, RAM_ABILITIES),
    ARISTOCRAT  (5,5,0,0,0,1,true,false, UnitType.KING, ARISTOCRAT_ABILITIES);


    
    public int attack;
    public int armour;
    public int mobility;
    public int atkspeed;
    public int hitpoints;
    public int atkrange;
    public boolean melee;
    public boolean ranged;
    public UnitType type;
    public List<Ability> abilities;


    Troop(int attack, int armour, int mobility, int atkspeed, int hitpoints, int atkrange, boolean melee, boolean ranged, UnitType type, List<Ability> abilities) {
        this.attack = attack;
        this.armour = armour;
        this.mobility = mobility;
        this.atkspeed = atkspeed;
        this.hitpoints = hitpoints;
        this.atkrange = atkrange;
        this.melee = melee;
        this.ranged = ranged;
        this.type = type;
        this.abilities = abilities;
    }
}
