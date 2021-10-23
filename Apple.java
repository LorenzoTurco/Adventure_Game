
public class Apple extends Items
{
    
    private int healHp = 5;
    private int xp = 5;
   
    public Apple()
    {

        super("apple", 5);
        
    }

    
    public int healHp(){
        
        return healHp;
        
    }
    
    public int getXp(){
        
        return xp;
    }

}
