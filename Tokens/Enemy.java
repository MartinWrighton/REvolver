import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;


public class Enemy extends Creature {
    //this is an enemy that will attack the player
    protected Token target;
    private ArrayList<Token> noContact = new ArrayList<Token>();//to prevent hitting same projectile again
    protected double armor;
    protected double rawDamage = 0;
    protected int hurtPlayer = 0;
    protected Template template;
    public Enemy(int xPos,int yPos,double moveSpeed,double maxHp,int xSize,int ySize,int xHit,int yHit, double armor, Token target,Template template,double regenRate,double regenDelay){
        super(Color.BLUE, xPos, yPos, moveSpeed , maxHp,xSize,ySize,xHit,yHit,regenRate,regenDelay);

        this.target = target;
        this.armor = armor;
        this.template = template;
        
    }
    @Override
    protected void pathfind(){
        double difx = Math.abs(target.xPos - this.xPos);
        double dify = Math.abs(target.yPos - this.yPos);
        double distance = Math.sqrt(difx*difx + dify*dify);
        
        double newX = target.xPos;
        double newY = target.yPos;
        if (Main.showWaypoints){
            if (new Random().nextInt(1)<=1){
                Main.tokens.add(new Waypoint(newX, newY, 10, 10,(int) this.xPos+this.xSize/2,(int)this.yPos+this.ySize/2));

            }
        }

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
            if (this.hitbox.intersects(Main.tokens.get(i).hitbox) && Main.tokens.get(i) instanceof Projectile && !(Main.tokens.get(i) instanceof WizardProjectile)){
                Projectile bullet = ((Projectile) Main.tokens.get(i));
                
                if (!currentContact.contains(bullet)){
                    bullet.hit();
                    takeDamage(bullet.damage);
                }
                this.noContact.add(bullet);
            }
        }
        //kill enemies who get too far away
        double difx = Math.abs(target.xPos - this.xPos);
        double dify = Math.abs(target.yPos - this.yPos);
        if (difx>Main.screenWidth*3||dify>Main.screenHeight*3){
            this.die();
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
        //set size to keep aspect ratio
        this.xSize = this.tokenImages.get(this.animationSet).get(0).getWidth()*2;//TODO consider replacing this 2 with a tokenScale attribute
        this.ySize = this.tokenImages.get(this.animationSet).get(0).getHeight()*2;
        super.animationStep();
    }
    @Override
    protected void takeDamage(double damage){
        this.rawDamage += damage;
        //armor calculation
        //the bullet is either able to penetrate the armor (damage-armor) and has a flat reduction, or it thumps against it (damage/armor) and is divided
        damage = Math.max((damage-this.armor),(damage/this.armor));
        super.takeDamage(damage);
    }

    @Override
    protected void die(){
        //calculate score
        int distanceScoreX = (int) ((Main.screenWidth/2)-Math.abs(this.target.xPos-this.xPos));
        distanceScoreX = 100*(distanceScoreX)/(Main.screenWidth/2);
        int distanceScoreY = (int) ((Main.screenHeight/2)-Math.abs(this.target.yPos-this.yPos));
        distanceScoreY = (distanceScoreY)/(Main.screenWidth/2);
        int distanceScoreBoth = (int) Math.sqrt(distanceScoreX*distanceScoreX+distanceScoreY*distanceScoreY);

        int score = (int) (distanceScoreBoth + this.rawDamage*5 + this.hurtPlayer*100);
        if (Main.printScore){
            System.out.println("\n Distance: "+distanceScoreBoth+"  Raw Damage: "+this.rawDamage*5+"  Hurt Player: "+this.hurtPlayer*100+"  Total: "+score);
        }
        if (score>this.template.getScore()){
            this.template.update(this.moveSpeed, this.maxHP, this.armor, score,this.regenRate,this.regenDelay);
        } else {
            this.template.scoreDecay();
        }

        //TODO death animations
        Main.tokens.remove(this);
    }

    public void hurtPlayer(){
        this.hurtPlayer++;
    }

    public void mutate(){

        //change each stat by a random percentage

        Random random = new Random();
        this.moveSpeed *= 1-Main.mutationRate/2+random.nextDouble(Main.mutationRate);
        this.moveSpeed = Math.max(0.01,this.moveSpeed);
        this.maxHP *= 1-Main.mutationRate/2+random.nextDouble(Main.mutationRate);
        this.maxHP = Math.max(1,this.maxHP);
        this.HP = this.maxHP;
        this.armor *= 1-Main.mutationRate/2+random.nextDouble(Main.mutationRate);
        this.armor = Math.max(0.001,this.armor);
        this.regenRate *= 1-Main.mutationRate/2+random.nextDouble(Main.mutationRate);
        this.regenRate = Math.max(0.00001,this.regenRate);
        this.regenDelay *= 1-Main.mutationRate/2+random.nextDouble(Main.mutationRate);
        this.regenDelay = Math.max(1,this.regenDelay);
    }
    public Template getTemplate(){
        return this.template;
    }
}
