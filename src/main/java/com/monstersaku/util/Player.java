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
    public void showListOfMonster(){
        for(Monster m: listOfMonster){
            m.printMonster();
            System.out.println();
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