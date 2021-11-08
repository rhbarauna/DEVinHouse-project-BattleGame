package com.rhbarauna.utils;

import com.rhbarauna.exception.AttackerMissesException;

import java.util.Random;

public class BattleUtils {
    private static final Integer DEFAULT_LIMIT = 19;
    private static Integer limit = DEFAULT_LIMIT;

    private BattleUtils(){}

    public static int getDiceValue() throws AttackerMissesException {
        return getDiceValue(DEFAULT_LIMIT);
    }

    public static int getDiceValue(Integer l) throws AttackerMissesException {
        limit = l;
        final int diceValue = new Random().nextInt(limit) + 1;

        if(diceValue == 1) {
            throw new AttackerMissesException();
        }
        return diceValue;
    }

    public static int calculateDamage(int diceValue, int defense) {
        return calculateDamage(0, diceValue, defense);
    }

    public static int calculateDamage(int attackPower, int defense, int diceValue) {
        int inflictedDamage = attackPower + diceValue;

        if (diceValue < limit) inflictedDamage -= defense;

        return Math.max(inflictedDamage, 0);
    }

    public static void printEnemyAttackMessage(int diceValue, float inflictedDamage, float defenderLifeGauge){
        String message = "%nO inimigo atacou! Você sofreu %.2f de dano e agora possui %.2f pontos de vida.%n";

        if(diceValue == 20) {
            message = "%nO inimigo acertou um ataque crítico! Você sofreu %.2f de dano e agora possui %.2f pontos de vida.%n";
        }
        System.out.printf(message, inflictedDamage, defenderLifeGauge);
    }
}
