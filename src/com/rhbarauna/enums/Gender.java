package com.rhbarauna.enums;

import java.util.stream.Stream;

public enum Gender {
    MALE(1, "Masculino"),
    FEMALE(2, "Feminino");

    private Integer id;
    private String name;

    Gender(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Gender getById(Integer id) {
        return Stream.of(Gender.values())
                .filter(g -> id.equals(g.getId()))
                .findFirst()
                .orElseThrow();
    }

    public Gender getByName(String name) {
        return Stream.of(Gender.values())
                .filter(g -> name.equals(g.name))
                .findFirst()
                .orElseThrow();
    }
}
