package com.monstersaku.util;

import com.monstersaku.util.CSVReader;
import com.monstersaku.util.ElementType;
import com.monstersaku.util.MoveType;
import com.monstersaku.util.Monster;
import com.monstersaku.util.Move;
import com.monstersaku.util.StatusMove;
import com.monstersaku.util.Stats;
import com.monstersaku.util.StatusCondition;
import com.monstersaku.util.Target;
import com.monstersaku.util.Player;

public class StatusMove extends Move{
    private String moveeffect;
    private Double movehp;
    private Double moveatk;
    private Double movedef;
    private Double movespatk;
    private Double movespdef;
    private Double movespeed;
    
    public StatusMove(Integer idMove, MoveType movetype, String movename, ElementType moveelementType, Integer accuracy, Integer priority, Integer ammunition, Target target, String moveeffect, Double movehp, Double moveatk, Double movedef, Double movespatk, Double movespdef, Double movespeed){
        super(idMove, movetype, movename, moveelementType, accuracy, priority, ammunition, target);
        this.moveeffect = moveeffect;
        this.movehp = movehp;
        this.moveatk = moveatk;
        this.movedef = movedef;
        this.movespatk = movespatk;
        this.movespdef = movespdef;
        this.movespeed = movespeed;    
    }

    public String getmoveeffect(){
        return moveeffect;
    }
    public Double getmovehp(){
        return movehp;
    }
    
    public Double getmoveatk(){
        return moveatk;
    }
    
    public Double getmovedef(){
        return movedef;
    }
    
    public Double getmovespatk(){
        return movespatk;
    }
    
    public Double getmovespdef(){
        return movespdef;
    }
    
    public Double getmovespeed(){
        return movespeed;
    }
    public void printMove(){
        super.printMove();
        System.out.println("Move Effect " + moveeffect);
        System.out.println("Move HP " + movehp);
        System.out.println("Move ATK " + moveatk);
        System.out.println("Move DEF " + movedef);
        System.out.println("Move SPCATK " + movespatk);
        System.out.println("Move SPDEF " + movespdef);
        System.out.println("Move Speed " + movespeed);
    }
}
