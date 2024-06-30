import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Token extends JComponent{
    //This is a view object, and should only be concerned with the visual representation of a creature
    private int xPos;
    private int yPos;
    private Color color;
    public Token(){
        this.xPos = 800;
        this.yPos = 450;
        this.color = Color.RED;
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.color);
        g2.drawRect(this.xPos,this.yPos,10,10);
        g2.fillRect(this.xPos,this.yPos, 10, 10);
    }

    public void goTo(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.repaint();
    }
    public void move(int xChange, int yChange){
        this.xPos = this.xPos + xChange;
        this.yPos = this.yPos + yChange;
        this.repaint();
    }
}
