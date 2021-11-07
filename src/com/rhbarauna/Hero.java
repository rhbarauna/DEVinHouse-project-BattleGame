package com.rhbarauna;

import com.rhbarauna.enums.Gender;
import com.rhbarauna.enums.Weapon;

public class Hero extends Character {
    private Gender gender;
    private Float maxLife;

    public Hero(String name, Gender gender, int attackPower, int level, Float lifeGauge, Weapon weapon) {
        super(name, attackPower, level, lifeGauge, weapon);
        this.maxLife = lifeGauge;
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void resetLifeGauge(){
        this.setLifeGauge(maxLife);
    }
}
