package com.monstersaku.util;

import javax.naming.NameAlreadyBoundException;

public class Player {
    private String nama;
    private List<Monster> listOfMonster;
    private static int numberOfMonster;

    
    //Konstruktor
    public Player(String nama, List<Monster> listOfMonster){
        //Secara default ketika permainan dimulai, jumlah monster 6
        this.nama = nama;
        this.listOfMonster = listOfMonster;
        this.numberOfMonster = 6;
    }

    public Player(String nama, List<Monster> listOfMonster, int numberOfMonster){
        this.nama = nama;
        this.listOfMonster = listOfMonster;
        this.numberOfMonster = numberOfMonster;
    }

    //Getter
    public String getName(){
        return this.nama;
    }

    public List<Monster> getListOfMonster(){
        return this.listOfMonster;
    }

    public int getNumberOfMonster(){
        return this.numberOfMonster;
    }


    //Setter
    public void setName(String nama){
        this.nama = nama;
    }

    public void setListOfMonster(List<Monster> listOfMonster){
        this.listOfMonster = listOfMonster;
    }

    public void setNumberOfMonster(int numberOfMonster){
        this.numberOfMonster = numberOfMonster;
    }
}


