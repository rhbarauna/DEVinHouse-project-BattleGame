package com.rhbarauna;

import com.rhbarauna.enums.Gender;
import com.rhbarauna.enums.GameLevel;
import com.rhbarauna.enums.Weapon;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        String heroName;
        Gender gender = null;
        GameLevel gameLevel = null;
        Game game;

        Scanner in = new Scanner(System.in);
        System.out.println("\n\nSeja bem vindo(a) ao BATALHA FINAL!\n\n" +
                "Me informe qual será o nome do seu avatar: ");
        heroName = in.nextLine();

        System.out.println("\nOlá " + heroName + "!");
        System.out.println("Qual o sexo do seu avatar:");
        Stream.of(Gender.values()).forEach(g -> System.out.println("\t" + g.getId() + " - " + g.getName()));

        while(gender == null) {
            System.out.print("Sexo (1 ou 2): ");
            try {
                gender = Gender.getById(in.nextInt());
            } catch (Exception e) {
                System.out.println("Escolha entre 1 ou 2.");
            }
        }

        System.out.println("\nEscolha do nível de dificuldade:");
        Stream.of(GameLevel.values()).forEach(level -> System.out.println("\t" + level.getId() + " - " + level.getName()));

        while(gameLevel == null){
            System.out.print("Nível (1, 2, ou 3): ");
            try {
                gameLevel = GameLevel.getById(in.nextInt());
            } catch (Exception e) {
                System.out.println("Escolha entre 1, 2 ou 3.");
            }
        }
        Hero hero = new Hero(heroName, gender,20, 1, 150F, Weapon.SWORD);
        game = new Game(hero, gameLevel, in);
        game.start();
        in.close();
    }
}
