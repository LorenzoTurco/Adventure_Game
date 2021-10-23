public class Fighter extends Character
{
    
    private Weapon thisWeapon = new Sword();
    private int damageAbsorbtion;

    public Fighter()
    {
   
       super("Fighter", "All sources of healing are increased from level 5+ but decreased from level 4-. | % of damage taken is also converted into hp scaling with player level.", 50);
       
    }
    
    public void setDamageAbsorbtion(int newDamageAbsorbtion){
        
        damageAbsorbtion = newDamageAbsorbtion;
        
    }
    
    public int getDamageAbsorbtion(){
        
        return damageAbsorbtion;
    }
    
    @Override
    public void setHp(int hpLost){
        
    //If player is below level 4, all healing is reduced and damage received in increased
    //if player is above level 5, all healing is increased and all damage received is reduced
    if(this.getLevel()==1){
        
        setDamageAbsorbtion(-3);
        super.hp = super.hp + (hpLost+getDamageAbsorbtion());
          
    }else if(this.getLevel()==2){
        
        setDamageAbsorbtion(-2);
        super.hp = super.hp + (hpLost+getDamageAbsorbtion());
        
    }else if(this.getLevel()==3){
        
        setDamageAbsorbtion(-1);
        super.hp = super.hp + (hpLost+getDamageAbsorbtion());
    }else if(this.getLevel()==4){
        
        setDamageAbsorbtion(0);
        super.hp = super.hp + (hpLost+getDamageAbsorbtion());
    }else if(this.getLevel()==5){
        
        setDamageAbsorbtion(1);
        super.hp = super.hp + (hpLost+getDamageAbsorbtion());
       
    }else if(this.getLevel()==6){
        
        setDamageAbsorbtion(1);
        super.hp = super.hp + (hpLost+getDamageAbsorbtion());
       
    }else if(this.getLevel()==7){
        
        setDamageAbsorbtion(2);
        super.hp = super.hp + (hpLost+getDamageAbsorbtion());
       
    }else if(this.getLevel()==8){
        
        setDamageAbsorbtion(2);
        super.hp = super.hp + (hpLost+getDamageAbsorbtion());
       
    }else if(this.getLevel()==9){
        
        setDamageAbsorbtion(3);
        super.hp = super.hp + (hpLost+getDamageAbsorbtion());
       
    }else if(this.getLevel()==10){
        
        setDamageAbsorbtion(3);
        super.hp = super.hp + (hpLost+getDamageAbsorbtion());
       
    }else{
        super.hp = super.hp + hpLost;
    }
    
}
}