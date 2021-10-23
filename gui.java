import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class gui 

{
    
    JFrame screen = new JFrame();
    Container mainContainer;
    Color mainColor = new Color(250, 218, 221);
    Color colorMainEvent = new Color(253, 171, 159); //salmon
    Character thisCharacter;
    
    
    //Start screen JPanel stuff
    JPanel startScreen;
    JLabel mainTitle;
    
    //Character selection JPanel stuff
    JPanel charSelectionPanel;
    String[] charNames = {"Wizard", "Villager", "Fighter"};
    
    JComboBox selectChar = new JComboBox(charNames);
    JTextArea charInfo = new JTextArea("CHARACTER INFO", 5,5);
    
    
    //Start + resume game button JPanel
    JPanel startButtonPanel = new JPanel();
    JButton startButton = new JButton("Start"); 
    JButton resumeButton = new JButton("Resume"); 
    
    //JPanel for the main events
    JPanel mainEventsPanel;
    JTextArea newScenario = new JTextArea();
    
    //JPanel for the player actions
    JPanel playerActionsPanel = new JPanel();
    JButton Option1 = new JButton();
    JButton Option2 = new JButton();
    JButton Option3 = new JButton();
    JButton Option4 = new JButton();
    
    //JPanel for the event log
    JPanel historyPanel;
    JTextArea historyTa = new JTextArea(12, 27);
    JScrollPane scrollPane = new JScrollPane(historyTa); //Add scroll bar to prevent the log from expanding
    
    //JPanel for the player info and inventory
    JPanel playerInfoPanel;
    JPanel playerStats;
    JPanel playerInventory;
    JTextArea playerInfo = new JTextArea();
    Image playerIcon;
    JLabel hp = new JLabel();
    JLabel level = new JLabel();
    JLabel weapon = new JLabel();
    
    //Item buttons
    JButton item1 = new JButton("");
    JButton item2 = new JButton("");
    JButton item3 = new JButton("");
   
    
    Apple thisApple = new Apple();
    Mushroom thisMushroom = new Mushroom();
    FreeSlot thisFreeSlot = new FreeSlot(); 
  
    public void firstWindow(buttonPressed checkButton)
    {
                
        screen.setSize(800, 1000);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.getContentPane().setBackground(mainColor);
        screen.setVisible(true);
        screen.setLayout(null);
        mainContainer= screen.getContentPane();
        
        // PANEL FOR THE NAME TITLE
        startScreen = new JPanel();
        startScreen.setBounds(100,100,600,150); //This panel starts from x=100, y=100, 600=width, 150=height
        mainTitle = new JLabel("ADVENTURE GAME");
        mainTitle.setForeground(Color.red);
        startScreen.add(mainTitle);
        mainContainer.add(startScreen);

        // PANEL FOR THE CHARACTER SELECTION
        charSelectionPanel = new JPanel();
        charSelectionPanel.setBounds(100,300,320,300);
        charInfo.setLineWrap(true);
        charInfo.setWrapStyleWord(true);
        charSelectionPanel.add(selectChar);
        charSelectionPanel.add(charInfo);
             
        selectChar.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                String s = selectChar.getSelectedItem().toString();
                
                if(s.equals(charNames[0])){
                    
                    thisCharacter = new Wizard();
                    
                    charInfo.setText("CHARACTER INFO: " + " HP:"+thisCharacter.getMaxHp() + " " + thisCharacter.getLore());
                    
                }else if(s.equals(charNames[1])){
                    
                    thisCharacter = new Villager();
                    
                    charInfo.setText("CHARACTER INFO: " +" HP:" +thisCharacter.getMaxHp()+ " "+ thisCharacter.getLore());                    
                    
                }else if(s.equals(charNames[2])){
                    
                    thisCharacter = new Fighter();
                    
                    charInfo.setText("CHARACTER INFO: "+ " HP:"+ thisCharacter.getMaxHp()+ " "+ thisCharacter.getLore());
                    
                }else{
                    
                    thisCharacter = new Wizard();
                    
                    charInfo.setText("CHARACTER INFO: " + " HP:"+thisCharacter.getMaxHp() + " " + thisCharacter.getLore());
                    
                
            }
        }
        }); 
        
        
        mainContainer.add(charSelectionPanel);
        charSelectionPanel.setVisible(true);
        
        // PANEL FOR BUTTON TO START GAME
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(600,400,80,70);
        startButtonPanel.add(startButton);
        startButtonPanel.add(resumeButton);
        mainContainer.add(startButtonPanel);
        startButton.addActionListener(checkButton);
        startButton.setActionCommand("Start");
        resumeButton.addActionListener(checkButton);
        resumeButton.setActionCommand("Resume");
        

       //MAKE INITIAL PANELS INVISIBLE
       

       
        //EVENT PANEL
        EventPanel();
        
        //PANEL FOR OPTIONS
        OptionsPanel(checkButton);
        
        //PANEL TO RECORD HISTORY OF EVENTS
        HistoryPanel();
        
        //PANEL TO KEEP TRACK OF PLAYER INFO
        PlayerInfoPanel(checkButton);
    }
    
    //ALL THE PANELS
   
    public void EventPanel(){
        
        mainEventsPanel = new JPanel();
        mainEventsPanel.setBounds(50,50,600,250);
        mainEventsPanel.setBackground(colorMainEvent);
        mainContainer.add(mainEventsPanel);
        
        newScenario.setBounds(100,100,600,150);
        newScenario.setBackground(Color.black);
        newScenario.setForeground(Color.white);
        newScenario.setFont(new Font("Serif",Font.PLAIN,30));
        newScenario.setLineWrap(true);
        newScenario.setWrapStyleWord(true);
        mainEventsPanel.add(newScenario);
        
    }
    
    public void OptionsPanel(buttonPressed checkButton){
        
        playerActionsPanel = new JPanel();
        playerActionsPanel.setBounds(360, 350, 300, 200);
        playerActionsPanel.setBackground(Color.red);
        playerActionsPanel.setLayout(new GridLayout (2, 2));
        mainContainer.add(playerActionsPanel);
        
        Option1.setBackground(Color.pink);
        Option1.setForeground(Color.black);
        Option1.setFocusPainted(false);//removes ring around the button when clicked
        Option1.addActionListener(checkButton);
        Option1.setActionCommand("Button1");
        playerActionsPanel.add(Option1);
        
      
        Option2.setBackground(Color.pink);
        Option2.setForeground(Color.black);
        Option2.setFocusPainted(false);
        Option2.addActionListener(checkButton);
        Option2.setActionCommand("Button2");
        playerActionsPanel.add(Option2); 
        
        Option3.setBackground(Color.pink);
        Option3.setForeground(Color.black);
        Option3.setFocusPainted(false);
        Option3.addActionListener(checkButton);
        Option3.setActionCommand("Button3");
        playerActionsPanel.add(Option3);
        
        Option4.setBackground(Color.pink);
        Option4.setForeground(Color.black);
        Option4.setFocusPainted(false);
        Option4.addActionListener(checkButton);
        Option4.setActionCommand("Button4");
        playerActionsPanel.add(Option4);
        
    }
    
    
    public void HistoryPanel(){
        
        historyPanel = new JPanel();
        historyPanel.setBounds(50, 350, 290, 200);
        historyPanel.setBackground(Color.green);
        historyTa.setLineWrap(true);
        historyTa.setEditable(false);
        historyPanel.add(scrollPane);        
        mainContainer.add(historyPanel);
        
    }
    
    public void PlayerInfoPanel(buttonPressed checkButton){
     
        playerInfoPanel = new JPanel();
        playerInfoPanel.setBounds(50, 600, 600, 200);
        playerInfoPanel.setBackground(mainColor);
        playerInfoPanel.setLayout(new GridLayout (1, 2)); //one half info and other half inventory
        
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new GridLayout (4, 1)); //one half info and other half inventory

        
        
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new GridLayout (2, 2)); //one half info and other half inventory

        
        hp.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        hp.setForeground(Color.black);
        firstPanel.add(hp);
        
        
        weapon.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        weapon.setForeground(Color.black);
        firstPanel.add(weapon);
        
        level.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        level.setForeground(Color.black);
        firstPanel.add(level);
        
        JButton saveButton = new JButton("save");
        level.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        level.setForeground(Color.black);
        saveButton.addActionListener(checkButton);
        saveButton.setActionCommand("save");
        firstPanel.add(saveButton);
        
        playerInfoPanel.add(firstPanel);
        
        JLabel  inventory =new JLabel("Inventory");
        inventory.setHorizontalAlignment(JLabel.CENTER);
        hp.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        hp.setForeground(Color.black);
        
   
        
        secondPanel.add(inventory);
        
        
        item1.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        item1.setForeground(Color.black);
        item1.addActionListener(checkButton);
        item1.setActionCommand("item1");
        item1.setVisible(true);
        secondPanel.add(item1);
        
        item2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        item2.setForeground(Color.black);
        item2.addActionListener(checkButton);
        item2.setActionCommand("item2");
        item2.setVisible(true);
        secondPanel.add(item2);
        
        item3.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        item3.setForeground(Color.black);
        item3.addActionListener(checkButton);
        item3.setActionCommand("item3");
        item3.setVisible(true);
        secondPanel.add(item3);
        
        playerInfoPanel.add(secondPanel);
        
        mainContainer.add(playerInfoPanel);
        
        
    }
    
   
    }
