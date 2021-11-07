package com.rhbarauna;

import com.rhbarauna.enums.Armor;
import com.rhbarauna.enums.Weapon;

import java.util.Optional;

public abstract class Character {
    protected String name;
    protected int strength;
    protected int defense;
    protected Float lifeGauge;
    protected Optional<Armor> armor;
    protected Weapon weapon;

    public Character(String name, int strength, int defense, Float lifeGauge, Weapon weapon) {
        this.strength = strength;
        this.name = name;
        this.defense = defense;
        this.lifeGauge = lifeGauge;
        this.weapon = weapon;
    }

    public int getAttackPower() {
        int equipmentsTotalAttackPower = weapon.getAttackPower();
        return this.strength + equipmentsTotalAttackPower;
    };
    public void takeDamage(int damage) {
        this.lifeGauge -= damage;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public Float getLifeGauge() {
        return lifeGauge;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setLifeGauge(Float lifeGauge) {
        this.lifeGauge = lifeGauge;
    }

    public Optional<Armor> getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = Optional.ofNullable(armor);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
