import java.util.ArrayList;

public class Main {
    public static ArrayList<Token> tokens = new ArrayList<Token>();
    public static Gui gui = new Gui();
    public static Player player;
    public static boolean playing = true;
    public static void main(String[] args) throws InterruptedException{



        int tickrate = 1000000;
        long timestamp = System.nanoTime();
        while (playing){//mainloop

            if (System.nanoTime()-tickrate>timestamp){//if a tick has passed
                timestamp = System.nanoTime();



                for (int i = 0 ; i < tokens.size() ; i++){
                    tokens.get(i).step();
                }



                if (gui.draw != null){
                    gui.draw.repaint();
                }
            }
        }
    }
}

//TODO collision detection
//TODO hold to shoot