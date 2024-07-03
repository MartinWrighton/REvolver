
public class Revolver extends Weapon{

    public Revolver(Token owner){
        this.owner = owner;
        this.fireDelay = 0.3;
        this.clipSize = 6;
        this.clip = this.clipSize;
        this.reloadTime = 3;
        this.damage = 5;
        this.penetration = 0;
        this.projectileSpeed = 0.5;
        this.projectileSize = 5;
        this.spread = 0.05;
        this.exampleProjectile = new SimpleProjectile();
    }

}
