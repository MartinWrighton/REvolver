
public class WizardStaff extends Weapon{

    public WizardStaff(Token owner,double fireDelay,int clipSize,double reloadTime,double projectileSpeed,int projectileSize,double spread){
        this.owner = owner;
        this.fireDelay = fireDelay;
        this.clipSize = clipSize;
        this.clip = this.clipSize;
        this.reloadTime = reloadTime;
        this.damage = 1;
        this.penetration = 0;
        this.projectileSpeed = projectileSpeed;
        this.projectileSize = projectileSize;
        this.spread = spread;
        this.lifetime = 10000;
        this.exampleProjectile = new WizardProjectile();
        //TODO have this evolve
    }

}
