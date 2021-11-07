package com.rhbarauna.enums;

import java.util.Arrays;
import java.util.List;

public enum Weapon {
    SWORD("Espada", 100F, 0, 20),
    AXE("Machado", 100F, 0, 20),
    HAMMER("Martelo", 100F, 0, 20),
    CLUB("Clava", 100F, 0, 20),
    STAFF("Cajado", 100F, 0, 20),
    BOOK("Livro", 100F, 0, 20),
    BOW("Arco", 100F, 0, 20),
    CROSS_BOW("Besta", 100F, 0, 20);

    private final String name;
    private final Float lifeGauge;
    private final int defense;
    private final int attackPower;

    Weapon(String name, Float lifeGauge, int defense, int attack) {
        this.name = name;
        this.lifeGauge = lifeGauge;
        this.defense = defense;
        this.attackPower = attack;
    }

    private static final List<Weapon> withAmmo = Arrays.asList(BOW,CROSS_BOW);

    public String getName() {
        return name;
    }

    public int getAttackPower() {
        return attackPower;
    }

    private boolean hasAmmo(){
        return withAmmo.contains(this);
    }

    private String getPronoun() {
        return switch (this) {
            case AXE, BOW, STAFF, HAMMER -> " seu ";
            case SWORD, CLUB, CROSS_BOW -> " sua ";
            case BOOK -> " ";
        };
    }

    private String getAmmoAttack() {
        return switch (this) {
            case BOW -> " a flecha";
            case CROSS_BOW -> " o virote";
            default -> " ";
        };
    }

    public String getAttackDescription() {
        StringBuilder descriptionBuilder = new StringBuilder("");
        switch(this){
            case BOOK -> {
                descriptionBuilder.append("absorvendo energia do livro com uma mão e liberando com a outra");
            }
            case STAFF -> {
                descriptionBuilder.append("com seu cajado, lançando uma bola de fogo");
            }
            case AXE,HAMMER,CLUB,BOW,CROSS_BOW, SWORD -> {
                descriptionBuilder
                    .append("com")
                    .append(getPronoun())
                    .append(getName());

                if(hasAmmo()) {
                    descriptionBuilder
                        .append(",")
                        .append(getAmmoAttack())
                        .append("atingiu");
                }
            }
        }

        return descriptionBuilder.toString();
    }
}
