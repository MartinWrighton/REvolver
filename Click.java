import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Click extends MouseAdapter{
    public Click(){
        //System.out.println("Click has been made");
    }

    @Override
    public void mousePressed(MouseEvent e){
        System.out.println("Player is Shooting");
        int xClick = e.getX();
        int yClick = e.getY();
        
        Main.player.shoot(xClick-10,yClick-35);//for some reason it shoots at a point near the cursor so we adjust manually
        
    }

    @Override
    public void mouseReleased(MouseEvent e){
        System.out.println("Player stopped Shooting");
    }
}