package com.rhbarauna.enums;

import com.rhbarauna.model.Monster;

public enum Monsters {
    GUNSMITH("Armeiro", 2, 1, 80F, Weapon.SWORD),
    ALCHEMIST("Alquimista", 4, 2, 90F, Weapon.STAFF),
    BOSS("MASTER", 10, 4, 200F, Weapon.AXE);

    private final String name;
    private final int attackPower;
    private final int level;
    private final Float lifeGauge;
    private final Weapon weapon;

    Monsters(String name, int attackPower, int level, Float lifeGauge, Weapon weapon) {
        this.name = name;
        this.attackPower = attackPower;
        this.level = level;
        this.lifeGauge = lifeGauge;
        this.weapon = weapon;
    }

    public Monster getMonster() {
        return new Monster(this.name, this.attackPower, this.level, this.lifeGauge, this.weapon);
    }
}
