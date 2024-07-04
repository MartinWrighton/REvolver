
public class PlasmaRifle extends Weapon{

    public PlasmaRifle(Token owner){
        this.owner = owner;
        this.fireDelay = 0.0001;
        this.clipSize = 10;
        this.clip = this.clipSize;
        this.reloadTime = 1;
        this.damage = 1;
        this.penetration = 0;
        this.projectileSpeed = 1.5;
        this.projectileSize = 7;
        this.spread = 0.0001;
        this.lifetime = 1000;
        this.exampleProjectile = new SimpleProjectile();
    }
    
}
