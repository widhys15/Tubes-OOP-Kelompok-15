package com.monstersaku.util;
import java.util.*;

public class DefaultMove extends Move {
    protected Double damage;
    private Double basehp;
    
    public DefaultMove(){
        super(0, MoveType.DEFAULT, ElementType.NORMAL, "Default Move", 100, 0, 9999, Target.ENEMY);
    }

    public Double getdamage(){
        return damage;
    }

    public void setdamage(Double damage){
        this.damage = damage;
    }
        
    @Override
    public void useMove (Monster attacker, Monster enemy, ArrayList<ElementEffectivity> arreffectivity, ArrayList<Monster> arrmonster){ 
        // System.out.println("MASUK METHOD DEFAULT MOVE");
        Double damagecalculation = Math.floor(damage * (attacker.getBaseStats().getAttack()/enemy.getBaseStats().getDefense()) + 2.0) * (Math.random() * (1-0.85) + 0.85) * findEffectivity(enemy, arreffectivity);
        System.out.println("Damage " + movename + " yang diberikan kepada " + enemy.getName() + " sebesar " + Math.round(damagecalculation));
        Double finaldamage = enemy.getBaseStats().getHealthPoint() - damagecalculation;
        System.out.println("HP " + enemy.getName() + " berubah menjadi " + Math.round(finaldamage));
        enemy.getBaseStats().setHealthPoint((double) Math.round(finaldamage));

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

    @Override
    public void applyDamage(Monster attacker, Monster target, ElementEffectivity findEffectivity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void copymove(Move move) {
        // TODO Auto-generated method stub
        setidMove(move.getidMove());
        setmovetype(move.getmovetype());
        setmovename(move.getmovename());
        setelementtype(move.getmoveelementtype());
        setaccuracy(move.getaccuracy());
        setpriority(move.getpriority());
        settarget(move.gettarget());
        setdamage(((DefaultMove) move).getdamage());
    }
}
