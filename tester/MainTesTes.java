package com.monstersaku.tester;

import com.monstersaku.Main;
import com.monstersaku.util.CSVReader;
import com.monstersaku.util.ElementType;
import com.monstersaku.util.Monster;
import com.monstersaku.util.Stats;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainTesTes {
    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/monsterpool.csv",
            "configs/movepool.csv",
            "configs/element-type-effectivity-chart.csv"));

    public static void main(String[] args) {
        try{
            System.out.printf("Filename: %s\n", "configs/monsterpool.csv");
            CSVReader reader = new CSVReader(new File(Main.class.getResource("configs/monsterpool.csv").toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            System.out.println("=========== CONTENT START ===========");

            for (String[] line : lines){
                System.out.println(line[0]);

//                String stat = line[3];
//                Stats baseStats = statTake(stat);
//                Monster monster = new Monster("Charmender", baseStats);
//                baseStats.printStats();
            }
        }catch (Exception e){}
    }
    public static Stats statTake(String take){
        String[] arrofStats = take.split(",", 7);
        ArrayList<Double> stats = new ArrayList<Double>();
        for (String string : arrofStats){
            Double statInput = Double.parseDouble(string);
            stats.add(statInput);
        }
        Stats baseStats = new Stats(stats.get(0), stats.get(1),stats.get(2),stats.get(3),stats.get(4),stats.get(5));
        return baseStats;
    }
}