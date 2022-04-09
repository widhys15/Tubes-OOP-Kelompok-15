package com.monstersaku.util;

import java.util.ArrayList;

public class StatsMove extends AbsMove {
    private StatusCondition conditon;
    private Double effect;
    
    public StatsMove(Integer idMove, MoveType movetype, String movename, ElementType moveelementType, Integer accuracy, Integer priority, Integer ammunition, Target target, StatusCondition condition){
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

    public StatusCondition getmovecondition(){
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
    
    @Override
    public void useMove (Monster attacker, Monster enemy, ArrayList<ElementEffectivity> arreffectivity, ArrayList<Monster> arrmonster){ 
        // System.out.println("MASUK METHOD STATUS MOVE");
        if(this.gettarget().equals(Target.ENEMY)){
            this.changeCondition(enemy);
            System.out.printf("Status condition monster %s menjadi %s%n", enemy.getName(), this.getmovecondition());
        }else{
            this.changeHP(attacker);
            System.out.printf("HP monster %s bertambah menjadi %f%n", attacker.getName(), this.getmoveeffect());
        }
    }
}
