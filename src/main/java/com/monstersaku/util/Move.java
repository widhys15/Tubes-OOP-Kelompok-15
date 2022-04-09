package com.monstersaku.util;
import java.util.ArrayList;

public abstract class Move {
    protected Integer idmove;
    protected MoveType movetype; 
    protected String movename;
    protected ElementType moveelementType;
    protected Integer accuracy;
    protected Integer priority;
    protected Integer ammunition;
    protected Target target;

    //Konstruktor
    public Move (Integer idmove, MoveType movetype, ElementType elementType, String name, Integer accuracy, Integer priority, Integer ammunition, Target target){
        this.idmove = idmove;
        this.movetype = movetype;
        this.moveelementType = elementType;
        this.movename = name;
        this.accuracy = accuracy;
        this.priority = priority;
        this.ammunition = ammunition;
        this.target = target;
    }
    public Move(){
    }

    //Getter
    public Integer getidMove(){
        return this.idmove;
    }
    public MoveType getmovetype(){
        return movetype;
    } 
    public String getmovename(){
        return movename;
    }
    public ElementType getmoveelementtype(){
        return moveelementType;
    }
    public Integer getaccuracy(){
        return accuracy;
    }
    public Integer getpriority(){
        return priority;
    }
    public Integer getammunition(){
        return ammunition;
    }
    public Target gettarget(){
        return target;
    }

    //Setter
    public void setidMove(Integer idmove){
        this.idmove = idmove;
    }

    public void setmovetype(MoveType movetype){
        this.movetype = movetype;
    }

    public void setmovename(String movename){
        this.movename = movename;
    }

    public void setelementtype(ElementType moveelementtype){
        this.moveelementType = moveelementtype;
    }

    public void setaccuracy(Integer accuracy){
        this.accuracy = accuracy;
    }

    public void setpriority(Integer priority){
        this.priority = priority;
    }

    public void setammunition(Integer ammunition){
        this.ammunition = ammunition;
    }

    public void settarget(Target target){
        this.target = target;
    }

    //Method
    public Double findEffectivity(Monster target, ArrayList<ElementEffectivity> arreffectivity){
        Double effectivity = 1.0;
        for(int i = 0; i < arreffectivity.size(); i++){
            for(int j = 0; j < target.getElementTypes().size(); j++){
                if(arreffectivity.get(i).getElementattacker().equals(moveelementType) && arreffectivity.get(i).getElementtarget().equals(target.getElementTypes().get(j))){
                    // System.out.println("Effect :" + arreffectivity.get(i).getEffectivity());
                    effectivity = effectivity * arreffectivity.get(i).getEffectivity();
                }
            }
        }
        // System.out.println("Effectivity adalah " + effectivity);
        return effectivity;
    }

    public void printMove(){
        System.out.println("ID Move             : " + idmove);
        System.out.println("Move Type           : " + movetype);
        System.out.println("Move Name           : " + movename);
        System.out.println("Move Element Type   : " + moveelementType);
        System.out.println("Accuracy            : " + accuracy);
        System.out.println("Priority            : " + priority);
        System.out.println("Ammunition          : " + ammunition);
        System.out.println("Target              : " + target);
    }
    public void printmonsMove(){
        System.out.println("Move Type           : " + movetype);
        System.out.println("Move Name           : " + movename);
        System.out.println("Move Element Type   : " + moveelementType);
        System.out.println("Ammunition          : " + ammunition);
    }
    
    //Abstract Method
    public abstract void useMove (Monster monsterPlayer2, Monster monsterPlayer1,
            ArrayList<ElementEffectivity> arreffectivity, ArrayList<Monster> arrmonster);
}
