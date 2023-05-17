package wof.warfare.abilities.swordsman;

import wof.warfare.Formation;
import wof.warfare.UnitType;
import wof.warfare.abilities.Ability;
import wof.warfare.abilities.ReceivedDamageModifier;

public class SwordsmanAbility1 implements Ability, ReceivedDamageModifier {
    @Override
    public double modifyReceivedDamage(Formation source, Formation target, double damage) {
        return (source.unit.type == UnitType.INFANTRY || source.unit.type == UnitType.ARCHER) && Math.random() < 0.3 ? 0 : damage;
    }
}
