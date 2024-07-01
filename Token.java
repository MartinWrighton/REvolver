import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Token extends JComponent{
    //This is the simplest moving thing e.g a bullet or a creature
    protected double xPos;
    protected double yPos;
    protected Color color;
    protected double moveSpeed;
    protected double[] direction = {0,0,0,0};//left/right,up/down
    protected int size;
    protected Rectangle hitbox;
    protected BufferedImage tokenImage;
    public Token(){
        this.hitbox = new Rectangle((int)xPos, (int)yPos, this.size, this.size);
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

    }
    public void move(double d, double e){
        /* 
        if (this.xPos + d > 0 && this.xPos + d < Main.screenWidth-25){
            this.xPos = this.xPos + d;
        }
        if (this.yPos + e > 0 && this.yPos + e < Main.screenHeight-50){
            this.yPos = this.yPos + e;
        }
        */
        this.xPos = this.xPos + d;
        this.yPos = this.yPos + e;
        
        this.hitbox = new Rectangle((int)this.xPos, (int)this.yPos, this.size, this.size);
    }

    public void step(){
        preStep();
        moveStep();
        postStep();
    }

    protected void moveStep(){
        //moves the token depending on the queued direction
        double moveL = this.direction[0];
        double moveR = this.direction[1];
        double moveU = this.direction[2];
        double moveD = this.direction[3];
        if (this.direction[0]+this.direction[2]>1){ //preventing diagonal movement from being faster
            double both = this.direction[0]+this.direction[2];
            moveL = this.direction[0]/both;
            moveU = this.direction[2]/both;
        }
        if (this.direction[0]+this.direction[3]>1){
            double both = this.direction[0]+this.direction[3];
            moveL = this.direction[0]/both;
            moveD = this.direction[3]/both;
        }
        if (this.direction[1]+this.direction[3]>1){
            double both = this.direction[1]+this.direction[3];
            moveR = this.direction[1]/both;
            moveD = this.direction[3]/both;
        }
        if (this.direction[2]+this.direction[1]>1){
            double both = this.direction[2]+this.direction[1];
            moveU = this.direction[2]/both;
            moveR = this.direction[1]/both;
        }
        this.move((moveR-moveL)*this.moveSpeed,(moveD-moveU)*this.moveSpeed);
    }

    public double[] getDirection(){
        return this.direction;
    }
    public void setDirection(double[] direction){
        this.direction = direction;
    }
    public void addDirection(int axis,double value){
        this.direction[axis] = value;
    }
    //exist to be overidden
    protected void preStep(){}
    protected void postStep(){}
}
