
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Enemy extends Creature {
    //this is an enemy that will attack the player
    private Token target;
    private ArrayList<Token> noContact = new ArrayList<Token>();//to prevent hitting same projectile again
    public Enemy(int xPos,int yPos,Token target){
        super(Color.BLUE, xPos, yPos, 0.09 , 6);

        this.target = Main.player;

        try {
            ArrayList<BufferedImage> set = new ArrayList<BufferedImage>();
            set.add(ImageIO.read(new File("resources\\PixelEnemyLeftFoot.PNG")));
            set.add(ImageIO.read(new File("resources\\PixelEnemyNeutral.PNG")));
            set.add(ImageIO.read(new File("resources\\PixelEnemyRightFoot.PNG")));
            set.add(ImageIO.read(new File("resources\\PixelEnemyNeutral.PNG")));
            this.tokenImages.add(set);

            set = new ArrayList<BufferedImage>();
            set.add(ImageIO.read(new File("resources\\PixelPlayerLeftFoot.PNG")));
            set.add(ImageIO.read(new File("resources\\PixelPlayerNeutral.PNG")));
            set.add(ImageIO.read(new File("resources\\PixelPlayerRightFoot.PNG")));
            set.add(ImageIO.read(new File("resources\\PixelPlayerNeutral.PNG")));
            this.tokenImages.add(set);
        } catch (IOException e) {
            System.out.println("Failed to load Enemy image");
        }
    }
    @Override
    protected void pathfind(){
        double difx = Math.abs(target.xPos - this.xPos);
        double dify = Math.abs(target.yPos - this.yPos);
        double distance = Math.sqrt(difx*difx + dify*dify);
        double movex = difx/distance;
        double movey = dify/distance;;
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
    }
    @Override
    public void move(double x, double y){
        /* 
        if (this.xPos + d > 0 && this.xPos + d < Main.screenWidth-25){
            this.xPos = this.xPos + d;
        }
        if (this.yPos + e > 0 && this.yPos + e < Main.screenHeight-50){
            this.yPos = this.yPos + e;
        }
        */
        
        //jostle checks
        //TODO if move fails in one direction make them move as much has possible in the other, be careful that adding this extra movement does not make them clip
        //TODO player movement jostles so dense packs of enemies dont get moved
        for (int i = 0; i<Main.tokens.size();i++){
            //diagonal
            /*
            this.hitbox = new Rectangle((int)(this.xPos+x), (int)(this.yPos+y), this.xHit, this.yHit);
            if (this.hitbox.intersects(Main.tokens.get(i).hitbox) && Main.tokens.get(i) instanceof Enemy && Main.tokens.get(i) != this){
                x = 0;
                y = 0;
            }
            */
            //only x
            this.hitbox = new Rectangle((int)(this.xPos+x), (int)(this.yPos), this.xHit, this.yHit);
            if (this.hitbox.intersects(Main.tokens.get(i).hitbox) && Main.tokens.get(i) instanceof Enemy && Main.tokens.get(i) != this){
                x = 0;
            }
            //only y
            this.hitbox = new Rectangle((int)(this.xPos), (int)(this.yPos+y), this.xHit, this.yHit);
            if (this.hitbox.intersects(Main.tokens.get(i).hitbox) && Main.tokens.get(i) instanceof Enemy && Main.tokens.get(i) != this){
                y = 0;
            }
        }
        super.move(x,y);

    }
    

    @Override
    protected void postStep(){
        //includes no contact to avoid hitting projectiles twice
        ArrayList<Token> currentContact = new ArrayList<Token>(this.noContact);
        this.noContact.clear();
        for(int i = 0 ; i<Main.tokens.size();i++){
            if (this.hitbox.intersects(Main.tokens.get(i).hitbox) && Main.tokens.get(i) instanceof Projectile){
                Projectile bullet = ((Projectile) Main.tokens.get(i));
                
                if (!currentContact.contains(bullet)){
                    bullet.hit();
                    takeDamage(bullet.damage);
                }
                this.noContact.add(bullet);
            }
        }
    }
    
    @Override
    protected void animationStep(){

        double difx = Math.abs(target.xPos - this.xPos);
        double dify = Math.abs(target.yPos - this.yPos);
        double distance = Math.sqrt(difx*difx + dify*dify);
        if(distance<300){
            this.animationSet = 1;
        } else {
            this.animationSet = 0;
        }
        super.animationStep();
    }

    @Override
    protected void die(){
        Main.tokens.remove(this);
    }
}
