package com.rhbarauna.model;

import com.rhbarauna.enums.Armor;
import com.rhbarauna.enums.Weapon;

public class Monster extends Character {

    public Monster(String name, int attackPower, int level, Float lifeGauge, Weapon weapon) {
        super(name, attackPower, level, lifeGauge, weapon);
        setArmor(Armor.ARMOR);
    }
}
