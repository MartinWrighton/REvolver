import java.awt.Color;

public class Laser extends Weapon{
    public Laser(Token owner){
        this.color = Color.RED;
        this.owner = owner;
        this.fireDelay = 0.0001;
        this.clipSize = 1000;
        this.clip = this.clipSize;
        this.reloadTime = 6;
        this.damage = 1;
        this.penetration = 0;
        this.projectileSpeed = 5;
        this.projectileSize = 5;
        this.spread = 0.0001;
        this.exampleProjectile = new SimpleProjectile();
    }
}