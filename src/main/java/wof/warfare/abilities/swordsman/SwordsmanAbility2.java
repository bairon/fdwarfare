package wof.warfare.abilities.swordsman;

import wof.warfare.abilities.Ability;
import wof.warfare.abilities.FormationProtector;

public class SwordsmanAbility2 implements Ability, FormationProtector {
    public double getProtection() {
        return .3d;
    }
}
