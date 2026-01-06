package wof.warfare.abilities.lans;

import wof.warfare.Formation;
import wof.warfare.Troop;
import wof.warfare.UnitType;
import wof.warfare.abilities.Ability;
import wof.warfare.abilities.ReceivedDamageModifier;

public class LansAbility2 implements Ability, ReceivedDamageModifier {

    @Override
    public double modifyReceivedDamage(Formation source, Formation targe, double damage) {
        if (source.unit.type == UnitType.INFANTRY || Troop.CAVALRY == source.unit) {
            return damage * (1 - 0.3);
        } else if (source.unit.type == UnitType.ARCHER) {
            return damage * (1 - 0.15);
        } else {
            return damage;
        }
    }
}
