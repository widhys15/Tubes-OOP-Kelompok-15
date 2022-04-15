package com.monstersaku.util;

public class Stats <S> {
    private S healthPoint;
    private S attack;
    private S defense;
    private S specialAttack;
    private S specialDefense;
    private S speed;
    
    //Konstruktor
    public Stats(S healthPoint, S attack, S defense, S specialAttack,S specialDefense, S speed) {
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    //Getter
    public S getHealthPoint() {
        return this.healthPoint;
    }

    public S getAttack() {
        return this.attack;
    }

    public S getDefense() {
        return this.defense;
    }

    public S getSpecialAttack() {
        return this.specialAttack;
    }

    public S getSpecialDefense() {
        return this.specialDefense;
    }

    public S getSpeed() {
        return this.speed;
    }

    //Setter
    public void setHealthPoint(S healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void setAttack(S attack) {
        this.attack = attack;
    }

    public void setDefense(S defense) {
        this.defense = defense;
    }
    
    public void setSpecialAttack(S specialAttack) {
        this.specialAttack = specialAttack;
    }
    
    public void setSpecialDefense(S specialDefense) {
        this.specialDefense = specialDefense;
    }
    
    public void setSpeed(S speed) {
        this.speed = speed;
    }

    //Method
    public void printStats(){
        System.out.println("HP                  : " + healthPoint.toString());
        System.out.println("Attack              : " + attack);
        System.out.println("Defense             : " + defense);
        System.out.println("Special Attack      : " + specialAttack);
        System.out.println("Special Defense     : " + specialDefense);
        System.out.println("Speed               : " + speed);
    }

    public void printStatsCompact(){
        System.out.println("    " + "Attack             : " + attack.toString());
        System.out.println("    " + "Defense            : " + defense.toString());
        System.out.println("    " + "Special Attack     : " + specialAttack.toString());
        System.out.println("    " + "Special Defense    : " + specialDefense.toString());
        System.out.println("    " + "Speed              : " + speed.toString());
    }

    public void printStatsBuff(Monster monster){
        System.out.println("    " + "Attack Buff        : " + monster.getAttack());
        System.out.println("    " + "Defense            : " + monster.getDefense());
        System.out.println("    " + "Special Attack     : " + monster.getSpecialAttack());
        System.out.println("    " + "Special Defense    : " + monster.getSpecialDefense());
        System.out.println("    " + "Speed              : " + monster.getSpeed());
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
        } else { //buff==4
            mult=(double)6/2;
        }
        return mult;
    }
}
