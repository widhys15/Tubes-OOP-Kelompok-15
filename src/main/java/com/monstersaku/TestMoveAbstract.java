package com.monstersaku;

import com.monstersaku.util.ElementType;
import com.monstersaku.util.Monster;
import com.monstersaku.util.MoveType;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.monstersaku.util.AbsMove;
import com.monstersaku.util.CSVReader;
import com.monstersaku.util.DefaultMove;
import com.monstersaku.util.ElementEffectivity;
import com.monstersaku.util.NormalMove;
import com.monstersaku.util.SpecialMove;
import com.monstersaku.util.StatsMove;
import com.monstersaku.util.Target;
import com.monstersaku.util.Stats;
import com.monstersaku.util.Player;

public class TestMoveAbstract{
    public static void main(String[] args) throws URISyntaxException, IOException{
        DefaultMove defmove = new DefaultMove();
        NormalMove normalmove = new NormalMove(1,MoveType.NORMAL,"Punch",ElementType.NORMAL,90,1,10,Target.ENEMY,10.0);
        SpecialMove specialmove = new SpecialMove(2,MoveType.SPECIAL,"Special Punch",ElementType.NORMAL,90,1,0,Target.ENEMY,10.0);
        StatsMove statusmove = new StatsMove(3,MoveType.STATUS,"Burn",ElementType.FIRE,90,1,10,Target.ENEMY,"BURN");
        StatsMove statusmove2 = new StatsMove(4,MoveType.STATUS,"Heal",ElementType.FIRE,90,1,10,Target.ENEMY,25.0);
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
        List<ElementType> lists = new ArrayList<>();
        lists.add(ElementType.NORMAL);
        lists.add(ElementType.FIRE);
        List<ElementType> lists1 = new ArrayList<>();
        lists.add(ElementType.FIRE);
        lists.add(ElementType.WATER);
        ArrayList<AbsMove> moves = new ArrayList<>();
        ArrayList<AbsMove> moves1 = new ArrayList<>();
        moves.add(normalmove);
        moves1.add(normalmove);
        moves.add(statusmove2);
        moves.add(statusmove);
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
        
        Monster mons = new Monster(1, "Vincent", lists, stats, moves, "-");
        Monster mons1 = new Monster(2, "AAAA", lists1, stats1, moves1, "-");

        mons.getMoves().get(0).printMove();
        mons.getMoves().get(0).useNormalMove(mons1, mons, arreffectivity);
        mons1.printMonster();
        mons1.getBaseStats().setHealthPoint(mons1.getBaseStats().getHealthPoint()-50.0);
        mons1.printMonster();
    }
}