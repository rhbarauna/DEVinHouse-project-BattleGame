package com.rhbarauna;

import com.rhbarauna.enums.Gender;
import com.rhbarauna.enums.Weapon;

public class Hero extends Character {
    private Gender gender;

    public Hero(String name, Gender gender, int attackPower, int level, Float lifeGauge, Weapon weapon) {
        super(name, attackPower, level, lifeGauge, weapon);
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

}
