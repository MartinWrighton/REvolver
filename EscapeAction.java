import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class EscapeAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Main.inMenu == ""){
            Main.inMenu = "PAUSE";
            Main.gui.cloakInfo.update();
            Main.gui.knightInfo.update();
            Main.gui.wizardInfo.update();
            //Main.gui.pauseMenu.setVisible(true);
        }
    }
    
}
