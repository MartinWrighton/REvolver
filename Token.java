import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Token extends JComponent{
    //This is a view object, and should only be concerned with the visual representation of a creature
    private double xPos;
    private double yPos;
    private Color color;
    private Creature creature;
    public Token(Creature creature, Color color){
        this.xPos = 800;
        this.yPos = 450;
        this.color = Color.RED;
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.color);
        g2.drawRect((int)this.xPos,(int)this.yPos,10,10);
        g2.fillRect((int)this.xPos,(int)this.yPos, 10, 10);
    }

    public void goTo(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.repaint();
    }
    public void move(double d, double e){
        if (this.xPos + d > 0 && this.xPos + d < 1575){
            this.xPos = this.xPos + d;
        }
        if (this.yPos + e > 0 && this.yPos + e < 850){
            this.yPos = this.yPos + e;
        }
        this.repaint();
    }
}
