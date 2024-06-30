import java.util.ArrayList;

public class Main {
    public static ArrayList<Creature> creatures = new ArrayList<Creature>();

    public static void main(String[] args) throws InterruptedException{

        Gui frame = new Gui();
        


        int tickrate = 10000000;
        long timestamp = System.nanoTime();
        while (true){//mainloop
            if (System.nanoTime()-tickrate>timestamp){//if a tick has passed
                timestamp = System.nanoTime();
            


                for (Creature i : creatures){
                    i.step();
                    //System.out.println(i.getDirection()[0]+i.getDirection()[1]);

                    
                }
            }
        }
    }
}
