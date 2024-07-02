
import java.util.ArrayList;


public class Main {
    public static ArrayList<Token> tokens = new ArrayList<Token>();
    public static int screenWidth = 1600;
    public static int screenHeight = 900;
    public static Gui gui = new Gui();
    public static Player player;
    public static boolean playing = true;
    public static double worldX;
    public static double worldY;
    public static void main(String[] args) throws InterruptedException{
        gui.add(player);//for some reason we need to add the player here and not in gui

        int spawntimer = 0;
        int tickrate = 1000000;
        long timestamp = System.nanoTime();
        while (playing){//mainloop

            if (System.nanoTime()-tickrate>timestamp){//if a tick has passed
                timestamp = System.nanoTime();


                //spawn new enemies
                if (spawntimer > 500){
                    gui.spawn();
                    spawntimer = 0;
                } else {
                    spawntimer++;
                }

                //do token steps
                for (int i = 0 ; i < tokens.size() ; i++){
                    tokens.get(i).step();
                }


                //redraw all tokens
                if (gui.draw != null){  
                    gui.draw.repaint();
                }

            }
        }
    }
}
//TODO figure out how to put classes in folders
//TODO let damage and health be doubles
//TODO projectile lifetime/slowdown
//TODO shotgun
//TODO rocket laucher + explosons
//TODO do flamethrower properly
//TODO do lazer properly
//TODO wave spawning
//TODO UI elements
//TODO gameplay elements; leveling and enemy progression
//TODO main menu