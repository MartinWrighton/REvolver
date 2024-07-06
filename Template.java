

public class Template {
    private double moveSpeed;
    private double maxHP;
    private double armor;
    private int score;
    private double regenRate;
    private double regenDelay;
    public Template(double moveSpeed,double maxHP,double armor,double regenRate,double regenDelay){
        this.moveSpeed =  moveSpeed;
        this.maxHP = maxHP;
        this.armor = armor;
        this.score = 0;
        this.regenRate = regenRate;
        this.regenDelay = regenDelay;
    }

    public void update(double moveSpeed,double maxHP,double armor,int score,double regenRate,double regenDelay){
        this.moveSpeed =  moveSpeed;
        this.maxHP = maxHP;
        this.armor = armor;
        this.score = score;
        this.regenRate = regenRate;
        this.regenDelay = regenDelay;
    }

    public double getMoveSpeed(){
        return this.moveSpeed;
    }
    public double getMaxHP(){
        return this.maxHP;
    }
    public double getArmor(){
        return this.armor;
    }
    public double getScore(){
        return this.score;
    }
    public double getRegenRate(){
        return this.regenRate;
    }
    public double getRegenDelay(){
        return this.regenDelay;
    }
    public void scoreDecay(){
        //TODO consider making score decay by a % of the dying tokens score
        this.score--;
    }
}
