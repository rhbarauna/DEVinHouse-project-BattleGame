package com.rhbarauna.model;

import com.rhbarauna.Battle;
import com.rhbarauna.enums.*;
import com.rhbarauna.exception.EndGameException;
import com.rhbarauna.exception.GameMotiveNotFoundException;
import com.rhbarauna.exception.HeroDefeatedException;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.rhbarauna.utils.ConsoleUtils.print;
import static com.rhbarauna.utils.ConsoleUtils.readInt;
import static com.rhbarauna.utils.ConsoleUtils.readText;
import static com.rhbarauna.utils.HeroUtils.getAvailableWeaponsFor;
import static com.rhbarauna.utils.HeroUtils.getAvailableArmorsFor;

public class Game {
    private GameLevel gameLevel;
    private Hero hero;
    private Scanner in;
    private GameMotive motive;

    private String door;

    public Game(){}

    public Game(Hero hero, GameLevel gameLevel, Scanner in) {
        this.hero = hero;
        this.gameLevel = gameLevel;
        this.in = in;
    }



    public void start() {

        System.out.println("\nA noite se aproxima, a lua já surge no céu, estrelas vão se acendendo, " +
                "e sob a luz do crepúsculo você está prestes a entrar na fase final da sua missão. " +
                "Você olha para o portal à sua frente, e sabe que a partir desse ponto, sua vida mudará para sempre.\n" +
                "Memórias do caminho percorrido para chegar até aqui invadem sua mente. Você se lembra de todos " +
                "os inimigos já derrotados para alcançar o covil do líder maligno. " +
                "Olha para seu equipamento de combate, já danificado e desgastado depois de tantas lutas. " +
                "Você está a um passo de encerrar para sempre esse mal.\n" +
                "Buscando uma injeção de ânimo, você se força a lembrar o que te trouxe até aqui.");

        revengeOrGlory();

        System.out.println("\nInspirado pelo motivo que te trouxe até aqui, você sente seu coração ardendo em chamas," +
                "suas mãos formigarem em volta da sua arma. Você a segura com firmeza. Seu foco está renovado." +
                "Você avança pelo portal.\n" +
                "A escuridão te envolve. Uma iluminação muito fraca entra pelo portal às suas costas. " +
                "À sua frente, só é possível perceber que você se encontra em um corredor extenso. " +
                "Você só pode ir à frente, ou desistir.");

        try {
            continueGameOrGiveUp();

            System.out.println("\nVocê se pergunta se dentro dessa sala pode haver inimigos, ou alguma armadilha, " +
                    "e pondera sobre como passar pela porta.");

            runJumpOrWalk();

            System.out.println("\nVocê se encontra sozinho em uma sala quadrada, contendo uma porta em cada parede. " +
                    "Uma delas foi aquela pela qual você entrou, que estava aberta, e as outras três estão fechadas. " +
                    "A porta à sua frente é a maior das quatro, com inscrições em seu entorno em uma língua que você " +
                    "não sabe ler, mas reconhece como sendo a língua antiga utilizada pelo inimigo. Você se aproxima da " +
                    "porta e percebe que ela está trancada por duas fechaduras douradas, e você entende que precisará " +
                    "primeiro derrotar o que estiver nas outras duas portas laterais, antes de conseguir enfrentar o líder.\n" +
                    "Você se dirige para a porta à direita.");

            startRightDoorLoop();

            System.out.println("\nApós derrotar o Armeiro, você percebe que seus equipamentos estão muito danificados," +
                    " e olha em volta, encarando todas aquelas peças de armaduras resistentes e em ótimo estado.\n");

            changeEquipments();

            System.out.println("Em uma mesa, você encontra uma chave dourada, e sabe que aquela chave abre uma das " +
                    "fechaduras da porta do líder inimigo. Você pega a chave e guarda numa pequena bolsa que leva " +
                    "presa ao cinto.");

            startLeftDoorLoop();

            System.out.println("Após derrotar o Alquimista, você olha em volta, tentando reconhecer alguma poção do " +
                    "estoque do inimigo. Em uma mesa, você reconhece uma pequena garrafa de vidro contendo um " +
                    "líquido levemente rosado, pega a garrafa e pondera se deve beber um gole.");

            takeElixir();

            System.out.println("Ao lado da porta, você vê uma chave dourada em cima de uma mesa, e sabe que aquela " +
                    "chave abre a outra fechadura da porta do líder inimigo. Você pega a chave e guarda na pequena " +
                    "bolsa que leva presa ao cinto.\n" +
                    "De volta à sala das portas, você se dirige à porta final. Coloca as chaves nas fechaduras, e a " +
                    "porta se abre. Seu coração acelera, memórias de toda a sua vida passam pela sua mente, e você " +
                    "percebe que está muito próximo do seu objetivo final. Segura sua arma com mais firmeza, foca no " +
                    "combate que você sabe que irá se seguir, e adentra a porta.");

            startMasterDoorLoop();

            System.out.println("!!!!!!!!!!!!!!!!!! VOCÊ CONSEGUIU !!!!!!!!!!!!!!!!!!");

            printMotiveVictoryMessage();

            System.out.println("Você se levanta, olha para os prisioneiros, vai de um em um e os liberta, e todos " +
                    "vocês saem em direção à noite, retornando à cidade. Seu dever está cumprido.\n");

        }
        catch (HeroDefeatedException ex) {
            System.out.println("Você não estava preparado para a força do inimigo. " + motive.getDefeatMessageFor(hero.getGender()));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("\n\n FIM DE JOGO \n\n");
        }
    }

    public void printMotiveDescription() {
        switch (motive) {
            case REVENGE ->System.out.println("\nImagens daquela noite trágica invadem sua mente. Você nem precisa se esforçar para lembrar, pois essas memórias estão sempre presentes, mesmo que de pano de fundo, quando você tem outros pensamentos em foco, elas nunca o deixaram. Elas são o combustível que te fizeram chegar até aqui. E você sabe que não irá desistir até ter vingado a morte daqueles que foram - e pra sempre serão - sua fonte de amor e desejo de continuar vivo. O maldito líder finalmente pagará por tanto mal causado na vida de tantos (e principalmente na sua).");
            case GLORY -> System.out.println("\nImagens daquela noite trágica invadem sua mente. Você nem precisa se esforçar para lembrar, pois essas memórias estão sempre presentes, mesmo que de pano de fundo, quando você tem outros pensamentos em foco, elas nunca o deixaram. Elas são o combustível que te fizeram chegar até aqui. E você sabe que não irá desistir até ter vingado a morte daqueles que foram - e pra sempre serão - sua fonte de amor e desejo de continuar vivo. O maldito líder finalmente pagará por tanto mal causado na vida de tantos (e principalmente na sua).");
        }
    }

    public void printMotiveVictoryMessage() {
        switch (motive) {
            case REVENGE ->System.out.println("\nVocê obteve sua vingança. Você se ajoelha e cai no choro, invadido por uma sensação de alívio e felicidade. Você se vingou, tudo que sempre quis, está feito. Agora você pode seguir sua vida.");
            case GLORY -> System.out.println("\nO êxtase em que você se encontra não cabe dentro de si. Você se ajoelha e grita de alegria. A glória o aguarda, você a conquistou.");
        }
    }

    private void revengeOrGlory() {
        while(motive == null) {
            System.out.println("Escolha sua motivação para invadir a caverna do inimigo e derrotá-lo: VINGANÇA (1) ou GLÓRIA (2) ");
            try{
                motive = GameMotive.getById(in.nextInt());
                printMotiveDescription();
            } catch (GameMotiveNotFoundException ex) {
                System.out.println(ex.getMessage());
            };
        }
    }

    private void continueGameOrGiveUp() throws EndGameException {
        System.out.println("SEGUIR (1) ou DESISTIR (2)? ");
        final int choice = in.nextInt();

        switch (choice) {
            case 1 -> System.out.println("\nVocê caminha, atento a todos os seus sentidos, por vários metros, até visualizar a " +
                    "frente uma fonte de luz, que você imagina ser a chama de uma tocha, vindo de dentro de uma " +
                    "porta aberta.");
            case 2 -> throw new EndGameException("O medo invade o seu coração e você sente que ainda não está à altura do desafio." +
                    "Você se volta para a noite lá fora e corre em direção à segurança.");
        }
    }

    private void runJumpOrWalk() {
        System.out.println("CORRENDO (1), SALTANDO (2) ou ANDANDO (3)? ");
        final int choice = in.nextInt();

        switch (choice) {
            case 1 -> System.out.println("\nCORRENDO\n Você respira fundo e desata a correr em direção à sala. " +
                    "Quando passa pela porta, sente que pisou em uma pedra solta, mas não dá muita importância " +
                    "e segue para dentro da sala, olhando ao redor à procura de inimigos. Não tem ninguém, " +
                    "mas você ouve sons de flechas batendo na pedra atrás de você, e quando se vira, " +
                    "vê várias flechas no chão. Espiando pela porta, você entende que pisou em uma armadilha " +
                    "que soltou flechas de uma escotilha aberta no teto, mas por sorte você entrou correndo e " +
                    "conseguiu escapar desse ataque surpresa.");
            case 2 -> System.out.println("\nSANTANDO\n Você se concentra e pula em direção à luz, saltando de antes " +
                    "da porta até o interior da sala.");
            case 3 -> System.out.println("\nANDANDO\n Você toma cuidado e vai caminhando vagarosamente em direção à luz. " +
                    "Quando você pisa exatamente embaixo da porta, você sente o chão ceder levemente, como se " +
                    "tivesse pisado em uma pedra solta. Você ouve um ruído de mecanismos se movimentando, " +
                    "e uma escotilha se abre no teto atrás de você, no corredor. Flechas voam da escotilha em " +
                    "sua direção, e você salta para dentro da sala, porém uma delas te acerta na perna.");
            //TODO - O jogo deve acrescentar +5 pontos de defesa para o jogador.\n")
        }
    }

    private void startRightDoorLoop() throws EndGameException, HeroDefeatedException, InterruptedException {
        System.out.println("Você se aproxima, tentando ouvir o que acontece porta adentro, mas não escuta nada. " +
                "Segura com mais força sua arma com uma mão, enquanto empurra a porta com a outra. Ao entrar, " +
                "você se depara com uma sala espaçosa, com vários equipamentos de batalha pendurados nas paredes " +
                "e dispostos em armários e mesas. Você imagina que este seja o arsenal do inimigo, onde estão " +
                "guardados os equipamentos que seus soldados utilizam quando saem para espalhar o terror nas " +
                "cidades e vilas da região.\n Enquanto seu olhar percorre a sala, você ouve a porta se fechando e " +
                "gira rapidamente para olhar para trás. Ali, de pé entre você e a porta fechada, bloqueando o " +
                "caminho do seu destino, está um dos capitães do inimigo. Um orque horrendo, de armadura, " +
                "capacete e espada em punho, em posição de combate. Ele avança em sua direção.");

        Monster orck = new Monster("Orck Armeiro", 2, 1, 80F, Weapon.SWORD);
        orck.setArmor(Armor.ARMOR);

        Battle b1 = new Battle(hero, orck, gameLevel);
        b1.startBattle();

    }

    private void startLeftDoorLoop() throws EndGameException, HeroDefeatedException, InterruptedException {
        System.out.println("Você retorna à sala anterior e se dirige à porta da esquerda. Você se aproxima, " +
                "tentando ouvir o que acontece porta adentro, mas não escuta nada. Segura com mais força sua arma " +
                "com uma mão, enquanto empurra a porta com a outra. Ao entrar, você se depara com uma sala parecida " +
                "com a do arsenal, mas em vez de armaduras, existem vários potes e garrafas de vidro com conteúdos " +
                "misteriosos e de cores diversas, e você entende que o capitão que vive ali, realiza experimentos " +
                "com diversos ingredientes, criando poções utilizadas pelos soldados para aterrorizar a região.\n" +
                "No fundo da sala, olhando em sua direção, está outro dos capitães do inimigo. Um orque horrendo, " +
                "de armadura, cajado em punho, em posição de combate. Ele avança em sua direção.");

        Monster orck = new Monster("Orck Alquimista", 4, 2, 90F, Weapon.STAFF);
        orck.setArmor(Armor.ARMOR);

        Battle b2 = new Battle(hero, orck, gameLevel);
        b2.startBattle();
    }

    private void startMasterDoorLoop() throws EndGameException, InterruptedException, HeroDefeatedException {
        System.out.println("Lá dentro, você vê o líder sentado em uma poltrona dourada, com duas fogueiras de cada lado, e " +
                "prisioneiros acorrentados às paredes.\n" +
                "\tEle percebe sua chegada e se levanta com um salto, apanhando seu machado de guerra de lâmina dupla.");

        boolean wait = true;

        while(wait) {
            System.out.println("Deseja iniciar a batalha?\n1 - Só se for agora\n2 - Vou esperar um pouco.");
            final int response = in.nextInt();
            wait = response == 2;

            if(wait) Thread.sleep(5000);
        }

        Monster orck = new Monster("Orck MASTER", 10, 4, 200F, Weapon.AXE);
        orck.setArmor(Armor.ARMOR);

        Battle b2 = new Battle(hero, orck, gameLevel);
        b2.startBattle();
    }

    private void changeEquipments() {
        System.out.println("Quer trocar de equipamentos?\n1 - SIM \n" +
                "2 - NÃO");

        int choice = in.nextInt();

        switch(choice) {
            case 1 -> System.out.println("Você resolve usar os equipamentos do inimigo contra ele, e trocar algumas" +
                    " peças suas, que estavam danificadas, pelas peças de armaduras existentes na sala. De armadura" +
                    " nova, você se sente mais protegido para os desafios à sua frente.");
            //TODO - O jogo deve acrescentar +5 pontos de defesa para o jogador.\n")
            case 2 -> System.out.println("Você decide que não precisa utilizar nada que venha das mãos do inimigo.");
        }
    }

    private void takeElixir(){
        System.out.println("Quer beber o elixir?\n" +
                "1 - SIM \n" +
                "2 - NÃO");

        int choice = in.nextInt();

        switch(choice) {
            case 1 -> {
                hero.resetLifeGauge();
                System.out.println("Você se sente revigorado para seguir adiante!");
            }
            case 2 -> System.out.println("Você fica receoso de beber algo produzido pelo inimigo.");
            default -> {
                System.out.println("Opção inválida");
                takeElixir();
            }
        }
    }

}
