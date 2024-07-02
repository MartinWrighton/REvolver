
public class SimpleProjectile extends Projectile{
    public SimpleProjectile(){
    }

    @Override
    public SimpleProjectile get() {
        return new SimpleProjectile();
    }

    //has no end() effect because it is simple

}
