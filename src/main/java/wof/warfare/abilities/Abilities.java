package wof.warfare.abilities;

import wof.warfare.abilities.bowman.BowmanAbility2;
import wof.warfare.abilities.cavalry.CavalryAbility13;
import wof.warfare.abilities.cavalry.CavalryAbility2;
import wof.warfare.abilities.crossbowman.CrossbowmanAbility1;
import wof.warfare.abilities.halberd.HalberdAbility1;
import wof.warfare.abilities.knight.KnightAbility1;
import wof.warfare.abilities.lans.LansAbility2;
import wof.warfare.abilities.swordsman.SwordsmanAbility1;

import java.util.Arrays;
import java.util.List;

public class Abilities {

    public static final List<Ability> CAVALRY_ABILITIES = Arrays.asList(new CavalryAbility13(), new CavalryAbility2()/*, new CavalryAbility3()*/);
    public static final List<Ability> LANDS_ABILITIES = Arrays.asList(/*new LansAbility1(), */new LansAbility2()/*, new LansAbility3()*/);
    public static final List<Ability> SWORDSMAN_ABILITIES = Arrays.asList(new SwordsmanAbility1());
    public static final List<Ability> HALBERD_ABILITIES = Arrays.asList(new HalberdAbility1());
    public static final List<Ability> SPEARMAN_ABILITIES = Arrays.asList();
    public static final List<Ability> KNIGHT_ABILITIES = Arrays.asList(new KnightAbility1());
    public static final List<Ability> CROSSBOWMAN_ABILITIES = Arrays.asList(new CrossbowmanAbility1());
    public static final List<Ability> BOWMAN_ABILITIES = Arrays.asList(/*new BowmanAbility1(), */new BowmanAbility2());
    public static final List<Ability> RAM_ABILITIES = Arrays.asList();
    public static final List<Ability> ARISTOCRAT_ABILITIES = Arrays.asList();

}
