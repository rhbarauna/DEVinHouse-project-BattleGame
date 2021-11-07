package com.rhbarauna.enums;

public enum HeroClass {
    WARRIOR("Guerreiro", 100, 30, 80, 30),
    WIZARD("Mago", 30, 80, 30, 80),
    ELF("Elfo", 50, 50, 40, 40);

    private String title;
    private int strength;
    private int magicStrength;

    private int defence;
    private int magicDefense;

    HeroClass(String title, int strength, int magicStrength, int defence, int magicDefense) {
        this.title = title;
        this.strength = strength;
        this.magicStrength = magicStrength;
        this.defence = defence;
        this.magicDefense = magicDefense;
    }
}
