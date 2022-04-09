package com.monstersaku.util;
import java.util.ArrayList;

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
    public float findEffectivity(Monster target, ArrayList<ElementEffectivity> arreffectivity){
        float effectivity = (float) 1.0;
        for(ElementEffectivity ef: arreffectivity){
            for(int i = 0; i < target.getElementTypes().size(); i++){
                if((moveelementType.equals(ef.getElementattacker())) && (target.getElementTypes().get(i).equals(ef.getElementattacker()))){
                    effectivity = (float) (effectivity * ef.getEffectivity());
                }
            }
        }
        return effectivity;
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
        //ID ga usah ya? biar gak salah masukin nomor move 
        // System.out.println("ID Move             : " + idMove);
        System.out.println("Move Type           : " + movetype);
        System.out.println("Move Name           : " + movename);
        System.out.println("Move Element Type   : " + moveelementType);
        System.out.println("Ammunition          : " + ammunition);
    }
    public void useMove (Monster monsterPlayer2, Monster monsterPlayer1,
            ArrayList<ElementEffectivity> arreffectivity, ArrayList<Monster> arrmonster) {
    }

    // public void useNormalMove(Monster monsterPlayer2, Monster monsterPlayer1,
    //         ArrayList<ElementEffectivity> arreffectivity) {
    // }
    // public void useSpecialMove(Monster monsterPlayer2, Monster monsterPlayer1,
    //         ArrayList<ElementEffectivity> arreffectivity) {
    // }

    // public void useMove(Monster monster, Monster enemy,
    //         ArrayList<ElementEffectivity> arreffectivity) {
    // }

    public void changeCondition(Monster monsterPlayer1) {
    }
    public void changeHP(Monster monsterPlayer2) {
    }
}
