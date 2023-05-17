package wof.warfare;

import java.util.*;
import java.util.stream.Collectors;

public class Battlefield {
    public Player attacker;
    public Player defender;

    public ArrayList<Formation> formations = new ArrayList<>(30);
    public final List<Formation> deadFormations = new ArrayList<>();
    private List<Formation> formationPrioritized = new ArrayList<>(30);
    private final boolean dungeon;
    private int round = 0;


    public void initFormations() {
        for (int i = 0; i < 30; ++i) {
            formations.add(null);
        }
    }
    public Battlefield(List<Player> attackers, List<Player> defenders, boolean dungeon) {
        this.dungeon = dungeon;
        attacker = attackers.get(0); // ToDo
        defender = defenders.get(0); // ToDo
        initFormations();
        int bowmanAttck = 0;
        int crossbowmanAttck = 0;
        int knightAttck = 0;
        int spearmanAttck = 0;
        int halberdAttck = 0;
        int swordsmanAttck = 0;
        int landsAttck = 0;
        int cavalryAttck = 0;
            for (Army army : attacker.armies) {
                bowmanAttck += army.getUnitsQty(Troop.BOWMAN);
                crossbowmanAttck += army.getUnitsQty(Troop.CROSSBOWMAN);
                knightAttck += army.getUnitsQty(Troop.KNIGHT);
                spearmanAttck += army.getUnitsQty(Troop.SPEARMAN);
                halberdAttck += army.getUnitsQty(Troop.HALBERD);
                swordsmanAttck += army.getUnitsQty(Troop.SWORDSMAN);
                landsAttck += army.getUnitsQty(Troop.LANDS);
                cavalryAttck += army.getUnitsQty(Troop.CAVALRY);
            }
        int attackingIndex = 0;
        if (bowmanAttck > 0) {
            formations.set(attackingIndex++, new Formation(true, Troop.BOWMAN, bowmanAttck, attacker.artifact, attacker.skill));
        }
        if (crossbowmanAttck > 0) {
            formations.set(attackingIndex++, new Formation(true, Troop.CROSSBOWMAN, crossbowmanAttck, attacker.artifact, attacker.skill));
        }
        if (knightAttck > 0) {
            formations.set(attackingIndex++, new Formation(true, Troop.KNIGHT, knightAttck, attacker.artifact, attacker.skill));
        }
        if (spearmanAttck > 0) {
            formations.set(attackingIndex++, new Formation(true, Troop.SPEARMAN, spearmanAttck, attacker.artifact, attacker.skill));
        }
        if (halberdAttck > 0) {
            formations.set(attackingIndex++, new Formation(true, Troop.HALBERD, halberdAttck, attacker.artifact, attacker.skill));
        }
        if (swordsmanAttck > 0) {
            formations.set(attackingIndex++, new Formation(true, Troop.SWORDSMAN, swordsmanAttck, attacker.artifact, attacker.skill));
        }
        if (landsAttck > 0) {
            formations.set(attackingIndex++, new Formation(true, Troop.LANDS, landsAttck, attacker.artifact, attacker.skill));
        }
        if (cavalryAttck > 0) {
            formations.set(attackingIndex++, new Formation(true, Troop.CAVALRY, cavalryAttck, attacker.artifact, attacker.skill));
        }
        formationPrioritized.addAll(formations.subList(0, attackingIndex));
        Collections.reverse(formationPrioritized);

        int bowmanDef = 0;
        int crossbowmanDef = 0;
        int knightDef = 0;
        int spearmanDef = 0;
        int halberdDef = 0;
        int swordsmanDef = 0;
        int landsDef = 0;
        int cavalryDef = 0;
            for (Army army : defender.armies) {
                bowmanDef += army.getUnitsQty(Troop.BOWMAN);
                crossbowmanDef += army.getUnitsQty(Troop.CROSSBOWMAN);
                knightDef += army.getUnitsQty(Troop.KNIGHT);
                spearmanDef += army.getUnitsQty(Troop.SPEARMAN);
                halberdDef += army.getUnitsQty(Troop.HALBERD);
                swordsmanDef += army.getUnitsQty(Troop.SWORDSMAN);
                landsDef += army.getUnitsQty(Troop.LANDS);
                cavalryDef += army.getUnitsQty(Troop.CAVALRY);
            }
        int defendingIndex = 29;
        if (bowmanDef > 0) {
            formations.set(defendingIndex--, new Formation(false, Troop.BOWMAN, bowmanDef, defender.artifact, defender.skill));
        }
        if (crossbowmanDef > 0) {
            formations.set(defendingIndex--, new Formation(false, Troop.CROSSBOWMAN, crossbowmanDef, defender.artifact, defender.skill));
        }
        if (knightDef > 0) {
            formations.set(defendingIndex--, new Formation(false, Troop.KNIGHT, knightDef, defender.artifact, defender.skill));
        }
        if (spearmanDef > 0) {
            formations.set(defendingIndex--, new Formation(false, Troop.SPEARMAN, spearmanDef, defender.artifact, defender.skill));
        }
        if (halberdDef > 0) {
            formations.set(defendingIndex--, new Formation(false, Troop.HALBERD, halberdDef, defender.artifact, defender.skill));
        }
        if (swordsmanDef > 0) {
            formations.set(defendingIndex--, new Formation(false, Troop.SWORDSMAN, swordsmanDef, defender.artifact, defender.skill));
        }
        if (landsDef > 0) {
            formations.set(defendingIndex--, new Formation(false, Troop.LANDS, landsDef, defender.artifact, defender.skill));
        }
        if (cavalryDef > 0) {
            formations.set(defendingIndex--, new Formation(false, Troop.CAVALRY, cavalryDef, defender.artifact, defender.skill));
        }

        formationPrioritized.addAll(formations.subList(defendingIndex + 1, formations.size()));
        formationPrioritized = formationPrioritized.stream().filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Formation::getUnitMobility).thenComparingInt(Formation::getUnitOrdinal))
                .collect(Collectors.toList());


    }

    public void turn() {
        for (Formation formation : formationPrioritized) {
            if (deadFormations.contains(formation)) {
                continue;
            }
            int index = formations.indexOf(formation);
            Formation target = findTarget(formations, formation, index);
            if (target != null) {
                formation.nextattack--;
                if (formation.nextattack <= 0) {
                    Formation randomTarget = formation.unit == Troop.BOWMAN ? findRandomTarget(formations, target) : null;
                    AttackUtil.processAttack(formation, target, randomTarget, dungeon);
                    formation.nextattack = formation.unit.atkspeed;
                }
                formation.nextmove = formation.unit.mobility;
                formation.isMoving = false;
            } else {
                formation.nextmove--;
                formation.isMoving = true;
                if (formation.nextmove <= 0) {
                    if (formation.attacking ? moveRightOnce(formations, index) : moveLeftOnce(formations, index)) {
                        formation.nextmove = formation.unit.mobility;
                        formation.nextattack = 0;
                        index = formations.indexOf(formation);
                        target = findTarget(formations, formation, index);
                        if (target != null) {
                            formation.nextattack--;
                            if (formation.nextattack <= 0) {
                                Formation randomTarget = formation.unit == Troop.BOWMAN ? findRandomTarget(formations, target) : null;
                                AttackUtil.processAttack(formation, target, randomTarget, dungeon);
                                formation.nextattack = formation.unit.atkspeed;
                            }
                            formation.nextmove = formation.unit.mobility;
                            formation.isMoving = false;
                        }
                    }
                }
            }

        }
        for (Formation formation : formationPrioritized) {
            if (deadFormations.contains(formation)) {
                continue;
            }
            if (formation.dmgReceived > 0) {
                double losses = AttackUtil.calculateLosses(formation);
                //System.out.println("LOSSES: " + losses + " " + formation);
                if (formation.quantity <= formation.perished) {
                    deadFormations.add(formation);
                    int indexdead = formations.indexOf(formation);
                    formations.set(indexdead, null);
                }
            }
        }
    }

    private Formation findRandomTarget(ArrayList<Formation> formations, Formation target) {
        List<Formation> randomFormations = formations.stream().filter(Objects::nonNull).filter(f -> f != target && f.attacking == target.attacking).collect(Collectors.toList());
        if (randomFormations.isEmpty()) return null;
        Collections.shuffle(randomFormations);
        return randomFormations.iterator().next();
    }

    private Formation findTarget(ArrayList<Formation> formations, Formation formation, int index) {
        if (formation.attacking) {
            for (int i = index + 1; i < formations.size(); ++i) {
                if (formations.get(i) != null && !formations.get(i).attacking && i - index <= formation.unit.atkrange) {
                    return formations.get(i);
                }
            }
        } else {
            for (int i = index - 1; i >= 0; --i) {
                if (formations.get(i) != null && formations.get(i).attacking && index - i <= formation.unit.atkrange) {
                    return formations.get(i);
                }
            }
        }
        return null;
    }

    public void restart() {
        for (int i = 0; i < formations.size(); ++i) {
            if (formations.get(i) == null) {
                continue;
            }
            if (formations.get(i).attacking) {
                formations.get(i).reset();
                moveLeft(formations, i);
            }
        }
        for (int i = formations.size() - 1; i >= 0; --i) {
            if (formations.get(i) == null) {
                continue;
            }
            if (!formations.get(i).attacking) {
                formations.get(i).reset();
                moveRight(formations, i);
            }
        }
        for (Formation formation : formationPrioritized) {
            //formation.hpLeft = formation.hitpoints;
            formation.quantity -= formation.perished;
            formation.perished = 0;
            formation.nextattack = 0;
            formation.nextmove = formation.unit.mobility;
            formation.dmgDealt = 0;
            formation.dmgReceived = 0;
        }
        round++;
    }

    private void moveRight(ArrayList<Formation> formations, int index) {
        for (int i = index + 1; i < formations.size(); ++i) {
            if (formations.get(i) == null) {
                formations.set(i, formations.get(i - 1));
                formations.set(i - 1, null);
            }
        }
    }

    private void moveLeft(ArrayList<Formation> formations, int index) {
        for (int i = index - 1; i >= 0; --i) {
            if (formations.get(i) == null) {
                formations.set(i, formations.get(i + 1));
                formations.set(i + 1, null);
            }
        }
    }

    private boolean moveRightOnce(ArrayList<Formation> formations, int index) {
        if (index < formations.size() - 1 && formations.get(index + 1) == null) {
            //System.out.println("MOVE:" + formations.get(index));
            formations.set(index + 1, formations.get(index));
            formations.set(index, null);
            return true;
        }
        return false;
    }

    private boolean moveLeftOnce(ArrayList<Formation> formations, int index) {
        if (index > 0 && formations.get(index - 1) == null) {
            //System.out.println("MOVE: " + formations.get(index));
            formations.set(index - 1, formations.get(index));
            formations.set(index, null);
            return true;
        }
        return false;
    }

    public int getVictorious() {
        boolean hasAttacking = formations.stream().filter(Objects::nonNull).anyMatch(f -> f.attacking);
        boolean hasDefending = formations.stream().filter(Objects::nonNull).anyMatch(f -> !f.attacking);
        if (hasAttacking && hasDefending) {
            return 0;
        }
        if (hasAttacking) {
            return 1;
        }
        if (hasDefending) {
            return 2;
        }
        return -1;
    }

    public Round getRound() {
        Round round = new Round();
        round.index = this.round;
        round.attacking = formationPrioritized.stream().filter(f -> f.attacking).map(FormationReport::new).collect(Collectors.toList());
        round.defending = formationPrioritized.stream().filter(f -> !f.attacking).map(FormationReport::new).collect(Collectors.toList());
        return round;
    }
}
