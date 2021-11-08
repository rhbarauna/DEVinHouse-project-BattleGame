package com.rhbarauna.enums;

import java.util.List;

import static java.util.Arrays.asList;

public enum Armor {
    ARMOR("Armadura", 100F, 10),
    MEDIUM_ARMOR("Armadura Média", 100F, 20),
    HEAVY_ARMOR("Armadura Pesada", 100F, 40);

    private final String name;
    private final Float lifeGauge;
    private final int defense;

    Armor(String name, Float lifeGauge, int defense) {
        this.name = name;
        this.lifeGauge = lifeGauge;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public Float getLifeGauge() {
        return lifeGauge;
    }

    public int getDefense() {
        return defense;
    }
}