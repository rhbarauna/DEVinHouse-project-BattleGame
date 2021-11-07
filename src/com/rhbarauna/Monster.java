package com.rhbarauna;

import com.rhbarauna.enums.Weapon;

public class Monster extends Character {

    public Monster(String name, int attackPower, int level, Float lifeGauge, Weapon weapon) {
        super(name, attackPower, level, lifeGauge, weapon);
    }
}
