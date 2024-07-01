public class Revolver extends Weapon{

    public Revolver(Token owner){
        this.owner = owner;
        this.fireDelay = 0.3;
        this.clipSize = 6;
        this.clip = this.clipSize;
        this.reloadTime = 3;
        this.damage = 1;
        this.penetration = 1;
    }

}
