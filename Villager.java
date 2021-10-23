public class Villager extends Character
{
    
    private Weapon thisWeapon = new Sword();
    private boolean secondLife = true;

    public Villager()
    {
   
     super("Villager", "Villagers will heal in critical times. Healing value scales with level. Only 1 use per game.",30);
       
    }
    
    public boolean getSecondLife(){
        
        return secondLife;
        
    }
    
    public void setSecondLife(boolean x){
        
        secondLife = x;
        
    }
    
    @Override
    public void setHp(int hpLost){ 
        //When player is below 10hp and below level 5, he heals by 10hp
        //When player is below 10hp and above level 4, he heals by 20hp
        //1 use per game
        
        int checkHp =hp-hpLost;

        if(checkHp<10 && secondLife == true){
            
            if(this.getLevel()<5){ 
                
          super.hp = super.hp + 10;
          secondLife = false;
          
        }else if(this.getLevel()>4){ 
          super.hp = super.hp + 20;
          secondLife = false;
        }
        
        }else{
            
            super.hp = super.hp + hpLost;
            
        }
        

    }
}
