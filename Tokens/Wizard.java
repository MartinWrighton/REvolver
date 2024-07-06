import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Wizard extends Enemy{
    private WizardStaff weapon = new WizardStaff(this);
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
    
}
