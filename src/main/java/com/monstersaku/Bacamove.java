package com.monstersaku;

import com.monstersaku.util.CSVReader;
import com.monstersaku.util.ElementType;
import com.monstersaku.util.MoveType;
import com.monstersaku.util.Monster;
import com.monstersaku.util.Move;
import com.monstersaku.util.StatusMoveLama;
import com.monstersaku.util.Stats;
import com.monstersaku.util.StatusCondition;
import com.monstersaku.util.Target;
import com.monstersaku.util.Player;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Bacamove{
    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/monsterpool.csv",
            "configs/movepool.csv",
            "configs/element-type-effectivity-chart.csv"));

    public static void main(String[] args) {
    try {
        System.out.printf("Filename: %s\n", "configs/movepool.csv");
        CSVReader reader = new CSVReader(new File(Main.class.getResource("configs/movepool.csv").toURI()), ";");
        reader.setSkipHeader(true);
        List<String[]> lines = reader.read();
        System.out.println("=========== CONTENT START ===========");
        ArrayList<Move> arrmove = new ArrayList<Move>();
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
                StatusMoveLama mov = new StatusMoveLama(idmove, movetaip, movename, moveelementType, accuracy, priority, ammunition, target, moveeffect, hp, atk, def, spcatk, spcdef, speed);
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
        System.out.println(arrmove);
        System.out.println("=========== CONTENT END ===========");
        System.out.println();
        } catch (Exception e) {
        }
}}