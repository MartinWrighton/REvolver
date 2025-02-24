public class WizardTemplate extends Template{
    private double fireDelay;
    private double clipSize;
    private double reloadTime;
    private double projectileSpeed;
    private double projectileSize;
    private double spread;
    public WizardTemplate(double moveSpeed,double maxHP,double armor,double regenRate,double regenDelay,double fireDelay,int clipSize,double reloadTime,double projectileSpeed,int projectileSize,double spread){
        super(moveSpeed,maxHP,armor,regenRate,regenDelay);
        this.fireDelay = fireDelay;
        this.clipSize = clipSize;
        this.reloadTime = reloadTime;
        this.projectileSpeed = projectileSpeed;
        this.projectileSize = projectileSize;
        this.spread = spread;
    }

    public void updateWeapon(double fireDelay,double clipSize,double reloadTime,double projectileSpeed,double projectileSize,double spread){
        this.fireDelay = fireDelay;
        this.clipSize = clipSize;
        this.reloadTime = reloadTime;
        this.projectileSpeed = projectileSpeed;
        this.projectileSize = projectileSize;
        this.spread = spread;
    }

    public double getFireDelay(){
        return this.fireDelay;
    }
    public double getClipSize(){
        return this.clipSize;
    }
    public double getReloadTime(){
        return this.reloadTime;
    }
    public double getProjectileSpeed(){
        return this.projectileSpeed;
    }
    public double getProjectileSize(){
        return this.projectileSize;
    }
    public double getSpread(){
        return this.spread;
    }
    

    
}
