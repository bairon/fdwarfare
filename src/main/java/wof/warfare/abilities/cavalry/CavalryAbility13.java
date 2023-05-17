package wof.warfare.abilities.cavalry;

import wof.warfare.Formation;
import wof.warfare.abilities.Ability;
import wof.warfare.abilities.ReceivedDamageModifier;

public class CavalryAbility13 implements Ability, ReceivedDamageModifier {
    @Override
    public double modifyReceivedDamage(Formation source, Formation target, double damage) {
        double dmg = !source.unit.melee && target.isMoving ? damage * (1 - 0.90) : damage;
        return source.unit.melee && Math.random() < 0.1 ? 0 : dmg;

    }
}
