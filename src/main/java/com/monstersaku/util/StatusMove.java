package com.monstersaku.util;

import java.util.*;

public class StatusMove extends Move {
    protected String condition;
    protected Double effect;
    
    public StatusMove(Integer idmove, MoveType movetype, String movename, ElementType moveelementType, Integer accuracy, Integer priority, Integer ammunition, Target target, String condition, Double effect){
        super(idmove, movetype, moveelementType, movename, accuracy, priority, ammunition, target);
        this.condition = condition;     
        this.effect = effect;
    }

    public StatusMove(StatusMove move, Integer ammunition){
        setidMove(move.getidMove());
        setmovetype(move.getmovetype());
        setmovename(move.getmovename());
        setelementtype(move.getmoveelementtype());
        setaccuracy(move.getaccuracy());
        setpriority(move.getpriority());
        this.ammunition = ammunition;
        settarget(move.gettarget());
        setmovecondition(move.getmovecondition());
        setmoveeffect(move.getmoveeffect());
    }

    public StatusMove() {
    }

    public String getmovecondition(){
        return condition;
    }
    
    public Double getmoveeffect(){
        return effect;
    }

    public void setmovecondition(String condition){
        this.condition = condition;
    }

    public void setmoveeffect(Double effect){
        this.effect = effect;
    }
    
    public void printMove(){
        super.printMove();
        System.out.println("Move Condition      : " + condition);
        System.out.println("Move Effect         : " + effect);
    }

    @Override
    public void useMove (Monster attacker, Monster enemy, ArrayList<ElementEffectivity> arreffectivity, ArrayList<Monster> arrmonster){ 
        // System.out.println("MASUK METHOD STATUS MOVE");
        if(this.gettarget().equals(Target.ENEMY)){
            this.changeCondition(enemy);
            System.out.printf("Status condition monster %s menjadi %s%n", enemy.getName(), this.getmovecondition());
        }else{
            this.changeHP(attacker);
            System.out.printf("HP monster %s bertambah menjadi %f%n", attacker.getName(), this.getmoveeffect());
        }
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
        setmovecondition(((StatusMove) move).getmovecondition());
        setmoveeffect(((StatusMove) move).getmoveeffect());
    }

    @Override
    public void applyDamage(Monster attacker, Monster target, ElementEffectivity findEffectivity) {
        // TODO Auto-generated method stub
        
    }
}
