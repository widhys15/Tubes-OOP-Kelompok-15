package com.monstersaku.util;

public class Stats <S> {
    private S healthPoint;
    private S attack;
    private S defense;
    private S specialAttack;
    private S specialDefense;
    private S speed;
    
    public Stats(S healthPoint, S attack, S defense, S specialAttack,S specialDefense, S speed) {
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    public S getHealthPoint() {
        return this.healthPoint;
    }

    public void setHealthPoint(S healthPoint) {
        this.healthPoint = healthPoint;
    }

    public S getAttack() {
        return this.attack;
    }

    public void setAttack(S attack) {
        this.attack = attack;
    }
    
    public S getDefense() {
        return this.defense;
    }

    public void setDefense(S defense) {
        this.defense = defense;
    }
    
    public S getSpecialAttack() {
        return this.specialAttack;
    }

    public void setSpecialAttack(S specialAttack) {
        this.specialAttack = specialAttack;
    }
    
    public S getSpecialDefense() {
        return this.specialDefense;
    }

    public void setSpecialDefense(S specialDefense) {
        this.specialDefense = specialDefense;
    }
    
    public S getSpeed() {
        return this.speed;
    }

    public void setSpeed(S speed) {
        this.speed = speed;
    }

    public void printStats(){
        System.out.println("HP                  : " + healthPoint.toString());
        System.out.println("Attack              : " + this.attack);
        System.out.println("Defense             : " + defense);
        System.out.println("Special Attack      : " + specialAttack);
        System.out.println("Special Defense     : " + specialDefense);
        System.out.println("Speed               : " + speed);
    }
}
