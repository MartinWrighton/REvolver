import java.awt.Color;
import java.awt.Rectangle;

public class Creature extends Token{
    //this is a creature, that can live and die
    protected double maxHP;
    protected double HP;
    
    public Creature(Color color, double xPos, double yPos, double moveSpeed, double maxHP){
        super();
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
        this.moveSpeed = moveSpeed;
        this.maxHP = maxHP;
        this.HP = this.maxHP;
        this.xSize = 50;
        this.ySize = 50;
        this.xHit = 30;
        this.yHit = 50;
        this.hitbox = new Rectangle((int)xPos, (int)yPos, this.xHit, this.yHit);
    }

    protected void takeDamage(double damage){
        this.HP-=damage;
        if (this.HP <= 0){
            die();
        }
    }
    protected void die(){}
    
}
