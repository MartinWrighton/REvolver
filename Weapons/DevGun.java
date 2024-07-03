
public class DevGun extends Weapon{
    public DevGun(Token owner){
        this.owner = owner;
        this.fireDelay = 0;
        this.clipSize = 999999999;
        this.clip = this.clipSize;
        this.reloadTime = 0;
        this.damage = 1000;
        this.penetration = 1000;
        this.projectileSpeed = 30;
        this.projectileSize = 10;
        this.spread = 0.0000001;
        this.exampleProjectile = new SimpleProjectile();
    }
}
