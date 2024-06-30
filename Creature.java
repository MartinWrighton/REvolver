import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Creature extends JComponent{
    //this is a model object, and should only be concerned with the internal representation of a creature
    private Token token;
    private double moveSpeed;
    private int health;
    private int[] direction = {0,0,0,0};//left/right,up/down
    protected Color color;
    public Creature(){
        this.token = new Token(this,this.color);
        this.moveSpeed = 0.1; //where 1 is one pixel per tick
        

    }


    public void step(){
        //moves the creatures token depending on the queued direction
        this.token.move((this.direction[1]-this.direction[0])*this.moveSpeed,(this.direction[3]-this.direction[2])*this.moveSpeed);
    }



    public Token getToken(){
        return this.token;
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
}
