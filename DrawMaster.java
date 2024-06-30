
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;


public class DrawMaster extends JComponent{
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;


        for(Token i : Main.tokens){
            g2.setColor(i.color);
            g2.drawRect((int)i.xPos,(int)i.yPos,10,10);
            g2.fillRect((int)i.xPos,(int)i.yPos, 10, 10);
        }
    }
}
