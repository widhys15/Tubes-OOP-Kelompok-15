package com.monstersaku.util;

public abstract class AbsMove {
    protected Integer idMove;
    protected MoveType movetype; 
    protected String movename;
    protected ElementType moveelementType;
    protected Integer accuracy;
    protected Integer priority;
    protected Integer ammunition;
    protected Target target;

    public Integer getidMove(){
        return this.idMove;
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

    public void printMove(){
        System.out.println("ID Move             : " + idMove);
        System.out.println("Move Type           : " + movetype);
        System.out.println("Move Name           : " + movename);
        System.out.println("Move Element Type   : " + moveelementType);
        System.out.println("Accuracy            : " + accuracy);
        System.out.println("Priority            : " + priority);
        System.out.println("Ammunition          : " + ammunition);
        System.out.println("Target              : " + target);
    }
    public void printmonsMove(){
        System.out.println("ID Move             : " + idMove);
        System.out.println("Move Type           : " + movetype);
        System.out.println("Move Name           : " + movename);
        System.out.println("Move Element Type   : " + moveelementType);
        System.out.println("Ammunition          : " + ammunition);
    }
}
