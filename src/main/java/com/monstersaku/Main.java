package com.monstersaku;

import com.monstersaku.util.CSVReader;
import com.monstersaku.util.ElementType;
import com.monstersaku.util.Monster;
import com.monstersaku.util.Move;
import com.monstersaku.util.MoveType;
import com.monstersaku.util.Target;
import com.monstersaku.util.StatusMove;
import com.monstersaku.util.Stats;
import com.monstersaku.util.Player;

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
    ArrayList<Move> arrmove = new ArrayList<Move>();
    ArrayList<Monster> arrmonster = new ArrayList<Monster>();
    // Try reading monsterpool and movepool csv
    try {
        System.out.printf("Filename: %s\n", "configs/movepool.csv");
        CSVReader reader = new CSVReader(new File(Main.class.getResource("configs/movepool.csv").toURI()), ";");
        reader.setSkipHeader(true);
        List<String[]> lines = reader.read();
        System.out.println("=========== CONTENT START ===========");
        for (String[] line : lines) {
            // Parsing for move constructor, creating move object and adding to arraylist move
            Integer idmove = Integer.parseInt(line[0]);
            MoveType movetaip = MoveType.valueOf(line[1]);
            String movename = line[2];
            ElementType moveelementType = ElementType.valueOf(line[3]);
            Integer accuracy = Integer.parseInt(line[4]);
            Integer priority = Integer.parseInt(line[5]);
            Integer ammunition = Integer.parseInt(line[6]);
            Target target = Target.valueOf(line[7]);
            if(movetaip.equals(MoveType.STATUS)){
                String moveeffect = line[8];
                String movestats = line[9];
                String[] arrofmovestats = movestats.split(",",6);
                Double hp = Double.parseDouble(arrofmovestats[0]);
                Double atk = Double.parseDouble(arrofmovestats[1]);
                Double def = Double.parseDouble(arrofmovestats[2]);
                Double spcatk = Double.parseDouble(arrofmovestats[3]);
                Double spcdef = Double.parseDouble(arrofmovestats[4]);
                Double speed = Double.parseDouble(arrofmovestats[5]);
                StatusMove mov = new StatusMove(idmove, movetaip, movename, moveelementType, accuracy, priority, ammunition, target, moveeffect, hp, atk, def, spcatk, spcdef, speed);
                arrmove.add(mov);
                mov.printMove();
                
            }else{
                Double damage = Double.parseDouble(line[8]);
                Move mov = new Move(idmove, movetaip, movename, moveelementType, accuracy, priority, ammunition, target, damage);
                arrmove.add(mov);
                mov.printMove();
            }
            System.out.println();    
        }
        System.out.printf("Filename: %s\n", "configs/monsterpool.csv");
        CSVReader reader1 = new CSVReader(new File(Main.class.getResource("configs/monsterpool.csv").toURI()), ";");
        reader1.setSkipHeader(true);
        List<String[]> lines1 = reader1.read();
        System.out.println("=========== CONTENT START ===========");

        for (String[] line1 : lines1) {
            // Parsing for monster constructor, creating monster object and adding to arraylist monster
            String stat = line1[3];
            String[] arrofstats = stat.split(",",7);
            ArrayList<Double> stats = new ArrayList<Double>();
            // Parsing for stats
            for(String a : arrofstats){
                Double d = Double.parseDouble(a);
                stats.add(d);
            }
            Stats<Double> basestats = new Stats<Double>(stats.get(0), stats.get(1),stats.get(2),stats.get(3),stats.get(4),stats.get(5));
            // Creating element type arraylist
            ArrayList<ElementType> eltype = new ArrayList<ElementType>();
            String eltaip = line1[2];
            String[] arrofeltaip = eltaip.split(",",7);
            for(String a : arrofeltaip){
                if(a.equals("FIRE")){
                    eltype.add(ElementType.FIRE);
                }
                if(a.equals("NORMAL")){
                    eltype.add(ElementType.NORMAL);
                }
                if(a.equals("GRASS")){
                    eltype.add(ElementType.GRASS);
                }
                if(a.equals("WATER")){
                    eltype.add(ElementType.WATER);
                }
            }
            String mov = line1[4];
            String[] arrofmov = mov.split(",",7);
            // Adding move object to listofmove monster
            ArrayList<Move> monsmove = new ArrayList<Move>();
            for(int i = 0; i < arrofmov.length; i++){
                monsmove.add(arrmove.get(Integer.valueOf(arrofmov[i])-1));
            }
            Integer idmons = Integer.parseInt(line1[0]);
            Monster mons = new Monster(idmons, line1[1], eltype, basestats, monsmove, "-");
            arrmonster.add(mons);
        }
        } catch (Exception e) {
        }
        // for(Move mov : arrmove){
        //     mov.printMove();
        //     System.out.println();
        // }
        // for(Monster mons : arrmonster){
        //     mons.printMonster();
        // }
        // Debug status move
        // StatusMove satmove = new StatusMove(1, MoveType.STATUS, "Satmove", ElementType.FIRE, 90, 1, 10, Target.ENEMY, "-", 90.0, 90.0, 90.0, 90.0, 90.0, 90.0);
        // satmove.printMove();
        // satmove.printmonsMove();
        
        // Main Program
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter player 1 name: ");
        String player1name = scan.nextLine();
        System.out.println("Enter player 2 name: ");
        String player2name = scan.nextLine();
        System.out.println("Player 1 name: " + player1name);
        System.out.println("Player 2 name: " + player2name);
        Random rand = new Random();
        Integer upperbound = arrmonster.size();
        ArrayList<Monster> player1monsterarray = new ArrayList<Monster>();
        for(int i = 0; i < 6; i++){
            Integer randommonster = rand.nextInt(upperbound);
            player1monsterarray.add(arrmonster.get(randommonster));
        }
        ArrayList<Monster> player2monsterarray = new ArrayList<Monster>();
        for(int i = 0; i < 6; i++){
            Integer randommonster = rand.nextInt(upperbound);
            player2monsterarray.add(arrmonster.get(randommonster));
        }
        Player player1 = new Player(player1name, player1monsterarray);
        Player player2 = new Player(player2name, player2monsterarray);
        System.out.println("List of Monster Player " + player1name);
        player1.showListOfMonster();
        System.out.println("List of Monster Player " + player2name);
        player2.showListOfMonster();
        // Testing akses elemen dari monster di player
        // player2.getListOfMonster().get(0).getMoves().get(0).useammunition();
        // player2.getListOfMonster().get(0).printMonster();

        // for (Monster m : arrmonster) {
        //     System.out.println(m.getName());
        // }

        Monster  monsterPlayer1 = player1.getListOfMonster().get(0);
        Monster  monsterPlayer2 = player2.getListOfMonster().get(0);

        int inputmove2 = 0;
        int inputmove1 = 0;

        // player1.getListOfMonster().remove(monsterPlayer1);

        // System.out.println("----------------------------------------------------------------");
        // for(int i = 0; i < player1.getNumberOfMonster(); i++){
        //     player1.getListOfMonster().get(i).printMonster();
        //     System.out.println();
        // }

        while (player1.getNumberOfMonster() != 0 || player2.getNumberOfMonster() !=0) {
            
            System.out.printf(("Giliran %s : pilih Move/Switch%n"), player1.getName());
            String op1 = scan.next();
            if (op1.equals("Switch")) {
                System.out.println("Pilih monster yang akan dimainkan (masukkan nomor monster)");
                for(int i = 0; i < player1.getNumberOfMonster(); i++){
                    player1.getListOfMonster().get(i).printMonster();
                    System.out.println();
                }
                int switchMonster = scan.nextInt();
                if (monsterPlayer1 == player1.getListOfMonster().get(switchMonster-1)) {
                    System.out.println("Tidak valid");
                } else {
                    monsterPlayer1 = player1.getListOfMonster().get(switchMonster-1);
                }

            } else if (op1.equals("Move")) {
                System.out.println("Pilih move yang akan dipakai (masukkan nomor move)");
                for(Move move : monsterPlayer1.getMoves()){
                    move.printmonsMove();
                    System.out.println();
                }
            }

            System.out.printf(("Giliran %s : pilih Move/Switch%n"), player2.getName());
            String op2 = scan.next();
            if (op2.equals("Switch")) {
                System.out.println("Pilih monster yang akan dimainkan (masukkan nomor monster)");
                for(int i = 0; i < player1.getNumberOfMonster(); i++){
                    player1.getListOfMonster().get(i).printMonster();
                    System.out.println();
                }
                int switchMonster = scan.nextInt();
                if (monsterPlayer2 == player2.getListOfMonster().get(switchMonster-1)) {
                    System.out.println("Tidak valid");
                } else {
                    monsterPlayer2 = player2.getListOfMonster().get(switchMonster-1);
                }
            } else if (op2.equals("Move")) {
                System.out.println("Pilih move yang akan dipakai (masukkan nomor move)");
                for(Move move : monsterPlayer2.getMoves()){
                    move.printmonsMove();
                    System.out.println();
                    //input scan.next();

                }
            }

            // CEK STATUS CONDITION

            // RESOLUSI DAMAGE CALC
            if (op1.equals("Switch") && op2.equals("Switch")) {
                System.out.println("Monster player 1 berubah menjadi "+ monsterPlayer1.getName());
                System.out.println("Monster player 2 berubah menjadi "+ monsterPlayer2.getName());
            } else if (op1.equals("Switch") && op2.equals("Move")) {
                System.out.println("Monster player 1 berubah menjadi "+ monsterPlayer1.getName());
                if (monsterPlayer2.getMoves().get(inputmove2).gettarget()==Target.ENEMY) {
                    // damageCalculation(monsterPlayer2.getMoves().get(inputmove2), monsterPlayer1);
                } else {
                    // damageCalculation(monsterPlayer2.getMoves().get(inputmove2), monsterPlayer2);
                }
            } else if (op2.equals("Switch") && op1.equals("Move")) {
                System.out.println("Monster player 2 berubah menjadi "+ monsterPlayer2.getName());
                if (monsterPlayer1.getMoves().get(inputmove1).gettarget()==Target.ENEMY) {
                    // damageCalculation(monsterPlayer1.getMoves().get(inputmove1), monsterPlayer2);
                } else {
                    // damageCalculation(monsterPlayer1.getMoves().get(inputmove1), monsterPlayer1);
                }
            } else if (op1.equals("Move") && op2.equals("Move")) {
                if (monsterPlayer1.getMoves().get(inputmove1).getpriority() > monsterPlayer2.getMoves().get(inputmove2).getpriority()) {
                    if (monsterPlayer1.getMoves().get(inputmove1).gettarget()==Target.ENEMY) {
                        // damageCalculation(monsterPlayer1.getMoves().get(inputmove1), monsterPlayer2);
                    } else {
                        // damageCalculation(monsterPlayer1.getMoves().get(inputmove1), monsterPlayer1);
                    }

                    if (monsterPlayer2.getMoves().get(inputmove2).gettarget()==Target.ENEMY) {
                        // damageCalculation(monsterPlayer2.getMoves().get(inputmove2), monsterPlayer1);
                    } else {
                        // damageCalculation(monsterPlayer2.getMoves().get(inputmove2), monsterPlayer2);
                    }   
                }
                if (monsterPlayer1.getMoves().get(inputmove1).getpriority() < monsterPlayer2.getMoves().get(inputmove2).getpriority()) {
                    if (monsterPlayer2.getMoves().get(inputmove2).gettarget()==Target.ENEMY) {
                        // damageCalculation(monsterPlayer2.getMoves().get(inputmove2), monsterPlayer1);
                    } else {
                        // damageCalculation(monsterPlayer2.getMoves().get(inputmove2), monsterPlayer2);
                    }   
                    if (monsterPlayer1.getMoves().get(inputmove1).gettarget()==Target.ENEMY) {
                        // damageCalculation(monsterPlayer1.getMoves().get(inputmove1), monsterPlayer2);
                    } else {
                        // damageCalculation(monsterPlayer1.getMoves().get(inputmove1), monsterPlayer1);
                    }

                } else {
                    if (monsterPlayer1.getBaseStats().getSpeed() >= monsterPlayer2.getBaseStats().getSpeed()) {
                        if (monsterPlayer1.getMoves().get(inputmove1).gettarget()==Target.ENEMY) {
                            // damageCalculation(monsterPlayer1.getMoves().get(inputmove1), monsterPlayer2);
                        } else {
                            // damageCalculation(monsterPlayer1.getMoves().get(inputmove1), monsterPlayer1);
                        }
    
                        if (monsterPlayer2.getMoves().get(inputmove2).gettarget()==Target.ENEMY) {
                            // damageCalculation(monsterPlayer2.getMoves().get(inputmove2), monsterPlayer1);
                        } else {
                            // damageCalculation(monsterPlayer2.getMoves().get(inputmove2), monsterPlayer2);
                        }   
                    } else {
                        if (monsterPlayer2.getMoves().get(inputmove2).gettarget()==Target.ENEMY) {
                            // damageCalculation(monsterPlayer2.getMoves().get(inputmove2), monsterPlayer1);
                        } else {
                            // damageCalculation(monsterPlayer2.getMoves().get(inputmove2), monsterPlayer2);
                        }   
                        if (monsterPlayer1.getMoves().get(inputmove1).gettarget()==Target.ENEMY) {
                            // damageCalculation(monsterPlayer1.getMoves().get(inputmove1), monsterPlayer2);
                        } else {
                            // damageCalculation(monsterPlayer1.getMoves().get(inputmove1), monsterPlayer1);
                        }
                    }
                }
            }

            // AFTER DAMAGE CALC
            if (monsterPlayer1.isEliminated() && !monsterPlayer2.isEliminated()) {
                System.out.printf("Monster %s milik %s kalah, pilih monster lain", monsterPlayer1, player1);
                player1.getListOfMonster().remove(monsterPlayer1);
            }



            /*di dalem move ada 4 kondisi yang mungkin terjadi
                1. Player1 switch player2 switch 
                2. player1 switch player2 move -> langsung damage 
                3. player1 move player2 switch -> langsung damage
                4. player1 move player2 move -> check prio 

                setiap ada move:
                cek move status move --> cek target
                target masukin ke parameter target

                2 player pilih move:
                cek prio --> cek speed
                misalnya player1 dulu --> 


            */


           
            


        }

}}
