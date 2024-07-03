
import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Player extends Creature {
    //this is the player, to be controlled
    private boolean isShooting = false;
    private int[] shootingAt = new int[] {0,0};
    private Weapon weapon;
    public Player(int xPos,int yPos){
        super();
        this.color = Color.RED;
        this.moveSpeed = -0.1; //where 1 is one pixel per tick // negative because we move the world around us
        this.xPos = xPos;
        this.yPos = yPos;
        this.hitbox = new Rectangle(xPos, yPos, xHit, yHit);
        this.maxHP = 100000;
        this.HP = this.maxHP;
        this.weapon = new PlasmaRifle(this);
        try {
            this.tokenImages.add(ImageIO.read(new File("resources\\PixelPlayerLeftFoot.PNG")));
            this.tokenImages.add(ImageIO.read(new File("resources\\PixelPlayerNeutral.PNG")));
            this.tokenImages.add(ImageIO.read(new File("resources\\PixelPlayerRightFoot.PNG")));
            this.tokenImages.add(ImageIO.read(new File("resources\\PixelPlayerNeutral.PNG")));
        } catch (IOException e) {
            System.out.println("Failed to load Player image");
        }
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
    
    

    @Override
    protected void preStep(){
        this.weapon.tick();
        if (this.isShooting){
            this.weapon.shoot(this.shootingAt[0], this.shootingAt[1]);
        }
    }

    public void move(double d, double e){
        
        for (int i = 0; i < Main.tokens.size() ; i++){
            if (Main.tokens.get(i) != this){
                Main.tokens.get(i).move((d),(e));//invert movespeed and move enemies instead
            }
        }
        //move the map beneath our feet
        Main.worldX+=(d);
        Main.worldY+=(e);
    }

    @Override
    protected void postStep(){
        for(int i = 0 ; i<Main.tokens.size();i++){
            if (this.hitbox.intersects(Main.tokens.get(i).hitbox) && Main.tokens.get(i) instanceof Enemy){
                ((Creature) Main.tokens.get(i)).die();
                takeDamage(1);
            }
        }
    }

    @Override
    protected void die(){
        Main.playing = false;
    }

    public void setShooting(boolean set){
        this.isShooting = set;
    }
    public void setShootingAt(int[] at){
        this.shootingAt = at;
    }
}
