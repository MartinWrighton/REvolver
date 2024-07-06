import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class WizardProjectile extends Projectile{
    public WizardProjectile(){
        super(Color.RED);
        

        try {
            ArrayList<BufferedImage> set = new ArrayList<BufferedImage>();

            for (int i = 0 ; i<3;i++){
                set.add(ImageIO.read(new File("resources\\Orb\\PixelOrb"+Integer.toString(i)+".png")));
            }
            for (int i = 2 ; i>=0;i--){
                set.add(ImageIO.read(new File("resources\\Orb\\PixelOrb"+Integer.toString(i)+".png")));
            }
            this.tokenImages.add(set);
        } catch (IOException e) {
            System.out.println("Failed to load WizardProjectile image");
        }
                

    }

    @Override
    public Projectile get() {
        return new WizardProjectile();
    }

    @Override
    protected void end(){
        Main.tokens.add(new OrbPop(this.xPos+(this.xSize/2)-this.xSize*2,this.yPos+(this.ySize/2)-this.xSize*2,this.xSize,this.ySize));
        super.end();
    }


}
