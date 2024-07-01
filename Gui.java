import java.awt.Color;
import java.util.Random;
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

    public void spawn(){
        //random spawn location
        Random random = new Random();
        int xSpawn = 0;
        int ySpawn = 0;
        int zone = random.nextInt(4);
        if (zone==0){
            //left
            xSpawn = -50;
            ySpawn = random.nextInt(Main.screenHeight+50);
        } else if (zone==1){
            //right
            xSpawn = Main.screenWidth+30;
            ySpawn = random.nextInt(Main.screenHeight+55);
        } else if (zone==2){
            //top
            ySpawn = -50;
            xSpawn = random.nextInt(Main.screenWidth+30);
        } else if (zone==3){
            //bottom
            ySpawn = Main.screenHeight+55;
            xSpawn = random.nextInt(Main.screenWidth+30);
        }

        Enemy enemy = new Enemy(xSpawn,ySpawn,Main.player);
        Main.tokens.add(enemy);
        this.add(enemy);

    }

}