import java.awt.Color;

public class Creature extends Token{
    //this is a creature, that can live and die
    protected double maxHP;
    protected double HP;
    protected double regenRate;
    protected double regenDelay = 1000;
    protected double regenProgress = 0;
    public Creature(Color color, double xPos, double yPos, double moveSpeed, double maxHP , int xSize, int ySize, int xHit, int yHit, double regenRate,double regenDelay){
        super(color,xPos,yPos,moveSpeed,xSize,ySize,xHit,yHit);

        this.maxHP = maxHP;
        this.HP = this.maxHP;
        this.regenRate = regenRate;
        this.regenDelay = regenDelay;

    }

    protected void takeDamage(double damage){
        this.regenProgress = 0;
        this.HP-=damage;
        if (this.HP <= 0){
            die();
        }
    }
    @Override
    protected void preStep(){
        this.regenProgress += Main.dynamicTick;
        if (this.regenProgress>this.regenDelay){
            this.HP += this.regenRate*Main.dynamicTick;
            if (this.HP < this.maxHP){
                if ((int)this.regenProgress%900==0){
                    Main.tokens.add(new Heal(this.xPos+this.xSize/2,this.yPos,12,12));
                }
            } else {
                this.HP = this.maxHP;
            }
        }
    }

    protected void die(){}
    
}
