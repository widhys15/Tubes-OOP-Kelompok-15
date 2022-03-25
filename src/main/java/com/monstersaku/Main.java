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

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/monsterpool.csv",
            "configs/movepool.csv",
            "configs/element-type-effectivity-chart.csv"));

    public static void main(String[] args) {
    //     for (String fileName : CSV_FILE_PATHS) {
    //         try {
    //             System.out.printf("Filename: %s\n", fileName);
    //             CSVReader reader = new CSVReader(new File(Main.class.getResource(fileName).toURI()), ";");
    //             reader.setSkipHeader(true);
    //             List<String[]> lines = reader.read();
    //             System.out.println("=========== CONTENT START ===========");
    //             for (String[] line : lines) {
    //                 for (String word : line) {
    //                     System.out.printf("%s ", word);
    //                 }
    //                 System.out.println();
    //             }
    //             System.out.println("=========== CONTENT END ===========");
    //             System.out.println();
    //         } catch (Exception e) {
    //             // do nothing
    //         }
    //     }
    // }
    ArrayList<Move> arrmove = new ArrayList<Move>();
    ArrayList<Monster> arrmonster = new ArrayList<Monster>();
    try {
        System.out.printf("Filename: %s\n", "configs/movepool.csv");
        CSVReader reader = new CSVReader(new File(Main.class.getResource("configs/movepool.csv").toURI()), ";");
        reader.setSkipHeader(true);
        List<String[]> lines = reader.read();
        System.out.println("=========== CONTENT START ===========");
        for (String[] line : lines) {
            // for (String word : line) {
            //     System.out.printf("%s ", word);
            // }
            // System.out.println();
            // for(int i = 0; i < 9; i++){
            //     System.out.println(line[i]);
            // }
            
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
            // for (String word1 : line1) {
            //     System.out.printf("%s ", word1);
            // }
            // System.out.println();
            // for(int i = 0; i <= 4; i++){
            //     System.out.println(line1[i]);
            // }
            String stat = line1[3];
            String[] arrofstats = stat.split(",",7);
            ArrayList<Double> stats = new ArrayList<Double>();
            for(String a : arrofstats){
                Double d = Double.parseDouble(a);
                // System.out.println(d);
                stats.add(d);
            }
            Stats basestats = new Stats(stats.get(0), stats.get(1),stats.get(2),stats.get(3),stats.get(4),stats.get(5));
            
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

            ArrayList<Move> monsmove = new ArrayList<Move>();
            for(int i = 0; i < arrofmov.length; i++){
                monsmove.add(arrmove.get(Integer.valueOf(arrofmov[i])-1));
            }
            // monsmove.add(arrmove.get(1));
            // monsmove.add(arrmove.get(2));
            // arrmove.get(1).printMove();
            System.out.println();
            System.out.println("JULMAH MOVE " + arrmove.size());
            System.out.println();
            Integer idmons = Integer.parseInt(line1[0]);
            Monster hoho = new Monster(idmons, line1[1], eltype, basestats, monsmove, "-");
            System.out.println("Ini data monster:");
            // hoho.printMonster();
        }
        
        System.out.println("=========== CONTENT END ===========");
        System.out.println();
        } catch (Exception e) {
        }
        for(Move move: arrmove){
            move.printMove();
        }
}}
