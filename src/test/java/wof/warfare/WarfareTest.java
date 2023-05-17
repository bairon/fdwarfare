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
        warfare.town = new Town(25);

        warfare.happen();
        System.out.println(warfare.victorious);
        //System.out.println(new Gson().toJson(warfare));

    }

    private Player createAttacker() {
        return new Player(Collections.singletonList(createArmyAttackers()),
                new Bonus(0, 0, 6, 8, 4),
                new Bonus(0, 0, 13, 5, 0));
    }
    private Player createDefender() {
        return new Player(Collections.singletonList(createArmyDefenders()),
                new Bonus(0, 0, 6, 8, 4),
                new Bonus(0, 0, 13, 5, 0));
    }

    private Army createArmyAttackers() {   // атака
        HashMap<Troop, Integer> troops = new HashMap<>();
        troops.put(Troop.KNIGHT, 80000); // кава
        //troops.put(Troop.CROSSBOWMAN, 121000); // арба
        //troops.put(Troop.BOWMAN, 4000); //луки
        return new Army(troops);
    }
    private Army createArmyDefenders() {  // защита
        HashMap<Troop, Integer> troops = new HashMap<>();
        troops.put(Troop.KNIGHT, 50000); // кава
        troops.put(Troop.CROSSBOWMAN, 4000); // кава
        troops.put(Troop.BOWMAN, 4000); // кава
        return new Army(troops);
    }

}