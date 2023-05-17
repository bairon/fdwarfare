package wof.warfare;

import java.util.HashMap;
import java.util.Map;

public class Army {
    public Map<Troop, Integer> troops;

    public Army(HashMap<Troop, Integer> troops) {
        this.troops = troops;
    }

    public int getUnitsQty(Troop unit) {
        Integer qty = troops.get(unit);
        if (qty == null) {
            return 0;
        }
        return qty;
    }
}
