package com.monstersaku.util;

import java.util.*;

public class Player {
    private String nama;
    private ArrayList<Monster> listOfMonster;

    //Konstruktor
    public Player(String nama, ArrayList<Monster> listOfMonster){
        //Secara default ketika permainan dimulai, jumlah monster 6
        this.nama = nama;
        this.listOfMonster = listOfMonster;
    }

    //Getter
    public String getName(){
        return this.nama;
    }

    public ArrayList<Monster> getListOfMonster(){
        return this.listOfMonster;
    }

    public int getNumberOfMonster(){
        return this.listOfMonster.size();
    }
    public void showListOfMonster(ArrayList<Monster> arrmonster){
        for(Monster m: listOfMonster){
            try {
                m.printMonsterCompact(arrmonster);
                System.out.println();
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }


    //Setter
    public void setName(String nama){
        this.nama = nama;
    }

    public void setListOfMonster(ArrayList<Monster> listOfMonster){
        this.listOfMonster = listOfMonster;
    }
}