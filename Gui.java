import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Gui extends JFrame{

    public Gui(){
        this.setTitle("REvolver");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1600,900);
        this.setVisible(true);
        //this.setLayout(null);

        
        Creature player = new Creature();
        Main.creatures.add(player);
        this.add(player);
        this.add(player.getToken());
        



  
    }
}