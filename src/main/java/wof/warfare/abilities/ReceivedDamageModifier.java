package wof.warfare.abilities;

import wof.warfare.Formation;

public interface ReceivedDamageModifier {
    public double modifyReceivedDamage(Formation source, Formation target, double damage);
}
