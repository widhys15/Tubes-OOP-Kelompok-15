package com.monstersaku;

import com.monstersaku.util.*;
import java.util.*;
import java.io.*;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    static Scanner scan = new Scanner(System.in);

    // ============================================ SEMACAM PROSEDUR DAN FUNGSI ============================================

    // ============================================ PILIH MENU ============================================
    private static int chooseGameMenu() {
        String menu[] = { "Start Game", "Help", "Exit" };

        System.out.println(ANSI_RED + "----------------------------------------------" + ANSI_RESET);
        System.out.println("Menu Permainan: ");
        for (int i = 0; i <= 2; i++) {
            System.out.printf("%d. %s%n", i + 1, menu[i]);
        }
        System.out.println(ANSI_RED + "----------------------------------------------" + ANSI_RESET);
        System.out.println();
        System.out.print("Masukkan nomor menu: ");
        return inputPlayermenu();
    }
    
    private static int inputPlayermenu() {
        String input = scan.next();
        try {
            int pilihanMenu = Integer.parseInt(input);
            if (pilihanMenu < 1 || pilihanMenu > 3) {
                throw new Exception("ERROR: nomor di luar range!");
            }
            return pilihanMenu;
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "ERROR: input tidak valid!" + ANSI_RESET);
            System.out.print("Masukkan nomor menu: ");
        } catch (Exception e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            System.out.print("Masukkan nomor menu: ");
        }
        return inputPlayermenu();
    }

    // ============================================ HELP ============================================
    private static void help() {
        System.out.println();
        System.out.println(ANSI_YELLOW + "===================== HELP ==================="+ANSI_RESET);
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\com\\monstersaku\\help.txt"));
            String s;
            while ((s=br.readLine()) != null) {
                System.out.println(s);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Mohon maaf terjadi kesalahan membaca dokumen bantuan");
        }
    }

    // ============================================ PILIH MONSTER ============================================
    private static int chooseMonster(Player player, Monster monsterPlayer) {
        System.out.println(ANSI_YELLOW + "====== Pilih monster yang akan dimainkan =====\n" + ANSI_RESET);

        System.out.println("No; Name; ElType; HP; Condition");
        for (int i = 0; i < player.getListOfMonster().size(); i++) {
            System.out.println((i + 1) + "; " + player.getListOfMonster().get(i).infoListOfMonster());
        }
        System.out.println();
        System.out.print("Masukkan nomor monster: ");
        return inputPlayer(player, monsterPlayer);
    }

    private static int inputPlayer(Player player, Monster monsterPlayer) {
        String input = scan.next();
        try {
            int switchMonster = Integer.parseInt(input);
            if (switchMonster > player.getListOfMonster().size() || switchMonster < 1) {
                throw new Exception("ERROR: Input di luar range!");
            } else if (monsterPlayer == player.getListOfMonster().get(switchMonster - 1)) {
                throw new Exception("ERROR: Monster yang kamu pilih tidak berbeda dengan monster yang sedang dimainkan!");
            }
            return switchMonster-1;
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "ERROR: input tidak valid!" + ANSI_RESET);
            System.out.print("Masukkan nomor monster: ");
        } catch (Exception e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            System.out.print("Masukkan nomor monster: ");
        }
        return inputPlayer(player, monsterPlayer);
    }

    // ============================================ PILIH MOVE ============================================
    private static int chooseMove(Monster monster) {
        System.out.println(ANSI_YELLOW + "======= Pilih move yang akan digunakan =======\n" + ANSI_RESET);
        for (int i = 0; i <= monster.getMoves().size() - 1; i++) {
            System.out.println(ANSI_GREEN+"MOVE NOMOR " + (i + 1) +ANSI_RESET);
            monster.getMoves().get(i).printmonsMove();
            System.out.println();
        }
        System.out.print("Masukkan nomor move: ");

        return inputPlayer(monster);
    }

    private static int inputPlayer(Monster monster) {
        String input = scan.next();
        try {
            int monsterMove = Integer.parseInt(input);
            if (monsterMove > monster.getMoves().size() || monsterMove < 1) {
                throw new Exception("ERROR: Input di luar range!");
            }
            return monsterMove - 1;
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED+"ERROR: input tidak valid!"+ANSI_RESET);
            System.out.print("Masukkan nomor move: ");
        } catch (Exception e) {
            System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
            System.out.print("Masukkan nomor move: ");
        }
        return inputPlayer(monster);
    }

    // ============================================ PILIH MENU IN GAME ============================================
    private static void menuInGame(Player player, Monster monster, int turn, ArrayList<Monster> arrmonster) {
                System.out.println(ANSI_YELLOW + "==================== Menu ====================" + ANSI_RESET);
                System.out.println("1.View Monster Info\n2.View Game Info");
                System.out.println();
                System.out.print("Masukkan nomor menu (ketik 0 untuk kembali ke permainan): ");
                int pilihanMenuInGame = inputPlayerMenuingame();
                if (pilihanMenuInGame == 1) {
                    // menampilkan informasi setiap atribut dari monster-monster yang ada saat permainan
                    System.out.println(ANSI_YELLOW + "\n============== VIEW MONSTERS INFO ============" + ANSI_RESET);
                    player.showListOfMonster(arrmonster);
                    menuInGame(player, monster, turn, arrmonster);
                } else if (pilihanMenuInGame == 2) {
                    // menampilkan informasi turn, informasi monster yang sedang bertarung, beserta informasi monster yang tidak sedang digunakan
                    System.out.println(ANSI_YELLOW + "\n=============== VIEW GAME INFO ===============" + ANSI_RESET);
                    System.out.printf("Permainan saat ini adalah putaran ke-" + ANSI_RED + "%d" + ANSI_RESET +"%n%n", turn);
                    System.out.printf("Monster yang saat ini sedang bertarung: " + ANSI_CYAN + "%s"+ ANSI_RESET + "%n%n", monster.infoListOfMonster());
                    System.out.println("Monster yang tidak sedang digunakan: ");
                    System.out.println("Name; ElType; HP; Condition");
                    for (int i = 0; i< player.getListOfMonster().size(); i++) {
                        if (player.getListOfMonster().get(i) != monster) {
                            System.out.println(ANSI_CYAN + player.getListOfMonster().get(i).infoListOfMonster() + ANSI_RESET);
                        }
                    }
                    System.out.println(); 
                    menuInGame(player, monster, turn, arrmonster);
                }
    }

    private static int inputPlayerMenuingame() {
        String input = scan.next();
        try {
            int op = Integer.parseInt(input);

            if (op < 0 || op > 2) {
                throw new Exception("ERROR: nomor di luar range!");
            }
            return op;
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED+"ERROR: input tidak valid!"+ANSI_RESET);
            System.out.print("Masukkan nomor menu (ketik 0 untuk kembali ke permainan): ");
        } catch (Exception e) {
            System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
            System.out.print("Masukkan nomor menu (ketik 0 untuk kembali ke permainan): ");
        }
        return inputPlayerMenuingame();
    }

    // ============================================ PILIH SWITCH/MOVE ============================================
    private static int inputPlayer() {
        String input = scan.next();
        try {
            int op = Integer.parseInt(input);

            if (op < 1 || op > 2) {
                throw new Exception("ERROR: nomor di luar range!");
            }
            return op;
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED+"ERROR: input tidak valid!"+ANSI_RESET);
            System.out.print("Masukkan nomor: ");
        } catch (Exception e) {
            System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
            System.out.print("Masukkan nomor: ");
        }
        return inputPlayer();
    }

    // ============================================ EKSEKUSI MOVE MONSTER ============================================
    private static void monstermovement(Move move, Monster attacker, Monster enemy,
            ArrayList<ElementEffectivity> arreffectivity, ArrayList<Monster> arrmonster, Player player) {
        if (attacker.getStatusCondition().equals("SLEEP")) {
            System.out.printf("Monster %s milik %s gagal mengeksekusi Move karena sedang dalam status condition SLEEP%n",
                    attacker.getName(), player.getName());
            attacker.setExtendCondition(attacker.getExtendCondition()-1);
            System.out.printf("Sisa SLEEP monster %s %d turn %n", attacker.getName() ,attacker.getExtendCondition());
            if (attacker.getExtendCondition() == 0) {
                attacker.setCondition("-");
                System.out.printf("Sleep monster %s sudah habis, status condition monster kembali normal%n", attacker.getName());
            }
        } else if (attacker.getStatusCondition().equals("PARALYZE") && attacker.getExtendCondition() == 1) {
            System.out.printf(
                    "Monster %s milik %s gagal mengeksekusi Move karena tidak bisa bergerak satu round akibat status condition PARALIZE%n",
                    attacker.getName(), player.getName());
            attacker.setExtendCondition(0);
        } else {
            if (attacker.getBaseStats().getHealthPoint() != 0) {
                System.out.printf("Hasil Eksekusi Move %s oleh Monster %s milik %s:%n", move.getmovename(), attacker.getName(), player.getName());
                move.useMove(attacker, enemy, arreffectivity, arrmonster);
                attacker.checkMoveAmmunition(move);
            }
        }
    }

    // ============================================ MAIN ========================================================================================
    public static void main(String[] args) {
        // Creating ArrayList Move, Monster, and Effectivity
        ArrayList<Move> arrmove = new ArrayList<Move>();
        ArrayList<Monster> arrmonster = new ArrayList<Monster>();
        ArrayList<ElementEffectivity> arreffectivity = new ArrayList<ElementEffectivity>();

        boolean exception = false;

        // ============================================ TRY READING MONSTER POOL AND MOVE POOL ============================================
        try {
            CSVReader efreader = new CSVReader(new File(Main.class.getResource("configs/elementeffectivity.csv").toURI()),";");
                efreader.setSkipHeader(true);
                List<String[]> eflines = efreader.read();
                for (String[] efline : eflines) {
                    // Parsing for element effectivity constructor
                    Double ef = Double.parseDouble(efline[2]);
                    ElementType atk = ElementType.valueOf(efline[0]);
                    ElementType tar = ElementType.valueOf(efline[1]);
                    ElementEffectivity eleffectivity = new ElementEffectivity(atk, tar, ef);
                    // Adding elementeffectivity object to arraylist elementeffectivity
                    arreffectivity.add(eleffectivity); 
                }
            CSVReader monreader = new CSVReader(new File(Main.class.getResource("configs/monsterpool.csv").toURI()), ";");
            CSVReader movreader = new CSVReader(new File(Main.class.getResource("configs/movepool.csv").toURI()), ";");
            monreader.setSkipHeader(true);
            movreader.setSkipHeader(true);
            List<String[]> monlines = monreader.read();
            List<String[]> movlines = movreader.read();
            // Creating Move Pool
            for (String[] movline : movlines) {
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
                    NormalMove mov = new NormalMove(idmove, movetaip, movename, moveelementType, accuracy, priority,
                            ammunition, target, damage);
                    arrmove.add(mov);
                } else if (movetaip.equals(MoveType.SPECIAL)) {
                    Double damage = Double.parseDouble(movline[8]);
                    SpecialMove mov = new SpecialMove(idmove, movetaip, movename, moveelementType, accuracy, priority,
                            ammunition, target, damage);
                    arrmove.add(mov);
                } else if (movetaip.equals(MoveType.STATUS)) {
                    String condition = movline[8];
                    String statbuff = movline[9];
                    String[] arrofstatbuff = statbuff.split(",");
                    ArrayList<Integer> effect = new ArrayList<Integer>();
                    for (String a : arrofstatbuff) {
                        Integer num = Integer.parseInt(a);
                        effect.add(num);
                    }
                    Stats<Integer> statsbuff = new Stats<Integer>(effect.get(0), effect.get(1), effect.get(2), effect.get(3), effect.get(4), effect.get(5));
                    // StatsBuff effect = new StatsBuff(Double.parseDouble(arrofstatbuff[0]), Integer.parseInt(arrofstatbuff[1]), Integer.parseInt(arrofstatbuff[2]), Integer.parseInt(arrofstatbuff[3]), Integer.parseInt(arrofstatbuff[4]), Integer.parseInt(arrofstatbuff[5]));
                    StatusMove mov = new StatusMove(idmove, movetaip, movename, moveelementType, accuracy, priority,
                            ammunition, target, condition, statsbuff);
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
                String[] arrofeltaip = eltaip.split(",");
                for (String a : arrofeltaip) {
                    eltype.add(ElementType.valueOf(a));
                }
                String stat = monline[3];
                String[] arrofstats = stat.split(",");
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
                for (String a : arrofmov) {
                    Integer i = Integer.parseInt(a) - 1;
                    movesid.add(i);
                }
                Monster mons = new Monster(idmonster, monstername, eltype, basestats, movesid);
                arrmonster.add(mons);
            }
        } catch (Exception e) {
            exception = true;
        }
        Random rand = new Random();
        ArrayList<Monster> player1mons = new ArrayList<Monster>();
        ArrayList<Monster> player2mons = new ArrayList<Monster>();
        try {
            CSVReader monreader = new CSVReader(new File(Main.class.getResource("configs/monsterpool.csv").toURI()), ";");
            CSVReader movreader = new CSVReader(new File(Main.class.getResource("configs/movepool.csv").toURI()), ";");
            monreader.setSkipHeader(true);
            movreader.setSkipHeader(true);
            List<String[]> monlines = monreader.read();
            List<String[]> movlines = movreader.read();
            // Random Monster
            for (int i = 0; i < 6; i++) {
                int id1 = rand.nextInt(arrmonster.size());
                int id2 = rand.nextInt(arrmonster.size());

                int j = 0;
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
                        Stats<Double> newstats = new Stats<Double>(stats.get(0), stats.get(1), stats.get(2),
                                stats.get(3), stats.get(4), stats.get(5));

                        Monster monster1 = new Monster(arrmonster.get(id1), newstats);

                        player1mons.add(monster1);

                        DefaultMove mov = new DefaultMove();
                        monster1.getMoves().add(mov);

                        for (int move : monster1.getmovesid()) {

                            for (String[] movline : movlines) {
                                if (move == Integer.parseInt(movline[0]) - 1) {

                                    int ammunition = Integer.parseInt(movline[6]);

                                    if (movline[1].equals("NORMAL")) {
                                        NormalMove normalMove = new NormalMove((NormalMove) arrmove.get(move),
                                                ammunition);
                                        monster1.getMoves().add(normalMove);
                                    } else if (movline[1].equals("SPECIAL")) {
                                        SpecialMove specialMove = new SpecialMove((SpecialMove) arrmove.get(move),
                                                ammunition);
                                        monster1.getMoves().add(specialMove);
                                    } else if (movline[1].equals("STATUS")) {
                                        StatusMove statusMove = new StatusMove((StatusMove) arrmove.get(move),
                                                ammunition);
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
                        Stats<Double> newstats = new Stats<Double>(stats.get(0), stats.get(1), stats.get(2),
                                stats.get(3), stats.get(4), stats.get(5));

                        Monster monster2 = new Monster(arrmonster.get(id2), newstats);

                        player2mons.add(monster2);

                        DefaultMove mov = new DefaultMove();
                        monster2.getMoves().add(mov);

                        for (int move : monster2.getmovesid()) {

                            for (String[] movline : movlines) {
                                if (move == Integer.parseInt(movline[0]) - 1) {

                                    int ammunition = Integer.parseInt(movline[6]);

                                    if (movline[1].equals("NORMAL")) {
                                        NormalMove normalMove = new NormalMove((NormalMove) arrmove.get(move),
                                                ammunition);
                                        monster2.getMoves().add(normalMove);
                                    } else if (movline[1].equals("SPECIAL")) {
                                        SpecialMove specialMove = new SpecialMove((SpecialMove) arrmove.get(move),
                                                ammunition);
                                        monster2.getMoves().add(specialMove);
                                    } else {
                                        StatusMove statusMove = new StatusMove((StatusMove) arrmove.get(move),
                                                ammunition);
                                        monster2.getMoves().add(statusMove);
                                    }
                                }
                            }
                        }
                    }

                    j++;
                }
            }
        } catch (Exception e) {
            exception = true;
        }

        // ============================================ MAIN PROGRAM ============================================
        System.out.println(ANSI_YELLOW + "=========== PERMAINAN MONSTER SAKU ===========" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Halo! Selamat datang di permainan Monster Saku" + ANSI_RESET);
        Scanner scan = new Scanner(System.in);

        int pilihanMenu = chooseGameMenu();

        while (pilihanMenu == 2) {
            help();
            System.out.println();
            System.out.println("Back to menu...\n");
            pilihanMenu = chooseGameMenu();
        }

        // ============================================ START GAME ============================================
        if (pilihanMenu == 1) {
            System.out.println();
            System.out.println(ANSI_GREEN + "================ GAME STARTED ================" + ANSI_RESET);
            System.out.print("Enter player 1 name: ");
            String player1name = scan.next();
            System.out.print("Enter player 2 name: ");
            String player2name = scan.next();
            System.out.println("loading...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
            }

            if (exception ==true) {
                System.out.println("\nMaaf, terjadi kesalahan pada aplikasi");
                System.out.println(ANSI_RED + "Keluar dari Permainan Monster Saku..." + ANSI_RESET);
                scan.close();
                return;
            }

            System.out.println();
            System.out.println(ANSI_CYAN + "=================== MONSTER ==================" + ANSI_RESET);
            // System.out.println("Player 1 name: " + player1name);
            // System.out.println("Player 2 name: " + player2name);

            Player player1 = new Player(player1name, player1mons);
            Player player2 = new Player(player2name, player2mons);
            System.out.println(ANSI_PURPLE + "List of Monster Player " + player1name + ANSI_RESET);
            player1.showListOfMonster(arrmonster);
            System.out.println("==============================================");
            System.out.println(ANSI_PURPLE + "List of Monster Player " + player2name + ANSI_RESET);
            player2.showListOfMonster(arrmonster);
            System.out.println(ANSI_GREEN + "================= GAME PLAY ==================" + ANSI_RESET);

            Integer rand1 = rand.nextInt(5) + 1;
            Integer rand2 = rand.nextInt(5) + 1;
            Monster monsterPlayer1 = player1.getListOfMonster().get(rand1);
            Monster monsterPlayer2 = player2.getListOfMonster().get(rand2);
            System.out.printf(ANSI_PURPLE + "%s" + ANSI_RESET + " akan memainkan " + ANSI_CYAN +"Monster %s" + ANSI_RESET + " di awal permainan%n", player1.getName(), monsterPlayer1.getName());
            System.out.printf(ANSI_PURPLE + "%s" + ANSI_RESET + " akan memainkan " + ANSI_CYAN +"Monster %s" + ANSI_RESET + " di awal permainan%n", player2.getName(), monsterPlayer2.getName());

            int inputmove2idx = 0;
            int inputmove1idx = 0;
            int switchMonster = 0;

            int turn = 1;
            while (player1.getNumberOfMonster() != 0 && player2.getNumberOfMonster() != 0) {
                System.out.println();

                // ========================================= GILIRAN PLAYER 1 =========================================
                System.out.printf("Giliran : " + ANSI_PURPLE + "%s" + ANSI_RESET + "%n", player1.getName() + ANSI_RESET);
                System.out.println();
                menuInGame(player1, monsterPlayer1, turn, arrmonster);
                System.out.println();
                System.out.println(ANSI_YELLOW + "======= Pilih aksi yang akan dilakukan =======\n" + ANSI_RESET + "1.Switch (mengganti monster) \n2.Move (melakukan pergerakan)");
                System.out.println();
                System.out.print("Masukkan nomor: ");
                int op1 = inputPlayer();
                while (player1.getListOfMonster().size()==1 && op1==1) {
                    System.out.println(ANSI_RED+"Monster tersisa satu, tidak bisa melakukan switch"+ANSI_RESET);
                    System.out.print("Masukkan nomor: ");
                    op1 = inputPlayer();
                }
                System.out.println();
                if (op1 == 1) {
                    switchMonster = chooseMonster(player1, monsterPlayer1);
                    monsterPlayer1.setStatsBuff(new Stats<Integer>(0,0,0,0,0,0)); //reset stats buff
                    monsterPlayer1 = player1.getListOfMonster().get(switchMonster);
                } else if (op1 == 2) {
                    inputmove1idx = chooseMove(monsterPlayer1);
                }

                // ========================================= GILIRAN PLAYER 2 =========================================
                System.out.println();
                System.out.printf("Giliran " + ANSI_PURPLE + "%s" + ANSI_RESET + " %n", player2.getName());
                System.out.println();
                menuInGame(player2, monsterPlayer2, turn, arrmonster);
                System.out.println();
                System.out.println(ANSI_YELLOW + "======= Pilih aksi yang akan dilakukan =======\n" + ANSI_RESET + "1.Switch (mengganti monster) \n2.Move (melakukan pergerakan)");
                System.out.println();
                System.out.print("Masukkan nomor: ");
                int op2 = inputPlayer();
                while (player2.getListOfMonster().size()==1 && op2==1) {
                    System.out.println(ANSI_RED+"Monster tersisa satu, tidak bisa melakukan switch"+ANSI_RESET);
                    System.out.print("Masukkan nomor: ");
                    op2 = inputPlayer();
                }
                System.out.println();
                if (op2 == 1) {
                    switchMonster = chooseMonster(player2, monsterPlayer2);
                    monsterPlayer2.setStatsBuff(new Stats<Integer>(0,0,0,0,0,0)); //reset stats buff
                    monsterPlayer2 = player2.getListOfMonster().get(switchMonster);
                } else if (op2 == 2) {
                    inputmove2idx = chooseMove(monsterPlayer2);
                }

                // ====================================================== RESOLUSI ======================================================

                System.out.println();
                System.out.println(ANSI_YELLOW + "================= Resolution =================" + ANSI_RESET);

                 // ================================================== DAMAGE CALCULATION ===============================================
                try {
                    if (op1 == 1 && op2 == 1) {
                        System.out.printf("%s mengganti monster yang dimainkan menjadi %s%n", player1.getName(), monsterPlayer1.getName());
                        System.out.printf("%s mengganti monster yang dimainkan menjadi %s%n", player2.getName(), monsterPlayer2.getName());
                    } else if (op1 == 1 && op2 == 2) {
                        Move moveMonster2 = monsterPlayer2.getMoves().get(inputmove2idx);
                        System.out.printf("%s mengganti monster yang dimainkan menjadi %s%n", player1.getName(), monsterPlayer1.getName());
                        System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer2.getName(), player2.getName(), moveMonster2.getmovename());
                        System.out.println();
                        System.out.println(ANSI_RED + "Damage Calculation" + ANSI_RESET);
                        System.out.println(ANSI_RED + "Calculating..." + ANSI_RESET);
                        monstermovement(moveMonster2, monsterPlayer2, monsterPlayer1, arreffectivity, arrmonster, player2);
                    } else if (op2 == 1 && op1 == 2) {
                        Move moveMonster1 = monsterPlayer1.getMoves().get(inputmove1idx);
                        System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer1.getName(), player1.getName(), moveMonster1.getmovename());
                        System.out.printf("%s mengganti monster yang dimainkan menjadi %s%n", player2.getName(), monsterPlayer2.getName());
                        System.out.println();
                        System.out.println(ANSI_RED + "Damage Calculation" + ANSI_RESET);
                        System.out.println(ANSI_RED + "Calculating..." + ANSI_RESET);
                        monstermovement(moveMonster1, monsterPlayer1, monsterPlayer2, arreffectivity, arrmonster, player1);
                    } else if (op1 == 2 && op2 == 2) {
                        Move moveMonster1 = monsterPlayer1.getMoves().get(inputmove1idx);
                        Move moveMonster2 = monsterPlayer2.getMoves().get(inputmove2idx);
                        System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer1.getName(), player1.getName(), moveMonster1.getmovename());
                        System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer2.getName(), player2.getName(), moveMonster2.getmovename());
                        System.out.println();
                        System.out.println(ANSI_RED + "Damage Calculation" + ANSI_RESET);
                        System.out.println(ANSI_RED + "Calculating..." + ANSI_RESET);
                        if (moveMonster1.getpriority() > moveMonster2.getpriority()) {
                            monstermovement(moveMonster1, monsterPlayer1, monsterPlayer2, arreffectivity, arrmonster, player1);
                            monstermovement(moveMonster2, monsterPlayer2, monsterPlayer1, arreffectivity, arrmonster, player2);
                        } else if (moveMonster1.getpriority() < moveMonster2.getpriority()) {                      
                            monstermovement(moveMonster2, monsterPlayer2, monsterPlayer1,arreffectivity, arrmonster, player2);
                            monstermovement(moveMonster1, monsterPlayer1, monsterPlayer2, arreffectivity, arrmonster, player1);
                        } else {
                            if (monsterPlayer1.getSpeed() > monsterPlayer2.getSpeed()) {                                
                                monstermovement(moveMonster1, monsterPlayer1, monsterPlayer2, arreffectivity, arrmonster, player1);                                
                                monstermovement(moveMonster2, monsterPlayer2, monsterPlayer1, arreffectivity, arrmonster, player2);
                            } else if (monsterPlayer1.getSpeed() < monsterPlayer2.getSpeed()) {
                                monstermovement(moveMonster2, monsterPlayer2, monsterPlayer1, arreffectivity, arrmonster, player2);                                
                                monstermovement(moveMonster1, monsterPlayer1, monsterPlayer2,  arreffectivity, arrmonster, player1);
                            } else {
                                int urutan = rand.nextInt(2)+1;
                                if (urutan==1) {
                                    monstermovement(moveMonster1, monsterPlayer1, monsterPlayer2, arreffectivity, arrmonster, player1);                                
                                    monstermovement(moveMonster2, monsterPlayer2, monsterPlayer1, arreffectivity, arrmonster, player2);
                                } else {
                                    monstermovement(moveMonster2, monsterPlayer2, monsterPlayer1, arreffectivity, arrmonster, player2);                                
                                    monstermovement(moveMonster1, monsterPlayer1, monsterPlayer2, arreffectivity, arrmonster, player1);
                                }
                            }
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                // ================================================== AFTER DAMAGE CALCULATION ===================================================
                System.out.println();
                System.out.println(ANSI_YELLOW + "=========== After Damage Calculation =========" + ANSI_RESET);
                System.out.println(ANSI_RED + "Calculating..." + ANSI_RESET);
                try {
                    if (!monsterPlayer1.isEliminated()) {
                        if (!monsterPlayer1.isStatusConditionNull()) {
                            System.out.printf("After damage untuk Monster %s milik %s: %n", monsterPlayer1.getName(), player1.getName());
                            monsterPlayer1.afterDamage(arrmonster);
                        } else {
                            System.out.printf("After damage untuk Monster %s milik %s: %nTidak ada (Status Condition Normal) %n", monsterPlayer1.getName(), player1.getName());
                        } 
                    }
                    if (!monsterPlayer2.isEliminated()) {
                        if (!monsterPlayer2.isStatusConditionNull()) {
                            System.out.printf("After damage untuk Monster %s milik %s: %n", monsterPlayer2.getName(), player2.getName());
                            monsterPlayer2.afterDamage(arrmonster);
                        } else {
                            System.out.printf("After damage untuk Monster %s milik %s: %nTidak ada (Status Condition Normal)%n", monsterPlayer2.getName(), player2.getName());
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                // ================================================== AFTER EFFECT ===================================================
                System.out.println();
                if (monsterPlayer1.isEliminated()) {
                    System.out.printf(ANSI_RED + "Monster %s milik %s telah dikalahkan%n" + ANSI_RESET, monsterPlayer1.getName(), player1.getName());
                    player1.getListOfMonster().remove(monsterPlayer1);
                    if (player1.getListOfMonster().size() != 0) {
                        switchMonster = chooseMonster(player1, null);
                        monsterPlayer1 = player1.getListOfMonster().get(switchMonster);
                    }
                    System.out.println();
                }
                if (monsterPlayer2.isEliminated()) {
                    System.out.printf(ANSI_RED + "Monster %s milik %s telah dikalahkan%n" + ANSI_RESET, monsterPlayer2.getName(), player2.getName());
                    player2.getListOfMonster().remove(monsterPlayer2);
                    if (player2.getListOfMonster().size() != 0) {
                        switchMonster = chooseMonster(player2, null);
                        monsterPlayer2 = player2.getListOfMonster().get(switchMonster);
                    }
                    System.out.println();
                }

                turn++;
                if (player1.getListOfMonster().size()!=0 && player2.getListOfMonster().size()!=0) {
                    System.out.println(ANSI_YELLOW+"================= END OF TURN ================"+ANSI_RESET);
                }
            }
            System.out.println(ANSI_RED+"================== GAME OVER ================="+ANSI_RESET);
            if (player1.getListOfMonster().size()!=0) {
                System.out.printf(ANSI_YELLOW + "Congratulations !!! " + ANSI_PURPLE + " %s Wins%n" + ANSI_RESET, player1.getName());
            } else if (player2.getListOfMonster().size()!=0){
                System.out.printf(ANSI_YELLOW + "Congratulations !!! " + ANSI_PURPLE + " %s Wins%n" + ANSI_RESET, player2.getName());
            } else {
                System.out.println(ANSI_YELLOW + "Semua monster milik kedua player tereliminasi -- hasil akhir game DRAW" + ANSI_RESET);
            }

        } else {
            // exit
            scan.close();
            System.out.println(ANSI_RED + "Keluar dari Permainan Monster Saku..." + ANSI_RESET);
            return;
        }
        scan.close();
    }
}
