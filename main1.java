import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class main1{
    

    
    gui thisGui = new gui();
    Game thisGame = new Game(this, thisGui);
    buttonPressed thisButtonPressed = new buttonPressed(this);
    String[] rooms = new String[4]; 

    public static void main(String[] args){
     
        
     new main1();
     
        
    }
    
    public main1()
    {
        
        thisGui.firstWindow(thisButtonPressed);
        showSelectionScreen();
        
    }
    
    public void showSelectionScreen(){
       
        thisGui.startScreen.setVisible(true);
        thisGui.charSelectionPanel.setVisible(true);
        thisGui.startButtonPanel.setVisible(true);
        thisGui.mainEventsPanel.setVisible(false);
        thisGui.playerActionsPanel.setVisible(false);
        thisGui.historyPanel.setVisible(false);
        thisGui.playerInfoPanel.setVisible(false);
        
    }
}

