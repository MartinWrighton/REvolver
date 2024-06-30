import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class MoveAction extends AbstractAction {
    //sets the triggering creatures direction
    // axis is the axis we want to change 0 is x axis and 1 is y axis
    //value is how we want to move on that axis -1 is left/up 0 is stationary and 1 is right/down
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
