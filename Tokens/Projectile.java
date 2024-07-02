
import java.awt.Color;

public class Projectile extends Token{
    protected int penetration;
    protected int damage;
    public Projectile(double xPos,double yPos,int xSize,int ySize,double moveSpeed, int penetration, int damage){
        this.color = Color.YELLOW;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.moveSpeed = moveSpeed;
        this.penetration = penetration;
        this.damage = damage;
        this.xHit = this.xSize;
        this.yHit = this.ySize;
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
