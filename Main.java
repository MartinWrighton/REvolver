
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;



public class Main {
    public static ArrayList<Token> tokens = new ArrayList<Token>();
    public static int screenWidth;
    public static int screenHeight;
    public static Gui gui;
    public static Player player;
    public static boolean playing = true;
    public static double worldX;
    public static double worldY;
    public static double dynamicTick;
    public static void main(String[] args) throws InterruptedException{

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        screenWidth = gd.getDisplayMode().getWidth();
        screenHeight = gd.getDisplayMode().getHeight();
        Main.gui = new Gui();
        gui.add(player);//for some reason we need to add the player here and not in gui



        int spawntimer = 99999999;
        int tickrate = 1000000;
        long timestamp = System.nanoTime();


        while (playing){//mainloop

            if (System.nanoTime()-tickrate>timestamp){//if a tick has passed
                if(spawntimer + dynamicTick >= 5000){
                    System.out.print("\nDynamic Tick: "+dynamicTick);
                }

                //do spawning first so that we can prevent dynamic tick
                //spawn new enemies
                if (spawntimer > 5000){
                    
                    spawnPack(10);
                    spawntimer = 0;

                    //no dynamic ticks on spawn ticks
                    timestamp = System.nanoTime() - tickrate;
                } else {
                    spawntimer+=dynamicTick;
                }


                int elapsedNano = (int)(System.nanoTime() - timestamp);
                dynamicTick =(double)((double)elapsedNano/tickrate);
                if(spawntimer < 1){
                    System.out.print("   Entities: "+tokens.size()+"   Dynamic Tick on spawn tick: "+dynamicTick);
                }
                
                timestamp = System.nanoTime();

                
                
                 
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

    public static void spawnPack(int packSize){
        Random random = new Random();
        //choose enemy type
        int enemyType = random.nextInt(2);
        //random spawn location

        int xSpawn = 0;
        int ySpawn = 0;
        int zone = random.nextInt(4);
        /*
        //make large packs spawn on long board edges
        if (packSize*50*2>Main.screenHeight){
            zone = random.nextInt(2)+2;
        }
        */


        for (int i = 0; i < packSize; i++){
            
            if (zone==0){
                //left
                xSpawn = -50-random.nextInt(500);
                ySpawn = random.nextInt(Main.screenHeight+50);
            } else if (zone==1){
                //right
                xSpawn = Main.screenWidth+30+random.nextInt(500);
                ySpawn = random.nextInt(Main.screenHeight+55);
            } else if (zone==2){
                //top
                ySpawn = -50-random.nextInt(500);
                xSpawn = random.nextInt(Main.screenWidth+30);
            } else if (zone==3){
                //bottom
                ySpawn = Main.screenHeight+55+random.nextInt(500);
                xSpawn = random.nextInt(Main.screenWidth+30);
            }
            //ensure they are not spawned on top of another entity
            Rectangle testHitbox = new Rectangle(xSpawn-5,ySpawn-5,50,50);//replace this with some way of dynamic way getting hitbox of enemy about to be spawned
            boolean spawnClear = true;                                             // currently just larger than all enemies
            for (int j = 0 ; j<tokens.size();j++){
                if (testHitbox.intersects(Main.tokens.get(j).hitbox) && Main.tokens.get(j) instanceof Enemy){
                    Main.tokens.get(j).step();//step blocking creature to maybe free the space
                    spawnClear = false;
                }
            }
            if (spawnClear){
                Enemy enemy;
                if (enemyType == 0){
                    enemy = new Cloak(xSpawn,ySpawn,Main.player);
                } else {
                    enemy = new Knight(xSpawn,ySpawn,Main.player);
                }
                enemy.step();
                Main.tokens.add(enemy);
            } else {
                i--;
            }

        }
       

    }
}

//TODO shotgun
//TODO rocket laucher + explosons
//TODO do flamethrower properly
//TODO do laser properly

//TODO different projectiles

//TODO ranged enemies

//TODO wave spawning
//TODO UI elements
//TODO gameplay elements; leveling and enemy progression
//TODO main menu

//TODO base enemy evaluation score on the closest they got (not the closest they died), the unmitigated damage they took (with little weight), and if they damaged the player (maybe)

//TODO affine transform can let us rotate art so bullets can fly straight