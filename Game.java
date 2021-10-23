import java.util.*;
import java.io.*;
public class Game
{
    ArrayList<Items> inventoryItems = new ArrayList<Items>();
    main1 thisMain;
    gui thisGui;
    Character thisChar;
    int xp = 0;
    Enemy thisEnemy;
    boolean kitchenFruit = true;
    
    public Game(main1 main, gui guii)
    {
       thisMain = main;    
       thisGui = guii; 
    }
    //Finds a place in the inventory without an item
    public int findItemInInventory(Items itemYouAreLookingFor){
        
        int indexOfItem = 9;
        
        for(int i = 0; i<3; i++){
            
            if (inventoryItems.get(i).getName().equals(itemYouAreLookingFor.getName())){
                
                indexOfItem = i;
                break;
            
            }
        
        }
        
        return indexOfItem;
    }
    
    public void buyBow(){
                
        //player must have at least 40xp to purchase bow
        if(xp>39 & !thisChar.getWeapon().equals("bow")){
            
            xp = xp - 40;
            checkIfLevelUp(); //check if the new xp results in a change in player level
            thisChar.setWeapon(new Bow()); //make bow the player weapon
            thisGui.weapon.setText(thisChar.getWeapon());
            updateLog("purchased " + thisChar.getWeapon() +" damage " + new Bow().getDamage());
            thisGui.newScenario.setText("Here is your bow!");
            
        }
        else{
            
            thisGui.newScenario.setText("You don't have enough XP or your inventory is full. Come back another day");
            
        }

        changeButtonText("Look back at the item selection","Leave","--","--");
        
        ChangeAreaPositions("village","outside", "--", "--");
        
        
    }
    

    public void loadGame(){
        
        ArrayList<String> charInfo = new ArrayList<String>();
        
        try{
        
         File myObj = new File("SaveStateFile.txt");
         BufferedReader br = new BufferedReader(new FileReader(myObj)); 
          /*while(myReader.hasNextLine()){ //while not EOF

            charInfo.add(myReader.next()); //add line to array
            
          }*/
          
          String originalChar =br.readLine();
          
          if(originalChar.equals("Wizard")){
              thisChar = new Wizard();
            }else if(originalChar.equals("Fighter")){
              thisChar = new Fighter();
            }else if(originalChar.equals("Villager")){
              thisChar = new Villager();
            }
  
        thisChar.setHp(Integer.parseInt(br.readLine())-thisChar.getMaxHp());

           if(br.readLine().equals(new Sword().getWeaponName())){
                
                thisChar.setWeapon(new Sword());
                
           }else{
                
               thisChar.setWeapon(new Bow());
           }
            
            thisChar.setLevel(Integer.parseInt(br.readLine()));
            xp = Integer.parseInt(br.readLine());
            checkIfLevelUp();
            

            String itemText1 =br.readLine();
            String itemText2 =br.readLine();
            String itemText3 =br.readLine();
            
            System.out.println(itemText1);
            System.out.println(itemText2);
            System.out.println(itemText3);
                        

            if(itemText1.equals(new Apple().getName())){
                 inventoryItems.add(0,thisGui.thisApple);
                 thisGui.item1.setText("apple");
            }else if(itemText1.equals(new Mushroom().getName())){
                 inventoryItems.add(0,thisGui.thisMushroom);
                 thisGui.item1.setText("mushroom");
            }else {
                 inventoryItems.add(0,thisGui.thisFreeSlot);
                 thisGui.item1.setText("");
                }
                
            if(itemText2.equals(new Apple().getName())){
                 inventoryItems.add(1,thisGui.thisApple);
                 thisGui.item2.setText("apple");
            }else if(itemText2.equals(new Mushroom().getName())){
                 inventoryItems.add(1, thisGui.thisMushroom);
                 thisGui.item2.setText("mushroom");
            }else{
                 inventoryItems.add(1,thisGui.thisFreeSlot);
                 thisGui.item2.setText("");
                }
                
                
                
            if(itemText3.equals(new Apple().getName())){
                 inventoryItems.add(2, thisGui.thisApple);
                 thisGui.item3.setText("apple");
            }else if(itemText3.equals(new Mushroom().getName())){
                 inventoryItems.add(2,thisGui.thisMushroom);
                 thisGui.item3.setText("mushroom");
            }else{
                 inventoryItems.add(2,thisGui.thisFreeSlot);
                 thisGui.item3.setText("");
                }
            
            thisGui.hp.setText("HP: "+ thisChar.getHp());
            thisGui.weapon.setText(thisChar.getWeapon());
            thisGui.level.setText("Lvl: "+ thisChar.getLevel());
                    
        }catch(IOException e){
        
            IOExceptionErrorLoad();
            e.printStackTrace();
            
       }
        
       thisGui.historyTa.setText("Event Log");
       home();
        
    }
    
    public void IOExceptionErrorLoad(){
        System.out.println("Game could not be loaded because the File is not found");
    }
    
    public void IOExceptionErrorSave(){
        System.out.println("Game could not be saved because the File is not found");
    }
        
    
    public void saveGame(){

        try{
       
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("SaveStateFile.txt"));
        
        //Saved: character Name, Hp, weapon, level, xp, all the items
        bw.write((thisChar.getName()));
        bw.newLine();
        bw.write(Integer.toString(thisChar.getHp()));
        bw.newLine();
        bw.write(thisChar.getWeapon());
        bw.newLine();
        bw.write(Integer.toString(thisChar.getLevel()));
        bw.newLine();
        bw.write(Integer.toString(xp));
        bw.newLine();
        
        bw.write(thisGui.item1.getText());
        bw.newLine();
        bw.write(thisGui.item2.getText());
        bw.newLine();
        bw.write(thisGui.item3.getText());
        bw.close();
       }catch(Exception e){
            
            IOExceptionErrorSave();
            e.printStackTrace();
       }
        
        
    }
    
    
    public void reset(){ //resets the game values
        
        thisChar = thisGui.thisCharacter;
        thisChar.resetLevel();
   
        
        
       thisGui.hp.setText("HP: "+ thisChar.getMaxHp());
       thisGui.weapon.setText(thisChar.getWeapon());
       thisGui.level.setText("Lvl: 1");
       
       thisGui.historyTa.setText("Event Log");
       inventoryItems.add(0, thisGui.thisFreeSlot);
       inventoryItems.add(1, thisGui.thisFreeSlot);
       inventoryItems.add(2, thisGui.thisFreeSlot);
       home();
    }
    
    public void home(){ //first scenario
        
        thisGui.newScenario.setText("You are in a house. What do you do?");

        ChangeAreaPositions("checkWindow","checkTv", "checkKitchen", "outside");
        changeButtonText("Look outside","Turn the TV on","Check the kitchen","leave");
    }
    
    public void checkTv(){
            
        thisGui.newScenario.setText("BREAKING NEWS \n A powerfull warrior has invaded EastPlain city \n Someone must stop him! ");
       
        changeButtonText("Go back","--","--","--");
        ChangeAreaPositions("home","--", "--", "--");
    }

    public void checkKitchen(){
        
        int freeSpace = findFreeItemSpace(); //find index in inventory array where there is space
        
        if(kitchenFruit == true & freeSpace<4){ //if apple has not been collected already and the player has space in the inventory:
            
              thisGui.newScenario.setText("You are in the kitchen. \nYou have found an apple");
              
              inventoryItems.set(freeSpace, thisGui.thisApple);
              updateItemsText(freeSpace, inventoryItems.get(freeSpace));
              kitchenFruit = false;
            
        }else{
            
              thisGui.newScenario.setText("Nothing is there.");

        }
        

        changeButtonText("Go back","--","--","--");
        ChangeAreaPositions("home","--", "--", "--");
        
        
    }
    
    public void outside(){
        thisGui.newScenario.setText("You are ouside your house.");
        
        changeButtonText("Go West","Go North","Go East","Go back");
        ChangeAreaPositions("village","forest", "Eastplain", "home");
    }
    
    public void village(){
        
       thisGui.newScenario.setText("You have entered the local village \n A food merchant is selling: \n Apple for 2xp \n Mushroom for 1xp");
       
        changeButtonText("Go to to fruit market","Go to armery","Sell","Leave");
        ChangeAreaPositions("buyFood","buyWeapons", "sell", "outside");
    }
    
    public void buyWeapon(){
        
       thisGui.newScenario.setText("Hello Stranger! I am selling: \nBow for 40xp");

       
       changeButtonText("Buy bow","Look at other items","Leave","--");
       
       ChangeAreaPositions("buyBow","village", "outside", "");
        
    }
   
    
    public void buyFood(){
        
        thisGui.newScenario.setText("Hello Stranger! I am selling: \nApple for 2xp \nMushroom for 1xp");
        
        changeButtonText("Buy " + thisGui.thisMushroom.getName(),"Buy " + thisGui.thisApple.getName(),"Look other items","Leave");
        
        ChangeAreaPositions("buyMushroom","buyApple", "village", "outside");
        
    }
    
    public void sell(){
        
        thisGui.newScenario.setText("Hey,this is what I am looking to buy: \n Apple for 5xp \n Mushroom for 2xp");
        
        changeButtonText("Sell " + thisGui.thisApple.getName(),"Sell " + thisGui.thisMushroom.getName(),"--","Go back");
        
        ChangeAreaPositions("sellApple","sellMushroom", "--", "village");
        
    }
    
    public void sellApple(){
     
        int thisPosition = findItemInInventory(thisGui.thisApple);
        
        if(thisPosition!=9){ 
            
            inventoryItems.set(thisPosition, new FreeSlot());
            updateItemsText(thisPosition, inventoryItems.get(thisPosition));
            xp = xp + thisGui.thisApple.getXp();
            checkIfLevelUp();
            updateLog(thisGui.thisApple.getName() + " sold" + " xp gained: "+ thisGui.thisApple.getXp());
        }else{
            
            thisGui.newScenario.setText("You cannot sell an item you don't have.");
        }
        
        ChangeAreaPositions("sellApple","sell", "--", "village");
        }
        
        public void sellMushroom(){
     
        int thisPosition = findItemInInventory(thisGui.thisMushroom);
        
        if(thisPosition!=9){
            
            inventoryItems.set(thisPosition, new FreeSlot());
            updateItemsText(thisPosition, inventoryItems.get(thisPosition));
            xp = xp + thisGui.thisMushroom.getXp();
            checkIfLevelUp();
            updateLog(thisGui.thisMushroom.getName() + " sold" + " xp gained: "+ thisGui.thisMushroom.getXp());

            
        }else{
            
            thisGui.newScenario.setText("You cannot sell an item you don't have.");
        }
        
        changeButtonText("Sell again","Sell different item","--","Go back");

        ChangeAreaPositions("sellMushroom","sell", "--", "village");
            
        }

    public void buyApple(){
        
        int freeSpace = findFreeItemSpace();
        
        if(xp>19 & findItemInInventory(new FreeSlot())!=9){
            
            xp = xp - 20;
            checkIfLevelUp();
            inventoryItems.set(freeSpace, thisGui.thisApple);
            updateItemsText(freeSpace, inventoryItems.get(freeSpace));
            updateLog("purchased " + inventoryItems.get(freeSpace).getName());
            thisGui.newScenario.setText("Here is your apple!");
            
        }
        else{
            
            thisGui.newScenario.setText("You don't have enough XP or your inventory is full. Come back another day");
            
        }

        changeButtonText("Buy more","Look again at the item selection","Sell","Leave");
        
        ChangeAreaPositions("buyApple","village", "sell", "outside");
    }
    
    public void buyMushroom(){
        
        int freeSpace = findFreeItemSpace();
        
        if(xp>9 & findItemInInventory(new FreeSlot())!=9){
            
            xp = xp - 10;
            checkIfLevelUp();
            inventoryItems.set(freeSpace, thisGui.thisMushroom);
            updateItemsText(freeSpace, inventoryItems.get(freeSpace));
            updateLog("purchased " + inventoryItems.get(freeSpace).getName());
            thisGui.newScenario.setText("Here is your mushroom!");
            
        }
        else{
            
            thisGui.newScenario.setText("You don't have enough XP or your inventory is full. Come back another day");
            
        }
        
        changeButtonText("Buy more","Look again at the item selection","Sell","Leave");
        
        ChangeAreaPositions("buyMushroom","village", "sell", "outside");
        
    }
    
    public void forest(){
                  
        thisGui.newScenario.setText(" You walked into a forest");
        
        changeButtonText("Look around","Go back","--","--");

        ChangeAreaPositions("lookAroundForest","outside", "--", "--");
        
    }
    
    public void lookAroundForest(){
        
        Random rand = new Random();
        int probability = rand.nextInt(10)+1;
        
        int freeSpace = findFreeItemSpace();
        
        switch(probability){
            
            case 1: thisEnemy = new Goblin(); break;
            case 2: thisEnemy = new Goblin();break;
            case 3: thisEnemy = new Goblin();break;
            case 4: thisEnemy = new Goblin();break;
            case 5: thisEnemy = new Archer();break;
            case 6: thisEnemy = new Archer();break;
            case 7: thisEnemy = new Archer();break;
            case 8: 
                    if(freeSpace!=404){
                    inventoryItems.set(freeSpace, thisGui.thisMushroom);
                    updateItemsText(freeSpace, inventoryItems.get(freeSpace));
                }else{
                    thisGui.newScenario.setText("You found nothing.");
                }
                break;
            case 9:
                    if(freeSpace!=404){
                    inventoryItems.set(freeSpace,thisGui.thisMushroom);
                    updateItemsText(freeSpace, inventoryItems.get(freeSpace));
                }else{
                    thisGui.newScenario.setText("You found nothing.");
                }
                break;
            case 10: forestStranger(); break;
        }
        
        if(probability<8){
            thisGui.newScenario.setText(" You encountered a " + thisEnemy.getName());

            changeButtonText("Fight","Run","--","--");

            ChangeAreaPositions("fight","forest", "--", "--");
        
        } else{
            
            changeButtonText("Go back","--","--","--");

            ChangeAreaPositions("forest","--", "--", "--");
        
        
     }
   }
    
    public void Eastplain(){
        
        thisGui.newScenario.setText("You arrived at Eastplane city");

        changeButtonText("Look around","Talk to stranger","look for invader","Go back");
        ChangeAreaPositions("lookAroundEastplain","EastplainStranger", "cityCentre", "outside");
    }
    
    public void cityCentre(){
        
        thisGui.newScenario.setText("You arrived at Eastplane city centre \n The warriors leader is standing in the middle.");

        changeButtonText("Prepare attack","Go back","--","--");
        ChangeAreaPositions("bossFight","Eastplain", "--", "--");
    }
    
    public void bossFight(){
     
        if(thisChar.getLevel() > 4){
            
            thisEnemy = new FinalBoss();

            changeButtonText("Attack","Go back","--","--");
            ChangeAreaPositions("fight","cityCentre", "--", "--");
            
        }
        
        else{
            
        thisGui.newScenario.setText("You are too weak! You can't stand a chance. \nYou must be at least level 5 to fight him.");
            
        }
        
    }
    
    public void lookAroundEastplain(){
        
        Random rand = new Random();
        int probability = rand.nextInt(5)+1;
        
        int freeSpace = findFreeItemSpace();
        
        switch(probability){
            
            case 1: thisEnemy = new littleWarrior(); break;
            case 2: thisEnemy = new littleWarrior();break;
            case 3: thisEnemy = new littleWarrior();break;
            case 4: thisEnemy = new littleWarrior();break;
            case 5: 
                if(freeSpace!=404){
                    inventoryItems.set(freeSpace,thisGui.thisApple);
                    updateItemsText(freeSpace, inventoryItems.get(freeSpace));
                }else{
                    thisGui.newScenario.setText("You found nothing.");
                        }
                break;

        }
        
        if(probability<5){
            thisGui.newScenario.setText(" You encountered a " + thisEnemy.getName());

            changeButtonText("Fight","Run","--","--");

            ChangeAreaPositions("fight","Eastplain", "", "");
        
        } else{

            changeButtonText("Go back","--","--","--");
        
            ChangeAreaPositions("Eastplain","", "", "");
        
     }
    }
    
    public void EastplainStranger(){
        
        Random rand = new Random();
        int probability = rand.nextInt(3)+1;
        
        switch(probability){ //1/3 chance of finding each stranger
        case 1: thisGui.newScenario.setText("Stranger: Help! these warriors are stealing our our goods"); break;
        case 2: thisGui.newScenario.setText("Stranger: these warriors have ruined all my crops"); break;
        case 3: thisGui.newScenario.setText("Stranger: Somebody save Eastplain!"); break;
       }

        changeButtonText("Go back","--","--","--");
        
        ChangeAreaPositions("Eastplain","", "", "");
        
    }
    
    //METHOD TO USE AN ITEM IN INVENTORY
    public void itemUsed(int itemNumber, String ButtonToChange){
        
        thisChar.setHp(inventoryItems.get(itemNumber).getHeal());
        thisGui.hp.setText("HP: " + thisChar.getHp());
        inventoryItems.set(itemNumber, thisGui.thisFreeSlot);
        inventoryItems.set(0, thisGui.thisFreeSlot);
        
        
     if(ButtonToChange.equals("item1")){
            
        thisGui.item1.setText(""); //remove text from GUI
        
    } else if(ButtonToChange.equals("item2")){
        
        thisGui.item2.setText("");
    } else if(ButtonToChange.equals("item3")){
        
        thisGui.item3.setText("");
    }
        
    }
    
    public int findFreeItemSpace(){
         
        int freeSlot = 404;
        
        
        if(inventoryItems.get(0).getName().equals( thisGui.thisFreeSlot.getName())){
            
            freeSlot = 0;
        } else if(inventoryItems.get(1).getName().equals( thisGui.thisFreeSlot.getName())){
            
            freeSlot = 1;
        } else if(inventoryItems.get(2).getName().equals( thisGui.thisFreeSlot.getName())){
            
            freeSlot = 2;
        }

        return freeSlot;
    }
    
    
    public void updateItemsText(int freeSpace, Items thisItem){
        
            inventoryItems.set(freeSpace, thisItem);
            
            if(freeSpace == 0){
                
                thisGui.item1.setText(thisItem.getName()); //add name of the item to inventory panel GUI
                
            } else if(freeSpace == 1){
                
                thisGui.item2.setText(thisItem.getName());
                
            } else if(freeSpace == 2){
                
                thisGui.item3.setText(thisItem.getName());
                
            }
        
        
    }
    
    public void checkWindow(){
         
        
        thisGui.newScenario.setText("You see a small village westwards and a stranger running hastily into a forest");

        changeButtonText("Go back", "--", "--", "--");
        
        ChangeAreaPositions("home","", "", "");
         
    }
    
    
    public void forestStranger(){
        
        thisGui.newScenario.setText("You have found a stranger. \nStranger: Hey, thank you for saving me. \nTake this XP and go buy youself a better weapon to defeat the warrior at EastPlains once and for all!");
        xp = xp + 40;
        checkIfLevelUp();

    }
    
    public void fight(){

        thisGui.newScenario.setText(thisEnemy.getName() + " " + thisEnemy.getHp());

        changeButtonText("Attack","--","--","--");
        
        ChangeAreaPositions("playerTurn","--", "--", "--");
        
    }
    
    public void playerTurn(){
                         
        Random rand = new Random();
        //get a random value from the damage the player weapon can deal
        int damageDealt = rand.nextInt(thisChar.getWeaponDamage()+1);
        
        thisGui.newScenario.setText("you dealt " + damageDealt + " damage to " + thisEnemy.getName());
        updateLog("you dealt " + damageDealt + " HP");
        thisEnemy.setHp(damageDealt);
        
        changeButtonText("next","--","--","--");
        
        if(thisEnemy.getHp() > 0){ //if enemy is still alive
        
        ChangeAreaPositions("enemyTurn","--", "--", "--");
        
    } else if(thisEnemy.getHp()<1){ //if enemy died
        
        ChangeAreaPositions("win","--", "--", "--");
    }
    
    }
    
    public void enemyTurn(){
        
        Random rand = new Random();
        int enemyDamage = rand.nextInt(thisEnemy.getDamage());
        if(enemyDamage>8){
            updateLog("You got headshot!");
        }
        thisGui.newScenario.setText("You received " + enemyDamage + " damage");
        updateLog("you lost " + enemyDamage + " HP");

        thisChar.setHp(0-enemyDamage);
        
        thisGui.hp.setText("HP: " + thisChar.getHp());
        
        changeButtonText("next","--","--","--");
        
        if(thisChar.getHp()>0){

        ChangeAreaPositions("fight","--", "--", "--");
        } else{

        ChangeAreaPositions("lose","--", "--", "--");
        }
        
        
    }
    
     public void checkIfLevelUp(Enemy thisEnemy){ //use when update xp from beating enemy
        
        int levelYouStartedWith = thisChar.getLevel();
         
        xp = xp + thisEnemy.getXp();
        
     if(xp<100){
            
            
            if (xp>19 & xp<30){
                thisChar.setLevel(2);                
            }else if (xp>29 & xp<40){
                thisChar.setLevel(3);                
            }else if (xp>39 & xp<50){
                thisChar.setLevel(4);                
            }else if (xp>49 & xp<60){
                thisChar.setLevel(5);                
            }else if (xp>59 & xp<70){
                thisChar.setLevel(6);               
            }else if (xp>69 & xp<80){
                thisChar.setLevel(7);               
            }else if (xp>79 & xp<90){
                thisChar.setLevel(8);
            }else if (xp>89 & xp<100){
                thisChar.setLevel(9);
        }
        thisGui.level.setText("Lvl: " + thisChar.getLevel());

     }else if (xp>99){
            thisChar.setLevel(10);
            thisGui.level.setText("Lvl: " + thisChar.getLevel() + " MAX");
     }
    
     if(thisChar.getLevel()>levelYouStartedWith){
         
         updateLog("LEVEL UP: " + thisChar.getLevel());
         
        }
           
        
  
}
  public void checkIfLevelUp(){ //use just to update xp
        
        int levelYouStartedWith = thisChar.getLevel();
                 
     if (xp<100){
         
            if (xp>-1 & xp<20){
                thisChar.setLevel(1);

            }else if (xp>19 & xp<30){
                thisChar.setLevel(2);                
            }else if (xp>29 & xp<40){
                thisChar.setLevel(3);                
            }else if (xp>39 & xp<50){
                thisChar.setLevel(4);                
            }else if (xp>49 & xp<60){
                thisChar.setLevel(5);                
            }else if (xp>59 & xp<70){
                thisChar.setLevel(6);               
            }else if (xp>69 & xp<80){
                thisChar.setLevel(7);               
            }else if (xp>79 & xp<90){
                thisChar.setLevel(8);
            }else if (xp>89 & xp<100){
                thisChar.setLevel(9);
        }
        
        thisGui.level.setText("Lvl: " + thisChar.getLevel());
    }else if (xp>99){
            thisChar.setLevel(10);
            thisGui.level.setText("Lvl: " + thisChar.getLevel() + " MAX");
     }
    
     if(thisChar.getLevel()>levelYouStartedWith){
         
         updateLog("LEVEL UP: " + thisChar.getLevel());
         
        }
           
    
  }
    
    
    public void win(){
        
        if(thisEnemy.getName().equals("Archer")) {
        
        thisGui.newScenario.setText("You've defeated the " + thisEnemy.getName());
        checkIfLevelUp(thisEnemy);
        updateLog("xp gained: " + thisEnemy.getXp());
        
        
        changeButtonText("Go back","--","--","--");

        ChangeAreaPositions("forest","--", "--", "--");
        
        
    }else if(thisEnemy.getName().equals("Goblin")){
        
        thisGui.newScenario.setText("You've defeated the " + thisEnemy.getName());
        checkIfLevelUp(thisEnemy);
        updateLog("xp gained: " + thisEnemy.getXp()  + " ("+(thisEnemy.getXp()-5)+ " extra xp)");
        
        changeButtonText("Go back","--","--","--");
        
        ChangeAreaPositions("forest","--", "--", "--");
        
    }else if(thisEnemy.getName().equals("Little Warrrior")){
        
        thisGui.newScenario.setText("You've defeated the " + thisEnemy.getName());
        checkIfLevelUp(thisEnemy);
        updateLog("exp gained: " + thisEnemy.getXp());
        

        changeButtonText("Go back","--","--","--");

        ChangeAreaPositions("Eastplain","--", "--", "--");
        
    }else if(thisEnemy.getName().equals("Big Warrior")){
        
        thisGui.newScenario.setText("You've defeated the " + thisEnemy.getName() + " the enemy dropped a silver ring");
        checkIfLevelUp(thisEnemy);
        updateLog("exp gained: " + thisEnemy.getXp());
        

        changeButtonText("Go back","--","--","--");
        
        ChangeAreaPositions("ending","--", "--", "--");
        
    }
        
    }
        
    public void lose(){
        
        thisGui.newScenario.setText("You are dead"); 
                
        changeButtonText("CLOSE GAME","--","--","--");

        ChangeAreaPositions("End","--", "--", "--");
        
    }
    
    public void ending(){
        
        thisGui.newScenario.setText("You have killed the leader of the warriors and liberated Eastplain!");
        
      
        changeButtonText("CLOSE GAME","--","--","--");

        ChangeAreaPositions("End","--", "--", "--");
        
    }
    
    public void End(){
     
        System.exit(0);
        
    }
    
    public void updateLog(String text){
        
        thisGui.historyTa.append("\n" + text);
    }
    
        public void ChangeAreaPositions(String thisArea11,String thisArea22,String thisArea33,String thisArea44){
        
        thisMain.rooms[0] = thisArea11;
        thisMain.rooms[1] = thisArea22;
        thisMain.rooms[2] = thisArea33;
        thisMain.rooms[3] = thisArea44;
       
    }
    
    public void changeButtonText(String option1,String option2,String option3,String option4){
        
        thisGui.Option1.setText(option1);
        thisGui.Option2.setText(option2);
        thisGui.Option3.setText(option3);
        thisGui.Option4.setText(option4);
        
    }
    
    public void room(String thisRoom){
        
        switch(thisRoom){
                    
        case "home" : home(); break;
        case "checkWindow": checkWindow(); break;
        case "checkKitchen": checkKitchen(); break;
        case "checkTv": checkTv(); break;
        case "outside": outside(); break;
        case "village": village(); break;
        case "buyWeapons": buyWeapon(); break;
        case "buyBow": buyBow(); break;
        case "buyFood": buyFood(); break;
        case "forest": forest(); break;
        case "lookAroundForest": lookAroundForest(); break;
        case "Eastplain": Eastplain(); break;
        case "lookAroundEastplain": lookAroundEastplain(); break;
        case "EastplainStranger" : EastplainStranger(); break;
        case "cityCentre": cityCentre(); break;
        case "fight" : fight(); break;
        case "bossFight" : bossFight(); break;
        case "playerTurn": playerTurn(); break;
        case "enemyTurn" : enemyTurn(); break;
        case "win" : win(); break;
        case "lose" : lose(); break;
        case "ending" : ending(); break;
        case "End" : End(); break;
        case "buyMushroom" : buyMushroom(); break;
        case "buyApple" : buyApple(); break;
        case "sell" : sell(); break; 
        case "sellApple" : sellApple(); break;
        case "sellMushroom" : sellMushroom(); break;
       }
    }

}
