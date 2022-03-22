public class Stats {
    private Double healthPoint;
    private Double attack;
    private Double defense;
    private Double specialAttack;
    private Double specialDefense;
    private Double speed;
    
    public Stats() {
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    public Double getHealthPoint() {
        return this.healthPoint;
    }

    public void setHealthPoint(Double healthPoint) {
        this.healthPoint = healthPoint;
    }

    public Double getAttack() {
        return this.attack;
    }

    public void setAttack(Double attack) {
        this.attack = attack;
    }
    
    public Double getDefense() {
        return this.defense;
    }

    public void setDefense(Double defense) {
        this.defense = defense;
    }
    
    public Double getSpecialAttack() {
        return this.specialAttack;
    }

    public void setSpecialAttack(Double specialAttack) {
        this.specialAttack = specialAttack;
    }
    
    public Double getSpecialDefense() {
        return this.specialDefense;
    }

    public void setSpecialDefense(Double specialDefense) {
        this.specialDefense = specialDefense;
    }
    
    public Double getSpeed() {
        return this.speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void decreaseStats(Double amount) {

    }
}
