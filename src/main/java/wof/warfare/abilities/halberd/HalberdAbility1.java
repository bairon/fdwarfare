package wof.warfare.abilities.halberd;

import wof.warfare.Formation;
import wof.warfare.UnitType;
import wof.warfare.abilities.Ability;
import wof.warfare.abilities.AttackModifier;

public class HalberdAbility1 implements Ability, AttackModifier {
    @Override
    public double modifyAttack(Formation source, Formation target, double attack, boolean dungeon) {
        return attack * (target.unit.type == UnitType.HORSEMAN ? 1.75 : 1);
    }
}
