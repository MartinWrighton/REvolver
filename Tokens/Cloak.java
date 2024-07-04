import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Cloak extends Enemy{
    public Cloak(int xPos,int yPos,Token target){
        super(xPos, yPos, 0.11, 4, 28, 40, 28, 40, 1.5,target);

        
        this.animationSpeed = 0.015;
        try {
            ArrayList<BufferedImage> set = new ArrayList<BufferedImage>();
            for (int i = 0 ; i<16;i++){
                set.add(ImageIO.read(new File("resources\\PixelEnemy\\PixelEnemyWalk"+Integer.toString(i)+".png")));
                
            }
            this.tokenImages.add(set);

            set = new ArrayList<BufferedImage>();
            for (int i = 0 ; i<16;i++){
                set.add(ImageIO.read(new File("resources\\PixelEnemy\\PixelEnemyAttack"+Integer.toString(i)+".png")));
            }
            this.tokenImages.add(set);
        } catch (IOException e) {
            System.out.println("Failed to load Cloak image");
        }
    }
    
}
