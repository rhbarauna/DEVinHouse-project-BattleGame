package com.rhbarauna;

import com.rhbarauna.enums.GameLevel;
import com.rhbarauna.exception.AttackerMissesException;
import com.rhbarauna.exception.EndGameException;
import com.rhbarauna.exception.HeroDefeatedException;
import com.rhbarauna.model.Character;
import com.rhbarauna.model.Hero;
import com.rhbarauna.model.Monster;

import static com.rhbarauna.utils.ConsoleUtils.print;

public class Battle {
    private final GameLevel gameLevel;
    private Character attacker;
    private Character defender;

    private void switchPlayers() {
        Character pivot = this.attacker;
        attacker = this.defender;
        defender = pivot;
    }

    public Battle(Character attacker, Character defender, GameLevel gameLevel) {
        this.attacker = attacker;
        this.defender = defender;
        this.gameLevel = gameLevel;
    }

    public void run() throws EndGameException, HeroDefeatedException {
        executeTurns();

        if(defender instanceof Hero) {
            throw new HeroDefeatedException();
        }

        print("O inimigo não é páreo para o seu heroísmo, e jaz imóvel aos seus pés.");
    }

    private void executeTurns() throws EndGameException {
        try {
            BattleTurn turn = (attacker instanceof Hero) ?
                    new HeroTurn(attacker,defender, gameLevel) :
                    new MonsterTurn(attacker,defender, gameLevel);
            turn.execute();

            if(defender.getLifeGauge() > 0) {
                switchAndExecute();
            }
        } catch(AttackerMissesException ex) {
            String message = "Você errou seu ataque! O inimigo não sofreu dano algum.";

            if( attacker instanceof Monster) {
                message = "O inimigo errou o ataque! Você não sofreu dano.";
            }

            print(message);

            switchAndExecute();
        }
    }

    private void switchAndExecute() throws EndGameException {
        switchPlayers();
        executeTurns();
    }
}