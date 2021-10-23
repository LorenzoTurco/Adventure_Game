import java.util.*;

public class Mushroom extends Items
{
    
    private int healValue = getHeal();
    private int xp = 2;

    public Mushroom()
    {
        super("mushroom");

    }


    public int getHeal()
    {
        //Mushroom will return a healing value between -6 and 6
        
        Random rand = new Random();
        int healValue = rand.nextInt(6 + 6) -6;
        
        return healValue;

    }
    
    public int getXp(){
        
        return xp;
    }
}
