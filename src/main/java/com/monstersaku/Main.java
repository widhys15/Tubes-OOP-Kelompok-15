package com.monstersaku;

import com.monstersaku.util.*;
import java.util.*;
import java.io.File;

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
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("== Pilih move yang akan digunakan ==\n");
            for (int i = 0; i <= monster.getMoves().size()-1; i++) {
                System.out.println("MOVE NOMOR " + (i + 1));
                monster.getMoves().get(i).printmonsMove();
                System.out.println();
            }
            System.out.print("Masukkan nomor move: ");
            String input = scan.next();
            int monsterMove = Integer.parseInt(input);
            while (monsterMove > monster.getMoves().size() || monsterMove < 1) {
                System.out.println("ERROR: Input di luar range!");
                System.out.print("Masukkan nomor move: ");
                monsterMove = scan.nextInt();
            }
            return monsterMove-1;
        } catch (NumberFormatException e) {
            System.out.println("ERROR: INPUT TIDAK VALID!");
            System.out.println("MASUKIN ANGKA");
        }
        return 0;
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

    private static void monstermovement(Move move, Monster attacker, Monster enemy, String condition, ArrayList<ElementEffectivity> arreffectivity, ArrayList<Monster> arrmonster) {
        if (condition.equals("SLEEP")) {
            System.out.printf("Monster %s gagal mengeksekusi Move karena sedang dalam status condition SLEEP%n", attacker.getName());
        } else if (condition.equals("PARALYZE") && attacker.getExtendCondition()==1) {
            System.out.printf("Monster %s gagal mengeksekusi Move karena tidak bisa bergerak satu round akibat status condition PARALIZE%n", attacker.getName());
            attacker.setExtendCondition(0);
        } else {
            if (attacker.getBaseStats().getHealthPoint() !=0) {
                move.useMove(attacker, enemy, arreffectivity, arrmonster);
            }
        }
    }
    // ============================================ MAIN ========================================================================================
    public static void main(String[] args) {
        // Creating ArrayList Move, Monster, and Effectivity
        ArrayList<Move> arrmove = new ArrayList<Move>();
        ArrayList<Monster> arrmonster = new ArrayList<Monster>();
        ArrayList<ElementEffectivity> arreffectivity = new ArrayList<ElementEffectivity>();

        // ============================================ TRY READING MONSTER POOL AND MOVE POOL ============================================
        try {
            System.out.println("READING FILE");
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
            for(String[] movline : movlines){
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
                for(String a: arrofmov){
                    Integer i = Integer.parseInt(a) - 1;
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

                        DefaultMove mov = new DefaultMove();
                        monster1.getMoves().add(mov);

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
                        
                        player2mons.add(monster2);

                        DefaultMove mov = new DefaultMove();
                        monster2.getMoves().add(mov);

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
                }


                // ====================================================== RESOLUSI ======================================================

                System.out.println();
                System.out.println("============= RESOLUTION ============");


                String conditionMonster1 = monsterPlayer1.getStatusCondition();
                String conditionMonster2 = monsterPlayer2.getStatusCondition();


                 // ================================================== DAMAGE CALCULATION ===============================================
                if (op1 == 1 && op2 == 1) {
                    System.out.printf("%s mengganti monster yang dimainkan menjadi %s%n", player1.getName(), monsterPlayer1.getName());
                    System.out.printf("%s mengganti monster yang dimainkan menjadi %s%n", player2.getName(), monsterPlayer2.getName());
                } else if (op1 == 1 && op2 == 2) {
                    Move moveMonster2 = monsterPlayer2.getMoves().get(inputmove2idx);
                    System.out.printf("%s mengganti monster yang dimainkan menjadi %s%n", player1.getName(), monsterPlayer1.getName());
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer2.getName(), player2.getName(), moveMonster2.getmovename());
                    System.out.println();
                    System.out.println("Damage Calculation");
                    System.out.println("Calculating...");
                    monstermovement(moveMonster2, monsterPlayer2, monsterPlayer1, conditionMonster2, arreffectivity, arrmonster);
                } else if (op2 == 1 && op1 == 2) {
                    Move moveMonster1 = monsterPlayer1.getMoves().get(inputmove1idx);
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer1.getName(), player1.getName(), moveMonster1.getmovename());
                    System.out.printf("%s mengganti monster yang dimainkan menjadi %s%n", player2.getName(), monsterPlayer2.getName());
                    System.out.println();
                    System.out.println("Damage Calculation");
                    System.out.println("Calculating...");
                    monstermovement(moveMonster1, monsterPlayer1, monsterPlayer2, conditionMonster1, arreffectivity, arrmonster);
                } else if (op1 == 2 && op2 == 2) {
                    Move moveMonster1 = monsterPlayer1.getMoves().get(inputmove1idx);
                    Move moveMonster2 = monsterPlayer2.getMoves().get(inputmove2idx);
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer1.getName(), player1.getName(), moveMonster1.getmovename());
                    System.out.printf("Monster %s milik %s melakukan move %s%n", monsterPlayer2.getName(), player2.getName(), moveMonster2.getmovename());
                    System.out.println();
                    System.out.println("Damage Calculation");
                    System.out.println("Calculating...");
                    if (moveMonster1.getpriority() > moveMonster2.getpriority()) {
                        System.out.printf("Eksekusi Move %s oleh Monster %s milik %s:%n", moveMonster1.getmovename(), monsterPlayer1.getName(), player1.getName());
                        monstermovement(moveMonster1, monsterPlayer1, monsterPlayer2, conditionMonster1, arreffectivity, arrmonster);
                        System.out.printf("Eksekusi Move %s oleh Monster %s milik %s:%n", moveMonster2.getmovename(), monsterPlayer2.getName(), player2.getName());
                        monstermovement(moveMonster2, monsterPlayer2, monsterPlayer1, conditionMonster2, arreffectivity, arrmonster);
                    } else if (moveMonster1.getpriority() < moveMonster2.getpriority()) {
                        System.out.printf("Eksekusi Move %s oleh Monster %s milik %s:%n", moveMonster2.getmovename(), monsterPlayer2.getName(), player2.getName());
                        monstermovement(moveMonster2, monsterPlayer2, monsterPlayer1, conditionMonster2, arreffectivity, arrmonster);
                        System.out.printf("Eksekusi Move %s oleh Monster %s milik %s:%n", moveMonster1.getmovename(), monsterPlayer1.getName(), player1.getName());
                        monstermovement(moveMonster1, monsterPlayer1, monsterPlayer2, conditionMonster1, arreffectivity, arrmonster);
                    } else {
                        if (monsterPlayer1.getBaseStats().getSpeed() >= monsterPlayer2.getBaseStats().getSpeed()) {
                            System.out.printf("Eksekusi Move %s oleh Monster %s milik %s:%n", moveMonster1.getmovename(), monsterPlayer1.getName(), player1.getName());
                            monstermovement(moveMonster1, monsterPlayer1, monsterPlayer2, conditionMonster1, arreffectivity, arrmonster);
                            System.out.printf("Eksekusi Move %s oleh Monster %s milik %s:%n", moveMonster2.getmovename(), monsterPlayer2.getName(), player2.getName());
                            monstermovement(moveMonster2, monsterPlayer2, monsterPlayer1, conditionMonster2, arreffectivity, arrmonster);
                        } else {
                            System.out.printf("Eksekusi Move %s oleh Monster %s milik %s:%n", moveMonster2.getmovename(), monsterPlayer2.getName(), player2.getName());
                            monstermovement(moveMonster2, monsterPlayer2, monsterPlayer1, conditionMonster2, arreffectivity, arrmonster);
                            System.out.printf("Eksekusi Move %s oleh Monster %s milik %s:%n", moveMonster1.getmovename(), monsterPlayer1.getName(), player1.getName());
                            monstermovement(moveMonster1, monsterPlayer1, monsterPlayer2, conditionMonster1, arreffectivity, arrmonster);
                        }
                    }
                }

                // ================================================== AFTER DAMAGE CALCULATION ===================================================
                System.out.println();
                System.out.println("After Damage Calculation");
                System.out.println("Calculating...");
                if (!monsterPlayer1.isEliminated()) {
                    if (!monsterPlayer1.isStatusConditionNull()) {
                        System.out.printf("After damage untuk Monster %s milik %s: %n", monsterPlayer1.getName(), player1.getName());
                        monsterPlayer1.afterDamage(arrmonster);
                    } else {
                        System.out.printf("After damage untuk Monster %s milik %s: %nTidak ada (Status Condition Normal)%n", monsterPlayer1.getName(), player1.getName());
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

                // ================================================== AFTER EFFECT ===================================================
                System.out.println();
                if (monsterPlayer1.isEliminated()) {
                    System.out.printf("Monster %s milik %s telah dikalahkan, pilih monster lain%n", monsterPlayer1.getName(), player1.getName());
                    player1.getListOfMonster().remove(monsterPlayer1);
                    if (player1.getListOfMonster().size()!=0) {
                        switchMonster = chooseMonster(player1, null);
                        monsterPlayer1 = player1.getListOfMonster().get(switchMonster);
                    }
                } 
                if (monsterPlayer2.isEliminated()) {
                    System.out.printf("Monster %s milik %s telah dikalahkan, pilih monster lain%n", monsterPlayer2.getName(), player2.getName());
                    player2.getListOfMonster().remove(monsterPlayer2);
                    if (player2.getListOfMonster().size()!=0) {
                        switchMonster = chooseMonster(player2, null);
                        monsterPlayer2 = player2.getListOfMonster().get(switchMonster);
                    }
                }

                turn++;
                System.out.println();
                if (player1.getListOfMonster().size()!=0 && player2.getListOfMonster().size()!=0) {
                    System.out.println("============= END OF TURN ===========");
                }
            }
            System.out.println("============== GAME OVER ============");
            if (player1.getListOfMonster().size()!=0) {
                System.out.printf("Player1: %s menang yey%n", player1.getName());
            } else if (player2.getListOfMonster().size()!=0){
                System.out.printf("Player2: %s menang yey%n", player2.getName());
            } else {
                System.out.println("Semua monster milik kedua player tereliminasi -- hasil akhir game DRAW");
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
