public class Enemy
{
    private String name;
    int hp;
    int damage;
    int xp;

    public Enemy(String namee, int hpp, int damagee, int xpp){
        
        name = namee;
        hp = hpp;
        damage = damagee;
        xp = xpp;
        
    }
    
    public int getArmour(){
        
        littleWarrior x = new littleWarrior();
        return x.getArmour();
        
    }
        
    public String getName(){
        
        return name;
        
    }
    
    public int getHp(){
        
        return hp;
        
    }
    
    public void setHp(int damageReceived){
        
        hp = hp - damageReceived;
        
    }
    
    public int getDamage(){
        
        return damage;
        
    }
    
    public int getXp(){
        
        return xp;
        
    }
    
    
}
