package com.monstersaku.util;


import java.util.ArrayList;

public class NormalMove extends Move {
    protected Double damage;

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

    public NormalMove() {
    }

    public Double getdamage(){
        return damage;
    }

    public void setdamage(Double damage){
        this.damage = damage;
    }

    @Override
    public void useMove (Monster attacker, Monster enemy, ArrayList<ElementEffectivity> arreffectivity, ArrayList<Monster> arrmonster){ 
        // System.out.println("MASUK METHOD NORMAL MOVE");
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
        setammunition(move.getammunition());
        settarget(move.gettarget());
        setdamage(((NormalMove) move).getdamage());
    }
}
