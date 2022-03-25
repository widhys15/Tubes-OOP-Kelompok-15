package com.monstersaku;

import com.monstersaku.util.CSVReader;
import com.monstersaku.util.ElementType;
import com.monstersaku.util.Monster;
import com.monstersaku.util.Move;
import com.monstersaku.util.Stats;
import com.monstersaku.util.Player;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class FileReader {
    public Monster createMonster(){
        try {
            System.out.printf("Filename: %s\n", "configs/monsterpool.csv");
            CSVReader reader = new CSVReader(new File(Main.class.getResource("configs/monsterpool.csv").toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            System.out.println("=========== CONTENT START ===========");
            for (String[] line : lines) {
                for (String word : line) {
                    System.out.printf("%s ", word);
                }
                System.out.println();
                for(int i = 0; i <= 4; i++){
                    System.out.println(line[i]);
                }
                String stat = line[3];
                String[] arrofstats = stat.split(",",7);
                ArrayList<Double> stats = new ArrayList<Double>();
                for(String a : arrofstats){
                    Double d = Double.parseDouble(a);
                    System.out.println(d);
                    stats.add(d);
                }
                System.out.println(stats.get(0));
                Stats basestats = new Stats(stats.get(0), stats.get(1),stats.get(2),stats.get(3),stats.get(4),stats.get(5));
                basestats.printStats();
                
                ArrayList<ElementType> eltype = new ArrayList<ElementType>();
                String eltaip = line[2];
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
                String mov = line[4];
                String[] arrofmov = mov.split(",",7);
                // bacamovpul
                System.out.printf("Filename: %s\n", "configs/movepool.csv");
                CSVReader reader1 = new CSVReader(new File(Main.class.getResource("configs/movepool.csv").toURI()), ";");
                reader1.setSkipHeader(true);
                List<String[]> lines1 = reader1.read();
                for(String[] line1 : lines1){
                    for (String word1 : line1) {
                        System.out.printf("%s ", word1);
                    }
                    System.out.println();
                    for(int i = 0; i < 9; i++){
                        System.out.println(line1[i]);
                }
                ArrayList<Move> move = new ArrayList<Move>();
    
                move.add(mov);
                //Monster hoho = new Monster(line[1], eltype, basestats, move, null );
            }
            System.out.println("=========== CONTENT END ===========");
            System.out.println();
            } catch (Exception e) {
            }
    }
}
