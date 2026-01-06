package wof.warfare;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class WarfareTest {
    @Test
    public void test1() {
        Warfare warfare = new Warfare();

        warfare.attackers = Collections.singletonList(createAttacker());
        warfare.defenders = Collections.singletonList(createDefender());
        warfare.town = new Town(0);
        warfare.dungeon = false;

        warfare.happen();
        System.out.println(warfare.victorious);
        System.out.println(warfare.attackers);
        System.out.println(warfare.defenders);
        System.out.println(warfare.battlefield);
        //System.out.println(new Gson().toJson(warfare));

    }

    private Player createAttacker() {
        return new Player(Collections.singletonList(createArmyAttackers()),// new Bonus(), new Bonus());
                new Bonus(27, 0, 50, 23, 8),
                new Bonus(0, 0, 0, 0, 0));
    }
    private Player createDefender() {
        return new Player(Collections.singletonList(createArmyDefenders()), //new Bonus(), new Bonus());
                new Bonus(27, 0, 50, 23, 8),
                new Bonus(0, 0, 0, 0, 0));
    }

    private Army createArmyAttackers() {   // атака
        HashMap<Troop, Integer> troops = new HashMap<>();
        //       troops.put(Troop.CAVALRY, 50000); //
        //troops.put(Troop.LANDS, 126); //
        //troops.put(Troop.SWORDSMAN, 20000); //
        //troops.put(Troop.HALBERD, 5000); //
        //troops.put(Troop.SPEARMAN, 5000); //
        troops.put(Troop.SWORDSMAN, 10000); //
        //troops.put(Troop.CROSSBOWMAN, 6000); //
        //troops.put(Troop.BOWMAN, 6000); // луки

        return new Army(troops);
    }
    private Army createArmyDefenders() {  // защита
        HashMap<Troop, Integer> troops = new HashMap<>();
//        troops.put(Troop.SWORDSMAN, 4500); //
//        troops.put(Troop.HALBERD, 4320); //
//        troops.put(Troop.SPEARMAN, 3120); //
//        troops.put(Troop.CROSSBOWMAN, 1200); //
//        troops.put(Troop.BOWMAN, 1200); // луки




        //troops.put(Troop.SWORDSMAN, 20000); //
        troops.put(Troop.CAVALRY, 10000); //
        //troops.put(Troop.SPEARMAN, 5000); //
        //troops.put(Troop.CAVALRY, 30000); //
        //troops.put(Troop.CROSSBOWMAN, 6000); //
        //troops.put(Troop.BOWMAN, 6000); // луки
        return new Army(troops);
    }

}