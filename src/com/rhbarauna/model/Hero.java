package com.rhbarauna.model;

import com.rhbarauna.enums.Armor;
import com.rhbarauna.enums.Gender;
import com.rhbarauna.enums.HeroClass;
import com.rhbarauna.enums.Weapon;

public class Hero extends Character {
    private final Gender gender;
    private Float maxLife;
    private final HeroClass heroClass;

    public Hero(String name, Gender gender, HeroClass heroClass, Weapon weapon, Armor armor) {
        super(name, heroClass.getStrength(), 1, 100F, weapon);
        this.maxLife = lifeGauge;
        this.gender = gender;
        this.setArmor(armor);
        this.heroClass = heroClass;
    }

    public Gender getGender() {
        return gender;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public void resetLifeGauge(){
        this.setLifeGauge(maxLife);
    }
}
