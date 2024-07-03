import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class BulletPop extends Effect{
    public BulletPop(double xPos, double yPos , int xSize, int ySize){
        super(Color.YELLOW,xPos,yPos,0,xSize*4,ySize*4,0,0);

        this.animationSpeed = 0.01;

        try {
            ArrayList<BufferedImage> set = new ArrayList<BufferedImage>();
                set.add(ImageIO.read(new File("resources\\PixelPop1.PNG")));
                set.add(ImageIO.read(new File("resources\\PixelPop2.PNG")));
                set.add(ImageIO.read(new File("resources\\PixelPop3.PNG")));
                this.tokenImages.add(set);
        } catch (IOException e) {
            System.out.println("Failed to load BulletPop image");
        }
    }
}
