
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
    public static Template cloakTemplate = new Template(0.14,2,1.5,0.0005,5000);
    public static Template knightTemplate = new Template(0.11,4,3,0.0005,5000);
    public static WizardTemplate wizardTemplate = new WizardTemplate(0.11,2,1,0.0005,5000,0.2,3,5,0.3,15,0.2);
    public static String inMenu = "MAIN";
    public static MainWorker mainWorker = new MainWorker();

    public static double mutationRate = 0.5;
    public static double playerMutationRate = 0.5;
    //output control
    public static Boolean printTicks = false;
    public static Boolean printTemplate = false;
    public static Boolean printScore = false;
    public static Boolean showHitboxes = false;
    public static Boolean showGrid = false;
    public static Boolean showWaypoints = false;
    public static void main(String[] args) throws InterruptedException{

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        screenWidth = gd.getDisplayMode().getWidth();
        screenHeight = gd.getDisplayMode().getHeight();
        Main.gui = new Gui();
        gui.add(player);//for some reason we need to add the player here and not in gui


        //mainWorker.execute(); done by clicking play now

        
    }

    public static void spawnPack(int packSize){
        Random random = new Random();
        //choose enemy type
        int enemyType = random.nextInt(3);
        //random spawn location

        int xSpawn = 0;
        int ySpawn = 0;
        int zone = random.nextInt(4);



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
                    
                } else if (enemyType ==1){
                    enemy = new Knight(xSpawn,ySpawn,Main.player);
                    
                    
                } else {
                    enemy = new Wizard(xSpawn,ySpawn,Main.player);
                }
                //print template stats
                if (i==0 && Main.printTemplate){
                    System.err.println("\n"+enemy.getClass()+": moveSpeed: "+((double)((int)(enemy.getTemplate().getMoveSpeed()*1000)))/1000
                                                                +"  maxHP: "+((double)((int)(enemy.getTemplate().getMaxHP()*1000)))/1000
                                                                +"  armor: "+((double)((int)(enemy.getTemplate().getArmor()*1000)))/1000
                                                                +"  regenRate: "+((double)((int)(enemy.getTemplate().getRegenRate()*100000)))/100000
                                                                +"  regenDelay: "+((double)((int)(enemy.getTemplate().getRegenDelay()*1000)))/1000
                                                                +"  score: "+enemy.getTemplate().getScore());
                    if (enemy instanceof Wizard){
                        System.err.println("            fireDelay: "+((double)((int)(((WizardTemplate) enemy.getTemplate()).getFireDelay()*1000)))/1000
                                                                +"  clipSize: "+((double)((int)(((WizardTemplate) enemy.getTemplate()).getClipSize()*100000)))/100000
                                                                +"  reloadTime: "+((double)((int)(((WizardTemplate) enemy.getTemplate()).getReloadTime()*1000)))/1000
                                                                +"  projectileSpeed: "+((double)((int)(((WizardTemplate) enemy.getTemplate()).getProjectileSpeed()*100000)))/100000
                                                                +"  projectileSize: "+((double)((int)(((WizardTemplate) enemy.getTemplate()).getProjectileSize()*1000)))/1000
                                                                +"  spread: "+((WizardTemplate) enemy.getTemplate()).getSpread());
                    }
                }
                enemy.mutate();
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
//TODO do laser properly, hitbox could be a line

//TODO different projectiles


//TODO UI elements
//TODO player model
//TODO gameplay elements; leveling and enemy progression
//TODO leveling: three options, each with two randomly chosen stats being modified by +- a random %
//TODO main menu
//TODO sound



