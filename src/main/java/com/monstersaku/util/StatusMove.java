package com.monstersaku.util;

import java.util.*;

public class StatusMove extends Move {
    protected String condition;
    protected Stats<Integer> effect;
    
    //Konstruktor
    public StatusMove(Integer idmove, MoveType movetype, String movename, ElementType moveelementType, Integer accuracy, Integer priority, Integer ammunition, Target target, String condition, Stats<Integer> effect){
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

    //Getter
    public String getmovecondition(){
        return condition;
    }
    
    public Stats<Integer> getmoveeffect(){
        return effect;
    }

    //Setter
    public void setmovecondition(String condition){
        this.condition = condition;
    }

    public void setmoveeffect(Stats<Integer> effect){
        this.effect = effect;
    }

    public void changeCondition(Monster monster) {
        monster.setCondition(condition);
    }
    
    //Method
    
    public void printMove(){
        super.printMove();
        System.out.println("Move Condition      : " + condition);
        System.out.println("Move Effect         : " + effect);
    }

    @Override
    public void useMove (Monster attacker, Monster enemy, ArrayList<ElementEffectivity> arreffectivity, ArrayList<Monster> arrmonster){ 
        // System.out.println("MASUK METHOD STATUS MOVE");
        Random rand = new Random();
        int accuracy = rand.nextInt(100)+1;
        if (accuracy > this.getaccuracy()) {
            System.out.printf("Move %s miss%n", this.getmovename());
        } else {
            if(this.gettarget().equals(Target.ENEMY)){
                if (this.getmovecondition().equals("-")) {
                    enemy.setStatsBuff(this.getmoveeffect());
                    System.out.printf("Status Buff monster %s (monster lawan) berubah%n", enemy.getName());
                } else {
                    if (enemy.isStatusConditionNull()) {
                        this.changeCondition(enemy);
                    System.out.printf("Status condition monster %s menjadi %s%n", enemy.getName(), this.getmovecondition());
        
                    if (this.getmovecondition().equals("PARALYZE")) {
                        enemy.getBaseStats().setSpeed(Math.floor(enemy.getBaseStats().getSpeed()*0.5)); //asumsi dibulatkan ke-bawah
                        System.out.println("Base Speed berkurang menjadi "+ enemy.getBaseStats().getSpeed());
                        int chance = rand.nextInt(100)+1;
                        if (chance>=1 && chance <=25) {
                            enemy.setExtendCondition(1);
                            System.out.println("Monster tidak bisa bergerak satu giliran");
                        }
                        
                    } else if (this.getmovecondition().equals("SLEEP")) {
                        int num = rand.nextInt(7)+1;
                        enemy.setExtendCondition(num);
                        System.out.println("Monster tidak bisa bergerak sebanyak "+num+" giliran");
                    }
                } else {
                    System.out.printf("Monster %s sudah memiliki status condition, effect move gagal diberikan%n", enemy.getName());
                }
                }                
            } else {
                if (this.getmoveeffect().getHealthPoint()==0) {
                    attacker.setStatsBuff(this.getmoveeffect());
                    System.out.printf("Status Buff monster %s berubah%n", attacker.getName());
                } else {
                    Double finalhp = attacker.getBaseStats().getHealthPoint()+((double)this.getmoveeffect().getHealthPoint());
                    if (finalhp > attacker.getBaseHP(arrmonster)) {
                        finalhp = attacker.getBaseHP(arrmonster);
                    }
                    attacker.getBaseStats().setHealthPoint(finalhp);
                    System.out.printf("HP monster %s bertambah menjadi %.0f%n", attacker.getName(), finalhp);
                }                
            }   
        }
        this.ammunition=this.ammunition-1;
    }
}
