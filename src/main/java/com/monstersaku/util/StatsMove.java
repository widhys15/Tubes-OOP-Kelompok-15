package com.monstersaku.util;

public class StatsMove extends AbsMove {
    private String conditon;
    private Double effect;
    
    public StatsMove(Integer idMove, MoveType movetype, String movename, ElementType moveelementType, Integer accuracy, Integer priority, Integer ammunition, Target target, String condition){
        this.idMove = idMove;
        this.movetype = movetype;
        this.movename = movename;
        this.moveelementType = moveelementType;
        this.accuracy = accuracy;
        this.priority = priority;
        this.ammunition = ammunition;
        this.target = target;
        this.conditon = condition;     
    }

    public StatsMove(Integer idMove, MoveType movetype, String movename, ElementType moveelementType, Integer accuracy, Integer priority, Integer ammunition, Target target, Double effect){
        this.idMove = idMove;
        this.movetype = movetype;
        this.movename = movename;
        this.moveelementType = moveelementType;
        this.accuracy = accuracy;
        this.priority = priority;
        this.ammunition = ammunition;
        this.target = target;
        this.effect = effect;     
    }

    public String getmovecondition(){
        return conditon;
    }
    
    public Double getmoveeffect(){
        return effect;
    }
    
    public void printMoveCondition(){
        super.printMove();
        System.out.println("Move Effect         : " + conditon);
    }
    public void printMoveEffect(){
        super.printMove();
        System.out.println("Move Effect         : " + effect);
    }

    public void changeCondition(Monster enemy){
        enemy.setStatusCondition(conditon);
        ammunition--;
    }
    public void changeHP(Monster monster){
        monster.getBaseStats().setHealthPoint(monster.getBaseStats().getHealthPoint() + effect);
        ammunition--;
    }
}
