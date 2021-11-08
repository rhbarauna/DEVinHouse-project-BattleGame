package com.rhbarauna;

import com.rhbarauna.enums.GameLevel;
import com.rhbarauna.exception.AttackerMissesException;
import com.rhbarauna.exception.EndGameException;
import com.rhbarauna.model.Character;

import static com.rhbarauna.utils.BattleUtils.getDiceValue;
import static com.rhbarauna.utils.BattleUtils.printEnemyAttackMessage;

public class MonsterTurn extends BattleTurn {

    public MonsterTurn(Character attacker, Character defender, GameLevel gameLevel) {
        super(attacker, defender, gameLevel);
    }

    @Override
    protected float getLevelMultiplier() {
        return gameLevel.getMonsterMultiplier();
    }
    @Override
    protected void printAttackMessage(float inflictedDamage) {
        printEnemyAttackMessage(diceValue, inflictedDamage, defender.getLifeGauge());
    }

    @Override
    public void execute() throws EndGameException, AttackerMissesException {
        super.execute();
    }
}
