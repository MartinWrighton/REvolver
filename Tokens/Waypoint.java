import java.awt.Color;

public class Waypoint extends Effect{
    private int ownerX;
    private int ownerY;
    public Waypoint(double xPos, double yPos , int xSize, int ySize, int ownerX, int ownerY){
        super(Color.RED,xPos,yPos,0,xSize*4,ySize*4,10,10);
        this.animationFrame = 0.1;
        this.animationSpeed = -0.01;
        this.ownerX =  ownerX;
        this.ownerY = ownerY;
    }

    public int getOwnerX(){
        return this.ownerX;
    }
    public int getOwnerY(){
        return this.ownerY;
    }
}
