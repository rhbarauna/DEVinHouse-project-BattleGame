package com.rhbarauna;

import com.rhbarauna.exception.AttackerMissesException;
import com.rhbarauna.exception.EndGameException;

import static com.rhbarauna.utils.BattleUtils.getDiceValue;

public class MonsterTurn extends BattleTurn {

    @Override
    public void execute() throws EndGameException, AttackerMissesException {
        int diceValue = getDiceValue();
        int inflictedDamage = calculateDamage(diceValue);
        defender.takeDamage(inflictedDamage);

        String message = "%nO inimigo atacou! Você sofreu %.2f de dano e agora possui %.2f pontos de vida.%n";

        if(diceValue == 20) {
            message = "%nO inimigo acertou um ataque crítico! Você sofreu %d de dano e agora possui %d pontos de vida.%n";
        }
        System.out.printf(message, inflictedDamage, defender.getLifeGauge());
        super.execute();
    }
}
