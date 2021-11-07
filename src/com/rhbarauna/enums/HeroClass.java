package com.rhbarauna.enums;

import java.util.stream.Stream;

public enum HeroClass {
    WARRIOR(1, "Guerreiro", 100, 30, 80, 30),
    WIZARD(2, "Mago", 30, 80, 30, 80),
    ELF(3, "Elfo", 50, 50, 40, 40);

    private int id;
    private String title;
    private int strength;
    private int magicStrength;

    private int defence;
    private int magicDefense;

    HeroClass(int id, String title, int strength, int magicStrength, int defence, int magicDefense) {
        this.id = id;
        this.title = title;
        this.strength = strength;
        this.magicStrength = magicStrength;
        this.defence = defence;
        this.magicDefense = magicDefense;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getStrength() {
        return strength;
    }

    public int getMagicStrength() {
        return magicStrength;
    }

    public int getDefence() {
        return defence;
    }

    public int getMagicDefense() {
        return magicDefense;
    }

    public static HeroClass getById(Integer id) {
        return Stream.of(HeroClass.values())
                .filter(hc -> id.equals(hc.getId()))
                .findFirst()
                .orElseThrow();
    }

}
