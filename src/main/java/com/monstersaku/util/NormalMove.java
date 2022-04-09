package com.monstersaku.util;


import java.util.*;

public class NormalMove extends Move {
    protected Double damage;

    //Konstruktor
    public NormalMove(Integer idmove, MoveType movetype, String movename, ElementType moveelementType, Integer accuracy, Integer priority, Integer ammunition, Target target, Double damage){
        super(idmove, movetype, moveelementType, movename, accuracy, priority, ammunition, target);
        this.damage = damage;   
    }

    public NormalMove(NormalMove move, Integer ammunition){
        setidMove(move.getidMove());
        setmovetype(move.getmovetype());
        setmovename(move.getmovename());
        setelementtype(move.getmoveelementtype());
        setaccuracy(move.getaccuracy());
        setpriority(move.getpriority());
        this.ammunition = ammunition;
        settarget(move.gettarget());
        setdamage(move.getdamage());
    }

    //Getter
    public Double getdamage(){
        return damage;
    }

    //Setter
    public void setdamage(Double damage){
        this.damage = damage;
    }

    //Method
    @Override
    public void useMove (Monster attacker, Monster enemy, ArrayList<ElementEffectivity> arreffectivity, ArrayList<Monster> arrmonster){ 
        // System.out.println("MASUK METHOD NORMAL MOVE");
        Random rand = new Random();
        int accuracy = rand.nextInt(100)+1;
        if (accuracy > this.getaccuracy()) {
            System.out.printf("Move %s miss", this.getmovename());
        } else {
            Double burn = 1.0;
            if (attacker.getStatusCondition().equals("-")) {
                burn = 1.0;
            } else if (attacker.getStatusCondition().equals("BURN")) {
                burn = 0.5;
            }
            Double damagecalculation = Math.floor(damage * (attacker.getBaseStats().getAttack()/enemy.getBaseStats().getDefense()) + 2.0) * (Math.random() * (1-0.85) + 0.85) * findEffectivity(enemy, arreffectivity) *burn;
            System.out.println("Damage " + movename + " yang diberikan kepada " + enemy.getName() + " sebesar " + damagecalculation);
            Double finaldamage = enemy.getBaseStats().getHealthPoint() - damagecalculation;
            if (finaldamage < 0) {
                finaldamage = 0.0;
            }
            System.out.println("HP " + enemy.getName() + " berubah menjadi " + finaldamage);
            enemy.getBaseStats().setHealthPoint(finaldamage);
        }        
        this.ammunition = this.ammunition - 1;
    }

    public void printMove(){
        super.printMove();
        System.out.println("Damage              : " + damage);
    }
}