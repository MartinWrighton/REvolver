
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
                for (int i = (Main.screenWidth/img.getWidth())+2;i>=0;i--){
                    
                    g2.drawImage(img,(int) xStart,(int)yStart,null);
                    xStart+=img.getWidth();
                    
                }
                yStart+=img.getHeight();
            }

            

        } catch (IOException e) {
            System.out.println("Failed to load background image");
        }


        for(int i = 0 ; i<Main.tokens.size();i++){
            Token token = Main.tokens.get(i);
            
            if (token.tokenImage != null){
                if (token.xPos>Main.player.xPos){
                    g2.drawImage(token.tokenImage,(int) token.xPos,(int)token.yPos,token.size,token.size,null);
                } else {
                    g2.drawImage(token.tokenImage,(int) token.xPos+token.size,(int)token.yPos,-token.size,token.size,null);
                }
            } else {
                g2.setColor(Main.tokens.get(i).color);
                g2.drawRect((int)token.xPos,(int)token.yPos,token.size,token.size);
                g2.fillRect((int)token.xPos,(int)token.yPos, token.size,token.size);
            }

        }
    }
}
