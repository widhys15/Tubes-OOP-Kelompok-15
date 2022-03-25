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
    private Integer idMove;
    private MoveType movetype; 
    private String movename;
    private ElementType moveelementType;
    private Integer accuracy;
    private Integer priority;
    private Integer ammunition;
    private Target target;
    private String moveeffect;
    private Double movehp;
    private Double moveatk;
    private Double movedef;
    private Double movespatk;
    private Double movespdef;
    private Double movespeed;
    
    public StatusMove(Integer idMove, MoveType movetype, String movename, ElementType moveelementType, Integer accuracy, Integer priority, Integer ammunition, Target target, String moveeffect, Double movehp, Double moveatk, Double movedef, Double movespatk, Double movespdef, Double movespeed){
        this.idMove = idMove;
        this.movetype = movetype;
        this.movename = movename;
        this.moveelementType = moveelementType;
        this.accuracy = accuracy;
        this.priority = priority;
        this.ammunition = ammunition;
        this.target = target;
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
        System.out.println("ID Move " + idMove);
        System.out.println("Move Type " + movetype);
        System.out.println("Move Name" + movename);
        System.out.println("Move Element Type " + moveelementType);
        System.out.println("Accuracy " + accuracy);
        System.out.println("Priority " + priority);
        System.out.println("Ammunition " + ammunition);
        System.out.println("Target " + target);
        System.out.println("Move Effect " + moveeffect);
        System.out.println("Move HP " + movehp);
        System.out.println("Move ATK " + moveatk);
        System.out.println("Move DEF " + movedef);
        System.out.println("Move SPCATK " + movespatk);
        System.out.println("Move SPDEF " + movespdef);
        System.out.println("Move Speed " + movespeed);
    }
}
