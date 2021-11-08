package com.rhbarauna.utils;

import com.rhbarauna.enums.Armor;
import com.rhbarauna.enums.Gender;
import com.rhbarauna.enums.HeroClass;
import com.rhbarauna.enums.Weapon;
import com.rhbarauna.model.Hero;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static com.rhbarauna.utils.ConsoleUtils.*;
import static com.rhbarauna.utils.ConsoleUtils.print;

public class HeroUtils {

    private HeroUtils(){}

    public static List<Weapon> getAvailableWeaponsFor(HeroClass heroClass) {
        return switch(heroClass) {
            case WARRIOR -> Arrays.asList(Weapon.AXE, Weapon.CLUB, Weapon.HAMMER, Weapon.SWORD);
            case WIZARD -> Arrays.asList(Weapon.BOOK, Weapon.STAFF, Weapon.SWORD);
            case ELF -> Arrays.asList(Weapon.BOOK, Weapon.STAFF, Weapon.BOW, Weapon.CROSS_BOW);
        };
    }

    public static List<Armor> getAvailableArmorsFor(HeroClass heroClass) {
        return switch(heroClass) {
            case WARRIOR -> Arrays.asList(Armor.MEDIUM_ARMOR, Armor.HEAVY_ARMOR);
            case WIZARD -> Arrays.asList(Armor.ARMOR, Armor.MEDIUM_ARMOR, Armor.HEAVY_ARMOR);
            case ELF -> Arrays.asList(Armor.ARMOR, Armor.MEDIUM_ARMOR);
        };
    }

    public static Hero buildHero() {
        HeroBuilder builder = new HeroBuilder();
        Hero hero = builder.buildHero();

        print("Estes são os dados do personagem:");
        print("Nome:\t\t" + hero.getName());
        print("Sexo:\t\t" + hero.getGender().getName());
        print("Classe:\t\t" + hero.getHeroClass().getTitle());
        print("Arma:\t\t" + hero.getWeapon().getName());
        print("Armadura:\t" + hero.getArmor().get().getName());
        int response = readInt("1 - Confirmar \t2 - Refazer");

        if(response == 2) {
            print("OK, vamos refazer o nosso personagem");
            return buildHero();
        }

        return hero;
    }

    private static class HeroBuilder {
        public Hero buildHero() {
            String heroName = readText("Primeiramente, me informe qual será o nome?");

            print("Ótimo!");
            print("Agora me informe, qual o sexo deste personagem?");

            Gender heroGender = requestHeroGender();

            print("Perfeito!");
            print("Só mais algumas perguntas e já vamos para a batalha");
            print("Me informe, qual será a classe deste personagem?");

            HeroClass heroClass = requestHeroClass();

            print("Incrível! Ótima escolha");
            print("Agora vamos só escolher nossos equipamentos de ataque e defesa e estaremos prontos para iniciar as batalhas");

            print("Me informe, com qual arma vc gostaria de lutar?");
            Weapon weapon = chooseWeaponFor(heroClass);

            print("Escolha agora a sua armadura");
            Armor armor = chooseArmorFor(heroClass);

            return new Hero(heroName, heroGender, heroClass, weapon, armor);
        }

        private Gender requestHeroGender() {
            StringBuilder messageBuilder = new StringBuilder();
            Stream.of(Gender.values()).forEach(g -> messageBuilder.append(g.getId() + " - " + g.getName() + "\n"));

            int response = readInt(messageBuilder.toString());

            try {
                return Gender.getById(response);
            } catch(Exception e) {
                print("Opção inválida");
                return requestHeroGender();
            }
        }

        private HeroClass requestHeroClass() {
            StringBuilder messageBuilder = new StringBuilder();
            Stream.of(HeroClass.values()).forEach(hc -> messageBuilder.append(hc.getId() + " - " + hc.getTitle() + "\n"));

            int response = readInt(messageBuilder.toString());

            try {
                return HeroClass.getById(response);
            } catch(Exception e) {
                print("Opção inválida");
                return requestHeroClass();
            }
        }

        private Weapon chooseWeaponFor(HeroClass heroClass) {
            try {
                StringBuilder messageBuilder = new StringBuilder();
                List<Weapon> availableWeapons = getAvailableWeaponsFor(heroClass);

                HashMap<Integer, Weapon> collect = availableWeapons
                        .stream()
                        .collect(HashMap::new, (map, streamValue) -> map.put(map.size(), streamValue), (map, map2) -> {});

                collect.forEach(
                        (index, availableWeapon) -> {
                            messageBuilder.append(index + 1)
                                    .append(" - ")
                                    .append(availableWeapon.getName())
                                    .append("\n");
                        }
                );

                int response = readInt(messageBuilder.toString());

                return collect.get(response - 1);

            } catch(Exception e) {
                print("Opção inválida");
                return chooseWeaponFor(heroClass);
            }
        }

        private Armor chooseArmorFor(HeroClass heroClass) {
            try {
                StringBuilder messageBuilder = new StringBuilder();
                List<Armor> availableArmors = getAvailableArmorsFor(heroClass);

                HashMap<Integer, Armor> collect = availableArmors
                        .stream()
                        .collect(HashMap::new, (map, streamValue) -> map.put(map.size(), streamValue), (map, map2) -> {});

                collect.forEach(
                        (index, availableArmor) -> {
                            messageBuilder.append(index+1)
                                    .append(" - ")
                                    .append(availableArmor.getName())
                                    .append("\n");
                        }
                );

                int response = readInt(messageBuilder.toString());

                return collect.get(response - 1);

            } catch(Exception e) {
                print("Opção inválida");
                return chooseArmorFor(heroClass);
            }
        }
    }
}
