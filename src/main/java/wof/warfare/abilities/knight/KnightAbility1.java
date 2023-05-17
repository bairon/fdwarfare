package wof.warfare.abilities.knight;

import wof.warfare.Formation;
import wof.warfare.abilities.Ability;
import wof.warfare.abilities.ReceivedDamageModifier;

public class KnightAbility1 implements Ability, ReceivedDamageModifier {
    @Override
    public double modifyReceivedDamage(Formation source, Formation target, double damage) {
        return damage * (1 - 0.25);
    }
}
