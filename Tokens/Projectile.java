import java.awt.Color;
import java.awt.Rectangle;
import java.util.function.Supplier;

public class Projectile extends Token implements Supplier<Projectile>{
    protected int penetration;
    protected double damage;
    protected int lifetime;
    public Projectile(Color color){
        super(color,0,0,0,0,0,0,0);
    }

    public void fillProjectile(double xPos,double yPos,int xSize,int ySize,double moveSpeed, int penetration, double damage, int lifetime){
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
        this.hitbox = new Rectangle((int)xPos, (int)yPos, this.xHit, this.yHit);
    }

    @Override
    protected void postStep(){
        if (this.lifetime<=0){
            this.end();
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
        return new Projectile(this.color);
    }

}
