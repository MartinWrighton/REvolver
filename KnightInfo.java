import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KnightInfo extends JPanel{
    private JLabel moveSpeed = new JLabel();
    private JLabel maxHP = new JLabel();
    private JLabel armor = new JLabel();
    private JLabel regenRate = new JLabel();
    private JLabel regenDelay = new JLabel(); 
    public KnightInfo(){



        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File("resources\\PixelKnight\\PixelKnightWalk0.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            c.gridx = 0;
            c.gridy = 0;

            add(picLabel,c);
        } catch (IOException e) {
            System.out.println("Failed to load lnight info icon");
        }
        
        

        c.gridy=1;
        this.add(moveSpeed,c);
        c.gridy=2;
        this.add(maxHP,c);
        c.gridy=3;
        this.add(armor,c);
        c.gridy=4;
        this.add(regenRate,c);
        c.gridy=5;
        this.add(regenDelay,c);

    }

    public void update(){
        this.moveSpeed.setText("MoveSpeed: "+((double)((int)(Main.knightTemplate.getMoveSpeed()*1000)))/1000);
        this.maxHP.setText("MaxHP: "+((double)((int)(Main.knightTemplate.getMaxHP()*1000)))/1000);
        this.armor.setText("Armor: "+((double)((int)(Main.knightTemplate.getArmor()*1000)))/1000);
        this.regenRate.setText("RegenRate: "+((double)((int)(Main.knightTemplate.getRegenRate()*1000)))/1000);
        this.regenDelay.setText("RegenDelay: "+((double)((int)(Main.knightTemplate.getRegenDelay()*1000)))/1000);
    }
}
