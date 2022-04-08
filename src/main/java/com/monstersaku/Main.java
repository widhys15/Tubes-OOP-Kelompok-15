package com.monstersaku;

import com.monstersaku.util.*;
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
        // Creating ArrayList Move, Monster, and Effectivity
        ArrayList<Move> arrmove = new ArrayList<Move>();
        ArrayList<Monster> arrmonster = new ArrayList<Monster>();

        // ============================================ TRY READING MONSTER POOL AND
        // MOVE POOL ============================================
        try {
            System.out.println("READING FILE");
            CSVReader monreader = new CSVReader(new File(Main.class.getResource("configs/monsterpool.csv").toURI()), ";");
            CSVReader movreader = new CSVReader(new File(Main.class.getResource("configs/movepool.csv").toURI()), ";");
            monreader.setSkipHeader(true);
            movreader.setSkipHeader(true);
            List<String[]> monlines = monreader.read();
            List<String[]> movlines = movreader.read();
            System.out.println("Srat");
            // Creating Move Pool
            for(String[] movline : movlines){
                System.out.println("SAT");
                Integer idmove = Integer.parseInt(movline[0]);
                MoveType movetaip = MoveType.valueOf(movline[1]);
                String movename = movline[2];
                ElementType moveelementType = ElementType.valueOf(movline[3]);
                Integer accuracy = Integer.parseInt(movline[4]);
                Integer priority = Integer.parseInt(movline[5]);
                Integer ammunition = Integer.parseInt(movline[6]);
                Target target = Target.valueOf(movline[7]);
                if (movetaip.equals(MoveType.NORMAL)) {
                    Double damage = Double.parseDouble(movline[8]);
                    NormalMove mov = new NormalMove(idmove, movetaip, movename, moveelementType, accuracy, priority, ammunition, target, damage);
                    arrmove.add(mov);
                } else if (movetaip.equals(MoveType.SPECIAL)) {
                    Double damage = Double.parseDouble(movline[8]);
                    SpecialMove mov = new SpecialMove(idmove, movetaip, movename, moveelementType, accuracy, priority, ammunition, target, damage);
                    arrmove.add(mov);
                } else if (movetaip.equals(MoveType.STATUS)){
                    String condition = movline[8];
                    Double effect = Double.parseDouble(movline[9]);
                    StatusMove mov = new StatusMove(idmove, movetaip, movename, moveelementType, accuracy, priority, ammunition, target, condition, effect);
                    arrmove.add(mov);
                }
            }
            
            for (String[] monline : monlines) {
                    // Parsing for monster constructor, creating monster object and adding to
                    // arraylist monster
                    Integer idmonster = Integer.parseInt(monline[0]);
                    String monstername = monline[1];
                    ArrayList<ElementType> eltype = new ArrayList<ElementType>();
                    String eltaip = monline[2];
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
                    String stat = monline[3];
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
                    
                    String mov = monline[4];
                    String[] arrofmov = mov.split(",");
                    ArrayList<Integer> movesid = new ArrayList<>();
                    // Adding move object to listofmove monster
                    for(String a: arrofmov){
                        Integer i = Integer.parseInt(a);
                        movesid.add(i);
                    }
                    Monster mons = new Monster(idmonster, monstername, eltype, basestats, movesid);
                    arrmonster.add(mons);
            }
        } catch (Exception e) {
        }
        Random rand = new Random();
        ArrayList<Monster> player1mons = new ArrayList<Monster>();
        ArrayList<Monster> player2mons = new ArrayList<Monster>();
        System.out.println("JUMLAH MONSTER :" + arrmonster.size());
        try {
            System.out.println("READING FILE");
            CSVReader monreader = new CSVReader(new File(Main.class.getResource("configs/monsterpool.csv").toURI()), ";");
            CSVReader movreader = new CSVReader(new File(Main.class.getResource("configs/movepool.csv").toURI()), ";");
            monreader.setSkipHeader(true);
            movreader.setSkipHeader(true);
            List<String[]> monlines = monreader.read();
            List<String[]> movlines = movreader.read();
            // Random Monster
            for (int i = 0; i < 6; i++) {
                int id1 = rand.nextInt(arrmonster.size()) ;
                int id2 = rand.nextInt(arrmonster.size()) ;

                int j = 0 ;
                for (String[] monline : monlines) {
                    if (j == id1) {
                        String stat = monline[3];
                        String[] arrofstats = stat.split(",");
                        ArrayList<Double> stats = new ArrayList<Double>();
                        // Parsing for stats
                        for (String a : arrofstats) {
                            Double d = Double.parseDouble(a);
                            stats.add(d);
                        }
                        Stats<Double> newstats = new Stats<Double>(stats.get(0), stats.get(1), stats.get(2), stats.get(3), stats.get(4), stats.get(5));

                        Monster monster1 = new Monster(arrmonster.get(id1), newstats);

                        player1mons.add(monster1) ;

                        for (int move : monster1.getmovesid()) {

                            for (String[] movline : movlines) {
                                if (move == Integer.parseInt(movline[0]) - 1) {

                                    int ammunition = Integer.parseInt(movline[6]) ;

                                    if (movline[1].equals("NORMAL")) {
                                        NormalMove normalMove = new NormalMove((NormalMove)arrmove.get(move), ammunition);
                                        monster1.getMoves().add(normalMove);
                                    }
                                    else if (movline[1].equals("SPECIAL")) {
                                        SpecialMove specialMove = new SpecialMove((SpecialMove)arrmove.get(move), ammunition);
                                        monster1.getMoves().add(specialMove);
                                    }
                                    else if (movline[1].equals("STATUS")){ 
                                        StatusMove statusMove = new StatusMove((StatusMove)arrmove.get(move), ammunition);
                                        monster1.getMoves().add(statusMove);
                                    }
                                }
                            }
                        }
                    }
                    if (j == id2) {
                        String stat = monline[3];
                        String[] arrofstats = stat.split(",");
                        ArrayList<Double> stats = new ArrayList<Double>();
                        // Parsing for stats
                        for (String a : arrofstats) {
                            Double d = Double.parseDouble(a);
                            stats.add(d);
                        }
                        Stats<Double> newstats = new Stats<Double>(stats.get(0), stats.get(1), stats.get(2), stats.get(3), stats.get(4), stats.get(5));
                        
                        Monster monster2 = new Monster(arrmonster.get(id2), newstats) ;
                        
                        player2mons.add(monster2) ;

                        for (int move : monster2.getmovesid()) {
    
                            for (String[] movline : movlines) {
                                if (move == Integer.parseInt(movline[0]) - 1) {
    
                                    int ammunition = Integer.parseInt(movline[6]) ;
    
                                    if (movline[1].equals("NORMAL")) {
                                        NormalMove normalMove = new NormalMove((NormalMove)arrmove.get(move), ammunition) ;
                                        monster2.getMoves().add(normalMove) ;
                                    }
                                    else if (movline[1].equals("SPECIAL")) {
                                        SpecialMove specialMove = new SpecialMove((SpecialMove)arrmove.get(move), ammunition) ;
                                        monster2.getMoves().add(specialMove) ;
                                    }
                                    else {  // TAMBAH EXCEPTION
                                        StatusMove statusMove = new StatusMove((StatusMove)arrmove.get(move), ammunition) ;
                                        monster2.getMoves().add(statusMove) ;
                                    }
                                }
                            }
                        }
                    }

                    j++ ;
                }
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

            Player player1 = new Player(player1name, player1mons);
            Player player2 = new Player(player2name, player2mons);
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

            Monster monsterPlayer1 = player1.getListOfMonster().get(1);
            System.out.printf("%s akan memainkan Monster 1.%s di awal permainan%n", player1.getName(), monsterPlayer1.getName());
            Monster monsterPlayer2 = player2.getListOfMonster().get(1);
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
                    player1.showListOfMonster();
                    player2.showListOfMonster();
                    // menampilkan informasi setiap atribut dari monster-monster yang ada saat permainan
                } else if (pilihanMenuInGame == 2) {
                    System.out.println("VIEW GAME INFO ...");
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
                    inputmove1idx = scan.nextInt()-1;
                    System.out.println("MOVEEEEEEEEEEEEE");
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
                    inputmove2idx = scan.nextInt()-1;
                    System.out.println("MOVEEEEEEEEEEEEE");
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
                    if(monsterPlayer2.getMoves().get(inputmove2idx).getmovetype().equals(MoveType.DEFAULT)){
                        System.out.println("INI DEFAULT MOVE");
                    }
                    // if(monsterPlayer2.getMoves().get(inputmove2idx).getmovetype().equals(MoveType.DEFAULT)){
                    //     System.out.println("INI DEFAULT MOVE AJG");
                    //     monsterPlayer2.getMoves().get(inputmove2idx).useDefaultMove(monsterPlayer2, monsterPlayer1, arreffectivity, arrmonster);
                    // } else if (monsterPlayer2.getMoves().get(inputmove2idx).getmovetype().equals(MoveType.NORMAL)){
                    //     System.out.println("INI NORMAL MOVE AJG");
                    //     monsterPlayer2.getMoves().get(inputmove2idx).useNormalMove(monsterPlayer2, monsterPlayer1, arreffectivity);
                    // } else if (monsterPlayer2.getMoves().get(inputmove2idx).getmovetype().equals(MoveType.SPECIAL)){
                    //     System.out.println("INI SPECIAL MOVE AJG");
                    //     monsterPlayer2.getMoves().get(inputmove2idx).useSpecialMove(monsterPlayer2, monsterPlayer1, arreffectivity);
                    // } else if (monsterPlayer2.getMoves().get(inputmove2idx).getmovetype().equals(MoveType.STATUS)){
                    //     System.out.println("INI STATUS MOVE AJG");
                    //     if(monsterPlayer2.getMoves().get(inputmove2idx).gettarget().equals(Target.ENEMY)){
                    //         monsterPlayer2.getMoves().get(inputmove2idx).changeCondition(monsterPlayer1);
                    //     }
                    //     else{
                    //         monsterPlayer2.getMoves().get(inputmove2idx).changeHP(monsterPlayer2);
                    //     }
                    // }
                    // monsterPlayer2.monsterMovement(inputmove2idx);
                } else if (op2 == 1 && op1 == 2) {
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer1.getName(), player1.getName(), monsterPlayer1.getMoves().get(inputmove1idx).getmovename());
                    System.out.printf("%s mengganti monster yang dimainkan menjadi %d.%s%n", player2.getName(), switchMonster, monsterPlayer2.getName());
                    System.out.println("Calculating...");
                    if(monsterPlayer1.getMoves().get(inputmove1idx).getmovetype().equals(MoveType.DEFAULT)){
                        System.out.println("INI DEFAULT MOVE");
                    }
                    // if(monsterPlayer1.getMoves().get(inputmove1idx).equals(MoveType.DEFAULT)){
                    //     monsterPlayer1.getMoves().get(inputmove1idx).useDefaultMove(monsterPlayer1, monsterPlayer2, arreffectivity, arrmonster);
                    // } else if (monsterPlayer1.getMoves().get(inputmove1idx).equals(MoveType.NORMAL)){
                    //     monsterPlayer1.getMoves().get(inputmove1idx).useNormalMove(monsterPlayer1, monsterPlayer2, arreffectivity);
                    // } else if (monsterPlayer1.getMoves().get(inputmove1idx).equals(MoveType.SPECIAL)){
                    //     monsterPlayer1.getMoves().get(inputmove1idx).useSpecialMove(monsterPlayer1, monsterPlayer2, arreffectivity);
                    // } else if (monsterPlayer1.getMoves().get(inputmove1idx).equals(MoveType.STATUS)){
                    //     if(monsterPlayer1.getMoves().get(inputmove1idx).gettarget().equals(Target.ENEMY)){
                    //         monsterPlayer1.getMoves().get(inputmove1idx).changeCondition(monsterPlayer2);
                    //     }
                    //     else{
                    //         monsterPlayer1.getMoves().get(inputmove1idx).changeHP(monsterPlayer1);
                    //     }
                    // }
                    // monsterPlayer1.monsterMovement(inputmove1idx);
                } else if (op1 == 2 && op2 == 2) {
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer1.getName(), player1.getName(), monsterPlayer1.getMoves().get(inputmove1idx).getmovename());
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer2.getName(), player2.getName(), monsterPlayer2.getMoves().get(inputmove2idx).getmovename());
                    System.out.println("Calculating...");
                    if (monsterPlayer1.getMoves().get(inputmove1idx).getpriority() > monsterPlayer2.getMoves()
                            .get(inputmove2idx)
                            .getpriority()) {
                        // monsterPlayer1.monsterMovement(inputmove1idx);
                        // monsterPlayer2.monsterMovement(inputmove2idx);

                    } else if (monsterPlayer1.getMoves().get(inputmove1idx).getpriority() < monsterPlayer2.getMoves()
                            .get(inputmove2idx)
                            .getpriority()) {
                        // monsterPlayer2.monsterMovement(inputmove2idx);
                        // monsterPlayer1.monsterMovement(inputmove1idx);

                    } else {
                        if (monsterPlayer1.getBaseStats().getSpeed() >= monsterPlayer2.getBaseStats().getSpeed()) {
                            // monsterPlayer1.monsterMovement(inputmove1idx);
                            // monsterPlayer2.monsterMovement(inputmove2idx);
                        } else {
                            // monsterPlayer2.monsterMovement(inputmove2idx);
                            // monsterPlayer1.monsterMovement(inputmove1idx);
                        }
                    }
                    // if(monsterPlayer1.getMoves().get(inputmove1idx).equals(MoveType.DEFAULT)){
                    //     monsterPlayer1.getMoves().get(inputmove1idx).useDefaultMove(monsterPlayer1, monsterPlayer2, arreffectivity, arrmonster);
                    // } else if (monsterPlayer1.getMoves().get(inputmove1idx).equals(MoveType.NORMAL)){
                    //     monsterPlayer1.getMoves().get(inputmove1idx).useNormalMove(monsterPlayer1, monsterPlayer2, arreffectivity);
                    // } else if (monsterPlayer1.getMoves().get(inputmove1idx).equals(MoveType.SPECIAL)){
                    //     monsterPlayer1.getMoves().get(inputmove1idx).useSpecialMove(monsterPlayer1, monsterPlayer2, arreffectivity);
                    // } else if (monsterPlayer1.getMoves().get(inputmove1idx).equals(MoveType.STATUS)){
                    //     if(monsterPlayer1.getMoves().get(inputmove1idx).gettarget().equals(Target.ENEMY)){
                    //         monsterPlayer1.getMoves().get(inputmove1idx).changeCondition(monsterPlayer2);
                    //     }
                    //     else{
                    //         monsterPlayer1.getMoves().get(inputmove1idx).changeHP(monsterPlayer1);
                    //     }
                    // }
                    
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
