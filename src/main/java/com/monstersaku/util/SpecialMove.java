package com.monstersaku.util;

import java.util.ArrayList;

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

    public void useSpecialMove (Monster attacker, Monster enemy, ArrayList<ElementEffectivity> arreffectivity){
        float damagecalculation = (float)Math.floor((((attacker.getBaseStats().getSpecialAttack()) / (enemy.getBaseStats().getSpecialDefense())) + 2 ) * Math.floor(Math.random()*(1-0.85+1)+0.85) * findEffectivity(enemy, arreffectivity));
        enemy.getBaseStats().setHealthPoint(enemy.getBaseStats().getHealthPoint()-damagecalculation);
        ammunition--;
    }

    public void printMove(){
        super.printMove();
        System.out.println("Damage              : " + damage);
    }
}
