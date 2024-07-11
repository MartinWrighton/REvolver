import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Wizard extends Enemy{
    private WizardStaff weapon = new WizardStaff(this,Main.wizardTemplate.getFireDelay(),Main.wizardTemplate.getClipSize(),Main.wizardTemplate.getReloadTime(),Main.wizardTemplate.getProjectileSpeed(),Main.wizardTemplate.getProjectileSize(),Main.wizardTemplate.getSpread());
    public Wizard(int xPos,int yPos,Token target){
        super(xPos, yPos, Main.wizardTemplate.getMoveSpeed(), Main.wizardTemplate.getMaxHP(), 28, 40, 28, 40, Main.wizardTemplate.getArmor(),target,Main.wizardTemplate,Main.wizardTemplate.getRegenRate(),Main.wizardTemplate.getRegenDelay());

        
        this.animationSpeed = 0.015;
        try {
            ArrayList<BufferedImage> set = new ArrayList<BufferedImage>();
            for (int i = 0 ; i<15;i++){
                set.add(ImageIO.read(new File("resources\\PixelWizard\\PixelWizard"+Integer.toString(i)+".png")));
                
            }
            this.tokenImages.add(set);

            set = new ArrayList<BufferedImage>();
            for (int i = 0 ; i<7;i++){
                set.add(ImageIO.read(new File("resources\\PixelWizard\\PixelWizardAttack"+Integer.toString(i)+".png")));
            }
            for (int i = 6 ; i>=0;i--){
                set.add(ImageIO.read(new File("resources\\PixelWizard\\PixelWizardAttack"+Integer.toString(i)+".png")));
            }
            this.tokenImages.add(set);
        } catch (IOException e) {
            System.out.println("Failed to load Wizard image");
        }
    }

    @Override
    protected void pathfind(){
        double difx = Math.abs(target.xPos - this.xPos);
        double dify = Math.abs(target.yPos - this.yPos);
        double distance = Math.sqrt(difx*difx + dify*dify);
        if(distance>500){
            super.pathfind();
        } else if(distance<200){
            super.pathfind();
            //invert directions to run away
            double swap = this.direction[0];
            this.direction[0] = this.direction[1];
            this.direction[1] = swap;
            swap = this.direction[2];
            this.direction[2] = this.direction[3];
            this.direction[3] = swap;
        } else {
            this.direction[0] = 0;
            this.direction[1] = 0;
            this.direction[2] = 0;
            this.direction[3] = 0;
        }
    }

    @Override
    protected void animationStep(){

        double difx = Math.abs(target.xPos - this.xPos);
        double dify = Math.abs(target.yPos - this.yPos);
        double distance = Math.sqrt(difx*difx + dify*dify);
        if(distance<200||distance>500){
            this.animationSet = 0;
            
        } else {
            this.animationSet = 1;
        }
        //set size to keep aspect ratio
        this.xSize = this.tokenImages.get(this.animationSet).get(0).getWidth()*2;//TODO consider replacing this 2 with a tokenScale attribute
        this.ySize = this.tokenImages.get(this.animationSet).get(0).getHeight()*2;
        


        if (this.tokenImages.size()>0){
            if (this.animationSet>=0){
                this.animationFrame+=animationSpeed*Main.dynamicTick;
                if ((int)this.animationFrame>this.tokenImages.get(this.animationSet).size()-1){
                    this.animationFrame=0;
                }
            }
        } else {
            this.animationFrame +=animationSpeed*Main.dynamicTick;
        }
    }

    @Override
    protected void preStep(){
        double difx = Math.abs(target.xPos - this.xPos);
        double dify = Math.abs(target.yPos - this.yPos);
        double distance = Math.sqrt(difx*difx + dify*dify);
        this.weapon.tick();
        if(distance>200&&distance<500){
            this.weapon.shoot((int)target.xPos, (int)target.yPos);  
        }
        
        super.preStep();
    }

    //apply the same evolving to their weapon
    @Override
    public void mutate(){
        //change each stat by a random percentage

        Random random = new Random();
        this.weapon.fireDelay *= 1-Main.mutationRate/2+random.nextDouble(Main.mutationRate);
        this.weapon.fireDelay = Math.max(0.0001,this.weapon.fireDelay);
        this.weapon.clipSize *= 1-Main.mutationRate/2+random.nextDouble(Main.mutationRate);
        this.weapon.clipSize = Math.max(1,this.weapon.clipSize);
        this.weapon.reloadTime *= 1-Main.mutationRate/2+random.nextDouble(Main.mutationRate);
        this.weapon.projectileSpeed *= 1-Main.mutationRate/2+random.nextDouble(Main.mutationRate);
        this.weapon.projectileSpeed = Math.max(0.01,this.weapon.projectileSpeed);
        this.weapon.projectileSize *= 1-Main.mutationRate/2+random.nextDouble(Main.mutationRate);
        this.weapon.projectileSize = Math.max(1,this.weapon.projectileSize);
        this.weapon.spread *= 1-Main.mutationRate/2+random.nextDouble(Main.mutationRate);
        this.weapon.spread = Math.max(0.01,this.weapon.spread);
        super.mutate();
    }
    //update weapon on death
    @Override
    protected void die(){
        //increase player score
        Main.player.incScore();
        //calculate score
        int distanceScoreX = (int) ((Main.screenWidth/2)-Math.abs(this.target.xPos-this.xPos));
        distanceScoreX = 100*(distanceScoreX)/(Main.screenWidth/2);
        int distanceScoreY = (int) ((Main.screenHeight/2)-Math.abs(this.target.yPos-this.yPos));
        distanceScoreY = (distanceScoreY)/(Main.screenWidth/2);
        int distanceScoreBoth = (int) Math.sqrt(distanceScoreX*distanceScoreX+distanceScoreY*distanceScoreY);

        int score = (int) (distanceScoreBoth/10 + this.rawDamage*5 + this.hurtPlayer*100);
        if (Main.printScore){
            System.out.println("\n !Distance: "+distanceScoreBoth+"!  Raw Damage: "+this.rawDamage*5+"  Hurt Player: "+this.hurtPlayer*100+"  Total: "+score);
        }
        if (score>this.template.getScore()){
            this.template.update(this.moveSpeed, this.maxHP, this.armor, score,this.regenRate,this.regenDelay);
            ((WizardTemplate) this.template).updateWeapon(this.weapon.fireDelay,this.weapon.clipSize,this.weapon.reloadTime,this.weapon.projectileSpeed,this.weapon.projectileSize,this.weapon.spread);
        } else {
            this.template.scoreDecay();
        }

        //TODO death animations
        Main.tokens.remove(this);
    }

    
}
