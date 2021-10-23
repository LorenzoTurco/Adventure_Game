
public class Items
{
    
    String name;
    int hpRecover;
   
    public Items(String namee, int healingValue)
    {
        name =namee;
        hpRecover = healingValue;
        
    }
    
    //constructor for items without a healing value
    public Items(String namee)
    {
        name =namee;
       
        
    }

    
    public String getName()
    {

        return name;
    }
    
    public int getHeal(){
        
        return hpRecover;
        
    }
    
   
}
