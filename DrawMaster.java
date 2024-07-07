
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JComponent;



public class DrawMaster extends JComponent{
    private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
    @Override
    public void paint(Graphics g){
        if (this.images.size() <= 0){
            try {
                for (int i = 0;i<5;i++){
                this.images.add(ImageIO.read(new File("resources\\Background\\PixelBackground"+Integer.toString(i)+".png")));
                }
                

            } catch (IOException e) {
                System.out.println("Failed to load background image");
            }
        }
        
        
        Graphics2D g2 = (Graphics2D) g;
        //background
        int tileSize = 100;//2x the art size seems to look good for most of my assets
        int yStart = ((int)Main.worldY%tileSize)-tileSize;
        for (int j = (Main.screenHeight/tileSize)+2;j>0;j--){
            int xStart = ((int)Main.worldX%tileSize)-tileSize;
            for (int i = (Main.screenWidth/tileSize)+2;i>=0;i--){
                //using world coordinates to create a random number
                //kind of like a Linear Congruental Generator
                


                int X = (int) Math.abs(i+(int)Main.worldX/tileSize);
                int Y = (int) Math.abs(j+(int)Main.worldY/tileSize);
                
              
                for (int y = 0;y<7;y++){
                    X = ((75)*X+74)%65536;
                    X = (X+Y)/2;
                }
                BufferedImage img = images.get((3*X+3)%5);

                g2.drawImage(img,(int) xStart,(int)yStart,tileSize,tileSize,null);
               

                //Show grid
                if (Main.showGrid){
                    g2.drawRect((int)xStart,(int)yStart,tileSize,tileSize);
                }
                xStart+=tileSize;
                
            }
            yStart+=tileSize;
        }
        

            



        for(int i = 0 ; i<Main.tokens.size();i++){
            Token token = null;
            if (i<Main.tokens.size()){
                token = Main.tokens.get(i);
            }
            if (token != null){

                if (token.animationSet>=0 && token.tokenImages.size()>0){
                    //make sure we dont get errors
                    int set = Math.min(token.animationSet,token.tokenImages.size()-1);
                    int frame = (int) Math.min(token.animationFrame,token.tokenImages.get(token.animationSet).size()-1);
                    

                    if (token.direction[0]<token.direction[1]){
                        g2.drawImage(token.tokenImages.get(set).get((int)frame),(int) token.xPos,(int)token.yPos,(int)token.xSize,(int)token.ySize,null);
                    } else {

                        g2.drawImage(token.tokenImages.get(set).get((int)frame),(int) token.xPos+(int)token.xSize,(int)token.yPos,-(int)token.xSize,(int)token.ySize,null);
                    }
                    if (i>=Main.tokens.size()){//TODO find out what is causing this
                        break;
                    }
                    //show hitboxes
                    if (Main.showHitboxes){
                        g2.setColor(Main.tokens.get(i).color);
                        g2.drawRect((int)token.xPos,(int)token.yPos,(int)token.xHit,(int)token.yHit);
                    }
                } else {
                    g2.setColor(Main.tokens.get(i).color);
                    g2.drawRect((int)token.xPos,(int)token.yPos,(int)token.xHit,(int)token.yHit);
                    g2.fillRect((int)token.xPos,(int)token.yPos, (int)token.xHit,(int)token.yHit);
                }

                //Drawing pathfinding lines
                if (token instanceof Waypoint){

                    g2.drawLine((int) token.xPos,(int) token.yPos, ((Waypoint) token).getOwnerX(), ((Waypoint) token).getOwnerY());
                }
            }
        }
    }
}
