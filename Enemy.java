import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Enemy extends Creature {
    //this is an enemy that will attack the player
    private Token target;
    private ArrayList<Token> noContact = new ArrayList<Token>();//to prevent hitting same projectile again
    public Enemy(int xPos,int yPos,Token target){
        super();
        this.color = Color.BLUE;
        this.xPos = xPos;
        this.yPos = yPos;
        this.moveSpeed = 0.09;
        this.target = target;
        this.maxHP = 3;
        this.HP = this.maxHP;
        try {
            this.tokenImages.add(ImageIO.read(new File("resources\\PixelEnemyLeftFoot.PNG")));
            this.tokenImages.add(ImageIO.read(new File("resources\\PixelEnemyNeutral.PNG")));
            this.tokenImages.add(ImageIO.read(new File("resources\\PixelEnemyRightFoot.PNG")));
            this.tokenImages.add(ImageIO.read(new File("resources\\PixelEnemyNeutral.PNG")));
        } catch (IOException e) {
            System.out.println("Failed to load Enemy image");
        }
    }
    @Override
    protected void pathfind(){
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
    protected void die(){
        Main.tokens.remove(this);
    }
}
