package com.rhbarauna;

import com.rhbarauna.enums.GameLevel;
import com.rhbarauna.exception.AttackerMissesException;
import com.rhbarauna.exception.EndGameException;
import com.rhbarauna.model.Character;

import static com.rhbarauna.utils.BattleUtils.getDiceValue;
import static com.rhbarauna.utils.BattleUtils.calculateDamage;

public abstract class BattleTurn {
    Character attacker;
    Character defender;
    GameLevel gameLevel;

    protected int diceValue;

    protected BattleTurn(Character attacker, Character defender, GameLevel gameLevel) {
        this.attacker = attacker;
        this.defender = defender;
        this.gameLevel = gameLevel;
    }

    public void execute() throws EndGameException, AttackerMissesException {
        diceValue = getDiceValue();
        float inflictedDamage = calculateDamage(attacker.getAttackPower(), defender.getDefense(), diceValue) * getLevelMultiplier();
        defender.takeDamage(inflictedDamage);
        printAttackMessage(inflictedDamage);
    }

    protected abstract void printAttackMessage(float inflictedDamage);
    protected abstract float getLevelMultiplier();

}
