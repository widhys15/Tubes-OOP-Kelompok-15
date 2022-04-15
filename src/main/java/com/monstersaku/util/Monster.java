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
    protected Stats<Integer> statsBuff = new Stats<Integer>(0,0,0,0,0,0);

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

    public void setStatsBuff(Stats<Integer> buff) {
        this.statsBuff = buff;
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

    public Stats<Integer> getStatsBuff() {
        return this.statsBuff;
    }

    //GETTER STATS MONSTER (BONUS)
    public Double getHealthPoint() {
        return this.getBaseStats().getHealthPoint();
    }

    public Double getAttack() {
        return Math.floor(this.getBaseStats().getAttack()*this.getStatsBuff().getFactor(this.getStatsBuff().getAttack()));
    }

    public Double getDefense() {
        return Math.floor(this.getBaseStats().getDefense()*this.getStatsBuff().getFactor(this.getStatsBuff().getDefense()));
    }

    public Double getSpecialAttack() {
        return Math.floor(this.getBaseStats().getSpecialAttack()*this.getStatsBuff().getFactor(this.getStatsBuff().getSpecialAttack()));
    }

    public Double getSpecialDefense() {
        return Math.floor(this.getBaseStats().getSpecialDefense()*this.getStatsBuff().getFactor(this.getStatsBuff().getSpecialDefense()));
    }

    public Double getSpeed() {
        return Math.floor(this.getBaseStats().getSpeed()*this.getStatsBuff().getFactor(this.getStatsBuff().getSpeed()));
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
        Double basehp = this.getBaseHP(arrmonster);
        Double afterdamage = 0.0;
        if (getStatusCondition().equals("BURN")) {
            afterdamage = Math.floor(basehp*0.125);
            Double finalhp = this.getBaseStats().getHealthPoint()-afterdamage;
            if (finalhp < 0 ) {
                finalhp = 0.0;
            }
            this.getBaseStats().setHealthPoint((double) Math.round(finalhp));
            System.out.printf("HP Monster %s berkurang sebesar %.0f akibat efek status %s%n", getName(), afterdamage, getStatusCondition());
            System.out.printf("HP Monster %s saat ini menjadi %.0f%n", getName(), finalhp);
        } else if (getStatusCondition().equals("POISON")) {
            afterdamage = Math.floor(basehp*0.0625);
            Double finalhp = this.getBaseStats().getHealthPoint()-afterdamage;
            if (finalhp < 0 ) {
                finalhp = 0.0;
            }
            this.getBaseStats().setHealthPoint((double) Math.round(finalhp));
            System.out.printf("HP Monster %s berkurang sebesar %.0f akibat efek status %s%n", getName(), afterdamage, getStatusCondition());
            System.out.printf("HP Monster %s saat ini menjadi %.0f%n", getName(), finalhp);
        } else if (getStatusCondition().equals("SLEEP")) {
            System.out.println("Tidak ada (Status Condition SLEEP)");
        } else if (getStatusCondition().equals("PARALYZE")) {
            System.out.println("Tidak ada (Status Condition PARALYZE)");
        }
    }

    public boolean isEliminated() {
        return this.baseStats.getHealthPoint() == 0;
    }

    public void printMonster() {
        System.out.println("ID Monster          : " + idmonster);
        System.out.println("Nama                : " + nama);
        System.out.println("Element types       : " + elementTypes);
        System.out.println("Available Moves");
        for(Move m : moves){
            System.out.println("    " + m.getmovename() + ": " + m.getammunition());
        }
        baseStats.printStats();
        System.out.println("Status Condition    : " + condition);
    }

    public void printMonsterCompact(ArrayList<Monster> arrmonster) {
        System.out.println(nama + " : " + baseStats.getHealthPoint() + "/" + this.getBaseHP(arrmonster) + " : " + elementTypes + " : " + condition);
        System.out.println("Stats");
        this.baseStats.printStatsCompact();
        System.out.println("Available Moves");
        for(Move m : moves){
            System.out.println("    " + m.getmovename() + ": " + m.getammunition());
        }
        System.out.println("Stats Buff");
        statsBuff.printStatsBuff(this);
    }

    public String infoListOfMonster() {
        return this.nama + "; " + this.elementTypes + "; " + this.baseStats.getHealthPoint() + "; " + this.condition;
    }

    public void showListOfMoves() {
        for (Move m : this.moves) {
            m.printmonsMove();
        }
    }

    public void checkMoveAmmunition(Move move) {
        if (move.getammunition() == 0) {
            System.out.printf("Ammunition Move %s sudah habis, move akan dihapus dari daftar available move%n", move.getmovename());
            this.getMoves().remove(move);
        }
    }
}