
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;


public class DrawMaster extends JComponent{
    private BufferedImage img;

    @Override
    public void paint(Graphics g){
        if (this.img==null){
            try {
                this.img = ImageIO.read(new File("resources\\PixelBackground.PNG"));
            } catch (IOException e) {
                System.out.println("Failed to load background image");
            }
        }
        Graphics2D g2 = (Graphics2D) g;
        //attempting image work
        int yStart = ((int)Main.worldY%img.getHeight())-img.getHeight();
        for (int j = (Main.screenWidth/img.getWidth())+2;j>0;j--){
            int xStart = ((int)Main.worldX%img.getWidth())-img.getWidth();
            for (int i = (Main.screenWidth/img.getWidth())+2;i>=0;i--){
                
                g2.drawImage(img,(int) xStart,(int)yStart,null);
                xStart+=img.getWidth();
                
            }
            yStart+=img.getHeight();
        }

            



        for(int i = 0 ; i<Main.tokens.size();i++){
            Token token = Main.tokens.get(i);
            
            if (token.tokenImages.size()>0){
                if (token.direction[0]>0){
                    g2.drawImage(token.tokenImages.get(token.animationFrame),(int) token.xPos,(int)token.yPos,token.xSize,token.ySize,null);
                } else {
                    g2.drawImage(token.tokenImages.get(token.animationFrame),(int) token.xPos+token.xSize,(int)token.yPos,-token.xSize,token.ySize,null);
                }
                token.animationFrame++;
                if (token.animationFrame>token.tokenImages.size()-1){
                    token.animationFrame=0;
                }
            } else {
                g2.setColor(Main.tokens.get(i).color);
                g2.drawRect((int)token.xPos,(int)token.yPos,token.xSize,token.ySize);
                g2.fillRect((int)token.xPos,(int)token.yPos, token.xSize,token.ySize);
            }

        }
    }
}
