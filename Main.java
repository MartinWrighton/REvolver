import java.util.ArrayList;

public class Main {
    public static ArrayList<Token> tokens = new ArrayList<Token>();
    public static Gui gui = new Gui();
    public static void main(String[] args) throws InterruptedException{

        
        
        

        int tickrate = 1000000;
        long timestamp = System.nanoTime();
        while (true){//mainloop
            if (System.nanoTime()-tickrate>timestamp){//if a tick has passed
                timestamp = System.nanoTime();
            


                for (Token i : tokens){
                    i.step();
                    
                    
                }
                if (gui.draw != null){
                    gui.draw.repaint();
                }
            }
        }
    }
}
