import java.awt.event.*;

public class buttonPressed implements ActionListener{

 main1 mainGame;
 
 public buttonPressed(main1 mainGamee) {
  
  mainGame = mainGamee;
}
 
 public void actionPerformed(ActionEvent event){
  
  String yourChoice = event.getActionCommand();

  switch(yourChoice){
      
  case "Start": 
  
        mainGame.thisGui.startScreen.setVisible(false);
        mainGame.thisGui.charSelectionPanel.setVisible(false);
        mainGame.thisGui.startButtonPanel.setVisible(false);
        
        mainGame.thisGui.mainEventsPanel.setVisible(true);
        mainGame.thisGui.playerActionsPanel.setVisible(true);
        mainGame.thisGui.historyPanel.setVisible(true);
        mainGame.thisGui.playerInfoPanel.setVisible(true);
        if(mainGame.thisGui.thisCharacter == null){
            mainGame.thisGui.thisCharacter = new Wizard();
        }
        mainGame.thisGame.reset();
        break;
  
  case "Resume":
  
        mainGame.thisGui.startScreen.setVisible(false);
        mainGame.thisGui.charSelectionPanel.setVisible(false);
        mainGame.thisGui.startButtonPanel.setVisible(false);
        
        mainGame.thisGui.mainEventsPanel.setVisible(true);
        mainGame.thisGui.playerActionsPanel.setVisible(true);
        mainGame.thisGui.historyPanel.setVisible(true);
        mainGame.thisGui.playerInfoPanel.setVisible(true);
        mainGame.thisGame.loadGame();break;
        
  case "save": mainGame.thisGame.saveGame(); break;
  
  case "item1" : mainGame.thisGame.itemUsed(0, "item1"); break;
  case "item2" : mainGame.thisGame.itemUsed(1, "item2"); break;
  case "item3" : mainGame.thisGame.itemUsed(2, "item3"); break;
  
  case "Button1": mainGame.thisGame.room(mainGame.rooms[0]);break;
  case "Button2": mainGame.thisGame.room(mainGame.rooms[1]);break;
  case "Button3": mainGame.thisGame.room(mainGame.rooms[2]);break;
  case "Button4": mainGame.thisGame.room(mainGame.rooms[3]);break;

  }
 }
}
