

import javax.swing.JFrame;

public class Gui extends JFrame{
    public DrawMaster draw;
    public Gui(){
        this.setTitle("REvolver");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1600,900);
        this.setVisible(true);
        //this.setLayout(null);

        DrawMaster draw = new DrawMaster();
        this.add(draw);
        draw.setVisible(false);
        draw.setVisible(true);

        Player player = new Player(600,450);
        Main.tokens.add(player);
        this.add(player);

    
       
        Enemy enemy = new Enemy(100,100,player);
        Main.tokens.add(enemy);
        this.add(enemy);


        
        
        
    }
}