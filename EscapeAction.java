import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class EscapeAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Main.inMenu == ""){
            Main.inMenu = "PAUSE";
            Main.gui.pauseMenu.cloakInfo.update();
            Main.gui.pauseMenu.knightInfo.update();
            Main.gui.pauseMenu.wizardInfo.update();
            //Main.gui.pauseMenu.setVisible(true);
        }
    }
    
}
