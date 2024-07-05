import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Click extends MouseAdapter{
    public Click(){
        //System.out.println("Click has been made");
    }

    @Override
    public void mousePressed(MouseEvent e){
        Main.player.setShooting(true);
        Main.player.setShootingAt(new int[] {e.getX()-12,e.getY()+2});//for some reason it shoots at a point near the cursor so we adjust manually
        Main.player.setShooting(true);
        
        
        
    }

    @Override
    public void mouseReleased(MouseEvent e){
        Main.player.setShooting(false);
    }


    @Override
    public void mouseDragged(MouseEvent e){
        Main.player.setShootingAt(new int[] {e.getX()-12,e.getY()+2});
    }

}