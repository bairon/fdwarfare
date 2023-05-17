package wof.warfare;


public class Formation {
    public boolean attacking;
    public Troop unit;
    public int quantity;

    public double attack;
    public double armour;
    public int hitpoints;
    public int attckRange;

    public int nextmove;
    public int nextattack;
    public int perished;
    public long dmgDealt;
    public long dmgReceived;
    public boolean isMoving;
    public int hpLeft;


    public Formation(boolean attacking, Troop unit, int quantity, Bonus artifact, Bonus skill) {
        this.attacking = attacking;
        this.unit = unit;
        this.quantity = quantity;
        this.attack = unit.attack * (1 + 0.01 * (artifact.attackPercent + skill.attackPercent)) + (unit.melee ? (artifact.attackMelee + skill.attackMelee) : (artifact.attackRanged + skill.attackRanged))
                        + (unit == Troop.LANDS && attacking ? 80 : 0);
        this.armour = unit.armour * (1 + 0.01 * (artifact.armourPercent + skill.armourPercent)) + artifact.armour + skill.armour;
        this.hitpoints = unit.hitpoints + (unit == Troop.LANDS && attacking ? 2000 : 0);
        this.attckRange = unit.atkrange + (unit == Troop.BOWMAN && !attacking ? 5 : 0);
        this.nextmove = unit.mobility;
        this.nextattack = 0;
        this.perished = 0;
        this.dmgDealt = 0;
        this.dmgReceived = 0;
        this.isMoving = false;
        this.hpLeft = this.hitpoints;

    }
    public int getUnitOrdinal() {
        return unit.ordinal();
    }
    public int getUnitMobility() {
        return unit.mobility;
    }
    @Override
    public String toString() {
        return "Formation{" +
                "attacking=" + attacking +
                ", unit=" + unit +
                ", quantity=" + (quantity - perished) +
                ", hpLeft=" + hpLeft +
                ", nextmove=" + nextmove +
                ", nextattack=" + nextattack +
                '}';
    }

    public void reset() {
        nextmove = 0;
        nextattack = 0;
    }
}
