package wof.warfare.abilities;

import wof.warfare.Formation;

public interface AttackModifier {
    public double modifyAttack(Formation source, Formation target, double attack, boolean dungeon);
}
