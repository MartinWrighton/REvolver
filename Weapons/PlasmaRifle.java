import java.awt.Color;

public class PlasmaRifle extends Weapon{

    public PlasmaRifle(Token owner){
        this.color = Color.CYAN;
        this.owner = owner;
        this.fireDelay = 0.0001;
        this.clipSize = 10;
        this.clip = this.clipSize;
        this.reloadTime = 0.5;
        this.damage = 1;
        this.penetration = 0;
        this.projectileSpeed = 2;
        this.projectileSize = 7;
        this.spread = 0.0001;
        this.exampleProjectile = new SimpleProjectile();
    }
    
}
