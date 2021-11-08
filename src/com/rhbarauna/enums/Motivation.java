package com.rhbarauna.enums;

import com.rhbarauna.exception.GameMotivationNotFoundException;

public enum Motivation {
    REVENGE(1),
    GLORY(2);

    private int id;

    Motivation(int id) {
        this.id = id;
    }

    public static Motivation getById(int id) throws GameMotivationNotFoundException {

        switch (id){
            case 1 -> { return REVENGE; }
            case 2 -> { return GLORY; }
            default -> new GameMotivationNotFoundException("Nenhuma motivação encontrada com o valor " + id);
        }

        return null;
    }

    public String getDefeatMessageFor(Gender gender) {
        if (this.id == REVENGE.id) {
            return "Não foi possível concluir sua vingança, e agora resta saber se alguém se vingará por você.";
        }

        if(gender == Gender.MALE) {
            return "A glória que buscavas não será sua, e a cidade aguarda por seu próximo herói.";
        }

        return "A glória que buscavas não será sua, e a cidade aguarda por sua próxima heroína.";
    }
}
