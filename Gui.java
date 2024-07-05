import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Gui extends JFrame{
    public DrawMaster draw;

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
    
        /*
        Enemy enemy = new Enemy(500,450,player);
        Main.tokens.add(enemy);
        this.add(enemy);
        */
        
  
    }

    

}