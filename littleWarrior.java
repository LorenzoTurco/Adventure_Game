public class littleWarrior extends Enemy
{
    
    private int armour = 2;
    
    public littleWarrior()
    {
        
        super("Little Warrrior", 8, 5, 10);

    }
    
    public void setArmour(int armourr){
        
        armour = armourr;
        
    }
    
    public int getArmour(){
        
        return armour;
        
    }
    
    @Override
    public void setHp(int damageReceived){
        
        if(damageReceived>2){ //if the player inflicted at least 3hp
        
        hp = hp - (damageReceived-armour); //little warriors takes (armour) less damage
    }
    else{
        
        hp = hp - damageReceived;
        
    }
    
    }
    


}
