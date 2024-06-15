import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * A class for implementation of whole gameboard and control the game flow.
 *
 * @author Ng Yu Mei 32423454, Yi Jin Tan 33263213, Amanda Goh 32694113
 * @version 1.0.0
 */
public class GameMainBoard {

    private JPanel panel, panel1, playerPanel1, playerPanel2;
    private GridBagConstraints constraints;
    private final int SIZE = 90;
    private int squareNum;
    private List<VolcanoCard> cornerCardCave;
    private List<VolcanoCard> otherCard;
    private int rowCountVolcano;
    private ArrayList<Dragon> playerArr;
    private ArrayList<VolcanoCard> volcanoCardList = new ArrayList<VolcanoCard>();
    private DragonCardSetUp dragonCardSetUp;
    private HashMap<JPanel, PictureHolder> squareMap = new HashMap<JPanel, PictureHolder>();
    private ArrayList<Path> finalPath;
    private ArrayList<VolcanoCard> totalList;
    private ArrayList<Path> caveList;
    private int currentPlayerIndex;
    private ArrayList<DragonCard> dragonCardList;
    private ArrayList<DragonCard> flippedCards;
    private int numPlayer;
    private int playerNo = 0;
    private JButton saveBtn, quitBtn, restartBtn;
    private HashMap<JPanel, PictureHolder> lookUpVolcano = new HashMap<JPanel, PictureHolder>();
    private boolean isRecover;
    private JLabel timerLabel; // Timer label
    private int countdownSeconds = 5; // Starting countdown time
    private Thread countdownThread;

    // gbpath
    private ArrayList<Path> gbPath = new ArrayList<Path>();

    /**
     * Constructor for GameMainBoard.
     */
    public GameMainBoard(int numPlayer, Boolean isRecover) {

        this.numPlayer = numPlayer;
        this.isRecover = isRecover;
        squareNum = 0;
        panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(new Color(247, 243, 158));
        panel1 = new JPanel(new GridBagLayout());
        panel1.setOpaque(false);
        playerArr = new ArrayList<>();
        playerPanel1 = new JPanel();
        playerPanel1.setOpaque(false);
        playerPanel1.setLayout(new BorderLayout());
        playerPanel2 = new JPanel();
        playerPanel2.setOpaque(true);
        playerPanel2.setBackground(Color.white);
        playerPanel2.setLayout(new BorderLayout());
        playerPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        constraints = new GridBagConstraints();
        currentPlayerIndex = 0;
        flippedCards = new ArrayList<>();
        dragonCardList = new ArrayList<>();
        volcanoCardList = new ArrayList<>();
        cornerCardCave = volcanoCardList.subList(0, 0);

        // Add timer label to the player panel
        timerLabel = new JLabel("Timer: 00:00"); // Initialize timer label
        timerLabel.setFont(new Font("Arial", Font.BOLD, 25));

        timerLabel.setForeground(Color.RED); // Set timer text color
        timerLabel.setHorizontalAlignment(JLabel.CENTER); // Center align text
        timerLabel.setOpaque(true); // Make the label opaque
        timerLabel.setBackground(Color.BLACK); // Set background color
        Border border = BorderFactory.createLineBorder(Color.RED, 4); // Line border
        timerLabel.setBorder(border); // Compound border

        playerPanel1.add(timerLabel, BorderLayout.SOUTH);

        // Start the countdown timer
        startCountdown();

    }

    public void initialzeSaveButton() throws FileNotFoundException {

        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        saveBtn = new JButton("Save");
        saveBtn.setBackground(Color.GRAY);
        saveBtn.setForeground(Color.white);
        saveBtn.addMouseListener(new MouseSave(this));
        
        // Set the font to the save button
        saveBtn.setFont(buttonFont);

        restartBtn = new JButton("Restart");
        restartBtn.setBackground(Color.GRAY);
        restartBtn.setForeground(Color.white);
        restartBtn.setFont(buttonFont);
        restartBtn.addMouseListener(new MouseRestart(this));

        quitBtn = new JButton("Quit");
        quitBtn.setBackground(Color.GRAY);
        quitBtn.setForeground(Color.white);
        quitBtn.setFont(buttonFont);
        quitBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                App app = new App();
                Container contentPane = e.getComponent().getParent().getParent().getParent().getParent();
                contentPane.remove(e.getComponent().getParent().getParent().getParent().getParent());
                contentPane.add(app.getPanel());
                contentPane.revalidate();
                contentPane.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
       

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        saveBtn.setPreferredSize(new Dimension(150, 40));
        restartBtn.setPreferredSize(new Dimension(150, 40));
        quitBtn.setPreferredSize(new Dimension(150, 40));
        buttonPanel.add(saveBtn); // Add the save button to the button panel
        buttonPanel.add(restartBtn);
        buttonPanel.add(quitBtn);
        playerPanel1.add(buttonPanel, BorderLayout.EAST);
    }

    // Method to start the countdown timer
    private void startCountdown() {
        // Start the countdown timer in a separate thread
        countdownThread = new Thread(() -> {
            long startTime;

            while (countdownSeconds >= 0) {
                
                startTime = System.currentTimeMillis();

                // Update the timer label on the event dispatch thread
                javax.swing.SwingUtilities.invokeLater(() -> {
                    timerLabel.setText("Timer: " +
                    String.format("%02d", countdownSeconds % 60));
                });

                long elapsedTime = System.currentTimeMillis() - startTime;
                long remainingTime = 1000 - elapsedTime; // Calculate the remaining time
                
                if (remainingTime > 0) {
                    try {
                        Thread.sleep(remainingTime); // Sleep for the remaining time
                    } catch (InterruptedException e) {
                        // Handle the interruption
                        Thread.currentThread().interrupt(); // Restore the interrupted status
                        break; // Exit the loop if interrupted
                    }
                }

                countdownSeconds--; // Decrement countdown

                // Check if the timer has reached 0
                if (countdownSeconds < 0) {
                    // Restart the countdown timer
                    restartCountdown();

                    // Switch the player's turn
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        setDefaultCurrentPlayerIndex();
                    });
                }
                
            }
        });
        countdownThread.start(); // Start the countdown thread
    }

    // Method to restart the countdown timer
    public void restartCountdown() {
        stopCountdown(); // Stop the countdown if it's running
        countdownSeconds = 5; // Reset the countdown time
        startCountdown(); // Start the countdown again
    }

    // Method to stop the countdown timer
    public void stopCountdown() {
        if (countdownThread != null && countdownThread.isAlive()) {
            countdownThread.interrupt(); // Interrupt the countdown thread
        }
    }

    /**
     * Read text file to get all the combinations of the squares in volcano card
     */
    public void readGetConfiguration() throws FileNotFoundException, ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        ArrayList<Animal> tempList = new ArrayList<>();

        try (InputStream inputStream = getClass().getResourceAsStream("/configurationSetting.txt");
        Scanner scanner = new Scanner(inputStream))
        {
            while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String[] dataSplit = data.split(",");
            for (String d : dataSplit) {
                Class animalClass = Class.forName(d);
                Class[] paramType = { Integer.TYPE, Integer.TYPE, Integer.TYPE };
                Constructor constructor = animalClass.getConstructor(paramType);

                Object[] params = { 1, SIZE, SIZE };
                Animal instance = (Animal) constructor.newInstance(params);
                tempList.add(instance);
                squareNum += 1;
            }

            addVolcanoCave(new VolcanoCard(new Square(tempList.get(0)), new Square(tempList.get(1)),
                    new Square(tempList.get(2))));
            tempList.clear();

        }
        }
        catch (IOException e) {
        // Handle any IO exception
        e.printStackTrace();
    }

    //scanner.close();
    rowCountVolcano = (squareNum - 10) / 2;
    randomArranngePosition();
    }

    /**
     * Random arrange the position of the volcano cards
     */
    public void randomArranngePosition() {

        int mainListSize = volcanoCardList.size();
        cornerCardCave = volcanoCardList.subList(0, 4);
        otherCard = volcanoCardList.subList(4, mainListSize);
        Collections.shuffle(cornerCardCave);
        Collections.shuffle(otherCard);

        int caveCardSize = 0;
        int noCaveCard = 0;

        totalList = new ArrayList<>();
        for (int i = 1; i <= volcanoCardList.size(); i++) {
            if (caveCardSize != cornerCardCave.size() + 1 && i % 2 != 0) {
                cornerCardCave.get(caveCardSize).setHasIndention();
                totalList.add(cornerCardCave.get(caveCardSize));
                caveCardSize += 1;
            } else {

                totalList.add(otherCard.get(noCaveCard));
                noCaveCard += 1;
            }
        }

        setUpMainBoard();

    }

    /**
     * Set Up the volcano cards and caves in the gameboard with random position and
     * in clockwise direction
     */

    public void setUpMainBoard() {

        finalPath = new ArrayList<>();
        caveList = new ArrayList<>();
        Square currentSquare;
        int[] xDirection = { 1, 0, -1, 0 };
        int[] yDirection = { 0, 1, 0, -1 };
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        int directionIndex = 0;
        int rowLimit = rowCountVolcano + 2 + 1;

        for (int i = 0; i < totalList.size(); i++) {
            for (int j = 0; j < totalList.get(i).getSquareList().size(); j++) {

                if ((constraints.gridx == rowLimit - 1 && directionIndex == 0)
                        || (constraints.gridy == 5 && directionIndex == 1)
                        || (constraints.gridx == 1 && directionIndex == 2)
                        || (constraints.gridy == 1 && directionIndex == 3)) {
                    directionIndex += 1;

                }

                constraints.gridx = constraints.gridx + xDirection[directionIndex];
                constraints.gridy = constraints.gridy + yDirection[directionIndex];

                currentSquare = totalList.get(i).getSquareList().get(j);
                if (j == 0 || j == 1) {
                    if (constraints.gridy == 1 && constraints.gridx != rowLimit - 1) {
                        if (j != 1) {
                            currentSquare.getPanel()
                                    .setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLACK));
                        } else {
                            if (cornerCardCave.contains(totalList.get(i)) & this.numPlayer > 1) {
                                int index = cornerCardCave.indexOf(totalList.get(i));
                                setUpCaveHorizontal(this.playerNo, -1);
                                caveList.add(currentSquare);
                                this.gbPath.add(playerArr.get(this.playerNo).getCave());
                                playerArr.get(this.playerNo).setCaveIndex(this.gbPath.size()-1);
                                System.out.println(playerArr.get(this.playerNo).getCave().getAnimal() + "caveeee" + this.gbPath.size());
                                this.numPlayer -= 1;
                                this.playerNo += 1;
                            }
                        }
                    } else if (constraints.gridx == rowLimit - 1 && constraints.gridy != 5) {

                        if (j != 1) {
                            currentSquare.getPanel()
                                    .setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK));
                        } else {
                            if (cornerCardCave.contains(totalList.get(i))) {
                                int index = cornerCardCave.indexOf(totalList.get(i));
                                setUpCaveVertical(index, 1);
                                caveList.add(currentSquare);
                                this.gbPath.add(playerArr.get(this.playerNo).getCave());
                                playerArr.get(this.playerNo).setCaveIndex(this.gbPath.size());


                            }
                        }
                    }

                    else if (constraints.gridy == 5 && constraints.gridx != 1) {
                        if (j != 1) {
                            currentSquare.getPanel()
                                    .setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK));
                        } else {
                            if (cornerCardCave.contains(totalList.get(i)) & this.numPlayer != 0) {
                                int index = cornerCardCave.indexOf(totalList.get(i));
                                setUpCaveHorizontal(this.playerNo, 1);
                                caveList.add(currentSquare);
                                this.gbPath.add(playerArr.get(this.playerNo).getCave());
                                playerArr.get(this.playerNo).setCaveIndex(this.gbPath.size()-1);
                                System.out.println(playerArr.get(this.playerNo).getCave().getAnimal() + "caveeeeeeeeeeeeeee" + this.gbPath.size());

                                this.numPlayer -= 1;
                                this.playerNo += 1;

                            }
                        }
                    }

                    else if (constraints.gridx == 1 && constraints.gridy != 1) {
                        if (j != 1) {
                            currentSquare.getPanel()
                                    .setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
                        } else {
                            if (cornerCardCave.contains(totalList.get(i))) {
                                int index = cornerCardCave.indexOf(totalList.get(i));
                                setUpCaveVertical(index, -1);
                                caveList.add(currentSquare);
                                this.gbPath.add(playerArr.get(this.playerNo).getCave());
                                playerArr.get(this.playerNo).setCaveIndex(this.gbPath.size());

                            }
                        }

                    }

                } else if (j == 2) {
                    if (constraints.gridy == 1) {
                        currentSquare.getPanel().setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK));
                    } else if (constraints.gridx == rowLimit - 1) {

                        currentSquare.getPanel().setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
                    }

                    else if (constraints.gridy == 5) {
                        currentSquare.getPanel().setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLACK));
                    }

                    else if (constraints.gridx == 1) {
                        currentSquare.getPanel().setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK));
                    }

                }

                panel1.add(currentSquare.getPanel(), constraints);
                squareMap.put(currentSquare.getPanel(), currentSquare);
                finalPath.add(currentSquare);
                gbPath.add(currentSquare);

            }

        }

        setPlayerPath();

    }

    /**
     * Set up all the dragon cards and put in center round ring of the gameboard and
     * are surrounded by the volcano cards and cave
     */
    public void setUpCenterPanel() {
        double width = 7 * totalList.get(0).getSquareList().get(0).getPanel().getPreferredSize().getWidth();
        double height = 3 * totalList.get(0).getSquareList().get(0).getPanel().getPreferredSize().getHeight();

        dragonCardSetUp = new DragonCardSetUp(width, height);
        try {

            if(isRecover == false){
                dragonCardSetUp.readGetConfiguration();
                dragonCardList = dragonCardSetUp.getDragonCards();
            }
            else{
                dragonCardSetUp.recover(dragonCardList);
                
            }
            
        } catch (FileNotFoundException | ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

       

        addListener();
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridheight = 3;
        constraints.gridwidth = 7;
        constraints.fill = GridBagConstraints.VERTICAL;

        panel1.add(dragonCardSetUp.getPanel(), constraints);

    }

    /**
     * Set up all unique path for each dragon token will walk through based on their
     * cave position
     */
    public void setPlayerPath() {
        for (int i = 0; i < playerArr.size(); i++) {
            // set finalpath to all player
            playerArr.get(i).setTotalPath(gbPath);
            // set caveindex as start place
            playerArr.get(i).setTotalPathIndex(playerArr.get(i).getCaveIndex());
            playerArr.get(i).setTotalPathSteps(gbPath.size());

        }
        


        setUpCenterPanel();

    }

    /**
     * Set cave position to gameboard when the cave is on top or bottom of the
     * rectangle
     * 
     * @param index    the index of the player in playerArr
     * @param position the distance between the square and the cave
     */
    public void setUpCaveHorizontal(int index, int position) {

        constraints.gridy = constraints.gridy + position;
        Cave cave = playerArr.get(index).getCave();
        cave.changeSize(SIZE, SIZE);
        panel1.add(cave.getPanel(), constraints);
        constraints.gridy = constraints.gridy - position;

    }

    /**
     * Set cave position to gameboard when the cave is on left side or and right
     * side of the rectangle
     * 
     * @param index the index of the player in playerArr
     * @param position the distance between the square and the cave
     */
    public void setUpCaveVertical(int index, int position) {

        constraints.gridx = constraints.gridx + position;
        Cave cave = playerArr.get(index).getCave();
        cave.changeSize(SIZE, SIZE);
        panel1.add(cave.getPanel(), constraints);
        constraints.gridx = constraints.gridx - position;

    }

    public Dragon getCurrentPlayer() {

        return playerArr.get(currentPlayerIndex);
    }

    /**
     * Set the index of the current dragon token in the playerArr who flip the
     * dragon cards
     */
    public void setDefaultCurrentPlayerIndex() {

        if (currentPlayerIndex < playerArr.size() - 1) {
            currentPlayerIndex += 1;
        } else {
            currentPlayerIndex = 0;
        }

        setUpPlayerTurnLabel();
    }

    /**
     * Register all dragon cards with mouse listener
     */
    public void addListener() {
        for (DragonCard card : dragonCardList) {
            card.getPanel().addMouseListener(new MouseAc(this, card));
        }
    }

    /**
     * Add Volcano card to volcanoCardList
     */
    public void addVolcanoCave(VolcanoCard card) {
        volcanoCardList.add(card);

    }

    /**
     * Unflip the dragon card that flipped by the current player
     */
    public void unFlipCard() {

        for (DragonCard card : getFlippedCards()) {
            card.getPanel().addMouseListener(new MouseAc(this, card));
            card.unFlip();
            card.addPicture();
            card.getPanel();
        }
        flippedCards.clear();
    }

    public JPanel getPanel() {

        panel.setLayout(new BorderLayout());
        panel.add(panel1, BorderLayout.CENTER);
        panel.add(playerPanel1, BorderLayout.NORTH);

        return panel;
    }

    /**
     * Set Label that represent the current dragon token turn
     */
    public void setUpPlayerTurnLabel() {

        playerPanel2.removeAll();
        Dragon d = new Dragon(playerArr.get(currentPlayerIndex).getPlayerNumber());
        d.setLabel();
        d.getLabel().setText("Current Player Turn   ");
        d.getLabel().setFont(new Font("ARIAL", Font.BOLD, 20));
        playerPanel2.add(d.getLabel(), BorderLayout.WEST);
        playerPanel2.revalidate();
        playerPanel2.repaint();
        playerPanel1.add(playerPanel2, BorderLayout.WEST);

        

    }

    public void addPlayer(Dragon player) {
        this.playerArr.add(player);
    }


    public ArrayList<Dragon> getPlayerArr() {
        return this.playerArr;
    }

    public void addIndentionCard(VolcanoCard volcano){
        cornerCardCave.add(volcano);
    }

    public ArrayList<DragonCard> getFlippedCards() {
        return flippedCards;
    }

    public void addFlipppedCards(DragonCard card){
        flippedCards.add(card);
    }

    public ArrayList<VolcanoCard> getTotalList() {
        return totalList;
    }

    public void setTotalist(ArrayList<VolcanoCard> totalList){
        this.totalList = totalList;
    }

    public ArrayList<DragonCard> getDragonCardList() {
        return dragonCardList;
    }


    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int index){

        this.currentPlayerIndex = index;
        setUpPlayerTurnLabel();
    }

    public void setRowCountVolcano(int num){
        this.rowCountVolcano = num;
    }

    public void addDragonCard(DragonCard card){
        dragonCardList.add(card);
    }

}