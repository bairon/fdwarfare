package wof.warfare;

import java.util.ArrayList;
import java.util.List;

public class Warfare {
    public List<Player> attackers;
    public List<Player> defenders;
    public Battlefield battlefield;
    public Town town;
    public List<Round> rounds = new ArrayList<>();
    public int victorious;
    int turns;


    public void happen() {
        battlefield = new Battlefield(attackers, defenders, false);

        do {
            rounds.add(doRound());
            victorious = battlefield.getVictorious();
        } while (victorious == 0);

        //System.out.println("Victorious: " + victorious + ", " +  (victorious == 1 ? " Attackers" : " Defenders"));

    }

    private Round doRound() {
        //System.out.println("==============NEW ROUND==============");
        battlefield.restart();
        turns = 2000;
        while (turns > 0) {
            //System.out.println("Turn: " + turns);
            battlefield.turn();
            turns--;
        }
        Round round = battlefield.getRound();
        System.out.println(round);
        return round;
    }
}