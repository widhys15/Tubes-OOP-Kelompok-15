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

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Move {
    private Integer idMove;
    private MoveType movetype; 
    private String movename;
    private ElementType moveelementType;
    private Integer accuracy;
    private Integer priority;
    private Integer ammunition;
    private Target target;
    private Double damage;

    public Move(Integer idMove){
        idMove = 0;
        movetype = MoveType.DEFAULT;
        movename = "Normal Move";
        moveelementType = ElementType.NORMAL;
        accuracy = 100;
        priority = 0;
        ammunition = 999999;
        target = Target.ENEMY;
        damage = 50.0;
    }
    public Move(Integer idMove, MoveType movetype, String movename, ElementType moveelementType, Integer accuracy, Integer priority, Integer ammunition, Target target){
        this.idMove = idMove;
        this.movetype = movetype;
        this.movename = movename;
        this.moveelementType = moveelementType;
        this.accuracy = accuracy;
        this.priority = priority;
        this.ammunition = ammunition;
        this.target = target;   
    }
    
    public Move(Integer idMove, MoveType movetype, String movename, ElementType moveelementType, Integer accuracy, Integer priority, Integer ammunition, Target target, Double damage){
        this.idMove = idMove;
        this.movetype = movetype;
        this.movename = movename;
        this.moveelementType = moveelementType;
        this.accuracy = accuracy;
        this.priority = priority;
        this.ammunition = ammunition;
        this.target = target;
        this.damage = damage;     
    }
    
    public Integer getidMove(){
        return this.idMove;
    }
    public MoveType getmovetype(){
        return movetype;
    } 
    public String getmovename(){
        return movename;
    }
    public ElementType getmoveelementtype(){
        return moveelementType;
    }
    public Integer getaccuracy(){
        return accuracy;
    }
    public Integer getpriority(){
        return priority;
    }
    public Integer getammunition(){
        return ammunition;
    }
    public Target gettarget(){
        return target;
    }
    public Double getdamage(){
        return damage;
    }
    public void useammunition(){
        ammunition = ammunition - 1;
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
        System.out.println("Damage " + damage);
    }
    public void printmonsMove(){
        System.out.println("ID Move " + idMove);
        System.out.println("Ammunition " + ammunition);
    }
}
