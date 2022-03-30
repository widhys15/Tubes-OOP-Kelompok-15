package com.monstersaku.util;

public class Stats {
    private Double healthPoint;
    private Double attack;
    private Double defense;
    private Double specialAttack;
    private Double specialDefense;
    private Double speed;
    
    public Stats(Double healthPoint, Double attack, Double defense, Double specialAttack,Double specialDefense, Double speed) {
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    public Double getHealthPoint() {
        return this.healthPoint;
    }

    public void setHealthPoint(Double healthPoint) {
        this.healthPoint = healthPoint;
    }

    public Double getAttack() {
        return this.attack;
    }

    public void setAttack(Double attack) {
        this.attack = attack;
    }
    
    public Double getDefense() {
        return this.defense;
    }

    public void setDefense(Double defense) {
        this.defense = defense;
    }
    
    public Double getSpecialAttack() {
        return this.specialAttack;
    }

    public void setSpecialAttack(Double specialAttack) {
        this.specialAttack = specialAttack;
    }
    
    public Double getSpecialDefense() {
        return this.specialDefense;
    }

    public void setSpecialDefense(Double specialDefense) {
        this.specialDefense = specialDefense;
    }
    
    public Double getSpeed() {
        return this.speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void decreaseStats(Double amount) {

    }
    public void printStats(){
        System.out.println("HP = " + healthPoint.toString());
        System.out.println("Atk = " + this.attack);
        System.out.println("Def = " + defense);
        System.out.println("Sp.Atk = " + specialAttack);
        System.out.println("Sp.Def = " + specialDefense);
        System.out.println("Speed = " + speed);
    }
}
