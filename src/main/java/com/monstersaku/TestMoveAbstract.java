package com.monstersaku;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import com.monstersaku.util.*;
=======
import com.monstersaku.util.AbsMove;
import com.monstersaku.util.CSVReader;
import com.monstersaku.util.DefaultMove;
import com.monstersaku.util.ElementEffectivity;
import com.monstersaku.util.NormalMove;
import com.monstersaku.util.SpecialMove;
import com.monstersaku.util.StatsMove;
import com.monstersaku.util.StatusCondition;
import com.monstersaku.util.Target;
import com.monstersaku.util.Stats;
import com.monstersaku.util.Player;
>>>>>>> rifda

public class TestMoveAbstract{
    public static void main(String[] args) throws URISyntaxException, IOException{
        DefaultMove defmove = new DefaultMove();
        NormalMove normalmove = new NormalMove(1,MoveType.NORMAL,"Punch",ElementType.NORMAL,90,1,10,Target.ENEMY,10.0);
        SpecialMove specialmove = new SpecialMove(2,MoveType.SPECIAL,"Special Punch",ElementType.NORMAL,90,1,0,Target.ENEMY,10.0);
        StatusMove statusmove = new StatusMove(3,MoveType.STATUS,"Burn",ElementType.FIRE,90,1,10,Target.ENEMY,"BURN",0.0);
        StatusMove statusmove2 = new StatusMove(4,MoveType.STATUS,"Heal",ElementType.FIRE,90,1,10,Target.ENEMY,"-",25.0);
        // defmove.printMove();
        // System.out.println();
        // normalmove.printMove();
        // System.out.println();
        // specialmove.printMove();
        // System.out.println();
        // statusmove.printMove();
        // System.out.println();
        // statusmove2.printMove();
        Stats<Double> stats = new Stats<Double>(100.0, 10.0, 12.0, 15.0, 16.0, 20.0);
        Stats<Double> stats1 = new Stats<Double>(150.0, 15.0, 17.0, 18.0, 19.0, 25.0);
        ArrayList<ElementType> lists = new ArrayList<ElementType>();
        lists.add(ElementType.NORMAL);
        lists.add(ElementType.FIRE);
        ArrayList<ElementType> lists1 = new ArrayList<ElementType>();
        lists1.add(ElementType.FIRE);
        lists1.add(ElementType.WATER);
        ArrayList<Move> moves = new ArrayList<>();
        ArrayList<Move> moves1 = new ArrayList<>();
        moves.add(defmove);
        moves.add(normalmove);
        moves.add(statusmove);
        moves.add(statusmove2);
        moves1.add(defmove);
        moves1.add(normalmove);
        moves1.add(statusmove);
        moves1.add(statusmove2);
        
        
        
        ArrayList<ElementEffectivity> arreffectivity = new ArrayList<>();

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
        
        Monster mons = new Monster(1, "AAAAAA", lists, stats, moves);
        Monster mons1 = new Monster(2, "BBBBBB", lists1, stats1, moves1);
        
        ArrayList<Monster> arrmonster = new ArrayList<>();
        arrmonster.add(mons);
        arrmonster.add(mons1);
        mons.getMoves().get(0).printMove();
        mons.getMoves().get(0).useMove(mons, mons1, arreffectivity, arrmonster);
        mons1.printMonster();
        System.out.println();
        mons.getMoves().get(1).changeHP(mons);
        System.out.println();
        mons.getMoves().get(0).printMove();
        System.out.println();
        mons.getMoves().get(1).printMove();
        System.out.println();
        mons.printMonster();
    }
}