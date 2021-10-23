public class Character
{
    
    private String name;
    int hp;
    private int MaxHp;
    private Weapon thisWeapon = new Sword();
    private String lore;
    private int level;


    public Character (String namee, String loree, int hpp)
    {
        
        name = namee;
        lore = loree;
        hp = hpp;
        MaxHp = hpp;
                
    }
    
    public void setName(String namee){
        
         name = namee;
        
    }
    
    public String getName(){
        
        return name;
        
    }
    
    public void resetLevel(){
     
        setLevel(1);
        
    }
    
    public void resetWeapon(){
     
        thisWeapon = new Sword();
        
    }
    
    public int getMaxHp(){
        
        return MaxHp;
        
    }
    
    public void setHp(int hpLost){
        
        hp = hp + hpLost;
        
    }
    
    public void resetHp(){
        
        hp = getMaxHp();
          
    }
    
    public int getHp(){
        
        return hp;
        
    }
    
    public String getLore(){
    
    return lore;
    }
    
    public int getLevel(){
        
        return level;
    }
    
    public void setLevel(int levell){
        
        level = levell;
    }
    
    public String getWeapon(){
        
        return thisWeapon.getWeaponName();
    }
    
    public void setWeapon(Weapon weaponn){
        
        thisWeapon =  weaponn;
    }
    
    public int getWeaponDamage(){
        
        return thisWeapon.getDamage();
    }

}