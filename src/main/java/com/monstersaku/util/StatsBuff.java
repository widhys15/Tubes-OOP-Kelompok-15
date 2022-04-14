package com.monstersaku.util;

public class StatsBuff {
    private Integer heal;
    private Integer attackBuff;
    private Integer defenseBuff;
    private Integer specialAttackBuff;
    private Integer specialDefenseBuff;
    private Integer speedBuff;

    public StatsBuff(Integer heal, Integer attackBuff, Integer defenseBuff, Integer specialAttackBuff, Integer specialDefenseBuff, Integer speedBuff) {
        this.heal = heal;
        this.attackBuff = attackBuff;
        this.defenseBuff = defenseBuff;
        this.specialAttackBuff = specialAttackBuff;
        this.specialDefenseBuff = specialDefenseBuff;
        this.speedBuff = speedBuff;
    }

    public StatsBuff() {
        this.heal = 0;
        this.attackBuff = 0;
        this.defenseBuff = 0;
        this.specialAttackBuff = 0;
        this.specialDefenseBuff = 0;
        this.speedBuff = 0;
    }

    public double getHeal() {
        return this.heal;
    }

    public int getAttackBuff() {
        return this.attackBuff;
    }

    public int getDefenseBuff() {
        return this.defenseBuff;
    }

    public int getSpecialAttackBuff() {
        return this.specialAttackBuff;
    }

    public int getSpecialDefenseBuff() {
        return this.specialDefenseBuff;
    }

    public int getSpeedBuff() {
        return this.speedBuff;
    }

    //SETTER
    public void setHeal(int val) {
        this.heal = val;
    }

    public void setAttackBuff(int val) {
        this.attackBuff = val;
    }

    public void setDefenseBuff(int val) {
        this.defenseBuff = val;
    }

    public void setSpecialAttackBuff(int val) {
        this.specialAttackBuff = val;
    }

    public void setSpecialDefenseBuff(int val) {
        this.specialDefenseBuff = val;
    }

    public void setSpeedBuff(int val) {
        this.speedBuff = val;
    }

    public double getFactor(int buff) {
        double mult;
        if (buff==-4) {
            mult=(double)2/6;
        } else if (buff==-3) {
            mult=(double)2/5;
        } else if (buff==-2) {
            mult=(double)2/4;
        } else if (buff==-1) {
            mult=(double)2/3;
        } else if (buff==0) {
            mult=1.0;
        } else if (buff==1) {
            mult=(double)3/2;
        } else if (buff==2) {
            mult=(double)4/2;
        } else if (buff==3) {
            mult=(double)5/2;
        } else {
            mult=(double)6/2;
        }
        return mult;
    }

    public void printStatsBuff(Stats<Double> stat){
        System.out.println("    " + "Attack Buff        : " + Math.floor(stat.getAttack()*getFactor(attackBuff)));
        System.out.println("    " + "Defense            : " + Math.floor(stat.getDefense()*getFactor(defenseBuff)));
        System.out.println("    " + "Special Attack     : " + Math.floor(stat.getSpecialAttack()*getFactor(specialAttackBuff)));
        System.out.println("    " + "Special Defense    : " + Math.floor(stat.getSpecialDefense()*getFactor(specialDefenseBuff)));
        System.out.println("    " + "Speed              : " + Math.floor(stat.getSpeed()*getFactor(speedBuff)));
    }
}
