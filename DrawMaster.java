
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
            BufferedImage img = ImageIO.read(new File("resources\\PixelGrass.jpg"));


            int yStart = ((int)Main.worldY%img.getHeight())-img.getHeight();
            for (int j = (Main.screenWidth/img.getWidth())+2;j>0;j--){
                int xStart = ((int)Main.worldX%img.getWidth())-img.getWidth();
                for (int i = (Main.screenWidth/img.getWidth())+2;i>0;i--){
                    xStart+=img.getWidth();
                    g2.drawImage(img,(int) xStart,(int)yStart,null);
                    
                }
                yStart+=img.getHeight();
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
