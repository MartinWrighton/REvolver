
public class Creature extends Token{
    //this is a creature, that can live and die
    protected int maxHP;
    protected int HP;
    
    public Creature(){
        super();
        this.size = 10;
    }

    protected void takeDamage(){}
    protected void die(){}
    
}
