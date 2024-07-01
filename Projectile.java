import java.awt.Color;

public class Projectile extends Token{
    protected int penetration;
    protected int damage;
    public Projectile(int xPos,int yPos){
        this.color = Color.YELLOW;
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = 5;
        this.moveSpeed = 0.5;
        this.penetration = 1;
        this.damage = 1;
    }

    @Override
    protected void postStep(){
        if (xPos < 10 || xPos > 1570 || yPos < 10 || yPos > 845){
            Main.tokens.remove(this);
        }
    }

    protected void hit(){
        this.penetration--;
        if (this.penetration<0){
            end();
        }
    }
    protected void end(){
        Main.tokens.remove(this);
    }

}
