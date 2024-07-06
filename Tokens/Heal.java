import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Heal extends Effect{
    public Heal(double xPos, double yPos , int xSize, int ySize){
        super(Color.YELLOW,xPos,yPos,0.1,xSize,ySize,0,0);
        this.direction = new double[] {0,0,1,0};
        this.animationSpeed = 0.01;

        try {
            ArrayList<BufferedImage> set = new ArrayList<BufferedImage>();

            for (int i = 0 ; i<4;i++){
                set.add(ImageIO.read(new File("resources\\Heal\\PixelHeal"+Integer.toString(i)+".png")));
            }
            this.tokenImages.add(set);
        } catch (IOException e) {
            System.out.println("Failed to load Heal image");
        }
    }

    @Override
    protected void pathfind(){};//dont pathfind

}
