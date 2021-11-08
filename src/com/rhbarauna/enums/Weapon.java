package com.rhbarauna.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum Weapon {
    SWORD(1, "Espada", 100F, 0, 20),
    AXE(2, "Machado", 100F, 0, 20),
    HAMMER(3, "Martelo", 100F, 0, 20),
    CLUB(4, "Clava", 100F, 0, 20),
    STAFF(5, "Cajado", 100F, 0, 20),
    BOOK(6, "Livro", 100F, 0, 20),
    BOW(7, "Arco", 100F, 0, 20),
    CROSS_BOW(8, "Besta", 100F, 0, 20);

    private int id;
    private final String name;
    private final Float lifeGauge;
    private final int defense;
    private final int attackPower;
    private static final List<Weapon> withAmmo = Arrays.asList(BOW,CROSS_BOW);

    Weapon(int id, String name, Float lifeGauge, int defense, int attack) {
        this.id = id;
        this.name = name;
        this.lifeGauge = lifeGauge;
        this.defense = defense;
        this.attackPower = attack;
    }

    public int getId() {
        return id;
    }

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

    public int getDefense() {
        return defense;
    }

    public String getAttackDescription() {
        StringBuilder descriptionBuilder = new StringBuilder();
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
                        .append(" atingiu");
                }
            }
        }

        return descriptionBuilder.toString();
    }

    public static Weapon getById(int id){
        return Stream.of(Weapon.values())
            .filter(g -> id == g.getId())
            .findFirst()
            .orElseThrow();
    }
}
