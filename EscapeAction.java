import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class EscapeAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Main.inMenu == ""){
            Main.inMenu = "PAUSE";
            //Main.gui.pauseMenu.setVisible(true);
        }
    }
    
}
