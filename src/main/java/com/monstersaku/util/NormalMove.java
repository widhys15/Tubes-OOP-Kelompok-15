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
        Double damagecalculation = Math.floor(damage * (attacker.getBaseStats().getAttack()/enemy.getBaseStats().getDefense()) + 2.0) * (Math.random() * (1-0.85) + 0.85) * findEffectivity(enemy, arreffectivity);
        System.out.println("Damage " + movename + " yang diberikan kepada " + enemy.getName() + " sebesar " + damagecalculation);
        Double finaldamage = enemy.getBaseStats().getHealthPoint() - damagecalculation;
        System.out.println("HP " + enemy.getName() + " berubah menjadi " + finaldamage);
        enemy.getBaseStats().setHealthPoint(finaldamage);
        this.ammunition = this.ammunition - 1;
    }

    public void printMove(){
        super.printMove();
        System.out.println("Damage              : " + damage);
    }
}
