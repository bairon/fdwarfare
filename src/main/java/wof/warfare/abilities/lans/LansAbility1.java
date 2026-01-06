package wof.warfare.abilities.lans;

import wof.warfare.Formation;
import wof.warfare.abilities.Ability;
import wof.warfare.abilities.AttackModifier;

public class LansAbility1 implements Ability, AttackModifier {
    @Override
    public double modifyAttack(Formation source, Formation target, double attack, boolean dungeon) {
        if (dungeon) {
            return attack * 1.3;
        } else {
            return attack;
        }
    }
}
