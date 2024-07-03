import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Enemy extends Creature {
    //this is an enemy that will attack the player
    private Token target;
    private ArrayList<Token> noContact = new ArrayList<Token>();//to prevent hitting same projectile again
    private double armor;
    public Enemy(int xPos,int yPos,double moveSpeed,double maxHp,int xSize,int ySize,int xHit,int yHit, double armor, Token target){
        super(Color.BLUE, xPos, yPos, moveSpeed , maxHp,xSize,ySize,xHit,yHit);

        this.target = target;
        this.armor = armor;
        
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

        //TODO player movement jostles so dense packs of enemies dont get moved
        for (int i = 0; i<Main.tokens.size();i++){
            if (Main.tokens.get(i) instanceof Enemy && Main.tokens.get(i) != this){
                Rectangle blocker = Main.tokens.get(i).hitbox;

                this.hitbox = new Rectangle((int)(this.xPos+x), (int)(this.yPos), this.xHit, this.yHit);
                if (this.hitbox.intersects(blocker)){
                        x = 0;
                    //check if it is safe to accelerate in y
                    this.hitbox = new Rectangle((int)(this.xPos), (int)(this.yPos+1), this.xHit, this.yHit);
                    if (this.hitbox.intersects(blocker)){
                        //leave y unchanged
                    } else {
                        if (y < 0){
                            y = -1*this.moveSpeed*Main.dynamicTick;
                        } else {
                            y = 1*this.moveSpeed*Main.dynamicTick;
                        }
                    }
                }
                //only y
                this.hitbox = new Rectangle((int)(this.xPos), (int)(this.yPos+y), this.xHit, this.yHit);
                if (this.hitbox.intersects(blocker)){
                         y = 0;
                    //check if it is safe to accelerate in x
                    this.hitbox = new Rectangle((int)(this.xPos+1), (int)(this.yPos), this.xHit, this.yHit);
                    if (this.hitbox.intersects(blocker)){
                        //leave x unchanged
                    } else {
                        if(x < 0){
                        x = -1*this.moveSpeed*Main.dynamicTick;
                        } else {
                            x = 1*this.moveSpeed*Main.dynamicTick;
                        }
                    }
                }
            }
        }
        //second pass without acceleration in the other direction
        for (int i = 0; i<Main.tokens.size();i++){
            if (Main.tokens.get(i) instanceof Enemy && Main.tokens.get(i) != this){
                Rectangle blocker = Main.tokens.get(i).hitbox;

                this.hitbox = new Rectangle((int)(this.xPos+x), (int)(this.yPos), this.xHit, this.yHit);
                if (this.hitbox.intersects(blocker)){
                        x = 0;
        
                }
                //only y
                this.hitbox = new Rectangle((int)(this.xPos), (int)(this.yPos+y), this.xHit, this.yHit);
                if (this.hitbox.intersects(blocker)){
                        y = 0;
                    }
                
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
        if(distance<100){//if enemy is close switch to attack animation
            this.animationSet = 1;
        } else {
            this.animationSet = 0;
        }
        super.animationStep();
    }
    @Override
    protected void takeDamage(double damage){
        //armor calculation
        //the bullet is either able to penetrate the armor (damage-armor) and has a flat reduction, or it thumps against it (damage/armor) and is divided
        damage = Math.max((damage-this.armor),(damage/this.armor));
        super.takeDamage(damage);
    }

    @Override
    protected void die(){
        Main.tokens.remove(this);
    }
}
