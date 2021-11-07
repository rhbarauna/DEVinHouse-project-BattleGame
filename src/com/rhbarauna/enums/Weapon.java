package com.rhbarauna.enums;

import java.util.Optional;

public enum Weapon {
    SWORD("Espada", 100F, 0, 20, null),
    AXE("Machado", 100F, 0, 20, null),
    HAMMER("Martelo", 100F, 0, 20, null),
    STAFF("Cajado", 100F, 0, 40, null),
    BOW("Arco", 100F, 0, 40, "flecha"),
    CROSS_BOW("Besta", 100F, 0, 40, "virote");

    private final String name;
    private final Float lifeGauge;
    private final int defense;
    private final int attackPower;
    private final Optional<String> ammo;

    Weapon(String name, Float lifeGauge, int defense, int attack, String ammo) {
        this.name = name;
        this.lifeGauge = lifeGauge;
        this.defense = defense;
        this.attackPower = attack;
        this.ammo = Optional.ofNullable(ammo);
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

    public int getAttackPower() {
        return attackPower;
    }

    public Optional<String> getAmmo() {
        return ammo;
    }

    private boolean hasAmmo(){
        return this.ammo.isPresent();
    }

    private String getPronoun() {
        return switch (this) {
            case AXE, BOW, STAFF, HAMMER -> "seu";
            default -> "sua";
        };
    }

    private String getAmmoPronoun() {
        return switch (this) {
            case BOW -> "a";
            case CROSS_BOW -> "o";
            default -> "";
        };
    }

    public String getAttackDescription() {
        StringBuilder descriptionBuilder = new StringBuilder("com ")
            .append(getPronoun())
            .append(" ")
            .append(getName());



        if(hasAmmo()) {
            descriptionBuilder.append(", ")
                .append(getAmmoPronoun())
                .append(" ")
                .append(getAmmo().get());
        }

        return descriptionBuilder.toString();
    }
}
