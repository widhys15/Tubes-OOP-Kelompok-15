package com.monstersaku.util;

public class SpecialMove extends AbsMove {
    protected Double damage;
    
    public SpecialMove(Integer idMove, MoveType movetype, String movename, ElementType moveelementType, Integer accuracy, Integer priority, Integer ammunition, Target target, Double damage){
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

    public void printMove(){
        super.printMove();
        System.out.println("Damage              : " + damage);
    }
}
