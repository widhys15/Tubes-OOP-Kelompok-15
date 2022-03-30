package com.monstersaku.util;

import javax.naming.NameAlreadyBoundException;
import java.util.Random;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Player {
    private String nama;
    private ArrayList<Monster> listOfMonster;
    private static int numberOfMonster;

    //Konstruktor
    public Player(String nama, ArrayList<Monster> listOfMonster){
        //Secara default ketika permainan dimulai, jumlah monster 6
        this.nama = nama;
        this.listOfMonster = listOfMonster;
        this.numberOfMonster = 6;
    }

    public Player(String nama, ArrayList<Monster> listOfMonster, int numberOfMonster){
        this.nama = nama;
        this.listOfMonster = listOfMonster;
        this.numberOfMonster = numberOfMonster;
    }

    //Getter
    public String getName(){
        return this.nama;
    }

    public ArrayList<Monster> getListOfMonster(){
        return this.listOfMonster;
    }

    public int getNumberOfMonster(){
        return this.numberOfMonster;
    }


    //Setter
    public void setName(String nama){
        this.nama = nama;
    }

    public void setListOfMonster(ArrayList<Monster> listOfMonster){
        this.listOfMonster = listOfMonster;
    }

    public void setNumberOfMonster(int numberOfMonster){
        this.numberOfMonster = numberOfMonster;
    }
}


