package com.monstersaku.util;

public class StatusMove extends Move {
    private StatusCondition moveeffect;
    private Double movehp;
    private Double moveatk;
    private Double movedef;
    private Double movespatk;
    private Double movespdef;
    private Double movespeed;
    
    public StatusMove(Integer idMove, MoveType movetype, String movename, ElementType moveelementType, Integer accuracy, Integer priority, Integer ammunition, Target target, Double damage, StatusCondition moveeffect, Double movehp, Double moveatk, Double movedef, Double movespatk, Double movespdef, Double movespeed){
        super(idMove, movetype, movename, moveelementType, accuracy, priority, ammunition, target, damage);
        this.moveeffect = moveeffect;
        this.movehp = movehp;
        this.moveatk = moveatk;
        this.movedef = movedef;
        this.movespatk = movespatk;
        this.movespdef = movespdef;
        this.movespeed = movespeed;    
    }

    public StatusCondition getmoveeffect(){
        return moveeffect;
    }
    public Double getmovehp(){
        return movehp;
    }
    
    public Double getmoveatk(){
        return moveatk;
    }
    
    public Double getmovedef(){
        return movedef;
    }
    
    public Double getmovespatk(){
        return movespatk;
    }
    
    public Double getmovespdef(){
        return movespdef;
    }
    
    public Double getmovespeed(){
        return movespeed;
    }
}
