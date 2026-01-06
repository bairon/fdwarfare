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
    public int totalPerished;
    public int perished;
    public long dmgDealt;
    public long dmgReceived;
    public boolean isMoving;
    public int hpLeft;
    public int [] wallHp = new int[] {0, 1, 2, 3, 4, 5, 7, 9, 11, 13, 15, 18, 21, 24, 27, 30, 35, 40, 50, 60, 75};


    public Formation(boolean attacking, Troop unit, int quantity, Bonus artifact, Bonus skill, int wall, boolean dungeon) {
        this.attacking = attacking;
        this.unit = unit;
        this.quantity = quantity;
        this.attack = unit.attack * (1 + 0.01 * (artifact.attackPercent + skill.attackPercent)) + (unit.melee ? (artifact.attackMelee + skill.attackMelee) : (artifact.attackRanged + skill.attackRanged))
                        + (unit == Troop.LANDS && attacking && !dungeon ? 80 : 0);
        this.armour = unit.armour * (1 + 0.01 * (artifact.armourPercent + skill.armourPercent)) + artifact.armour + skill.armour;
        this.hitpoints = Math.round((unit.hitpoints + (unit == Troop.LANDS && attacking ? 2000 : 0)) * (attacking ? 1 : 1 + .01f * wallHp[wall]));
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
