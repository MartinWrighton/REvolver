import java.util.List;

import javax.swing.SwingWorker;

public class MainWorker extends SwingWorker<Object,Object>{

    @Override
    protected Object doInBackground() throws Exception {
        


        int spawntimer = 99999999;
        int spawnRate = 10000;
        int tickrate = 1000000;
        long timestamp = System.nanoTime();


        while (Main.playing){//mainloop
            if (System.nanoTime()-tickrate>timestamp){//if a tick has passed
                if (Main.inMenu){
                    timestamp = System.nanoTime();
                    publish();
                } else {
                    if(spawntimer + Main.dynamicTick >= spawnRate && Main.printTicks){
                        System.out.print("\nDynamic Tick: "+Main.dynamicTick);
                    }

                    //do spawning first so that we can prevent dynamic tick
                    //spawn new enemies
                    if (spawntimer > spawnRate){
                        
                        Main.spawnPack(5);
                        spawntimer = 0;

                        //no dynamic ticks on spawn ticks
                        timestamp = System.nanoTime() - tickrate;
                    } else {
                        spawntimer+=Main.dynamicTick;
                    }


                    int elapsedNano = (int)(System.nanoTime() - timestamp);
                    Main.dynamicTick =(double)((double)elapsedNano/tickrate);
                    if(spawntimer < 1  && Main.printTicks){
                        System.out.print("   Entities: "+Main.tokens.size()+"   Dynamic Tick on spawn tick: "+Main.dynamicTick);
                    }
                    
                    timestamp = System.nanoTime();

                    
                    
                    
                    //do token steps
                    for (int i = 0 ; i < Main.tokens.size() ; i++){
                        Main.tokens.get(i).step();
                    }


                    //redraw all tokens
                    if (Main.gui.draw != null){  
                        Main.gui.draw.repaint();
                    }
                }
            }
        }
        return true;
    }

    @Override
    protected void process(List<Object> chunks){
        Main.gui.mainMenu.setVisible(true);
        Main.gui.mainMenu.repaint();

    }

}
