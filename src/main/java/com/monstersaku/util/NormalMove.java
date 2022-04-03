package com.monstersaku.util;


import java.util.ArrayList;

public class NormalMove extends AbsMove {
    protected Double damage;

    public NormalMove(Integer idMove, MoveType movetype, String movename, ElementType moveelementType, Integer accuracy, Integer priority, Integer ammunition, Target target, Double damage){
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

    public void useNormalMove (Monster attacker, Monster enemy, ArrayList<ElementEffectivity> arreffectivity){
        float damagecalculation = (float)Math.floor((((attacker.getBaseStats().getAttack()) / (enemy.getBaseStats().getDefense())) + 2 ) * Math.floor(Math.random()*(1-0.85+1)+0.85) * findEffectivity(enemy, arreffectivity));
        System.out.println("Damagenya sebesar " + damagecalculation);
        Double finalhp = enemy.getBaseStats().getHealthPoint() - (double) damagecalculation;
        enemy.getBaseStats().setHealthPoint(finalhp);
        this.ammunition = this.ammunition - 1;
    }

    public void printMove(){
        super.printMove();
        System.out.println("Damage              : " + damage);
    }
}
