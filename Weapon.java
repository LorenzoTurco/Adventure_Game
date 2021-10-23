
public class Weapon
{
    
   String name;
   int damage;
    
   public Weapon(String namee, int damagee){
    
       name = namee;
       damage = damagee;

    }
    
   public String getWeaponName(){

    return name;
    
    }
    
   public void setWeaponName(String namee){

    name = namee;
    
    }
    
   public int getDamage(){

    return damage;
    
    }
    
   public void setWeaponDamage(int damagee){

    damage = damagee ;
    
    }

}