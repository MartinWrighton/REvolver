import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Creature extends JComponent{
    //this is a model object, and should only be concerned with the internal representation of a creature
    private Token token;
    private double moveSpeed;
    private int health;
    private int[] direction = {0,0,0,0};//left/right,up/down

    public Creature(){
        this.token = new Token();
        this.moveSpeed = 0.1; //where 1 is one pixel per tick

        //TODO this functionality should belong only to a player class
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "aPressed");
        this.getActionMap().put("aPressed", new MoveAction(0,1));
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "dPressed");
        this.getActionMap().put("dPressed", new MoveAction(1,1));
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "wPressed");
        this.getActionMap().put("wPressed", new MoveAction(2,1));
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "sPressed");
        this.getActionMap().put("sPressed", new MoveAction(3,1));

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released A"), "aReleased");
        this.getActionMap().put("aReleased", new MoveAction(0,0));
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released D"), "dReleased");
        this.getActionMap().put("dReleased", new MoveAction(1,0));
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"), "wReleased");
        this.getActionMap().put("wReleased", new MoveAction(2,0));
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"), "sReleased");
        this.getActionMap().put("sReleased", new MoveAction(3,0));

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
