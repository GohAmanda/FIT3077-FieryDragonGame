import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JPanel;

/**
 * A class for set up the arrangement of all dragon cards and get the configuration settings of the dragon cards from text file.
 *
 * @author Ng Yu Mei 32423454
 * @version 1.0.0
 */
public class DragonCardSetUp {


    private JPanel panel, gridPanel;
    private ArrayList<DragonCard> dragonCards = new ArrayList<>();
    private final int SIZE = 80;

    /**
     * Constructor for DragonCardSetUp.
     * @param width An integer that represent width of the main panel that contains all dragon cards
     * @param height An integer that represent height of the main panel that contains all dragon cards
     */
    public DragonCardSetUp(double width, double height){
        panel= new JPanel();
        panel.setOpaque(false);
        this.panel.setPreferredSize(new Dimension((int)width, (int)height));
        


    }

    /**
    * Read text file to get all the dragon cards
    */
    public void readGetConfiguration() throws FileNotFoundException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException{
        try (InputStream inputStream = getClass().getResourceAsStream("/DragonCardConfiguration.txt");
        Scanner scanner = new Scanner(inputStream))
        {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();

                Class animalClass = Class.forName(data.substring(0, data.length()-1));
                Class[] paramType = {Integer.TYPE, Integer.TYPE, Integer.TYPE};
                Constructor constructor = animalClass.getConstructor(paramType);

                Object[] params = {Integer.parseInt(data.substring(data.length()-1)), SIZE, SIZE};
                Animal instance = (Animal) constructor.newInstance(params);
                dragonCards.add(new DragonCard(instance));

            }
        }

        catch (IOException e) {
        // Handle any IO exception
        e.printStackTrace();
    }
    gridPanel =new JPanel(new GridLayout(3, 6));
    gridPanel.setOpaque(false);
    randomArranngeCard();
        
    }

     /**
    * Random arrange the order of the dragon cards in the list
    */
    public void randomArranngeCard(){
        for(int i=0; i<30; i++){
            Collections.shuffle(dragonCards);
        }

        setUpGridPanel();

    }

     /**
     * Returns An arraylist of all dragon cards
     *
     * @return An arraylist of all dragon cards
     */
    public ArrayList<DragonCard> getDragonCards() {
        return dragonCards;
    }

    public void recover(ArrayList<DragonCard> cards){
        gridPanel =new JPanel(new GridLayout(3, 6));
        gridPanel.setOpaque(false);
        dragonCards = cards;
        setUpGridPanel();


    }


     /**
    * Put all dragon cards in the Jpanel with grid layout
    */
    public void setUpGridPanel(){
        for(DragonCard card : dragonCards){
            card.addPicture();
            gridPanel.add(card.getPanel());
        }
    }

    /**
    *Add panel that contains the dragon card in main panel
    */
    public void combinePanel(){
        panel.add(gridPanel, BorderLayout.CENTER);

    }


    /**
     * Returns A main Japnel contains all dragon cards with a proper layout
     *
     * @return A main Japnel contains all dragon cards with a proper layout
     */
    public JPanel getPanel(){

        combinePanel();
        return panel;
    }


}
