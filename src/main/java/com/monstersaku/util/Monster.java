package com.monstersaku.util;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import javax.lang.model.element.Element;

public class Monster {
    protected Integer idmonster;
    protected String nama;
    protected List<ElementType> elementTypes;
    protected Stats<Double> baseStats;
    protected ArrayList<AbsMove> moves;
    protected StatusCondition condition;

    // KONSTRUKTOR
    public Monster(Integer idmonster, String nama, List<ElementType> elementTypes, Stats<Double> baseStats) {
        this.idmonster = idmonster;
        this.nama = nama;
        this.elementTypes = elementTypes;
        this.baseStats = baseStats;
    }

    public Monster(Integer idmonster, String nama, List<ElementType> elementTypes, Stats<Double> baseStats,
            ArrayList<AbsMove> moves, StatusCondition condition) {
        this.idmonster = idmonster;
        this.nama = nama;
        this.elementTypes = elementTypes;
        this.baseStats = baseStats;
        this.moves = moves;
        this.condition = condition;
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

    public List<AbsMove> getMoves() {
        return this.moves;
    }

    public StatusCondition getStatusCondition() {
        return this.condition;
    }

    public void setBaseStats(Stats<Double> newBaseStats) {
        this.baseStats = newBaseStats;
    }

    public void setStatusCondition(StatusCondition newCondition) {
        this.condition = newCondition;
    }

    // OTHER METHOD
    public boolean isStatusConditionNull() {
        return this.condition == null;
    }

    // bertarung

    // cek salah satu monster move null atau tidak, if null:
    // cek prioritas , speed, dll

    // EKSEKUSI MOVE
    // normal move --> damageCalculation()
    // special move --> damageCalculation()
    // status move --> ubah status condition (setStatusCondition()) + langsung
    // eksekusi effect dari status conditionnya?

    // AFTER DAMAGE
    // cek monster yang diserang isEliminated()
    // kalau belum mati, cek status condition monster yang diserang
    // BURN, POISSON --> pengurangan HP
    // SLEEP, PARALYZED??? masih bingung

    // AFTER EFFECT
    // cek monster yang diserang isEliminated()
    // kalau udah mati, player pilih monster lain

    public boolean isEliminated() {
        return this.baseStats.getHealthPoint() == 0;
    }

    public void printMonster() {
        System.out.println("ID Monster          : " + idmonster);
        System.out.println("Nama                : " + nama);
        System.out.println("Element types       : " + elementTypes);
        for (AbsMove move : moves) {
            move.printmonsMove();
        }
        baseStats.printStats();
        System.out.println("Status Condition    : " + condition);
    }

    public String infoListOfMonster() {
        return this.nama + "; " + this.elementTypes + "; " + this.baseStats.getHealthPoint() + "; " + this.condition;
    }

    public void showListOfMoves() {
        for (AbsMove m : this.moves) {
            m.printmonsMove();
        }
    }
}