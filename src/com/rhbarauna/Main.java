package com.rhbarauna;

import com.rhbarauna.model.Game;
import com.rhbarauna.model.Hero;
import com.rhbarauna.utils.ConsoleUtils;
import com.rhbarauna.utils.HeroUtils;

import static com.rhbarauna.utils.ConsoleUtils.print;

public class Main {

    public static void main(String[] args) {
        ConsoleUtils.print("Seja bem vindo(a) ao BATALHA FINAL!");


        print("Vamos começar construindo o nosso avatar");
        Hero hero = HeroUtils.buildHero();

//        Game game = new Game(hero);

        print("Olá " + hero.getName() + "!\n Prazer em conhecê-lo.");
//        System.out.println("\nEscolha do nível de dificuldade:");
//        Stream.of(GameLevel.values()).forEach(level -> System.out.println("\t" + level.getId() + " - " + level.getName()));
//
//        while(gameLevel == null){
//            System.out.print("Nível (1, 2, ou 3): ");
//            try {
//                gameLevel = GameLevel.getById(in.nextInt());
//            } catch (Exception e) {
//                System.out.println("Escolha entre 1, 2 ou 3.");
//            }
//        }
//        Hero hero = new Hero(heroName, gender,20, 1, 150F, Weapon.SWORD);
//        game = new Game(hero, gameLevel, in);
//        game.start();
//        in.close();
    }
}
