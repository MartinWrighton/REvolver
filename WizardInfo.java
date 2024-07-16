import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WizardInfo extends JPanel{
    private JLabel moveSpeed = new JLabel();
    private JLabel maxHP = new JLabel();
    private JLabel armor = new JLabel();
    private JLabel regenRate = new JLabel();
    private JLabel regenDelay = new JLabel();

    private JLabel fireDelay = new JLabel();
    private JLabel clipSize = new JLabel();
    private JLabel reloadTime = new JLabel();
    private JLabel projectileSpeed = new JLabel();
    private JLabel projectileSize = new JLabel();
    private JLabel spread = new JLabel();
    public WizardInfo(){



        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File("resources\\PixelWizard\\PixelWizard0.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            c.gridx = 0;
            c.gridy = 0;

            add(picLabel,c);
        } catch (IOException e) {
            System.out.println("Failed to load wizard info icon");
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


        c.gridy=6;
        this.add(fireDelay,c);
        c.gridy=7;
        this.add(clipSize,c);
        c.gridy=8;
        this.add(reloadTime,c);
        c.gridy=9;
        this.add(projectileSpeed,c);
        c.gridy=10;
        this.add(projectileSize,c);
        c.gridy=11;
        this.add(spread,c);

    }

    public void update(){
        this.moveSpeed.setText("MoveSpeed: "+((double)((int)(Main.wizardTemplate.getMoveSpeed()*1000)))/1000);
        this.maxHP.setText("MaxHP: "+((double)((int)(Main.wizardTemplate.getMaxHP()*1000)))/1000);
        this.armor.setText("Armor: "+((double)((int)(Main.wizardTemplate.getArmor()*1000)))/1000);
        this.regenRate.setText("RegenRate: "+((double)((int)(Main.wizardTemplate.getRegenRate()*1000)))/1000);
        this.regenDelay.setText("RegenDelay: "+((double)((int)(Main.wizardTemplate.getRegenDelay()*1000)))/1000);
        this.fireDelay.setText("FireDelay: "+((double)((int)(Main.wizardTemplate.getFireDelay()*1000)))/1000);
        this.clipSize.setText("Shots: "+((double)((int)(Main.wizardTemplate.getClipSize()*1000)))/1000);
        this.reloadTime.setText("ReloadTime: "+((double)((int)(Main.wizardTemplate.getReloadTime()*1000)))/1000);
        this.projectileSpeed.setText("ProjectileSpeed: "+((double)((int)(Main.wizardTemplate.getProjectileSpeed()*1000)))/1000);
        this.projectileSize.setText("ProjectileSize: "+((double)((int)(Main.wizardTemplate.getProjectileSize()*1000)))/1000);
        this.spread.setText("Spread: "+((double)((int)(Main.wizardTemplate.getSpread()*1000)))/1000);
    }
}
