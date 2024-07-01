import java.awt.Color;

public class Projectile extends Token{
    protected int penetration;
    protected int damage;
    public Projectile(int xPos,int yPos){
        this.color = Color.YELLOW;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = 5;
        this.ySize = 5;
        this.moveSpeed = 0.5;
        this.penetration = 1;
        this.damage = 1;
    }

    @Override
    protected void postStep(){
        if (xPos < 10 || xPos > Main.screenWidth-25 || yPos < 10 || yPos > Main.screenHeight-50){
            Main.tokens.remove(this);
        }
    }

    protected void hit(){
        this.penetration--;
        if (this.penetration<0){
            end();
        }
    }
    protected void end(){
        Main.tokens.remove(this);
    }

}
