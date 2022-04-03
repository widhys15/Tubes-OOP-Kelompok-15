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

    public void useDefaultMove (Monster attacker, Monster enemy, ArrayList<ElementEffectivity> arreffectivity, ArrayList<Monster> arrmonster){
        
        float damagecalculation = (float)Math.floor((((attacker.getBaseStats().getAttack()) / (enemy.getBaseStats().getDefense())) + 2 ) * Math.floor(Math.random()*(1-0.85+1)+0.85) * findEffectivity(enemy, arreffectivity));
        enemy.getBaseStats().setHealthPoint(enemy.getBaseStats().getHealthPoint()-damagecalculation);
        
        for(Monster m: arrmonster){
            if(m.getidmonster() == attacker.getidmonster()){
                basehp = m.getBaseStats().getHealthPoint();
            }
        }
        Double finalhp = attacker.getBaseStats().getHealthPoint() - basehp;
        attacker.getBaseStats().setHealthPoint(finalhp);
        this.ammunition = this.ammunition - 1;
    }

    public void printMove(){
        super.printMove();
        System.out.println("Damage              : " + damage);
    }
}
