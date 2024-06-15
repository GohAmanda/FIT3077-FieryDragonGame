import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Concrete class for Dragon.
 * It represent the token that can be controlled by the player
 *
 * @author Ng Yu Mei 32423454, Amanda Goh 32694113
 * @version 1.0.0
 */
public class Dragon {

    /**
    * Integer represent the player number, it is player 1 or player 2..
    */
    private int playerNumber;

    /**
    * JLabel contains player image dragon token
    */
    private JLabel label, text;

    /**
    * An instance of PictureComponent that produce image
    */
    private PictureComponent playerPic;

    /**
    * An instance of Cave that the dragon or player holds
    */
    private Cave cave;


    /**
    * An ArrayList that contains Place instance that represents the path that the dragon or player will walk through in ascending order in clockwise direction
    */
    private ArrayList<Path> totalPath;


     /**
    * An Integer that represents the cave index of player
    */
    private int caveIndex;

    /**
    * An Integer that represents the size of icon image
    */
    private int size;
    private Color color;

    /**
    * An Integer that represents the current place the player stand in the pathList
    */
    private int totalPathIndex;

    private boolean isStartMovement;

    /**
     * An integer that represents the total steps player has made
     */
    private int totalPathSteps;

    private HashMap<String, Object> saveItem;

    /**
     * Constructor for Dragon.
     * @param playerNumber An integer that represent the player number(plyery type)
     */
    public Dragon(int playerNumber){
        this.playerNumber = playerNumber;
        label = new JLabel();
        totalPath = new ArrayList<>();
        this.size=80;
        this.isStartMovement = false;
        saveItem = new HashMap<String, Object>();
}


    /**
     * Set Cave that the dragon token owns and stays
     * @param cave An instance of the Cave that the dragon token owns and stays
     */
    public void setCave(Cave cave) {
        this.cave = cave;
    }

     /**
     * Returns An instance of cave that belongs to the player
     *
     * @return An instance of cave that belongs to the player.
     */
    public Cave getCave() {
        return cave;
    }

     /**
     * Returns An integer represent the player number, it is player 1 or player 2.
     *
     * @return  An integer represent the player number, it is player 1 or player 2.
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    public boolean getIsStartMovement(){
        return isStartMovement;
    }

    public void setStartMovement(){
        isStartMovement = true;
    }



    /**
     * Set An ArrayList that contains Place instance that represents the path that the dragon or player will walk through in ascending order in clockwise direction
     * @param newPath An ArrayList that contains Place instance that represents the path that the dragon or player will walk through in ascending order in clockwise direction
     */
    public void setTotalPath(ArrayList<Path> totalPath)
    {
        this.totalPath = totalPath;
    }

     /**
     * Returns An ArrayList that contains Place instance that represents the path that the dragon or player will walk through in ascending order in clockwise direction
     *
     * @return  An ArrayList that contains Place instance that represents the path that the dragon or player will walk through in ascending order in clockwise direction
     */
    public ArrayList<Path> getTotalPath(){
        return this.totalPath;
    }

    /**
     * Set An integer that represents the current index of place in the pathList that the player stand on
     * @param index An integer that represents the current index of place in the pathList that the player stand on
     */
    public void setTotalPathIndex(int index){
        this.totalPathIndex = index;
    }

    /**
     * Returns An integer that represents the current index of place in the pathList that the player stand on
     *
     * @return  An integer that represents the current index of place in the pathList that the player stand on
     *
     */
    public int getTotalPathIndex(){
        return this.totalPathIndex;
    }

    /**
     * Returns An instance of place that represents the current place  player stand on
     *
     * @return An instance of place that represents the current place  player stand on
     *
     */
    public Path getTotalCurrentPlace(){
        return this.totalPath.get(this.totalPathIndex);
    }

    /**
     * Setter function for a integer that contains the cave index of player
     *
     * @return a integer that contains the cave index of player
     *
     */
    public void setCaveIndex(int i)
    {
        this.caveIndex = i;
    }

    /**
     * Getter function for a integer that contains the cave index of player
     *
     * @return a integer that contains the cave index of player
     *
     */
    public int getCaveIndex()
    {
        return this.caveIndex;
    }

    /**
     * Setter function for a integer that contains the total steps
     *
     * @return a integer that contains the total steps
     *
     */
    public void setTotalPathSteps(int i)
    {
        this.totalPathSteps = i;
    }

    /**
     * Getter function for a integer that contains the total steps
     *
     * @return a integer that contains the total steps
     *
     */
    public int getTotalPathSteps()
    {
        return this.totalPathSteps;
    }

    /**
     * Function for increasing the total steps
     *
     * @return a integer about total steps
     *
     */    
    public void incrementIndex(int i)
    {
        this.totalPathIndex += i;
        this.totalPathSteps -= i;
        // to check if out of bounds
        if(this.totalPathIndex >= this.totalPath.size())
        {
            this.totalPathIndex -= this.totalPath.size();
        }
    }

    /**
     * Function for decreasing the total steps
     *
     * @return a integer about total steps
     *
     */
    public void decrementIndex(int i)
    {
        this.totalPathIndex -= i;
        this.totalPathSteps += i;
        // to check if out of bounds
        if(this.totalPathIndex < 0)
        {
            this.totalPathIndex += this.totalPath.size();
        }
    }

    /**
     * Set color that represent the dragon token
     * @param color An color that represent the dragon token, the color of the dragon token in the picture
     */
    public  void setColor(Color color){
        this.color = color;
    }

    /**
     * Produce picture that contains dragon token picture, add image component to JLabel that represent the dragon token
     */
    public void setLabel() {
        playerPic = new PictureComponent(size, size);
        playerPic.setImage("Pictures/player"+playerNumber+".png");         
        label.setIcon(new ImageIcon(playerPic.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
    }

     /**
     * Returns A JLabel that contains the player image and represent the player token
     *
     * @return A JLabel that contains the player image and represent the player token
     *
     */
    public JLabel getLabel() {
        return label;
    }

    
    public HashMap<String, Object> getSaveItem(){
        saveItem.put("number", playerNumber);
        saveItem.put("cave", cave.getAnimal().getClass().toString().substring(6));
        saveItem.put("currentIndex", totalPathIndex);
        saveItem.put("totalSteps", totalPathSteps);
        saveItem.put("caveIndex", caveIndex);
        saveItem.put("color", cave.getColor().toString());
        System.out.println("save item" + saveItem);
        
        return saveItem;
    }

}



