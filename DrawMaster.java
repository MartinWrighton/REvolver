
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;


public class DrawMaster extends JComponent{
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;


        for(int i = 0 ; i<Main.tokens.size();i++){
            g2.setColor(Main.tokens.get(i).color);
            g2.drawRect((int)Main.tokens.get(i).xPos,(int)Main.tokens.get(i).yPos,Main.tokens.get(i).size,Main.tokens.get(i).size);
            g2.fillRect((int)Main.tokens.get(i).xPos,(int)Main.tokens.get(i).yPos, Main.tokens.get(i).size, Main.tokens.get(i).size);
        }
    }
}
