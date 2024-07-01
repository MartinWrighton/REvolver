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
        this.maxHP = 3;
        this.HP = this.maxHP;
    }
    @Override
    protected void preStep(){
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
        for(int i = 0 ; i<Main.tokens.size();i++){
            if (this.hitbox.intersects(Main.tokens.get(i).hitbox) && Main.tokens.get(i) instanceof Projectile){
                Projectile bullet = ((Projectile) Main.tokens.get(i));
                bullet.hit();
                takeDamage(bullet.damage);
            }
        }
    }
    
    

    @Override
    protected void die(){
        Main.tokens.remove(this);
    }
}
