package com.monstersaku.util;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import javax.lang.model.element.Element;

public class Monster {
    private Integer idmonster;
    private String nama;
    private List<ElementType> elementTypes;
    private Stats<Double> baseStats;
    private ArrayList<Move> moves;
    private String condition;

    // KONSTRUKTOR
    public Monster(Integer idmonster, String nama, List<ElementType> elementTypes, Stats<Double> baseStats) {
        this.idmonster = idmonster;
        this.nama = nama;
        this.elementTypes = elementTypes;
        this.baseStats = baseStats;
    }

    public Monster(Integer idmonster, String nama, List<ElementType> elementTypes, Stats<Double> baseStats,
            ArrayList<Move> moves, String condition) {
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

    public List<Move> getMoves() {
        return this.moves;
    }

    public String getStatusCondition() {
        return this.condition;
    }

    // SETTER (yang mungkin dibutuhkan aja)
    // asumsi nama dan elType monster gabisa/gaperlu diubah-ubah
    public void setBaseStats(Stats<Double> newBaseStats) {
        // melakukan set terhadap status HP monster setelah terkena damage
        this.baseStats = newBaseStats;
    }

    public void setMoves(Move move) {
        // mengurangi ammunition move yang digunakan (YANG DIPILIH PLAYER saat melakukan
        // MOVE)
        // iterasi list of move
        // kalau move.getName() sama --> panggil method move (misal
        // setAmmunition(getAmmunition-1) untuk mengurangi nilai ammunition move yang
        // ada dalam list
        // cek kalau ammunition == 0, dikeluarkan dari list of move
        // asumsi: move berhasil dieksekusi atau ngga, ammunition tetap akan berkurang
        // 1??
    }

    public void setStatusCondition(String newCondition) {
        // melakukan set terhadap status condition saat monster terkena status move dari
        // moster lawan
        this.condition = newCondition;
    }

    // OTHER METHOD
    public boolean isStatusConditionNull() {
        // cek apakah monster sudah memiliki status condition
        return this.condition == null;

        // aturannya monster hanya boleh terkena SATU status condition
        // kalau dikenai status move pilihannya mau gmn??
        // status move tetep berhasil dieksekusi --> status condition monster berubah
        // status move batal dieksekusi --> status condition monster tetap
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

    public void damageCalculation(Monster attacker, Move move) {
        // menghitung damage
        // **data yang dibutuhin
        // sourceattack, targetdeffence, burnstatus : dari monster
        // power: effect dari move pool??

        // setHP monster yang diserang (this.)
    }

    public boolean isEliminated() {
        //
        return this.baseStats.getHealthPoint() == 0;
    }

    public void printMonster() {
        System.out.println("ID Monster          : " + idmonster);
        System.out.println("Nama                : " + nama);
        System.out.println("Element types       : " + elementTypes);
        for (Move move : moves) {
            move.printmonsMove();
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

    public void monsterMovement(int moveIdx) {
        if (this.getMoves().get(moveIdx).gettarget() == Target.ENEMY) {
            // damageCalculation(monsterPlayer2.getMoves().get(moveIdx), monsterPlayer1);
        } else {
            // damageCalculation(monsterPlayer2.getMoves().get(moveIdx), monsterPlayer2);
        }
    }

    public int moveIdx() {
        Scanner scan = new Scanner(System.in);
        System.out.println("== Pilih move yang akan digunakan ==\n");
        for (int i = 0; i <= this.getMoves().size()-1; i++) {
            System.out.println("Move Nomor " + (i + 1));
            this.getMoves().get(i).printmonsMove();
            System.out.println();
        }
        System.out.print("Masukkan nomor move: ");
        int monsterMove = scan.nextInt();
        while (monsterMove > this.getMoves().size() || monsterMove < 1) {
            System.out.println("ERROR: Input di luar range!");
            System.out.println();
            System.out.print("Silakan masukan ulang nomor: ");
            monsterMove = scan.nextInt();
        }
        return monsterMove-1;
    }

}