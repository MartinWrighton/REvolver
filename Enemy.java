import java.awt.Color;

public class Enemy extends Creature {
    //this is an enemy that will attack the player
    public Enemy(int xPos,int yPos){
        super();
        this.color = Color.BLUE;
        this.xPos = xPos;
        this.yPos = yPos;

    }
}
