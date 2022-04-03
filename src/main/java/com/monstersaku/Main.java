package com.monstersaku;

import com.monstersaku.util.AbsMove;
import com.monstersaku.util.CSVReader;
import com.monstersaku.util.DefaultMove;
import com.monstersaku.util.ElementType;
import com.monstersaku.util.Monster;
import com.monstersaku.util.Move;
import com.monstersaku.util.MoveType;
import com.monstersaku.util.NormalMove;
import com.monstersaku.util.Target;
import com.monstersaku.util.StatusMove;
import com.monstersaku.util.Stats;
import com.monstersaku.util.StatsMove;
import com.monstersaku.util.Player;
import com.monstersaku.util.SpecialMove;

import java.util.Random;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/monsterpool.csv",
            "configs/movepool.csv",
            "configs/element-type-effectivity-chart.csv"));

    public static void main(String[] args) {
        // Creating ArrayList Move and Monster
        ArrayList<AbsMove> arrmove = new ArrayList<AbsMove>();
        ArrayList<Monster> arrmonster = new ArrayList<Monster>();

        // ============================================ TRY READING MONSTER POOL AND
        // MOVE POOL ============================================
        try {
            System.out.printf("Filename: %s\n", "configs/movepool.csv");
            CSVReader reader = new CSVReader(new File(Main.class.getResource("configs/movepool.csv").toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            System.out.println("=========== CONTENT START ===========");
            for (String[] line : lines) {
                // Parsing for move constructor, creating move object and adding to arraylist
                // move
                Integer idmove = Integer.parseInt(line[0]);
                MoveType movetaip = MoveType.valueOf(line[1]);
                String movename = line[2];
                ElementType moveelementType = ElementType.valueOf(line[3]);
                Integer accuracy = Integer.parseInt(line[4]);
                Integer priority = Integer.parseInt(line[5]);
                Integer ammunition = Integer.parseInt(line[6]);
                Target target = Target.valueOf(line[7]);
                if (movetaip.equals(MoveType.NORMAL)) {
                    Double damage = Double.parseDouble(line[8]);
                    NormalMove mov = new NormalMove(idmove, movetaip, movename, moveelementType, accuracy, priority,ammunition, target, damage);
                    arrmove.add(mov);
                    mov.printMove();
                } else if (movetaip.equals(MoveType.SPECIAL)) {
                    Double damage = Double.parseDouble(line[8]);
                    SpecialMove mov = new SpecialMove(idmove, movetaip, movename, moveelementType, accuracy, priority, ammunition,
                            target, damage);
                    arrmove.add(mov);
                    mov.printMove();
                } else if (movetaip.equals(MoveType.STATUS)){
                    if(line[8].equals("BURN") ||line[8].equals("PARALYZE") ||line[8].equals("POISON") ||line[8].equals("SLEEP") ){
                        String condition = line[8];
                        StatsMove mov = new StatsMove(idmove, movetaip, movename, moveelementType, accuracy, priority, ammunition, target, condition);
                        arrmove.add(mov);
                        mov.printMoveCondition();
                    } else{
                        Double effect = Double.parseDouble(line[8]);
                        StatsMove mov = new StatsMove(idmove, movetaip, movename, moveelementType, accuracy, priority, ammunition, target, effect);
                        arrmove.add(mov);
                        mov.printMoveEffect();
                    }
                }
                System.out.println();
            }

            System.out.printf("Filename: %s\n", "configs/monsterpool.csv");
                CSVReader reader1 = new CSVReader(new File(Main.class.getResource("configs/monsterpool.csv").toURI()),
                        ";");
                reader1.setSkipHeader(true);
                List<String[]> lines1 = reader1.read();
                // System.out.println("=========== CONTENT START ===========");

                for (String[] line1 : lines1) {
                    // Parsing for monster constructor, creating monster object and adding to
                    // arraylist monster
                    String stat = line1[3];
                    String[] arrofstats = stat.split(",", 7);
                    ArrayList<Double> stats = new ArrayList<Double>();
                    // Parsing for stats
                    for (String a : arrofstats) {
                        Double d = Double.parseDouble(a);
                        stats.add(d);
                    }
                    Stats<Double> basestats = new Stats<Double>(stats.get(0), stats.get(1), stats.get(2), stats.get(3),
                            stats.get(4), stats.get(5));
                    // Creating element type arraylist
                    ArrayList<ElementType> eltype = new ArrayList<ElementType>();
                    String eltaip = line1[2];
                    String[] arrofeltaip = eltaip.split(",", 7);
                    for (String a : arrofeltaip) {
                        if (a.equals("FIRE")) {
                            eltype.add(ElementType.FIRE);
                        }
                        if (a.equals("NORMAL")) {
                            eltype.add(ElementType.NORMAL);
                        }
                        if (a.equals("GRASS")) {
                            eltype.add(ElementType.GRASS);
                        }
                        if (a.equals("WATER")) {
                            eltype.add(ElementType.WATER);
                        }
                    }
                    String mov = line1[4];
                    String[] arrofmov = mov.split(",", 7);
                    // Adding move object to listofmove monster
                    ArrayList<AbsMove> monsmove = new ArrayList<AbsMove>();
                    DefaultMove defmove = new DefaultMove();
                    monsmove.add(defmove);
                    for (int i = 0; i < arrofmov.length; i++) {
                        monsmove.add(arrmove.get(Integer.valueOf(arrofmov[i]) - 1));
                    }
                    Integer idmons = Integer.parseInt(line1[0]);
                    Monster mons = new Monster(idmons, line1[1], eltype, basestats, monsmove, "-");
                    arrmonster.add(mons);
                }

        } catch (Exception e) {
        }

        // for(Move mov : arrmove){
        // mov.printMove();
        // System.out.println();
        // }
        // for(Monster mons : arrmonster){
        // mons.printMonster();
        // }
        // Debug status move
        // StatusMove satmove = new StatusMove(1, MoveType.STATUS, "Satmove",
        // ElementType.FIRE, 90, 1, 10, Target.ENEMY, "-", 90.0, 90.0, 90.0, 90.0, 90.0,
        // 90.0);
        // satmove.printMove();
        // satmove.printmonsMove();

        // ============================================ MAIN PROGRAM ============================================
        System.out.println("=========== PERMAINAN MONSTER SAKU ===========");
        System.out.println("Halo! Selamat datang di permainan Monster Saku");
        String menu[] = { "Start Game", "Help", "Exit" };

        System.out.println("Menu Permainan: ");
        for (int i = 0; i <= 2; i++) {
            System.out.printf("%d. %s%n", i + 1, menu[i]);
        }

        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.print("Masukkan nomor menu: ");
        int pilihanMenu = scan.nextInt();
        System.out.println();

        if (pilihanMenu == 1) {
            System.out.println("=========== GAME STARTED ============");
            System.out.print("Enter player 1 name: ");
            String player1name = scan.next();
            System.out.print("Enter player 2 name: ");
            String player2name = scan.next();

            System.out.println();
            System.out.println("============== MONSTER ==============");
            // System.out.println("Player 1 name: " + player1name);
            // System.out.println("Player 2 name: " + player2name);
            Random rand = new Random();
            Integer upperbound = arrmonster.size();
            ArrayList<Monster> player1monsterarray = new ArrayList<Monster>();
            for (int i = 0; i < 6; i++) {
                Integer randommonster = rand.nextInt(upperbound);
                player1monsterarray.add(arrmonster.get(randommonster));
            }
            ArrayList<Monster> player2monsterarray = new ArrayList<Monster>();
            for (int i = 0; i < 6; i++) {
                Integer randommonster = rand.nextInt(upperbound);
                player2monsterarray.add(arrmonster.get(randommonster));
            }

            Player player1 = new Player(player1name, player1monsterarray);
            Player player2 = new Player(player2name, player2monsterarray);
            System.out.println("List of Monster Player " + player1name);
            player1.showListOfMonster();
            System.out.println("=====================================");
            System.out.println("List of Monster Player " + player2name);
            player2.showListOfMonster();
            System.out.println("============= GAME PLAY =============");
            // Testing akses elemen dari monster di player
            // player2.getListOfMonster().get(0).getMoves().get(0).useammunition();
            // player2.getListOfMonster().get(0).printMonster();

            // for (Monster m : arrmonster) {
            // System.out.println(m.getName());
            // }

            Monster monsterPlayer1 = player1.getListOfMonster().get(0);
            System.out.printf("%s akan memainkan Monster 1.%s di awal permainan%n", player1.getName(), monsterPlayer1.getName());
            Monster monsterPlayer2 = player2.getListOfMonster().get(0);
            System.out.printf("%s akan memainkan Monster 1.%s di awal permainan%n", player2.getName(), monsterPlayer2.getName());

            int inputmove2idx = 0;
            int inputmove1idx = 0;

            int switchMonster = 0;

            // player1.getListOfMonster().remove(monsterPlayer1);

            // System.out.println("----------------------------------------------------------------");
            // for(int i = 0; i < player1.getNumberOfMonster(); i++){
            // player1.getListOfMonster().get(i).printMonster();
            // System.out.println();
            // }

            int turn = 1;
            while (player1.getNumberOfMonster() != 0 || player2.getNumberOfMonster() != 0) {
                System.out.println();
                System.out.println("================ MENU ===============");
                System.out.println("1.View Monster Info\n2.View Game Info");
                System.out.println();
                System.out.print("Masukkan nomor menu (ketik 0 untuk kembali ke permainan): ");
                int pilihanMenuInGame = scan.nextInt();
                if (pilihanMenuInGame == 1) {
                    // menampilkan informasi setiap atribut dari monster-monster yang ada saat permainan
                } else if (pilihanMenuInGame == 2) {
                    // menampilkan informasi turn, informasi monster yang sedang bertarung, beserta informasi monster yang tidak sedang digunakan
                } else if (pilihanMenuInGame == 0) {
                }

                System.out.println();
                System.out.println("============ PUTARAN KE-"+turn+" ===========");

                // ========================================= GILIRAN PLAYER 2 =========================================
                System.out.printf(("Giliran : %s %n1.Switch %n2.Move%n"), player1.getName());
                System.out.println();
                System.out.print("Masukkan nomor: ");
                int op1 = scan.nextInt();
                System.out.println();
                if (op1 == 1) {
                    System.out.println("= Pilih monster yang akan dimainkan =\n");
                    System.out.println("No; Name; ElType; HP; Condition");
                    for (int i = 0; i< player1.getListOfMonster().size(); i++) {
                        System.out.println((i+1)+"; "+player1.getListOfMonster().get(i).infoListOfMonster());
                    }
                    System.out.println();
                    System.out.print("Masukkan nomor monster: ");
                    switchMonster = scan.nextInt();
                    while ((switchMonster > player1.getListOfMonster().size() || switchMonster<1) || monsterPlayer1 == player1.getListOfMonster().get(switchMonster - 1)) {
                        if (switchMonster > player1.getListOfMonster().size() || switchMonster<1) {
                            System.out.println("ERROR: Input di luar range!");
                        } else {
                            System.out.println("ERROR: Monster yang kamu pilih tidak berbeda dengan monster yang sedang dimainkan!");
                        }
                        System.out.println();
                        System.out.print("Silakan masukan ulang nomor: ");
                        switchMonster = scan.nextInt();
                    }
                    monsterPlayer1 = player1.getListOfMonster().get(switchMonster - 1);

                } else if (op1 == 2) {
                    inputmove1idx = monsterPlayer1.moveIdx();
                }


                // ========================================= GILIRAN PLAYER 2 =========================================
                System.out.println();
                // System.out.printf(("Giliran : %s %nKetik 1 untuk melakukan Switch%nKetik 2 untuk melakukan Move%n"), player2.getName());
                System.out.printf(("Giliran : %s %n1.Switch %n2.Move%n"), player2.getName());
                System.out.println();
                System.out.print("Masukkan nomor: ");
                int op2 = scan.nextInt();
                System.out.println();
                if (op2 == 1) {
                    System.out.println("= Pilih monster yang akan dimainkan =\n");
                    System.out.println("No; Name; ElType; HP; Condition");
                    for (int i = 0; i< player2.getListOfMonster().size(); i++) {
                        System.out.println((i+1)+"; "+player2.getListOfMonster().get(i).infoListOfMonster());
                    }
                    System.out.println();
                    System.out.print("Masukkan nomor monster: ");
                    switchMonster = scan.nextInt();
                    while ((switchMonster > player2.getListOfMonster().size() || switchMonster<1) || monsterPlayer2 == player2.getListOfMonster().get(switchMonster - 1)) {
                        if (switchMonster > player2.getListOfMonster().size() || switchMonster<1) {
                            System.out.println("ERROR: Input di luar range!");
                        } else {
                            System.out.println("ERROR: Monster yang kamu pilih tidak berbeda dengan monster yang sedang dimainkan!");
                        }
                        System.out.println();
                        System.out.print("Silakan masukan ulang nomor: ");
                        switchMonster = scan.nextInt();
                    }
                    monsterPlayer2 = player2.getListOfMonster().get(switchMonster - 1);

                } else if (op2 == 2) {
                    inputmove2idx = monsterPlayer2.moveIdx();
                }


               // =========================== RESOLUSI ===========================

                System.out.println();
                System.out.println("============= RESOLUTION ============");

                 // =========================== DAMAGE CALCULATION ===========================
                if (op1 == 1 && op2 == 1) {
                    System.out.printf("%s mengganti monster yang dimainkan menjadi %d.%s%n", player1.getName(), switchMonster, monsterPlayer1.getName());
                    System.out.printf("%s mengganti monster yang dimainkan menjadi %d.%s%n", player2.getName(), switchMonster, monsterPlayer2.getName());
                } else if (op1 == 1 && op2 == 2) {
                    System.out.printf("%s mengganti monster yang dimainkan menjadi %d.%s%n", player1.getName(), switchMonster, monsterPlayer1.getName());
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer2.getName(), player2.getName(), monsterPlayer2.getMoves().get(inputmove2idx).getmovename());
                    System.out.println("Calculating...");
                    monsterPlayer2.monsterMovement(inputmove2idx);
                } else if (op2 == 1 && op1 == 2) {
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer1.getName(), player1.getName(), monsterPlayer1.getMoves().get(inputmove1idx).getmovename());
                    System.out.printf("%s mengganti monster yang dimainkan menjadi %d.%s%n", player2.getName(), switchMonster, monsterPlayer2.getName());
                    System.out.println("Calculating...");
                    monsterPlayer1.monsterMovement(inputmove1idx);
                } else if (op1 == 2 && op2 == 2) {
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer1.getName(), player1.getName(), monsterPlayer1.getMoves().get(inputmove1idx).getmovename());
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer2.getName(), player2.getName(), monsterPlayer2.getMoves().get(inputmove2idx).getmovename());
                    System.out.println("Calculating...");
                    if (monsterPlayer1.getMoves().get(inputmove1idx).getpriority() > monsterPlayer2.getMoves()
                            .get(inputmove2idx)
                            .getpriority()) {
                        monsterPlayer1.monsterMovement(inputmove1idx);
                        monsterPlayer2.monsterMovement(inputmove2idx);

                    } else if (monsterPlayer1.getMoves().get(inputmove1idx).getpriority() < monsterPlayer2.getMoves()
                            .get(inputmove2idx)
                            .getpriority()) {
                        monsterPlayer2.monsterMovement(inputmove2idx);
                        monsterPlayer1.monsterMovement(inputmove1idx);

                    } else {
                        if (monsterPlayer1.getBaseStats().getSpeed() >= monsterPlayer2.getBaseStats().getSpeed()) {
                            monsterPlayer1.monsterMovement(inputmove1idx);
                            monsterPlayer2.monsterMovement(inputmove2idx);
                        } else {
                            monsterPlayer2.monsterMovement(inputmove2idx);
                            monsterPlayer1.monsterMovement(inputmove1idx);
                        }
                    }
                    
                }

                // =========================== AFTER DAMAGE CALCULATION ===========================
                if (monsterPlayer1.isEliminated() && !monsterPlayer2.isEliminated()) {
                    System.out.printf("Monster %s milik %s kalah, pilih monster lain", monsterPlayer1, player1);
                    player1.getListOfMonster().remove(monsterPlayer1);
                }

                /*
                 * di dalem move ada 4 kondisi yang mungkin terjadi
                 * 1. Player1 switch player2 switch
                 * 2. player1 switch player2 move -> langsung damage
                 * 3. player1 move player2 switch -> langsung damage
                 * 4. player1 move player2 move -> check prio
                 * 
                 * setiap ada move:
                 * cek move status move --> cek target
                 * target masukin ke parameter target
                 * 
                 * 2 player pilih move:
                 * cek prio --> cek speed
                 * misalnya player1 dulu -->
                 * 
                 * 
                 */

                // CEK STATUS CONDITION

                turn++;
                System.out.println();
                System.out.println("============= END OF TURN ===========");
            }

        } else if (pilihanMenu == 2) {
            // help
        } else if (pilihanMenu == 3) {
            //exit
            scan.close();
            System.out.println("Keluar dari Permainan Monster Saku...");
            return;
        }
        scan.close();

        // ============================================== CATATAN ==============================================
        /* 
            (Handling invalid input dengan while loop)
            
            Sudah ada handling invalid input:
            1. pilih monster saat switch -> input di luar range, input tidak mengganti monster
            2. pilih move -> input di luar range

            Belum ada handling invalid input:
            1. input pilihanMenu
            2. input pilihanMenuInGame

            Pertanyaan:
            1. MenuInGame ditampilin di awal turn (1x dalam 1 turn) 
            atau ditampilin setiap giliran player (2x dalam 1 turn, sesuai giliran player yang sedang main)
            2. menu exit dan help bisa dipanggil setelah kita udh start game?

        */
    }
}
