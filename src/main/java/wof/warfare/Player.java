package wof.warfare;

import java.util.List;

public class Player {
    public List<Army> armies;
    public Bonus artifact;
    public Bonus skill;

    public Player(List<Army> armies, Bonus artifact, Bonus skill) {
        this.armies = armies;
        this.artifact = artifact;
        this.skill = skill;
    }
}
