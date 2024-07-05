import java.awt.Color;

public class Effect extends Token{
    public Effect(Color color, double xPos, double yPos, double moveSpeed, int xSize, int ySize, int xHit, int yHit){
        super(color,xPos,yPos,moveSpeed,xSize,ySize,xHit,yHit);
    }

    @Override
    protected void animationStep(){
        super.animationStep();
        if (this.animationFrame <= 0){
            Main.tokens.remove(this);
        }
    }
}
