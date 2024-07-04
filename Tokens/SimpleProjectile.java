import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SimpleProjectile extends Projectile{
    public SimpleProjectile(){
        super(new Color(252,140,48));
        

        try {
            ArrayList<BufferedImage> set = new ArrayList<BufferedImage>();
            set.add(ImageIO.read(new File("resources\\SimpleProjectile.png")));
            this.tokenImages.add(set);
        } catch (IOException e) {
            System.out.println("Failed to load SimpleProjectile image");
        }
                

    }

    @Override
    public SimpleProjectile get() {
        return new SimpleProjectile();
    }

    @Override
    protected void end(){
        Main.tokens.add(new BulletPop(this.xPos-this.xSize/2,this.yPos-this.ySize/2,this.xSize,this.ySize));
        super.end();
    }


}
