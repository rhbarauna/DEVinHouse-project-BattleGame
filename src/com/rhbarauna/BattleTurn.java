package com.rhbarauna;

import com.rhbarauna.exception.AttackerMissesException;
import com.rhbarauna.exception.EndGameException;
import com.rhbarauna.model.Character;

import static com.rhbarauna.utils.BattleUtils.getDiceValue;

public abstract class BattleTurn {
    Character attacker;
    Character defender;

    public void execute() throws EndGameException, AttackerMissesException {
        int diceValue = getDiceValue();
        int inflictedDamage = calculateDamage(diceValue);
        defender.takeDamage(inflictedDamage);

        String message = "Você atacou %s e causou %d de dano no inimigo! "+defender.getLifeGauge();

        if(diceValue == 20) {
            message = "Você acertou um ataque crítico! "+message;
        }

        final String weaponDescription = attacker.getWeapon().getAttackDescription();
        System.out.printf("%n"+message, weaponDescription, inflictedDamage);
    }

    protected int calculateDamage(int diceValue) {
        int inflictedDamage = attacker.getAttackPower() + diceValue;

        if (diceValue < 20) inflictedDamage -= defender.getDefense();

        return inflictedDamage;
    }
}
