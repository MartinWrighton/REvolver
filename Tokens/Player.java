
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Player extends Creature {
    //this is the player, to be controlled
    private boolean isShooting = false;
    private int[] shootingAt = new int[] {0,0};
    private Weapon weapon;
    private int score = 0;

    private String op1st1 = "";
    private Double op1n1 = 0.0;
    private String op1st2 = "";
    private Double op1n2 = 0.0;

    private String op2st1 = "";
    private Double op2n1 = 0.0;
    private String op2st2 = "";
    private Double op2n2 = 0.0;

    private String op3st1 = "";
    private Double op3n1 = 0.0;
    private String op3st2 = "";
    private Double op3n2 = 0.0;

    public Player(){//                         v                         v    these are half the token dimensions
        super(Color.RED, (Main.screenWidth/2)-25, (Main.screenHeight/2)-25, -0.1 , 3,50,50, 30, 50,0.0001,3000);

        this.hitbox = new Rectangle((int) this.xPos, (int) this.yPos, (int)xHit, (int)yHit);
        this.weapon = new Revolver(this);

        try {
            ArrayList<BufferedImage> set = new ArrayList<BufferedImage>();
            set.add(ImageIO.read(new File("resources\\PixelPlayerLeftFoot.PNG")));
            set.add(ImageIO.read(new File("resources\\PixelPlayerNeutral.PNG")));
            set.add(ImageIO.read(new File("resources\\PixelPlayerRightFoot.PNG")));
            set.add(ImageIO.read(new File("resources\\PixelPlayerNeutral.PNG")));
            this.tokenImages.add(set);
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

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escPressed");
        this.getActionMap().put("escPressed", new EscapeAction());

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
        if (this.score>=20){
            Main.inMenu = "LEVEL";

        }
        this.weapon.tick();
        if (this.isShooting){
            this.weapon.shoot(this.shootingAt[0], this.shootingAt[1]);
        }
        super.preStep();
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
            if (this.hitbox.intersects(Main.tokens.get(i).hitbox)){
                if (Main.tokens.get(i) instanceof Enemy){
                    ((Enemy) Main.tokens.get(i)).hurtPlayer();
                    ((Enemy) Main.tokens.get(i)).die();
                    takeDamage(1);
                } else if(Main.tokens.get(i) instanceof WizardProjectile){
                    ((Enemy) ((Projectile) Main.tokens.get(i)).owner).hurtPlayer();
                    ((Projectile) Main.tokens.get(i)).end();
                    takeDamage(1);
                } else {
                    
                }
            }
            
        }
    }

    @Override
    protected void die(){
        Main.playing = false;
    }

    public void applyLevel(int choice){
        //TODO maybe appy minimums for stats
        String stat1 = "";
        Double num1 = 0.0;
        String stat2 = "";
        Double num2 = 0.0;
        if (choice == 1){
            stat1=op1st1;
            num1=op1n1;
            stat2=op1st2;
            num2=op1n2;
        } else if (choice == 2){
            stat1=op2st1;
            num1=op2n1;
            stat2=op2st2;
            num2=op2n2;
        } else if (choice == 3){
            stat1=op3st1;
            num1=op3n1;
            stat2=op3st2;
            num2=op3n2;
        }
        //apply stats
        if (stat1 == "Move Speed"){
            this.moveSpeed *= 1+num1;
        } else if (stat1 == "Max HP"){
            this.maxHP *= 1+num1;
        } else if (stat1 == "Regeneration Rate"){
            this.regenRate  *= 1+num1;
        } else if (stat1 == "Regeneration Delay"){
            this.regenDelay *= 1+num1;
        } else if (stat1 == "Time between Shots"){
            this.weapon.fireDelay *= 1+num1;
        } else if (stat1 == "Clip Size"){
            this.weapon.clipSize *= 1+num1;
        } else if (stat1 == "Reload Time"){
            this.weapon.reloadTime *= 1+num1;
        } else if (stat1 == "Damage"){
            this.weapon.damage *= 1+num1;
        } else if (stat1 == "Spread"){
            this.weapon.spread *= 1+num1;
        } else if (stat1 == "Projectile Speed"){
            this.weapon.projectileSpeed *= 1+num1;
        } else if (stat1 == "Projectile Size"){
            this.weapon.projectileSize *= 1+num1;
        } else {
            System.out.println("Apply stat failed");
        }
        if (stat2 == "Move Speed"){
            this.moveSpeed *= 1+num2;
        } else if (stat2 == "Max HP"){
            this.maxHP *= 1+num2;
        } else if (stat2 == "Regeneration Rate"){
            this.regenRate  *= 1+num2;
        } else if (stat2 == "Regeneration Delay"){
            this.regenDelay *= 1+num2;
        } else if (stat2 == "Time between Shots"){
            this.weapon.fireDelay *= 1+num2;
        } else if (stat2 == "Clip Size"){
            this.weapon.clipSize *= 1+num2;
        } else if (stat2 == "Reload Time"){
            this.weapon.reloadTime *= 1+num2;
        } else if (stat2 == "Damage"){
            this.weapon.damage *= 1+num2;
        } else if (stat2 == "Spread"){
            this.weapon.spread *= 1+num2;
        } else if (stat2 == "Projectile Speed"){
            this.weapon.projectileSpeed *= 1+num2;
        } else if (stat2 == "Projectile Size"){
            this.weapon.projectileSize *= 1+num2;
        } else {
            System.out.println("Apply stat failed");
        }
    }
    public void setLevelEffects(String c1s1,Double c1n1,String c1s2,Double c1n2,
                                String c2s1,Double c2n1,String c2s2,Double c2n2,
                                String c3s1,Double c3n1,String c3s2,Double c3n2){
        this.op1st1 = c1s1;
        this.op1n1 = c1n1;
        this.op1st2 = c1s2;
        this.op1n2 = c1n2;

        this.op2st1 = c2s1;
        this.op2n1 = c2n1;
        this.op2st2 = c2s2;
        this.op2n2 = c2n2;

        this.op3st1 = c3s1;
        this.op3n1 = c3n1;
        this.op3st2 = c3s2;
        this.op3n2 = c3n2;
    }

    public void setShooting(boolean set){
        this.isShooting = set;
    }
    public void setShootingAt(int[] at){
        this.shootingAt = at;
    }
    public int getScore(){
        return this.score;
    }
    public void incScore(){
        this.score++;
    }
    public void resetScore(){
        this.score = 0;
    }
}
