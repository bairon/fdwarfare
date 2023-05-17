package wof.warfare.abilities;

import wof.warfare.Formation;

public interface HpModifier {
    public int modifyHp(Formation target, int hp);
}
