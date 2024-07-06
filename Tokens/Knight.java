import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Knight extends Enemy{
    public Knight(int xPos,int yPos,Token target){
        super(xPos, yPos, Main.knightTemplate.getMoveSpeed(), Main.knightTemplate.getMaxHP(), 36, 40, 36, 40, Main.knightTemplate.getArmor(),target,Main.knightTemplate,Main.knightTemplate.getRegenRate(),Main.knightTemplate.getRegenDelay());

        
        this.animationSpeed = 0.015;
        try {
            ArrayList<BufferedImage> set = new ArrayList<BufferedImage>();
            for (int i = 0 ; i<16;i++){
                set.add(ImageIO.read(new File("resources\\PixelKnight\\PixelKnightWalk"+Integer.toString(i)+".png")));
            }
            this.tokenImages.add(set);

            set = new ArrayList<BufferedImage>();
            for (int i = 0 ; i<16;i++){
                set.add(ImageIO.read(new File("resources\\PixelKnight\\PixelKnightAttacking"+Integer.toString(i)+".png")));
            }
            
            this.tokenImages.add(set);
        } catch (IOException e) {
            System.out.println("Failed to load Knight image");
        }
    }
    protected void pathfind(){
        double difx = Math.abs(target.xPos - this.xPos);
            double dify = Math.abs(target.yPos - this.yPos);
            double distance = Math.sqrt(difx*difx + dify*dify);
            //attempting predictive guidance
            
            //how long would it take to reach the players current position
            double timeToPlayer = distance/this.moveSpeed;



            //where will the player be at that time
            double newX = target.xPos + timeToPlayer*-target.moveSpeed*(target.direction[1]-target.direction[0]);
            double newY = target.yPos + timeToPlayer*-target.moveSpeed*(target.direction[3]-target.direction[2]);



            double oldTime = 0;
            int breakCount = 101;
            //recalculate trajectory
            while(((int)oldTime < 0.9*(int)timeToPlayer ||(int)oldTime > 1.1*(int)timeToPlayer) && breakCount>0){
                oldTime = timeToPlayer;
                breakCount--;

                difx = Math.abs(newX - this.xPos);
                dify = Math.abs(newY - this.yPos);
                distance = Math.sqrt(difx*difx + dify*dify);

                //how long would it take to reach the players new position
                timeToPlayer = distance/this.moveSpeed;
                
                //where will the player be at that time
                newX = target.xPos + timeToPlayer*-target.moveSpeed*(target.direction[1]-target.direction[0]);
                newY = target.yPos + timeToPlayer*-target.moveSpeed*(target.direction[3]-target.direction[2]);
            }
            /*
            if (breakCount == 0){
                newX = target.xPos;
                newY = target.yPos;
            }
            */
            if (Main.showWaypoints){
                if (new Random().nextInt(1)<=1){
                    Main.tokens.add(new Waypoint(newX, newY, 10, 10,(int) this.xPos+this.xSize/2,(int)this.yPos+this.ySize/2));

                }
            }

            difx = Math.abs(newX - this.xPos);
            dify = Math.abs(newY - this.yPos);
            distance = Math.sqrt(difx*difx + dify*dify);

            

            double movex = difx/distance;
            double movey = dify/distance;
            if (newX < this.xPos){
                this.addDirection(0, movex);
                this.addDirection(1, 0);
            } else if (newX > this.xPos){
                this.addDirection(1, movex);
                this.addDirection(0, 0);
            } else {
                this.addDirection(0,0);
                this.addDirection(1, 0);
            }
            if (newY < this.yPos){
                this.addDirection(2, movey);
                this.addDirection(3, 0);
            } else if (newY > this.yPos){
                this.addDirection(3, movey);
                this.addDirection(2, 0);
            } else {
                this.addDirection(2,0);
                this.addDirection(3, 0);
            }
            if ((this.direction[0]>0 && direction[1]>0)||(this.direction[2]>0 && direction[3]>0)){
                System.out.println("OOOPS");
            }
        

    }

}
