package wof.warfare;

import wof.warfare.abilities.*;

public class AttackUtil {
    public static final int DMG_UNIT_LIMIT = 4000;
    public static final int ARMOUR_LIMIT = 75;

    public static double processAttack(Formation source, Formation target, Formation randomTarget, boolean dungeon) {
        double unitAttack = calculateUnitAttack(source, target, dungeon);
        double formationDamage = calculateFormationDamage(source, target, unitAttack, dungeon);
        double targetArmour = Math.min(ARMOUR_LIMIT, calculateTargetArmour(source, target));
        long damageDealt = calculateDamageDealt(source, target, formationDamage, targetArmour);
        //System.out.println("ATTACK: " + damageDealt + " " + source + " to " + target);
        if (source.unit == Troop.BOWMAN && randomTarget != null) {
            for (Ability ability : source.unit.abilities) {
                if (ability instanceof ZalpAbility) {
                    double dmgPart = ((ZalpAbility) ability).damagePart();
                    double randomTargetDamage = formationDamage * dmgPart;
                    double randomTargetArmour = calculateTargetArmour(source, randomTarget);
                    long randomDamageDealt = calculateDamageDealt(source, randomTarget, randomTargetDamage, randomTargetArmour);
                    //System.out.println("ATTACK: " + randomDamageDealt + " " + source + " to " + randomTarget);
                }
            }
        }
        return damageDealt;

    }


    private static double calculateUnitAttack(Formation source, Formation target, boolean dungeon) {
        double attack = source.attack;
        for (Ability ability : source.unit.abilities) {
            if (ability instanceof AttackModifier) {
                attack = ((AttackModifier) ability).modifyAttack(source, target, attack,dungeon);
            }
        }
        return attack;
    }

    private static double calculateFormationDamage(Formation source, Formation target, double unitAttack, boolean dungeon) {
        return unitAttack * Math.min(DMG_UNIT_LIMIT, source.quantity - source.perished);
    }

    private static double calculateTargetArmour(Formation source, Formation target) {
        double armour = target.armour;
        for (Ability ability : target.unit.abilities) {
            if (ability instanceof ArmourModifier) {
                armour = ((ArmourModifier) ability).modifyArmour(armour);
            }
        }

        for (Ability ability : source.unit.abilities) {
            if (ability instanceof TargetArmourModifier) {
                armour = ((TargetArmourModifier) ability).modifyTargetArmour(source, target, armour);
            }
        }

        return armour;
    }

    private static long calculateDamageDealt(Formation source, Formation target, double formationDamage, double targetArmour) {
        double damageDealt = formationDamage * (1 - targetArmour / 100);
        for (Ability ability : target.unit.abilities) {
            if (ability instanceof ReceivedDamageModifier) {
                damageDealt = ((ReceivedDamageModifier) ability).modifyReceivedDamage(source, target, damageDealt);
            }
        }
        long d = Math.round(damageDealt);
        source.dmgDealt += d;
        target.dmgReceived += d;

        return d;
    }

    public static int calculateLosses(Formation target) {
        if (target.dmgReceived == 0) {
            return 0;
        }
        long unitslost = 0;
        int targetUnits = target.quantity - target.perished;
        if (targetUnits <= 0 ) {
            return 0;
        }

        int unitHp = target.unit.hitpoints;
        for (Ability ability : target.unit.abilities) {
            if (ability instanceof HpModifier) {
                unitHp = ((HpModifier) ability).modifyHp(target, unitHp);
            }
        }
        if (target.hpLeft == 0) {
            target.hpLeft = unitHp;
        }
        if (target.dmgReceived >= target.hpLeft) {
            long d1 = target.dmgReceived - target.hpLeft;
            unitslost = Math.min(targetUnits, d1 / unitHp + 1);
            target.perished += unitslost;
            if (target.perished >= target.quantity) {
                target.hpLeft = 0;
            } else {
                target.hpLeft = (int) (unitHp - (d1 % unitHp));
            }
        } else {
            target.hpLeft -= target.dmgReceived;
        }
        target.dmgReceived = 0;
        return (int) unitslost;

    }
}
