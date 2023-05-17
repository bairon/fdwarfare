package wof.warfare.abilities;

import wof.warfare.Formation;

public interface TargetArmourModifier {
    public double modifyTargetArmour(Formation sorce, Formation target, double armour);
}
