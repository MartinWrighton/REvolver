
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Token extends JComponent{
    //This is the simplest moving thing e.g a bullet or a creature
    protected double xPos;
    protected double yPos;
    protected Color color;
    protected double moveSpeed;
    protected double[] direction = {0,0,0,0};//left/right,up/down
    protected double xSize;
    protected double ySize;
    protected Rectangle hitbox;
    protected double xHit;
    protected double yHit;
    protected ArrayList<ArrayList<BufferedImage>> tokenImages = new ArrayList<ArrayList<BufferedImage>>();
    protected int animationSet = 0;
    protected double animationFrame = 0;
    protected double animationSpeed = 0.005;
    public Token(Color color, double xPos, double yPos, double moveSpeed, double xSize, double ySize, double xHit, double yHit){
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
        this.moveSpeed = moveSpeed;
        this.xSize = xSize;
        this.ySize = ySize;
        this.xHit = xHit;
        this.yHit = yHit;
        this.hitbox = new Rectangle((int)xPos, (int)yPos,(int) this.xHit,(int) this.yHit);
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
        //prevent leaving screen
        if (this.xPos + d > 0 && this.xPos + d < Main.screenWidth-25){
            this.xPos = this.xPos + d;
        }
        if (this.yPos + e > 0 && this.yPos + e < Main.screenHeight-50){
            this.yPos = this.yPos + e;
        }
        */
        this.xPos = this.xPos + d;
        this.yPos = this.yPos + e;
        
        this.hitbox = new Rectangle((int)this.xPos, (int)this.yPos, (int) this.xHit, (int) this.yHit);
    }

    public void step(){
        animationStep();
        preStep();
        pathfind();
        moveStep();
        postStep();
    }

    protected void moveStep(){
        //moves the token depending on the queued direction
        double moveL = this.direction[0];
        double moveR = this.direction[1];
        double moveU = this.direction[2];
        double moveD = this.direction[3];

        //preventing diagonal movement from being faster
        double dify = Math.abs(moveU-moveD);
        double difx = Math.abs(moveL-moveR);
        double both = Math.sqrt(difx*difx+dify*dify);
        double movex = difx/both;
        double movey = dify/both;
        if (moveL>moveR){
            moveL = movex;
        } else {
            moveR = movex;
        }
        if (moveU>moveD){
            moveU = movey;
        } else {
            moveD = movey;
        }

        if (both == 0){
            moveL = 0;
            moveR = 0;
            moveU = 0;
            moveD = 0;
        }

        
        this.move((moveR-moveL)*this.moveSpeed*Main.dynamicTick,(moveD-moveU)*this.moveSpeed*Main.dynamicTick);
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
    protected void animationStep(){
        if (this.tokenImages.size()>0){
            if (this.animationSet>=0){
                this.animationFrame+=animationSpeed*Main.dynamicTick;
                if ((int)this.animationFrame>this.tokenImages.get(this.animationSet).size()-1){
                    this.animationFrame=0;
                }
            }
        } else {
            this.animationFrame +=animationSpeed*Main.dynamicTick;
        }
    }
    protected void preStep(){}

    protected void pathfind(){}
        
    protected void postStep(){}
}
