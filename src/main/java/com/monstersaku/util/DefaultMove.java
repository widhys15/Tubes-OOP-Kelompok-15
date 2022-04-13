package com.monstersaku.util;
import java.util.*;

public class DefaultMove extends Move {
    private Double damage;
    private Double basehp;

    //Konstruktor    
    public DefaultMove(){
        super(0, MoveType.DEFAULT, ElementType.NORMAL, "Default Move", 100, 0, 9999, Target.ENEMY);
        this.damage = 10.0;
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
        Random rand = new Random();
        int accuracy = rand.nextInt(100)+1;
        if (accuracy > this.getaccuracy()) {
            System.out.printf("Move %s miss%n", this.getmovename());
        } else {
            Double burn = 1.0;
            if (attacker.getStatusCondition().equals("-")) {
                burn = 1.0;
            } else if (attacker.getStatusCondition().equals("BURN")) {
                burn = 0.5;
            }
            Double eff = findEffectivity(enemy, arreffectivity);
            Double calculation = Math.floor(damage * (attacker.getAttack()/enemy.getDefense()) + 2.0) * (Math.random() * (1-0.85) + 0.85) * eff * burn;
            System.out.println("Damage " + movename + " yang diberikan kepada " + enemy.getName() + " sebesar " + Math.round(calculation));
            Double finaldamage = enemy.getBaseStats().getHealthPoint() - calculation;
            if (finaldamage < 0) {
                finaldamage = 0.0;
            }
            System.out.println("HP " + enemy.getName() + " berubah menjadi " + Math.round(finaldamage));
            enemy.getBaseStats().setHealthPoint((double) Math.round(finaldamage));
    
            for(Monster m: arrmonster){
                if(m.getidmonster() == attacker.getidmonster()){
                    basehp = m.getBaseStats().getHealthPoint();
                }
            }
            Double finalhp = attacker.getBaseStats().getHealthPoint() - (basehp * 0.25);
            if (finalhp < 0) {
                finalhp = 0.0;
            }
            attacker.getBaseStats().setHealthPoint((double) Math.round(finalhp));
            System.out.println("HP akhir dari " + attacker.getName() + " adalah " + Math.round(finalhp));
        }
        this.ammunition = this.ammunition - 1;
    }

    public void printMove(){
        super.printMove();
        System.out.println("Damage              : " + damage);
    }
}
