import javax.swing.JComponent;
import java.awt.Color;

public class Token extends JComponent{
    //This is the simplest moving thing e.g a bullet or a creature
    protected double xPos;
    protected double yPos;
    protected Color color;
    protected double moveSpeed;
    private int[] direction = {0,0,0,0};//left/right,up/down
    public Token(){

    }
    /*
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.color);
        g2.drawRect((int)this.xPos,(int)this.yPos,10,10);
        g2.fillRect((int)this.xPos,(int)this.yPos, 10, 10);
    }
    */
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

    public void step(){
        //moves the token depending on the queued direction
        this.move((this.direction[1]-this.direction[0])*this.moveSpeed,(this.direction[3]-this.direction[2])*this.moveSpeed);
    }



    public int[] getDirection(){
        return this.direction;
    }
    public void setDirection(int[] direction){
        this.direction = direction;
    }
    public void addDirection(int axis,int value){
        this.direction[axis] = value;
    }
    public void pathfind(){}//exists to be overidden
}
