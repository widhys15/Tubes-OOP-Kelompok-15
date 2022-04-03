package com.monstersaku;

import com.monstersaku.util.ElementType;
import com.monstersaku.util.Monster;
import com.monstersaku.util.MoveType;
import com.monstersaku.util.AbsMove;
import com.monstersaku.util.DefaultMove;
import com.monstersaku.util.NormalMove;
import com.monstersaku.util.SpecialMove;
import com.monstersaku.util.StatsMove;
import com.monstersaku.util.Target;
import com.monstersaku.util.Stats;
import com.monstersaku.util.Player;

public class TestMoveAbstract{
    public static void main(String[] args){
        DefaultMove defmove = new DefaultMove();
        NormalMove normalmove = new NormalMove(1,MoveType.NORMAL,"Punch",ElementType.NORMAL,90,1,10,Target.ENEMY,120.0);
        SpecialMove specialmove = new SpecialMove(2,MoveType.SPECIAL,"Special Punch",ElementType.NORMAL,90,1,0,Target.ENEMY,120.0);
        StatsMove statusmove = new StatsMove(3,MoveType.STATUS,"Burn",ElementType.FIRE,90,1,10,Target.ENEMY,"BURN");
        StatsMove statusmove2 = new StatsMove(4,MoveType.STATUS,"Heal",ElementType.FIRE,90,1,10,Target.ENEMY,25.0);
        defmove.printMove();
        System.out.println();
        normalmove.printMove();
        System.out.println();
        specialmove.printMove();
        System.out.println();
        statusmove.printMove();
        System.out.println();
        statusmove2.printMove();
    }
}