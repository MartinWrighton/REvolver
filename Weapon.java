public class Weapon {
    protected Token owner;
    protected double fireDelay;
    protected double fireProgress=999;
    protected int clipSize;
    protected int clip;
    protected double reloadTime;
    protected double reloadProgress=999;
    protected int damage;
    protected int penetration;

    public void shoot(int x,int y){
        if (this.fireProgress>this.fireDelay && this.clip>0){
            this.fireProgress = 0;
            this.reloadProgress = 0;
            this.clip--;

            double xdif = x - owner.xPos;
            double ydif = y - owner.yPos;
            double both = Math.abs(xdif) + Math.abs(ydif);
            double movx = xdif/both;
            double movy = ydif/both;
            Projectile bullet = new Projectile(owner.xPos+25, owner.yPos+10, 5, 5, 0.5, 1, 1);
            if (movx < 0){
                bullet.addDirection(0, -1*movx);
            } else if (movx > 0){
                bullet.addDirection(1, movx);
            } else {
                bullet.addDirection(0,0);
                bullet.addDirection(1, 0);
            }
            if (movy < 0){
                bullet.addDirection(2, -1*movy);
            } else if (movy > 0){
                bullet.addDirection(3, movy);
            } else {
                bullet.addDirection(2,0);
                bullet.addDirection(3, 0);
            }
            Main.tokens.add(bullet);
        }
    }
    public void tick(){
        this.fireProgress += 0.001;
        this.reloadProgress += 0.001;
        if (this.reloadProgress>=this.reloadTime){
            this.clip = this.clipSize;
        }
    }

}
