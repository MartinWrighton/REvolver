import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Player extends Creature {


    public Player(){
        super();
        this.color = Color.RED;

        //ability to control the player
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
    
}
