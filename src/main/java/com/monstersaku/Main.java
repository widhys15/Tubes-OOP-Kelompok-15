package com.monstersaku;

import com.monstersaku.util.AbsMove;
import com.monstersaku.util.CSVReader;
import com.monstersaku.util.DefaultMove;
import com.monstersaku.util.ElementEffectivity;
import com.monstersaku.util.ElementType;
import com.monstersaku.util.Monster;
import com.monstersaku.util.Move;
import com.monstersaku.util.MoveType;
import com.monstersaku.util.NormalMove;
import com.monstersaku.util.Target;

import org.omg.CORBA.DynAnyPackage.Invalid;

import com.monstersaku.util.StatusMove;
import com.monstersaku.util.Stats;
import com.monstersaku.util.StatsMove;
import com.monstersaku.util.StatusCondition;
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

    //semacam prosedur dan fungsi
    private static int chooseGameMenu() {
        String menu[] = { "Start Game", "Help", "Exit" };

        System.out.println("Menu Permainan: ");
        for (int i = 0; i <= 2; i++) {
            System.out.printf("%d. %s%n", i + 1, menu[i]);
        }

        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.print("Masukkan nomor menu: ");
        int pilihanMenu = scan.nextInt();
        
        boolean invalidinput = false;
        if (pilihanMenu < 1 || pilihanMenu >3) {
            invalidinput = true;
        }

        while (invalidinput) {
            System.out.println("ERROR: nomor di luar range!");
            System.out.print("Masukkan nomor menu: ");
            pilihanMenu = scan.nextInt();
            if (pilihanMenu > 0 && pilihanMenu<=3) {
                invalidinput = false;
            }
        }
        return pilihanMenu;
    }

    private static void help() {
        System.out.println();
        System.out.println("================ HELP ===============");
        System.out.println("Pilih menu Start Game dengan cara memasukkan angka 1 ketika memasuki permainan untuk mulai bermain");
        System.out.println("");
    }

    private static int chooseMonster(Player player, Monster monsterPlayer) {
        System.out.println("== Pilih monster yang akan dimainkan ==\n");
                    System.out.println("No; Name; ElType; HP; Condition");
                    for (int i = 0; i< player.getListOfMonster().size(); i++) {
                        System.out.println((i+1)+"; "+player.getListOfMonster().get(i).infoListOfMonster());
                    }
                    System.out.println();
                    System.out.print("Masukkan nomor monster: ");
                    Scanner scan = new Scanner(System.in);
                    int switchMonster = scan.nextInt();
                    while ((switchMonster > player.getListOfMonster().size() || switchMonster<1) || monsterPlayer == player.getListOfMonster().get(switchMonster - 1)) {
                        if (switchMonster > player.getListOfMonster().size() || switchMonster<1) {
                            System.out.println("ERROR: Input di luar range!");
                        } else {
                            System.out.println("ERROR: Monster yang kamu pilih tidak berbeda dengan monster yang sedang dimainkan!");
                        }
                        System.out.print("Masukkan nomor monster: ");
                        switchMonster = scan.nextInt();
                    }
                    return switchMonster-1;
    }

    private static int chooseMove(Monster monster) {
        Scanner scan = new Scanner(System.in);
        System.out.println("== Pilih move yang akan digunakan ==\n");
        for (int i = 0; i <= monster.getMoves().size()-1; i++) {
            System.out.println("MOVE NOMOR " + (i + 1));
            monster.getMoves().get(i).printmonsMove();
            System.out.println();
        }
        System.out.print("Masukkan nomor move: ");
        int monsterMove = scan.nextInt();
        while (monsterMove > monster.getMoves().size() || monsterMove < 1) {
            System.out.println("ERROR: Input di luar range!");
            System.out.print("Masukkan nomor move: ");
            monsterMove = scan.nextInt();
        }
        return monsterMove-1;
    }

    private static void menuInGame(Player player, Monster monster, int turn) {
                System.out.println("Menu:");
                System.out.println("1.View Monster Info\n2.View Game Info");
                System.out.println();
                System.out.print("Masukkan nomor menu (ketik 0 untuk kembali ke permainan): ");
                
                Scanner scan = new Scanner(System.in);
                int pilihanMenuInGame = scan.nextInt();

                while (pilihanMenuInGame!=0) {
                    boolean invalidinput = false;
                    
                    if (pilihanMenuInGame < 0 || pilihanMenuInGame >2) {
                        invalidinput = true;
                    }
            
                    while (invalidinput) {
                        System.out.println("ERROR: nomor di luar range!");
                        System.out.print("Masukkan nomor menu(ketik 0 untuk kembali ke permainan): ");
                        pilihanMenuInGame = scan.nextInt();
                        if (pilihanMenuInGame >= 0 && pilihanMenuInGame<=2) {
                            invalidinput = false;
                        }
                    }
                    if (pilihanMenuInGame == 1) {
                        // menampilkan informasi setiap atribut dari monster-monster yang ada saat permainan
                        System.out.println("\nVIEW Monster INFO ...");
                        player.showListOfMonster();
                    } else if (pilihanMenuInGame == 2) {
                        // menampilkan informasi turn, informasi monster yang sedang bertarung, beserta informasi monster yang tidak sedang digunakan
                        System.out.println("\nVIEW GAME INFO ...");
                        System.out.printf("Permainan saat ini adalah putaran ke-%d%n", turn);
                        System.out.printf("Monster yang saat ini sedang bertarung: %s%n", monster.infoListOfMonster());
                        System.out.println("Monster yang tidak sedang digunakan: ");
                        System.out.println("Name; ElType; HP; Condition");
                        for (int i = 0; i< player.getListOfMonster().size(); i++) {
                            if (player.getListOfMonster().get(i) != monster) {
                                System.out.println(player.getListOfMonster().get(i).infoListOfMonster());
                            }
                        }
                        System.out.println(); 
                    }
                    System.out.println("Menu:");
                    System.out.println("1.View Monster Info\n2.View Game Info");
                    System.out.println();
                    System.out.print("Masukkan nomor menu (ketik 0 untuk kembali ke permainan): ");
                    
                    scan = new Scanner(System.in);
                    pilihanMenuInGame = scan.nextInt();
                }
    }

    private static int inputPlayer(int op) {
        boolean invalidinput = false;
        
        Scanner scan = new Scanner(System.in);
        if (op < 1 || op >2) {
            invalidinput = true;
        }
            
        while (invalidinput) {
            System.out.println("ERROR: nomor di luar range!");
            System.out.print("Masukkan nomor: ");
            op = scan.nextInt();
            if (op > 0 && op<=2) {
                invalidinput = false;
            }
        }
        return op;
    }
    // ============================================ MAIN ========================================================================================
    public static void main(String[] args) {
        // Creating ArrayList Move, Monster, and Effectivity
        ArrayList<AbsMove> arrmove = new ArrayList<AbsMove>();
        ArrayList<Monster> arrmonster = new ArrayList<Monster>();
        ArrayList<ElementEffectivity> arreffectivity = new ArrayList<ElementEffectivity>();

        // ============================================ TRY READING MONSTER POOL AND MOVE POOL ============================================
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
                        StatusCondition condition = StatusCondition.valueOf(line[8]);
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
                    Monster mons = new Monster(idmons, line1[1], eltype, basestats, monsmove, null);
                    arrmonster.add(mons);
            }
            System.out.printf("Filename: %s\n", "configs/elementeffectivity.csv");
                CSVReader reader2 = new CSVReader(new File(Main.class.getResource("configs/elementeffectivity.csv").toURI()),
                        ";");
                reader2.setSkipHeader(true);
                List<String[]> lines2 = reader2.read();

                for (String[] line2 : lines2) {
                    // Parsing for element effectivity constructor
                    Double effectivity = Double.parseDouble(line2[2]);
                    ElementType atk = ElementType.valueOf(line2[0]);
                    ElementType tar = ElementType.valueOf(line2[1]);
                    ElementEffectivity eleffectivity = new ElementEffectivity(atk, tar, effectivity);
                    // Adding elementeffectivity object to arraylist elementeffectivity
                    eleffectivity.printeffectivity();
                    arreffectivity.add(eleffectivity); 
                    }
                System.out.println();
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
        Scanner scan = new Scanner(System.in);
        
        int pilihanMenu = chooseGameMenu();
        
        while (pilihanMenu == 2) {
            help();
            System.out.println("Back to menu...\n");
            pilihanMenu = chooseGameMenu();
        }

        // ============================================ START GAME ============================================
        if (pilihanMenu == 1) {
            System.out.println();
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
            Monster monsterPlayer2 = player2.getListOfMonster().get(0);
            System.out.printf("%s akan memainkan Monster %s di awal permainan%n", player1.getName(), monsterPlayer1.getName());
            System.out.printf("%s akan memainkan Monster %s di awal permainan%n", player2.getName(), monsterPlayer2.getName());

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

                // System.out.println();
                // System.out.println("============ PUTARAN KE-"+turn+" ===========");

                // ========================================= GILIRAN PLAYER 1 =========================================
                System.out.printf("Giliran : %s %n", player1.getName());
                System.out.println();
                menuInGame(player1, monsterPlayer1, turn);
                System.out.println();
                System.out.println("Pilih aksi yang akan dilakukan: \n1.Switch (mengganti monster) \n2.Move (melakukan pergerakan)");
                System.out.println();
                System.out.print("Masukkan nomor: ");
                int op1 = scan.nextInt();
                op1 = inputPlayer(op1);
                System.out.println();
                if (op1 == 1) {
                    switchMonster = chooseMonster(player1, monsterPlayer1);
                    monsterPlayer1 = player1.getListOfMonster().get(switchMonster);
                } else if (op1 == 2) {
                    inputmove1idx = chooseMove(monsterPlayer1);
                    // System.out.println("MOVEEEEEEEEEEEEE");
                }


                // ========================================= GILIRAN PLAYER 2 =========================================
                System.out.println();
                // System.out.printf(("Giliran : %s %nKetik 1 untuk melakukan Switch%nKetik 2 untuk melakukan Move%n"), player2.getName());
                System.out.printf("Giliran : %s %n", player2.getName());
                System.out.println();
                menuInGame(player2, monsterPlayer2, turn);
                System.out.println();
                System.out.println("Pilih aksi yang akan dilakukan: \n1.Switch (mengganti monster) \n2.Move (melakukan pergerakan)");
                System.out.println();
                System.out.print("Masukkan nomor: ");
                int op2 = scan.nextInt();
                op2 = inputPlayer(op2);
                System.out.println();
                if (op2 == 1) {
                    switchMonster = chooseMonster(player2, monsterPlayer2);
                    monsterPlayer2 = player2.getListOfMonster().get(switchMonster);
                } else if (op2 == 2) {
                    inputmove2idx = chooseMove(monsterPlayer2);
                    // System.out.println("MOVEEEEEEEEEEEEE");
                }


                // ====================================================== RESOLUSI ======================================================

                System.out.println();
                System.out.println("============= RESOLUTION ============");


                StatusCondition conditionMonster1 = monsterPlayer1.getStatusCondition();
                StatusCondition conditionMonster2 = monsterPlayer2.getStatusCondition();


                 // ================================================== DAMAGE CALCULATION ===============================================
                if (op1 == 1 && op2 == 1) {
                    System.out.printf("%s mengganti monster yang dimainkan menjadi %s%n", player1.getName(), monsterPlayer1.getName());
                    System.out.printf("%s mengganti monster yang dimainkan menjadi %s%n", player2.getName(), monsterPlayer2.getName());
                } else if (op1 == 1 && op2 == 2) {
                    AbsMove moveMonster2 = monsterPlayer2.getMoves().get(inputmove2idx);
                    System.out.printf("%s mengganti monster yang dimainkan menjadi %s%n", player1.getName(), monsterPlayer1.getName());
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer2.getName(), player2.getName(), moveMonster2.getmovename());
                    System.out.println();
                    System.out.println("Calculating...");
                    moveMonster2.useMove(monsterPlayer2, monsterPlayer1, arreffectivity, arrmonster);
                } else if (op2 == 1 && op1 == 2) {
                    AbsMove moveMonster1 = monsterPlayer1.getMoves().get(inputmove1idx);
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer1.getName(), player1.getName(), moveMonster1.getmovename());
                    System.out.printf("%s mengganti monster yang dimainkan menjadi %s%n", player2.getName(), monsterPlayer2.getName());
                    System.out.println();
                    System.out.println("Calculating...");
                    moveMonster1.useMove(monsterPlayer1, monsterPlayer2, arreffectivity, arrmonster);
                } else if (op1 == 2 && op2 == 2) {
                    AbsMove moveMonster1 = monsterPlayer1.getMoves().get(inputmove1idx);
                    AbsMove moveMonster2 = monsterPlayer2.getMoves().get(inputmove2idx);
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer1.getName(), player1.getName(), moveMonster1.getmovename());
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer2.getName(), player2.getName(), moveMonster2.getmovename());
                    System.out.println();
                    System.out.println("Calculating...");
                    if (moveMonster1.getpriority() > moveMonster2.getpriority()) {
                        moveMonster1.useMove(monsterPlayer1, monsterPlayer2, arreffectivity, arrmonster);
                        moveMonster2.useMove(monsterPlayer2, monsterPlayer1, arreffectivity, arrmonster);
                    } else if (moveMonster1.getpriority() < moveMonster2.getpriority()) {
                        moveMonster2.useMove(monsterPlayer2, monsterPlayer1, arreffectivity, arrmonster);
                        moveMonster1.useMove(monsterPlayer1, monsterPlayer2, arreffectivity, arrmonster);
                    } else {
                        if (monsterPlayer1.getBaseStats().getSpeed() >= monsterPlayer2.getBaseStats().getSpeed()) {
                            moveMonster1.useMove(monsterPlayer1, monsterPlayer2, arreffectivity, arrmonster);
                            moveMonster2.useMove(monsterPlayer2, monsterPlayer1, arreffectivity, arrmonster);
                        } else {
                            moveMonster2.useMove(monsterPlayer2, monsterPlayer1, arreffectivity, arrmonster);
                            moveMonster1.useMove(monsterPlayer1, monsterPlayer2, arreffectivity, arrmonster);
                        }
                    }
                }
                System.out.println();

                // ================================================== AFTER DAMAGE CALCULATION ===================================================
                if (monsterPlayer1.isEliminated() && !monsterPlayer2.isEliminated()) {
                    System.out.printf("Monster %s milik %s telah dikalahkan, pilih monster lain", monsterPlayer1, player1);
                    player1.getListOfMonster().remove(monsterPlayer1);
                } else {

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

        } else {
            //exit
            scan.close();
            System.out.println("Keluar dari Permainan Monster Saku...");
            return;
        }
        scan.close();
    }
}
