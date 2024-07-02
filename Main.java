
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;



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

        int spawntimer = 99999999;
        int tickrate = 1000000;
        long timestamp = System.nanoTime();


        while (playing){//mainloop

            if (System.nanoTime()-tickrate>timestamp){//if a tick has passed
                timestamp = System.nanoTime();

                
                //spawn new enemies
                if (spawntimer > 5000){
                    spawnPack(10);
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

    public static void spawnPack(int packSize){
        //random spawn location
        Random random = new Random();
        int xSpawn = 0;
        int ySpawn = 0;
        int zone = random.nextInt(4);
        
        for (int i = 0; i < packSize; i++){
            if (zone==0){
                //left
                xSpawn = -50;
                ySpawn = random.nextInt(Main.screenHeight+50);
            } else if (zone==1){
                //right
                xSpawn = Main.screenWidth+30;
                ySpawn = random.nextInt(Main.screenHeight+55);
            } else if (zone==2){
                //top
                ySpawn = -50;
                xSpawn = random.nextInt(Main.screenWidth+30);
            } else if (zone==3){
                //bottom
                ySpawn = Main.screenHeight+55;
                xSpawn = random.nextInt(Main.screenWidth+30);
            }
            //ensure they are not spawned on top of another entity
            Rectangle testHitbox = new Rectangle(xSpawn,ySpawn,30,50);//replace this with some way of dynamicaky getting hitbox of enemy about to be spawned
            boolean spawnClear = true;
            for (int j = 0 ; j<tokens.size();j++){
                if (testHitbox.intersects(Main.tokens.get(j).hitbox)){
                    spawnClear = false;
                }
            }
            if (spawnClear){
                Enemy enemy = new Enemy(xSpawn,ySpawn,Main.player);
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
//TODO effect tokens
//TODO do flamethrower properly
//TODO do laser properly
//TODO different projectiles
//TODO different enemy types
//TODO ranged enemies
//TODO wave spawning
//TODO UI elements
//TODO gameplay elements; leveling and enemy progression
//TODO main menu