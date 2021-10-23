import java.util.*;
public class Archer extends Enemy
{
    
    private int criticalHit = damage*4;
    private int headshot;
    
    public Archer()
    {
        
        super("Archer", 5, 7, 8);

    }
    
        public void setCriticalHit(int crit){
        
        criticalHit = crit;
        
    }
    
    public int getCriticalHit(){
        
        return criticalHit;
        
    }
    
    @Override
    public int getDamage(){
        
        Random rand = new Random();
        headshot = rand.nextInt(10)+1;
        
        int damageToReturn;
        
        if (headshot ==10){ //10%chance of landing critical hit
        
         
        damageToReturn = criticalHit;
        
    } else{
     
        damageToReturn = damage;
        
    }
        return damageToReturn;
    }


   

}
