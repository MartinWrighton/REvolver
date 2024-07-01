
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;


public class DrawMaster extends JComponent{
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        //attempting image work
        try {
            BufferedImage img = ImageIO.read(new File("resources\\Gerbil.PNG"));

            int space = (int)Main.worldX;
            while (space>-img.getWidth()){
                g2.drawImage(img,(int) space,(int)Main.worldY,null);
                space-=img.getWidth();
            }

            

        } catch (IOException e) {
            System.out.println("Failed to load image");
        }


        for(int i = 0 ; i<Main.tokens.size();i++){
            g2.setColor(Main.tokens.get(i).color);
            g2.drawRect((int)Main.tokens.get(i).xPos,(int)Main.tokens.get(i).yPos,Main.tokens.get(i).size,Main.tokens.get(i).size);
            g2.fillRect((int)Main.tokens.get(i).xPos,(int)Main.tokens.get(i).yPos, Main.tokens.get(i).size, Main.tokens.get(i).size);
        }
    }
}
