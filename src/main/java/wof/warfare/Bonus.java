package wof.warfare;

public class Bonus {
    public int attackPercent;
    public int armourPercent;
    public int armour;
    public int attackMelee;
    public int attackRanged;

    public Bonus() {
    }

    public Bonus(int attackPercent, int armourPercent, int armour, int attackMelee, int attackRanged) {
        this.attackPercent = attackPercent;
        this.armourPercent = armourPercent;
        this.armour = armour;
        this.attackMelee = attackMelee;
        this.attackRanged = attackRanged;
    }
}
