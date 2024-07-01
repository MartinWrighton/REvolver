import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Click extends MouseAdapter{
    public Click(){
        //System.out.println("Click has been made");
    }

    @Override
    public void mousePressed(MouseEvent e){
        System.out.println("Player is Shooting");
        Main.player.setShooting(true);
        Main.player.setShootingAt(new int[] {e.getX()-35,e.getY()-45});//for some reason it shoots at a point near the cursor so we adjust manually
        
        
        
    }

    @Override
    public void mouseReleased(MouseEvent e){
        System.out.println("Player stopped Shooting");
        Main.player.setShooting(false);
    }


    @Override
    public void mouseDragged(MouseEvent e){
        Main.player.setShootingAt(new int[] {e.getX()-35,e.getY()-45});
    }

}