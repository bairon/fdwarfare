package wof.warfare.abilities.crossbowman;

import wof.warfare.Formation;
import wof.warfare.abilities.Ability;
import wof.warfare.abilities.TargetArmourModifier;

public class CrossbowmanAbility1 implements Ability, TargetArmourModifier {
    @Override
    public double modifyTargetArmour(Formation sorce, Formation target, double armour) {
        return armour * (1 - 0.75);
    }
}
