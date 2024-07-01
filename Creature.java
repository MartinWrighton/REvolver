
public class Creature extends Token{
    //this is a creature, that can live and die
    protected int maxHP;
    protected int HP;
    
    public Creature(){
        super();
        this.size = 50;
    }

    protected void takeDamage(int damage){
        this.HP-=damage;
        if (this.HP <= 0){
            die();
        }
    }
    protected void die(){}
    
}
