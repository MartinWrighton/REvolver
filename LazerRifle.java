
public class LazerRifle extends Weapon{

    public LazerRifle(Token owner){
        this.owner = owner;
        this.fireDelay = 0.0001;
        this.clipSize = 10;
        this.clip = this.clipSize;
        this.reloadTime = 0.5;
        this.damage = 1;
        this.penetration = 0;
        this.projectileSpeed = 5;
        this.projectileSize = 5;
        this.spread = 0.0001;
    }
    
}
