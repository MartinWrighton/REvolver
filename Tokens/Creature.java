import java.awt.Color;

public class Creature extends Token{
    //this is a creature, that can live and die
    protected double maxHP;
    protected double HP;
    
    public Creature(Color color, double xPos, double yPos, double moveSpeed, double maxHP , int xSize, int ySize, int xHit, int yHit){
        super(color,xPos,yPos,moveSpeed,xSize,ySize,xHit,yHit);

        this.maxHP = maxHP;
        this.HP = this.maxHP;

    }

    protected void takeDamage(double damage){
        this.HP-=damage;
        if (this.HP <= 0){
            die();
        }
    }
    protected void die(){}
    
}
