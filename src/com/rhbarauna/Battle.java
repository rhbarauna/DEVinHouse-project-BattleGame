package com.rhbarauna;

import com.rhbarauna.enums.GameLevel;
import com.rhbarauna.enums.Weapon;
import com.rhbarauna.exception.AttackerMissesException;
import com.rhbarauna.exception.EndGameException;
import com.rhbarauna.exception.HeroDefeatedException;

import java.util.Random;
import java.util.Scanner;

public class Battle {
    private GameLevel gameLevel;
    private Character attacker;
    private Character defender;
    private Scanner scanner;

    private void switchPlayers() {
        Character pivot = this.attacker;
        attacker = this.defender;
        defender = pivot;
    }

    public Battle(Character attacker, Character defender, GameLevel gameLevel) {
        this.attacker = attacker;
        this.defender = defender;
        this.gameLevel = gameLevel;
        this.scanner = new Scanner(System.in);
    }

    public void startBattle() throws EndGameException, HeroDefeatedException, InterruptedException {
        boolean continueBattle = true;
        //TODO - REFACTOR THIS
        while(continueBattle) {
            try {
                if(attacker instanceof Hero) {
                    executeHeroTurn();
                    continue;
                }

                executeMonsterTurn();

                if(defender.lifeGauge < 0) {
                    continueBattle = false;
                    continue;
                }

                switchPlayers();
            } catch(AttackerMissesException ex) {
                String message = "Você errou seu ataque! O inimigo não sofreu dano algum.";

                if( attacker instanceof Monster) {
                    message = "O inimigo errou o ataque! Você não sofreu dano.";
                }

                System.out.println(message);
            }
        }

        if(defender instanceof Hero) {
            throw new HeroDefeatedException();
        }

        System.out.println("O inimigo não é páreo para o seu heroísmo, e jaz imóvel aos seus pés.");
    }

    private void executeMonsterTurn() throws InterruptedException, AttackerMissesException {
        Thread.sleep(1200);
        int diceValue = getDiceValue();
        int inflictedDamage = calculateDamage(diceValue);
        defender.takeDamage(inflictedDamage);

        String message = "%nO inimigo atacou! Você sofreu %d de dano e agora possui %d pontos de vida.";

        if(diceValue == 20) {
            message = "%nO inimigo acertou um ataque crítico! Você sofreu %d de dano e agora possui %d pontos de vida.";
        }
        System.out.printf(message, inflictedDamage, defender.getLifeGauge());
    }

    private void executeHeroTurn() throws InterruptedException, EndGameException, AttackerMissesException {
        Thread.sleep(1200);

        System.out.println("1 - Atacar ou 2 - Fugir");

        if(scanner.nextInt() == 2) {
            throw new EndGameException("Você não estava preparado para a força do inimigo, e decide fugir para que possa tentar novamente em uma próxima vez.");
        }

        int diceValue = getDiceValue();
        int inflictedDamage = calculateDamage(diceValue);
        defender.takeDamage(inflictedDamage);

        String message = "Você atacou com %s e causou %d de dano no inimigo!";

        if(diceValue == 20) {
            message = "Você acertou um ataque crítico! "+message;
        }

        Weapon attackerWeapon = attacker.getWeapon();

        System.out.printf("%n"+message, inflictedDamage);
    }

    private int getDiceValue() throws AttackerMissesException {
        final int diceValue = new Random().nextInt(19) + 1;

        if(diceValue == 1) {
            throw new AttackerMissesException();
        }
        return diceValue;
    }

    private int calculateDamage(int diceValue) {
        int inflictedDamage = attacker.getAttackPower() + diceValue;

        if (diceValue < 20) inflictedDamage -= defender.getDefense();

        return inflictedDamage;
    }
}