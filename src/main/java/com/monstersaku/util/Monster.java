package com.monstersaku.util;

import java.util.*;

public class Monster {
    protected Integer idmonster;
    protected String nama;
    protected List<ElementType> elementTypes;
    protected Stats<Double> baseStats;
    protected ArrayList<Integer> movesid;
    protected ArrayList<Move> moves;
    protected String condition;
    protected Integer extendCondition;

    // KONSTRUKTOR
    public Monster(Integer idmonster, String nama, List<ElementType> elementTypes, Stats<Double> baseStats, ArrayList<Integer> movesid) {
        this.idmonster = idmonster;
        this.nama = nama;
        this.elementTypes = elementTypes;
        this.baseStats = baseStats;
        this.movesid = movesid;
        this.condition = "-";
        this.extendCondition = 0;
    }
    
	public Monster(Monster monster, Stats<Double> stats) {
        this.idmonster = monster.getidmonster();
        this.nama = monster.getName();
        this.elementTypes = monster.getElementTypes();
        this.baseStats = stats;
        this.movesid = monster.getmovesid();
        this.moves = new ArrayList<Move>();
        this.condition = monster.getStatusCondition();
        this.extendCondition = monster.getExtendCondition();
	}

    // Setter
    public void setidmonster(Integer idmonster){
        this.idmonster = idmonster;
    }

    public void setmonstername(String name){
        this.nama = name;
    }

    public void seteltype(List<ElementType> listeltype){
        this.elementTypes = listeltype;
    }

    public void setStats(Stats<Double> stats){
        this.baseStats = stats;
    }

    public void setMoves(ArrayList<Move> moves){
        this.moves = moves;
    }

    public void setCondition(String condition){
        this.condition = condition;
    }

    public void setmovesid(ArrayList<Integer> movesid){
        this.movesid = movesid;
    }

    public void setExtendCondition(Integer num) {
        this.extendCondition = num;
    }

	// GETTER
    public Integer getidmonster() {
        return idmonster;
    }

    public String getName() {
        return this.nama;
    }

    public List<ElementType> getElementTypes() {
        return this.elementTypes;
    }

    public Stats<Double> getBaseStats() {
        return this.baseStats;
    }

    public ArrayList<Move> getMoves() {
        return this.moves;
    }

    public String getStatusCondition() {
        return this.condition;
    }

    public ArrayList<Integer> getmovesid(){
        return this.movesid;
    }

    public Integer getExtendCondition() {
        return this.extendCondition;
    }

    //METHOD
    public boolean isStatusConditionNull() {
        return this.condition.equals("-");
    }

    public Double getBaseHP(ArrayList<Monster> arrmonster) {
        Double basehp = 0.0;
        for(Monster m: arrmonster){
            if(m.getidmonster() == this.getidmonster()){
                basehp = m.getBaseStats().getHealthPoint();
            }
        }
        return basehp;
    }

    public void afterDamage(ArrayList<Monster> arrmonster) {
        Double basehp = getBaseHP(arrmonster);
        Double afterdamage = 0.0;
        if (getStatusCondition().equals("BURN")) {
            afterdamage = basehp*0.125;
            Double finalhp = this.getBaseStats().getHealthPoint()-afterdamage;
            if (finalhp <0 ) {
                finalhp = 0.0;
            }
            this.getBaseStats().setHealthPoint(finalhp);
            System.out.printf("HP Monster %s berkurang sebesar %f akibat efek status %s%n", getName(), afterdamage, getStatusCondition());
            System.out.printf("HP Monster %s saat ini menjadi %f%n", getName(), finalhp);
        } else if (getStatusCondition().equals("POISON")) {
            afterdamage = basehp*0.0625;
            Double finalhp = this.getBaseStats().getHealthPoint()-afterdamage;
            if (finalhp <0 ) {
                finalhp = 0.0;
            }
            this.getBaseStats().setHealthPoint(finalhp);
            System.out.printf("HP Monster %s berkurang sebesar %f akibat efek status %s%n", getName(), afterdamage, getStatusCondition());
            System.out.printf("HP Monster %s saat ini menjadi %f%n", getName(), finalhp);
        } else if (getStatusCondition().equals("SLEEP")) {
            this.extendCondition--;
            System.out.printf("Monster %s memiliki sisa sleep sebanyak %d%n", this.getName(), this.getExtendCondition());
            if (extendCondition == 0) {
                this.setCondition("-");
                System.out.printf("Sleep monster %s sudah habis, status condition monster kembali normal%n", this.getName());
            }
        }
    }

    public boolean isEliminated() {
        return this.baseStats.getHealthPoint() == 0;
    }

    public void printMonster() {
        System.out.println("ID Monster          : " + idmonster);
        System.out.println("Nama                : " + nama);
        System.out.println("Element types       : " + elementTypes);
        for(Move m : moves){
            m.printmonsMove();
        }
        baseStats.printStats();
        System.out.println("Status Condition    : " + condition);
    }

    public String infoListOfMonster() {
        return this.nama + "; " + this.elementTypes + "; " + this.baseStats.getHealthPoint() + "; " + this.condition;
    }

    public void showListOfMoves() {
        for (Move m : this.moves) {
            m.printmonsMove();
        }
    }

}