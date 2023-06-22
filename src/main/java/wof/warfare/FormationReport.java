package wof.warfare;

public class FormationReport {
    public boolean attacking;
    public Troop unit;
    public int quantity;


    public double attack;
    public double armour;
    public int hitpoints;
    public int attckRange;

    public int perished;
    public long dmgDealt;

    public FormationReport(Formation f) {
        this.attacking = f.attacking;
        this.unit = f.unit;
        this.quantity = f.quantity;
        this.attack = f.attack;
        this.armour = f.armour;
        this.hitpoints = f.hitpoints;
        this.attckRange = f.attckRange;
        this.perished = f.perished;
        this.dmgDealt = f.dmgDealt;
    }

    @Override
    public String toString() {
        return "\n" + unit.name() + ": " + perished + " Нанесли урона: " + dmgDealt;
    }
}
