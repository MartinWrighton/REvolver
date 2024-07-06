
public class WizardStaff extends Weapon{

    public WizardStaff(Token owner){
        this.owner = owner;
        this.fireDelay = 0.2;
        this.clipSize = 3;
        this.clip = this.clipSize;
        this.reloadTime = 5;
        this.damage = 1;
        this.penetration = 0;
        this.projectileSpeed = 0.2;
        this.projectileSize = 15;
        this.spread = 0.1;
        this.lifetime = 10000;
        this.exampleProjectile = new WizardProjectile();
        //TODO have this evolve
    }

}
