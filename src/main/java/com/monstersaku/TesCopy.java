package com.monstersaku;

import com.monstersaku.util.*;

import java.util.Random;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class TesCopy {
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
        // Integer upperbound = arrmonster.size();
        // for (int i = 0; i < 2; i++) {
        //     Integer randommonster = rand.nextInt(upperbound);
        //     Monster m = new Monster();
        //     m.copyMonster(arrmonster.get(i));
        //     player1.add(m);
        // }
        System.out.println("------------------------------------");
        player1mons.get(0).getBaseStats().setHealthPoint(40.0);
        // player1.get(0).getMoves().get(1).setammunition(1);
        player1mons.get(0).printMonster();
        System.out.println("Jumlah monster = " + player1mons.size());
        // arrmonster.get(0).printMonster();
        System.out.println("------------------------------------");
        for(Monster mo: arrmonster){
            mo.printMonster();
            System.out.println();
        }
        // for(Monster m: player1mons){
        //     m.printMonster();
        //     System.out.println("aaaaaaaaaaaaaa");
        // }
        // for(Monster m: player2mons){
        //     m.printMonster();
        //     System.out.println("bbbbbbbbbbb");
        // }
    }
}
