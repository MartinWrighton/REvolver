import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class EscapeAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Main.inMenu = true;
        Main.gui.mainMenu.setVisible(true);
    }
    
}
