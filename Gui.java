import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Gui extends JFrame{
    public DrawMaster draw;
    public JPanel mainMenu;
    public Gui(){


        this.setTitle("REvolver");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Main.screenWidth,Main.screenHeight);
        Click mouseClick = new Click();
        this.getContentPane().addMouseListener(mouseClick);
        this.getContentPane().addMouseMotionListener(mouseClick);
        this.getContentPane().setBackground(new Color(212,255,171));
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setVisible(true);

        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("resources\\PixelCursor.png").getImage(),new Point(0,0),"custom cursor"));
        

        draw = new DrawMaster();
        this.add(draw);
        draw.setVisible(false);
        draw.setVisible(true);

        Player player = new Player();
        Main.tokens.add(player);
        Main.player = player;
    
        

        //menu
        this.mainMenu = new JPanel();
        this.add(mainMenu);
        mainMenu.setBounds(0, 0, Main.screenWidth, Main.screenHeight);
        this.mainMenu.setVisible(true);
        
        JButton button = new JButton("Play");
        button.setBounds((Main.screenWidth/2)-50, (Main.screenHeight/2)-50, 100, 100);
        mainMenu.add(button);
        button.setVisible(true);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.inMenu = false;
                Main.gui.mainMenu.setVisible(false);
            }
            
        });
  
    }

    

}