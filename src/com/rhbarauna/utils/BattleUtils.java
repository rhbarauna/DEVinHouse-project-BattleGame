package com.rhbarauna.utils;

import com.rhbarauna.exception.AttackerMissesException;

import java.util.Random;

public class BattleUtils {
    private static final Integer DEFAULT_LIMIT = 19;

    private BattleUtils(){}

    public static int getDiceValue() throws AttackerMissesException {
        return getDiceValue(DEFAULT_LIMIT);
    }

    public static int getDiceValue(Integer limit) throws AttackerMissesException {
        final int diceValue = new Random().nextInt(limit) + 1;

        if(diceValue == 1) {
            throw new AttackerMissesException();
        }
        return diceValue;
    }
}
