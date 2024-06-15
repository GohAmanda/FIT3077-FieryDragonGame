import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;
import java.util.HashMap;

/**
 * To let the player choose each cave
 * @author Ng Yu Mei 32423454, Yi Jin Tan 33263213
 * @version 1.0.0
 */
public class CaveSelection {

    private final JPanel panel;

    private JPanel overallPanel, titleHolderPanel, caveHolderPanel;
    private HashMap<Integer, Color> playerColor = new HashMap<Integer, Color>();
    private Cave spiderCave, batCave, babyDragonCave, salamanderCave;
    private HashMap<JPanel, Cave> caveMap = new HashMap<JPanel, Cave>();
    private JLabel titleLabel, playerLabel;
    private final int SIZE = 120;
    private Dragon player1, player2, player3, player4;
    private Dragon playerArr[] = {player1, player2, player3, player4};
    private int playerType =1;
    private GameMainBoard gameMainBoard;
    public int numPlayer;

    /**
     * Constructor of the class
     * Setup the page of the class
     * @param numPlayer
     */
    public CaveSelection(int numPlayer){

        panel = new JPanel();
        this.numPlayer = numPlayer;
        panel.setBackground(new Color(247, 243, 158));
        overallPanel = new JPanel();
        titleHolderPanel = new JPanel();
        caveHolderPanel = new JPanel();
        playerColor.put(1, Color.orange);
        playerColor.put(2, Color.blue);
        playerColor.put(3, Color.white);
        playerColor.put(4, Color.green);
        titleLabel = new JLabel("Choose your Cave!", JLabel.CENTER);
        titleLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 45));
        titleLabel.setForeground(new Color(250, 239, 30));
        playerLabel = new JLabel("Player"+playerType, JLabel.CENTER);
        playerLabel.setFont(new Font("ARIAL", Font.BOLD, 43));
        playerLabel.setForeground(playerColor.get(playerType));
        

        spiderCave = new Cave(new Spider(1, SIZE, SIZE));
        setUpCave(spiderCave);
        batCave = new Cave(new Bat(1, SIZE, SIZE));
        setUpCave(batCave);
        babyDragonCave =new Cave(new BabyDragon(1, SIZE, SIZE));
        setUpCave(babyDragonCave);
        salamanderCave = new Cave(new Salamander(1, SIZE, SIZE));
        setUpCave(salamanderCave);

        gameMainBoard = new GameMainBoard(numPlayer, false);
        
    }

    /**
     * Setup each cave to the page
     * @param cave
     */
    public void setUpCave(Cave cave){
        cave.setColor(Color.BLACK);
        cave.getPanel().addMouseListener(new MouseAc());
        cave.addPicture();
        caveMap.put(cave.getPanel(), cave);
    }

    /**
     * To show which player choose now
     */
    public void setPlayerLabel(){
        playerType+=1;
        this.playerLabel.setText("Player"+playerType);
        playerLabel.setForeground(playerColor.get(playerType));
       
    }

    /**
     * Setup title of the page
     */
    public void setTitleLayout(){
        
        titleHolderPanel.setBackground(Color.black);
        titleHolderPanel.setLayout(new GridLayout(2,1, 0, -50));
        titleHolderPanel.add(titleLabel);
        titleHolderPanel.add(playerLabel);

    }

    /**
     * Setup each cave layout
     */
    public void setCaveLayout(){
        
        caveHolderPanel.setOpaque(false);
        caveHolderPanel.setPreferredSize(new Dimension((spiderCave.getAnimal().getHeight()+20)*4+500, spiderCave.getAnimal().getHeight()+20));
        caveHolderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
        caveHolderPanel.add(spiderCave.getPanel());
        caveHolderPanel.add(batCave.getPanel());
        caveHolderPanel.add(babyDragonCave.getPanel());
        caveHolderPanel.add(salamanderCave.getPanel());

    }

    /**
     * Start page of the class
     */
    public void startPage(){
        
        overallPanel.setOpaque(false);
        overallPanel.setLayout(new GridLayout(2, 1, 0, 130));
        overallPanel.add(titleHolderPanel);
        overallPanel.add(caveHolderPanel);
        panel.add(overallPanel, BorderLayout.CENTER);
    }

    /**
     * Getter to get the panel of this class
     * @return
     */
    public JPanel getPanel() {
        return panel;
    }

    class MouseAc implements MouseListener{

        /**
         * Setup the perform of the class
         * @param e
         */
        public void setUp(MouseEvent e){
            playerArr[playerType-1] = new Dragon(playerType);
            caveMap.get(e.getSource()).setColor(playerColor.get(playerType));
            playerArr[playerType-1].setCave(caveMap.get(e.getSource()));
            caveMap.get(e.getSource()).addPicture();
            playerArr[playerType-1].setLabel();
            
            caveMap.get(e.getSource()).setUpPlayer(playerArr[playerType-1]);
            e.getComponent().removeMouseListener(this);
            caveHolderPanel.remove(e.getComponent());
            caveHolderPanel.repaint();
            
            gameMainBoard.addPlayer(playerArr[playerType-1]);
            
           
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            // If the current player haven reach the number of player
            if(playerType != numPlayer){
                setUp(e);    
                setPlayerLabel();
            }

            else{
            setUp(e);
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
            currentFrame.dispose();
            
            int screenWidth =(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            int screenHeight =(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
            JFrame f=new JFrame();
            f.setTitle("The Fiery Dragon Game");
            f.setBounds(0,0,screenWidth,screenHeight);
            f.setExtendedState(f.MAXIMIZED_BOTH);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            try {
                gameMainBoard.initialzeSaveButton();
                gameMainBoard.readGetConfiguration();
                gameMainBoard.setUpPlayerTurnLabel();
            } catch (FileNotFoundException | ClassNotFoundException | NoSuchMethodException | InstantiationException
                    | IllegalAccessException | InvocationTargetException e1) {
                
                e1.printStackTrace();
            }

            JScrollPane scroll = new JScrollPane(gameMainBoard.getPanel());
            
            f.add(scroll);
            f.setVisible(true);
        }
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
           
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
           
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
           
        }
    
    }
}
