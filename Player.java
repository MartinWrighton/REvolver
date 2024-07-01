import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Player extends Creature {
    //this is the player, to be controlled

    public Player(int xPos,int yPos){
        super();
        this.color = Color.RED;
        this.moveSpeed = 0.1; //where 1 is one pixel per tick
        this.xPos = xPos;
        this.yPos = yPos;
        this.maxHP = 3;
        this.HP = this.maxHP;
        //ability to control the player
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "aPressed");
        this.getActionMap().put("aPressed", new MoveAction(0,1));
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "dPressed");
        this.getActionMap().put("dPressed", new MoveAction(1,1));
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "wPressed");
        this.getActionMap().put("wPressed", new MoveAction(2,1));
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "sPressed");
        this.getActionMap().put("sPressed", new MoveAction(3,1));

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released A"), "aReleased");
        this.getActionMap().put("aReleased", new MoveAction(0,0));
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released D"), "dReleased");
        this.getActionMap().put("dReleased", new MoveAction(1,0));
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"), "wReleased");
        this.getActionMap().put("wReleased", new MoveAction(2,0));
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"), "sReleased");
        this.getActionMap().put("sReleased", new MoveAction(3,0));
    }
    
    public void shoot(int x,int y){
        double xdif = x - xPos;
        double ydif = y - yPos;
        double both = Math.abs(xdif) + Math.abs(ydif);
        double movx = xdif/both;
        double movy = ydif/both;
        Main.gui.shoot((int)xPos,(int)yPos,movx,movy);
    }
    @Override
    protected void moveStep(){
        //moves the token depending on the queued direction
        double moveL = this.direction[0];
        double moveR = this.direction[1];
        double moveU = this.direction[2];
        double moveD = this.direction[3];
        if (this.direction[0]+this.direction[2]>1){ //preventing diagonal movement from being faster
            double both = this.direction[0]+this.direction[2];
            moveL = this.direction[0]/both;
            moveU = this.direction[2]/both;
        }
        if (this.direction[0]+this.direction[3]>1){
            double both = this.direction[0]+this.direction[3];
            moveL = this.direction[0]/both;
            moveD = this.direction[3]/both;
        }
        if (this.direction[1]+this.direction[3]>1){
            double both = this.direction[1]+this.direction[3];
            moveR = this.direction[1]/both;
            moveD = this.direction[3]/both;
        }
        if (this.direction[2]+this.direction[1]>1){
            double both = this.direction[2]+this.direction[1];
            moveU = this.direction[2]/both;
            moveR = this.direction[1]/both;
        }
        for (int i = 0; i < Main.tokens.size() ; i++){
            if (Main.tokens.get(i) != this){
                Main.tokens.get(i).move((moveR-moveL)*this.moveSpeed*-1,(moveD-moveU)*this.moveSpeed*-1);//invert movespeed and move enemies instead
            }
        }


    }
    @Override
    protected void postStep(){
        for(int i = 0 ; i<Main.tokens.size();i++){
            if (this.hitbox.intersects(Main.tokens.get(i).hitbox) && Main.tokens.get(i) instanceof Enemy){
                ((Creature) Main.tokens.get(i)).die();
                System.out.println("hit");
                takeDamage();
            }
        }
    }
    @Override
    protected void takeDamage(){
        this.HP-=1;
        if (this.HP <= 0){
            die();
        }
    }
    @Override
    protected void die(){
        Main.playing = false;
    }
}
