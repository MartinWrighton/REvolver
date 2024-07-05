
public class Rifle extends Weapon{
    public Rifle(Token owner){
        this.owner = owner;
        this.fireDelay = 1.5;
        this.clipSize = 5;
        this.clip = this.clipSize;
        this.reloadTime = 6;
        this.damage = 12;
        this.penetration = 1;
        this.projectileSpeed = 1;
        this.projectileSize = 5;
        this.spread = 0.01;
        this.lifetime = 1000;
        this.exampleProjectile = new SimpleProjectile();
    }
    
}