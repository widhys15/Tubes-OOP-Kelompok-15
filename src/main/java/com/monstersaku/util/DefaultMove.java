package com.monstersaku.util;

public class DefaultMove extends AbsMove {
    protected Double damage;
    
    public DefaultMove(){
        idMove = 0;
        movetype = MoveType.DEFAULT;
        movename = "Normal Move";
        moveelementType = ElementType.NORMAL;
        accuracy = 100;
        priority = 0;
        ammunition = 999999;
        target = Target.ENEMY;
        damage = 50.0;
    }

    public void printMove(){
        super.printMove();
        System.out.println("Damage              : " + damage);
    }
}
