package com.rhbarauna;

import com.rhbarauna.exception.AttackerMissesException;
import com.rhbarauna.exception.EndGameException;
import static com.rhbarauna.utils.BattleUtils.getDiceValue;
import static com.rhbarauna.utils.ConsoleUtils.print;
import static com.rhbarauna.utils.ConsoleUtils.readInt;

public class HeroTurn extends BattleTurn {
    public void execute() throws EndGameException, AttackerMissesException {
        int response = readInt("1 - Atacar ou 2 - Fugir");

        if(response == 2) {
            throw new EndGameException("Você não estava preparado para a força do inimigo, e decide fugir para que possa tentar novamente em uma próxima vez.");
        }

        super.execute();
    }

    public String printAttackMessage(int diceValue) {
        String message = "Você atacou %s e causou %.2f de dano no inimigo!";

        if(diceValue == 20) {
            message = "Você acertou um ataque crítico! "+message;
        }

        final String weaponDescription = attacker.getWeapon().getAttackDescription();

//        System.out.printf("%n"+message, weaponDescription, inflictedDamage);
        return "";
    }
}
