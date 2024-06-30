import java.awt.Color;

public class Enemy extends Creature {
    //this is an enemy that will attack the player
    private Token target;
    public Enemy(int xPos,int yPos,Token target){
        super();
        this.color = Color.BLUE;
        this.xPos = xPos;
        this.yPos = yPos;
        this.moveSpeed = 0.05;
        this.target = target;
    }
    @Override
    public void pathfind(){
        
        if (this.target.xPos < this.xPos){
            this.addDirection(0, 1);
        } else {
            this.addDirection(0, 0);
        }
        if (this.target.xPos > this.xPos){
            this.addDirection(1, 1);
        } else {
            this.addDirection(1, 0);
        }
        if (this.target.yPos < this.yPos){
            this.addDirection(2, 1);
        } else {
            this.addDirection(2, 0);
        }
        if (this.target.yPos > this.yPos){
            this.addDirection(3, 1);
        } else {
            this.addDirection(3, 0);
        }

    }
}
