package wof.warfare.abilities.cavalry;

import wof.warfare.Formation;
import wof.warfare.abilities.Ability;
import wof.warfare.abilities.ReceivedDamageModifier;

public class CavalryAbility3 implements Ability, ReceivedDamageModifier {

    @Override
    public double modifyReceivedDamage(Formation source, Formation targe, double damage) {
        return source.unit.melee && Math.random() < 0.1 ? 0 : damage;
    }
}
