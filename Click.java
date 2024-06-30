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
        
        Main.player.shoot(xClick,yClick);
        
    }

    @Override
    public void mouseReleased(MouseEvent e){
        System.out.println("Player stopped Shooting");
    }
}