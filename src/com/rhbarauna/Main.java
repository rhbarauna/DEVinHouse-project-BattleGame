package com.rhbarauna;

import com.rhbarauna.enums.GameLevel;
import com.rhbarauna.model.Game;
import com.rhbarauna.model.Hero;
import com.rhbarauna.utils.HeroUtils;

import java.util.stream.Stream;

import static com.rhbarauna.utils.ConsoleUtils.print;
import static com.rhbarauna.utils.ConsoleUtils.readInt;

public class Main {

    public static void main(String[] args) {
        print("Seja bem vindo(a) ao BATALHA FINAL!");

        print("Vamos começar construindo o nosso avatar");
        Hero hero = HeroUtils.buildHero();

        print("Olá " + hero.getName() + "!\n Muito prazer em te conhecer.");
        print("Vamos agora configurar o nivel de dificuldade desta partida.");

        GameLevel level = chooseGameLevel();

        print("Ok, vamos de " + level.getName());

        print("Tudo pronto");

        try {
            Game game = new Game(hero, level);
            game.start();
        }
        catch (Exception e) {
            print(e.getMessage());
        }
        finally {
            print("\n FIM DE JOGO \n");
        }
    }

    private static GameLevel chooseGameLevel() {
        print("Estes são os níveis disponíveis:");
        Stream.of(GameLevel.values()).forEach(level -> print(level.getId() + " - " + level.getName()));

        try {
            int response = readInt("Qual você escolhe?");
            return GameLevel.getById(response);
        } catch (Exception e) {
            print("Opção inválida");
            return chooseGameLevel();
        }
    }
}
