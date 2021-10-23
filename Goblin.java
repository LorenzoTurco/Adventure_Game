import java.util.*; 

public class Goblin extends Enemy
{
    
    private int gold = 3;
       
    public Goblin()
    {
        
        super("Goblin", 6, 5, 5);

    }
    
    @Override
    public int getXp(){
        
        Random rand = new Random();
        int extraXp = rand.nextInt(3)+1;
        
        return xp + extraXp;
        
    }
    
    public void setGold(int additionalCoins){
        
        gold = additionalCoins;
        
    }
    
    public int getGold(){
        
        return gold;
        
    }

}
    
    

   

