import java.awt.Color;

public class Projectile extends Token{
    public Projectile(int xPos,int yPos){
        this.color = Color.YELLOW;
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = 5;
        this.moveSpeed = 0.5;
    }

    @Override
    protected void postStep(){
        if (xPos < 10 || xPos > 1570 || yPos < 10 || yPos > 845){
            Main.tokens.remove(this);
        }
    }

}
