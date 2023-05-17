package wof.warfare;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FormationFinder {

    @Test
    public void test1() {
        Army bestAttacking;
        int  bestAttackingWins = 0;
        int totalLimit = 20000;
        int landsLimit = 0;
        int cavLimit = totalLimit;
        int swordLimit = 0;
        int knightLimit = totalLimit;
        int halberdLimit = totalLimit;
        int spearLimit = 0;
        int crossbowLimit = 8000;
        int bowLimit = 8000;
        int step = 2000;
        for (int lands = 0; lands <= totalLimit && lands <= landsLimit; lands += step) {
            for (int cav = 0; cav <= totalLimit - lands && cav <= cavLimit; cav += step) {
                for (int sword = 0; sword <= swordLimit && sword <= totalLimit - lands - cav; sword += step) {
                    for (int bow = 0; bow <= bowLimit && bow <= totalLimit - lands - cav - sword; bow += step) {
                        for (int halberd = 0; halberd <= halberdLimit && halberd <= totalLimit - lands - cav - sword - bow; halberd += step) {
                            for (int spear = 0; spear <= spearLimit && spear <= totalLimit - lands - cav - sword - bow - halberd; spear += step) {
                                for (int crossbow = 0; crossbow <= crossbowLimit && crossbow <= totalLimit - lands - cav - sword - bow - halberd - spear; crossbow += step) {
                                    int knight = totalLimit - lands - cav - sword - halberd - spear - crossbow;
                                        if (lands + cav + sword + knight + halberd + spear + crossbow + bow == 0) continue;
                                        if (lands + cav + sword + knight == 0) continue;
                                        List<Player> attacker = Collections.singletonList(createAttacker(lands, cav, sword, knight, halberd, spear, crossbow, bow));
                                        Army currentAttacking = attacker.get(0).armies.get(0);
                                        int currentWins = 0;


                                    for (int lands2 = 0; lands2 <= totalLimit && lands2 <= landsLimit; lands2 += step) {
                                        for (int cav2 = 0; cav2 <= totalLimit - lands && cav2 <= cavLimit; cav2 += step) {
                                            for (int sword2 = 0; sword2 <= swordLimit && sword2 <= totalLimit - lands2 - cav2; sword2 += step) {
                                                for (int bow2 = 0; bow2 <= bowLimit && bow2 <= totalLimit - lands2 - cav2 - sword2; bow2 += step) {
                                                    for (int halberd2 = 0; halberd2 <= halberdLimit && halberd2 <= totalLimit - lands2 - cav2 - sword2 - bow2; halberd2 += step) {
                                                        for (int spear2 = 0; spear2 <= spearLimit && spear2 <= totalLimit - lands2 - cav2 - sword2 - bow2 - halberd2; spear2 += step) {
                                                            for (int crossbow2 = 0; crossbow2 <= crossbowLimit && crossbow2 <= totalLimit - lands2 - cav2 - sword2 - bow2 - halberd2 - spear2; crossbow2 += step) {
                                                                int knight2 = totalLimit - lands2 - cav2 - sword2 - halberd2 - spear2 - crossbow2;
                                                                if (lands2 + cav2 + sword2 + knight2 + halberd2 + spear2 + crossbow2 + bow2 == 0) continue;
                                                                if (lands2 + cav2 + sword2 + knight2 == 0) continue;

                                                                        Warfare warfare = new Warfare();

                                                                        warfare.attackers = attacker;
                                                                        warfare.defenders = Collections.singletonList(createDefender(lands2, cav2, sword2, knight2, halberd2, spear2, crossbow2, bow2));
                                                                        warfare.town = new Town(0);

                                                                        warfare.happen();

                                                                        if (warfare.victorious == 1) {
                                                                            currentWins++;
                                                                        }




                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        if (currentWins > bestAttackingWins) {
                                            bestAttacking = currentAttacking;
                                            bestAttackingWins = currentWins;
                                            System.out.println("Best attacking now with wins " + bestAttackingWins + " is " + new Gson().toJson(bestAttacking));
                                        }



                                }
                            }
                        }
                    }
                }
            }
        }


    }

    private Player createAttacker(int lands, int cav, int sword, int knight, int halberd, int spear, int crossbow, int bow) {
        return new Player(Collections.singletonList(createArmyAttackers(lands, cav, sword, knight, halberd, spear, crossbow, bow)),
                new Bonus(0, 0, 6, 8, 4),
                new Bonus(0, 0, 13, 5, 0));
    }
    private Player createDefender(int lands2, int cav2, int sword2, int knight2, int halberd2, int spear2, int crossbow2, int bow2) {
        return new Player(Collections.singletonList(createArmyDefenders(lands2, cav2, sword2, knight2, halberd2, spear2, crossbow2, bow2)),
                new Bonus(0, 0, 6, 8, 4),
                new Bonus(0, 0, 13, 5, 0));
    }

    private Army createArmyAttackers(int lands, int cav, int sword, int knight, int halberd, int spear, int crossbow, int bow) {   // атака
        HashMap<Troop, Integer> troops = new HashMap<>();
        troops.put(Troop.LANDS, lands);
        troops.put(Troop.CAVALRY, cav);
        troops.put(Troop.SWORDSMAN, sword);
        troops.put(Troop.HALBERD, halberd);
        troops.put(Troop.SPEARMAN, spear);
        troops.put(Troop.KNIGHT, knight);
        troops.put(Troop.CROSSBOWMAN, crossbow);
        troops.put(Troop.BOWMAN, bow);
        //troops.put(Troop.RAM, 400);
        return new Army(troops);
    }
    private Army createArmyDefenders(int lands2, int cav2, int sword2, int knight2, int halberd2, int spear2, int crossbow2, int bow2) {  // защита
        HashMap<Troop, Integer> troops = new HashMap<>();
        troops.put(Troop.LANDS, lands2);
        troops.put(Troop.CAVALRY, cav2);
        troops.put(Troop.SWORDSMAN, sword2);
        troops.put(Troop.HALBERD, halberd2);
        troops.put(Troop.SPEARMAN, spear2);
        troops.put(Troop.KNIGHT, knight2);
        troops.put(Troop.CROSSBOWMAN, crossbow2);
        troops.put(Troop.BOWMAN, bow2);
        return new Army(troops);
    }


}
