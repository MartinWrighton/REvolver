import java.awt.Color;
import java.util.function.Supplier;

public class Projectile extends Token implements Supplier<Projectile>{
    protected int penetration;
    protected double damage;
    protected int lifetime;
    public Projectile(){

    }

    public void fillProjectile(Color color, double xPos,double yPos,int xSize,int ySize,double moveSpeed, int penetration, double damage, int lifetime){
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.moveSpeed = moveSpeed;
        this.penetration = penetration;
        this.damage = damage;
        this.xHit = this.xSize;
        this.yHit = this.ySize;
        this.lifetime = lifetime;
    }

    @Override
    protected void postStep(){
        if (xPos < 10 || xPos > Main.screenWidth-25 || yPos < 10 || yPos > Main.screenHeight-50 ||this.lifetime<=0){
            Main.tokens.remove(this);
        } else {
            this.lifetime--;
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

    @Override
    public Projectile get() {
        return new Projectile();
    }

}
