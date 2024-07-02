import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;



public class MoveAction extends AbstractAction {
    //sets the triggering creatures direction
    // axis is the direction we want to change 0 is left 1 is right 2 is up and 3 down
    private int axis;
    private int value;
    public MoveAction(int direction,int value){
        this.axis = direction;
        this.value = value;
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ((Creature) e.getSource()).addDirection(this.axis,this.value);
    }
    
}
