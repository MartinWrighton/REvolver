import java.awt.Color;
import javax.swing.JFrame;


public class Gui extends JFrame{
    public DrawMaster draw;

    public Gui(){


        this.setTitle("REvolver");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Main.screenWidth,Main.screenHeight);
        Click mouseClick = new Click();
        this.addMouseListener(mouseClick);
        this.addMouseMotionListener(mouseClick);
        this.getContentPane().setBackground(new Color(212,255,171));
        this.setVisible(true);
        //this.setLayout(null);

        draw = new DrawMaster();
        this.add(draw);
        draw.setVisible(false);
        draw.setVisible(true);

        Player player = new Player((Main.screenWidth-25)/2,(Main.screenHeight-50)/2);
        Main.tokens.add(player);
        Main.player = player;
    
        /*
        Enemy enemy = new Enemy(500,450,player);
        Main.tokens.add(enemy);
        this.add(enemy);
        */
        
  
    }

    

}