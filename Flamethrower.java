
public class Flamethrower extends Weapon{

    public Flamethrower(Token owner){
        this.owner = owner;
        this.fireDelay = 0.005;
        this.clipSize = 1000;
        this.clip = this.clipSize;
        this.reloadTime = 5;
        this.damage = 1;
        this.penetration = 1000;
        this.projectileSpeed = 0.2;
        this.projectileSize = 10;
        this.spread = 0.2;
    }
    
    
}
