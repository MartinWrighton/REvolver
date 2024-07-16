import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PauseMenu extends JPanel{
    public CloakInfo cloakInfo = new CloakInfo();
    public KnightInfo knightInfo = new KnightInfo();
    public WizardInfo wizardInfo = new WizardInfo();
    public PauseMenu(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //c.weightx=1;
        //c.weighty=1;

        JButton resumeButton = new JButton("Resume");
        c.gridx=1;
        c.gridy=0;
        add(resumeButton,c);
        resumeButton.setBounds((Main.screenWidth/4)-50, (Main.screenHeight/4)-50, 100, 100);
        //resumeButton.setVisible(false);
        resumeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.inMenu = "";
                //Main.gui.pauseMenu.setVisible(false);
            }
            
        });

        JButton exitButton = new JButton("Exit");
        c.gridx=1;
        c.gridy=1;
        add(exitButton,c);
        exitButton.setBounds((Main.screenWidth/4)-50, (Main.screenHeight/4)-50, 100, 100);
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
        c.gridx=0;
        c.gridy=0;
        add(this.cloakInfo,c);

        c.gridx=0;
        c.gridy=1;
        add(this.knightInfo,c);

        c.gridx=2;
        c.gridy=0;
        c.gridheight=2;
        add(this.wizardInfo,c);
    }
}
