import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class OrbPop extends Effect{
    public OrbPop(double xPos, double yPos , double xSize, double ySize){
        super(Color.RED,xPos,yPos,0,xSize*4,ySize*4,0,0);

        this.animationSpeed = 0.01;

        try {
            ArrayList<BufferedImage> set = new ArrayList<BufferedImage>();
            for (int i = 0 ; i<3;i++){
                set.add(ImageIO.read(new File("resources\\OrbPop\\PixelOrbPop"+Integer.toString(i)+".png")));
            }
                this.tokenImages.add(set);
        } catch (IOException e) {
            System.out.println("Failed to load OrbPop image");
        }
    }
}

