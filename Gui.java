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
    public JPanel pauseMenu;
    public JPanel levelMenu;
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
    
        //pauseMenu
        this.pauseMenu = new JPanel();
        this.add(pauseMenu);
        pauseMenu.setBounds(Main.screenWidth/4, Main.screenHeight/4, Main.screenWidth/2, Main.screenHeight/2);
        pauseMenu.setVisible(false);
        JButton resumeButton = new JButton("Resume");
        pauseMenu.add(resumeButton);
        resumeButton.setBounds((Main.screenWidth/4)-50, (Main.screenHeight/4)-50, 100, 100);
        //resumeButton.setVisible(false);
        resumeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.inMenu = "";
                Main.gui.pauseMenu.setVisible(false);
            }
            
        });

        //levelMenu
        this.levelMenu = new JPanel();
        this.add(levelMenu);
        levelMenu.setBounds(Main.screenWidth/4, Main.screenHeight/4, Main.screenWidth/2, Main.screenHeight/2);
        levelMenu.setVisible(false);
        JButton levelButton = new JButton("Level");
        levelMenu.add(levelButton);
        levelButton.setBounds((Main.screenWidth/4)-50, (Main.screenHeight/4)-50, 100, 100);

        levelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.inMenu = "";
                Main.gui.levelMenu.setVisible(false);
            }
            
        });

        //mainMenu
        this.mainMenu = new JPanel();
        this.add(mainMenu);
        mainMenu.setBounds(0, 0, Main.screenWidth, Main.screenHeight);
        this.mainMenu.setVisible(true);
        JButton playButton = new JButton("Play");
        mainMenu.add(playButton);
        playButton.setBounds((Main.screenWidth/2)-50, (Main.screenHeight/2)-50, 100, 100);
        playButton.setVisible(true);
        playButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Main.inMenu = "";
                Main.gui.mainMenu.setVisible(false);
                Main.mainWorker.execute();
            }
            
        });

        

        
  
    }

    

}