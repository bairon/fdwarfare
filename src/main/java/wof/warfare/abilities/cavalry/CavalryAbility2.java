package wof.warfare.abilities.cavalry;

import wof.warfare.Formation;
import wof.warfare.abilities.Ability;
import wof.warfare.abilities.ArmourModifier;

public class CavalryAbility2 implements Ability, ArmourModifier {
    @Override
    public double modifyArmour(double armour) {
        return Math.min(armour, 55);
    }
}
