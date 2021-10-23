import java.util.*;

public class Wizard extends Character
{
    
    private Weapon thisWeapon = new Sword();
    private int criticalHit;
    private int multiplier = 1;

    public Wizard()
    {
   
       super("Wizard", "Player can land critical hits dealing additional damage. | % damage increase and frequency of critical hits scales with player level.", 25);
       
    }
    
    public int getMultiplier(){
        return multiplier;
    }
    
    public void setMultiplier(int multiplierr){
        multiplier = multiplierr;
    }
    
    public int getCriticalHit(){
        
       return criticalHit;

    }
    
    public void setCriticalHit(int multiplierr){
        
       criticalHit = thisWeapon.getDamage() * multiplier;

    }
    
   @Override
     public int getWeaponDamage(){
         
        Random rand = new Random();
        int headshot;
        int damageToReturn = 0;
        
    //If player level is less than 5. Critical hits deal 100% more damage and have a 10% likelihood of occurring
    //If player is level 5+ critical hits have 20% likelyhood and deal 200% more damage.
        
    if(this.getLevel()<5){  
        
        setCriticalHit(2);
        headshot = rand.nextInt(10)+1;
        if (headshot >9){ //10% crit chance
        
        damageToReturn = super.getWeaponDamage() * getCriticalHit(); 
       }else{
           damageToReturn = super.getWeaponDamage();
        }
     }else{
         
         setCriticalHit(3);
         headshot = rand.nextInt(10)+1;
        if (headshot >8){ //20% crit chance
        
        damageToReturn = super.getWeaponDamage() * getCriticalHit(); 
       }else{
           damageToReturn = super.getWeaponDamage();
        }
        
   }
   
       return damageToReturn;
    
   }
}