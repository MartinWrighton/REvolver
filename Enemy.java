import java.awt.Color;

public class Enemy extends Creature {
    //this is an enemy that will attack the player
    private Token target;
    public Enemy(int xPos,int yPos,Token target){
        super();
        this.color = Color.BLUE;
        this.xPos = xPos;
        this.yPos = yPos;
        this.moveSpeed = 0.09;
        this.target = target;
    }
    @Override
    public void pathfind(){
        
        double difx = Math.abs(target.xPos - this.xPos);
        double dify = Math.abs(target.yPos - this.yPos);
        double distance = difx + dify;
        double movex;
        if (difx != 0){
            movex = difx/distance;
        } else {
            movex = 0;
        }
        double movey;
        if (dify != 0){
            movey = dify/distance;
        } else {
            movey = 0;
        }

        if (this.target.xPos < this.xPos){
            this.addDirection(0, movex);
        } else if (this.target.xPos > this.xPos){
            this.addDirection(1, movex);
        } else {
            this.addDirection(0,0);
            this.addDirection(1, 0);
        }
        if (this.target.yPos < this.yPos){
            this.addDirection(2, movey);
        } else if (this.target.yPos > this.yPos){
            this.addDirection(3, movey);
        } else {
            this.addDirection(2,0);
            this.addDirection(3, 0);
        }



        /*
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
        */

    }
}
