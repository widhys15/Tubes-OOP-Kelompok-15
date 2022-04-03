package com.monstersaku.util;

import java.util.ArrayList;

public class ElementEffectivity {
    ElementType attacker;
    ElementType target;
    Double effectivity;
    
    //Konstruktor
    public ElementEffectivity(ElementType attacker, ElementType target, Double effectivity){
        this.attacker = attacker;
        this.target = target;
        this.effectivity = effectivity;
    }

    //Getter
    public ElementType getElementattacker(){
        return attacker;
    }
    public ElementType getElementtarget(){
        return target;
    }
    public Double getEffectivity(){
        return effectivity;
    }

    public void printeffectivity(){
        System.out.println("Attacker type: " + attacker);
        System.out.println("Target type: " + target);
        System.out.println("Effectivity: " + effectivity);
    }
}
