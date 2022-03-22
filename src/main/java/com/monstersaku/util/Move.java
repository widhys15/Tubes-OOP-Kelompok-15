package com.monstersaku.util;

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

    public Move(){
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
    
}
