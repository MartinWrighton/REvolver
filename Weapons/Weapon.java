

import java.util.Random;



public class Weapon {
    protected Token owner;
    protected double fireDelay;
    protected double fireProgress=999;
    protected double clipSize;
    protected double clip;
    protected double reloadTime;
    protected double reloadProgress=999;
    protected double damage;
    protected int penetration;
    protected double projectileSpeed;
    protected double projectileSize;
    protected double spread;
    protected int lifetime;
    protected Projectile exampleProjectile;
    public void shoot(int x,int y){
        if (this.fireProgress>this.fireDelay && this.clip>0){
            this.fireProgress = 0;
            this.reloadProgress = 0;
            this.clip--;

            double xdif = Math.abs(x - owner.xPos);
            double ydif = Math.abs(y - owner.yPos);
            
            double combine = Math.sqrt(xdif*xdif + ydif*ydif);
            //adding spread
            Random random = new Random();
            xdif = xdif+(combine*(this.spread-random.nextDouble(this.spread*2)));
            ydif = ydif+(combine*(this.spread-random.nextDouble(this.spread*2)));
            
            double both = Math.sqrt(xdif*xdif + ydif*ydif);

            double movx = xdif/both;
            double movy = ydif/both;
            
            Projectile bullet = this.exampleProjectile.get();
            bullet.fillProjectile(owner,owner.xPos+25, owner.yPos+10, this.projectileSize, this.projectileSize, this.projectileSpeed, this.penetration, this.damage, this.lifetime);
            if (x < owner.xPos){
                bullet.addDirection(0, movx);
            } else if (x > owner.xPos){
                bullet.addDirection(1, movx);
            } else {
                bullet.addDirection(0,0);
                bullet.addDirection(1, 0);
            }
            if (y < owner.yPos){
                bullet.addDirection(2, movy);
            } else if (y > owner.yPos){
                bullet.addDirection(3, movy);
            } else {
                bullet.addDirection(2,0);
                bullet.addDirection(3, 0);
            }
            
            Main.tokens.add(bullet);
        }
    }
    public void tick(){
        this.fireProgress += 0.001*Main.dynamicTick;
        this.reloadProgress += 0.001*Main.dynamicTick;
        if (this.reloadProgress>=this.reloadTime){
            this.clip = this.clipSize;
        }
    }


}
