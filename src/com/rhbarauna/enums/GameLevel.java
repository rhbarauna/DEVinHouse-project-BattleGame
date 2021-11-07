package com.rhbarauna.enums;

import java.util.stream.Stream;

public enum GameLevel {
    EASY(1, "Fácil", 1F, 0.2F),
    Normal(2, "Normal", 1F, 1F),
    HARD(3, "Difícil", 0.1F, 1F);

    private int id;
    private String name;
    private float heroMultiplier;
    private float monsterMultiplier;

    GameLevel(int id, String name, float heroMultiplier, float monsterMultiplier) {
        this.id = id;
        this.name = name;
        this.heroMultiplier = heroMultiplier;
        this.monsterMultiplier = monsterMultiplier;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getHeroMultiplier() {
        return heroMultiplier;
    }

    public float getMonsterMultiplier() {
        return monsterMultiplier;
    }

    public static GameLevel getById(Integer id) {
        return Stream.of(GameLevel.values())
            .filter(gameLevel -> id.equals(gameLevel.id))
            .findFirst()
            .orElseThrow();
    }
}
