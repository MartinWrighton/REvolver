import java.util.List;
import java.util.Random;

import javax.swing.SwingWorker;

public class MainWorker extends SwingWorker<Object,String>{

    @Override
    protected Object doInBackground() throws Exception {
        


        int spawntimer = 99999999;
        int spawnRate = 10000;
        int tickrate = 1000000;
        long timestamp = System.nanoTime();


        while (Main.playing){//mainloop
            if (System.nanoTime()-tickrate>timestamp){//if a tick has passed
                if (Main.inMenu.length()>0){
                    timestamp = System.nanoTime();
                    publish(Main.inMenu);
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
    protected void process(List<String> chunks){
        if (chunks.get(0)=="PAUSE"){
            Main.gui.pauseMenu.setVisible(true);
            Main.gui.pauseMenu.repaint();
            Main.inMenu = "HOLD";
        } else if(chunks.get(0)=="LEVEL"){
            if (Main.player.getScore() != 0){
                //roll new stats              mvSpeed, maxHP, regenrate, regendelay, firedelay,clipsize,reloadrate,damage,spread,projspeed,projsize
                Random random = new Random();
                //choice 1
                String c1s1 = getRandomStat();
                Double c1n1 = -Main.playerMutationRate/2+random.nextDouble(Main.playerMutationRate);
                String c1s2 = getRandomStat();
                Double c1n2 = -Main.playerMutationRate/2+random.nextDouble(Main.playerMutationRate);
                //choice 2
                String c2s1 = getRandomStat();
                Double c2n1 = -Main.playerMutationRate/2+random.nextDouble(Main.playerMutationRate);
                String c2s2 = getRandomStat();
                Double c2n2 = -Main.playerMutationRate/2+random.nextDouble(Main.playerMutationRate);
                //choice 3
                String c3s1 = getRandomStat();
                Double c3n1 = -Main.playerMutationRate/2+random.nextDouble(Main.playerMutationRate);
                String c3s2 = getRandomStat();
                Double c3n2 = -Main.playerMutationRate/2+random.nextDouble(Main.playerMutationRate);

                Main.player.setLevelEffects(c1s1, c1n1, c1s2, c1n2, c2s1, c2n1, c2s2, c2n2, c3s1, c3n1, c3s2, c3n2);
                c1n1 = ((double)((int)(c1n1*1000)))/10;
                c1n2 = ((double)((int)(c1n2*1000)))/10;
                c2n1 = ((double)((int)(c2n1*1000)))/10;
                c2n2 = ((double)((int)(c2n2*1000)))/10;
                c3n1 = ((double)((int)(c3n1*1000)))/10;
                c3n2 = ((double)((int)(c3n2*1000)))/10;
                Main.gui.levelButton1.setText("<html>"+c1s1+" "+c1n1+"% <br>"+c1s2+" "+c1n2+"%</html>");
                Main.gui.levelButton2.setText("<html>"+c2s1+" "+c2n1+"% <br>"+c2s2+" "+c2n2+"%</html>");
                Main.gui.levelButton3.setText("<html>"+c3s1+" "+c3n1+"% <br>"+c3s2+" "+c3n2+"%</html>");
                Main.player.resetScore();
            }


            Main.gui.levelMenu.setVisible(true);
            Main.gui.levelMenu.repaint();
        }

    }

    private String getRandomStat(){
        Random random = new Random();
        int randNum = random.nextInt(11);
        if (randNum == 0){
            return "Move Speed";
        } else if (randNum == 1){
            return "Max HP";
        } else if (randNum == 2){
            return "Regeneration Rate";
        } else if (randNum == 3){
            return "Regeneration Delay";
        } else if (randNum == 4){
            return "Time between Shots";
        } else if (randNum == 5){
            return "Clip Size";
        } else if (randNum == 6){
            return "Reload Time";
        } else if (randNum == 7){
            return "Damage";
        } else if (randNum == 8){
            return "Spread";
        } else if (randNum == 9){
            return "Projectile Speed";
        } else if (randNum == 10){
            return "Projectile Size";
        } else {
            System.out.println("Get stat failed");
            return "oops";
        }
    }

}
