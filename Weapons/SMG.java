
public class SMG extends Weapon{
    public SMG(Token owner){
        this.owner = owner;
        this.fireDelay = 0.1;
        this.clipSize = 50;
        this.clip = this.clipSize;
        this.reloadTime = 3;
        this.damage = 1;
        this.penetration = 0;
        this.projectileSpeed = 0.5;
        this.projectileSize = 3;
        this.spread = 0.1;
    }
}
