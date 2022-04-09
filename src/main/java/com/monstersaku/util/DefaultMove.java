package com.monstersaku.util;
import java.util.*;

public class DefaultMove extends AbsMove {
    protected Double damage;
    private Double basehp;
    
    public DefaultMove(){
        idMove = 0;
        movetype = MoveType.DEFAULT;
        movename = "Default Move";
        moveelementType = ElementType.NORMAL;
        accuracy = 100;
        priority = 0;
        ammunition = 999999;
        target = Target.ENEMY;
        damage = 50.0;
    }

    @Override
    public void useMove (Monster attacker, Monster enemy, ArrayList<ElementEffectivity> arreffectivity, ArrayList<Monster> arrmonster){ 
        // System.out.println("MASUK METHOD DEFAULT MOVE");
        Double damagecalculation = Math.floor(damage * (attacker.getBaseStats().getAttack()/enemy.getBaseStats().getDefense()) + 2.0) * (Math.random() * (1-0.85) + 0.85) * findEffectivity(enemy, arreffectivity);
        System.out.println("Damage " + movename + " yang diberikan kepada " + enemy.getName() + " sebesar " + damagecalculation);
        Double finaldamage = enemy.getBaseStats().getHealthPoint() - damagecalculation;
        System.out.println("HP " + enemy.getName() + " berubah menjadi " + finaldamage);
        enemy.getBaseStats().setHealthPoint(finaldamage);

        for(Monster m: arrmonster){
            if(m.getidmonster() == attacker.getidmonster()){
                basehp = m.getBaseStats().getHealthPoint();
            }
        }
        Double finalhp = attacker.getBaseStats().getHealthPoint() - (basehp * 0.25);
        attacker.getBaseStats().setHealthPoint(finalhp);
        System.out.println("HP akhir dari " + attacker.getName() + " adalah " + finalhp);
        this.ammunition = this.ammunition - 1;
    }

    public void printMove(){
        super.printMove();
        System.out.println("Damage              : " + damage);
    }
}
