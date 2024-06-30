

import java.awt.Color;

import javax.swing.JFrame;

public class Gui extends JFrame{
    public DrawMaster draw;
    public Gui(){
        this.setTitle("REvolver");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1600,900);
        this.addMouseListener(new Click());
        this.getContentPane().setBackground(new Color(212,255,171));
        this.setVisible(true);
        //this.setLayout(null);

        draw = new DrawMaster();
        this.add(draw);
        draw.setVisible(false);
        draw.setVisible(true);

        Player player = new Player(600,450);
        Main.tokens.add(player);
        this.add(player);
        Main.player = player;
    
       
        Enemy enemy = new Enemy(100,100,player);
        Main.tokens.add(enemy);
        this.add(enemy);

        enemy = new Enemy(150,100,player);
        Main.tokens.add(enemy);
        this.add(enemy);

        enemy = new Enemy(200,100,player);
        Main.tokens.add(enemy);
        this.add(enemy);


        
        
  
    }

    public void shoot(int xPos, int yPos,double movx, double movy){

        



        Projectile bullet = new Projectile(xPos,yPos);
        Main.tokens.add(bullet);
        if (movx < 0){
            bullet.addDirection(0, -1*movx);
        } else if (movx > 0){
            bullet.addDirection(1, movx);
        } else {
            bullet.addDirection(0,0);
            bullet.addDirection(1, 0);
        }
        if (movy < 0){
            bullet.addDirection(2, -1*movy);
        } else if (movy > 0){
            bullet.addDirection(3, movy);
        } else {
            bullet.addDirection(2,0);
            bullet.addDirection(3, 0);
        }
        
    }
}