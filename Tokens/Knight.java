import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Knight extends Enemy{
    public Knight(int xPos,int yPos,Token target){
        super(xPos, yPos, Main.knightTemplate.getMoveSpeed(), Main.knightTemplate.getMaxHP(), 36, 40, 36, 40, Main.knightTemplate.getArmor(),target,Main.knightTemplate);

        
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

}
