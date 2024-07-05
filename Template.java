

public class Template {
    private double moveSpeed;
    private double maxHP;
    private double armor;
    private int score;
    public Template(double moveSpeed,double maxHP,double armor){
        this.moveSpeed =  moveSpeed;
        this.maxHP = maxHP;
        this.armor = armor;
        this.score = 0;
    }

    public void update(double moveSpeed,double maxHP,double armor,int score){
        this.moveSpeed =  moveSpeed;
        this.maxHP = maxHP;
        this.armor = armor;
        this.score = score;
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
}
