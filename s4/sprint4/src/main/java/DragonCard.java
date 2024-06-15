import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * A class that contains different animal type picture that can be flipped by the dragon token to determine the movement
 *
 * @author Ng Yu Mei 32423454
 * @version 1.0.0
 */
public class DragonCard extends PictureHolder{

    /**
    * String represent file name
    */
    private String file;
    private HashMap<String, Object> saveItem;
    private int isFlip;

    /**
     * Constructor for DragonCard.
     * @param newAnimal An instance of animal type the dragon card contains
     */
    public DragonCard(Animal newAnimal) {
        super(newAnimal);
        saveItem = new HashMap<String, Object>();       
        this.file = "Pictures/dragon_logo.png";
        this.isFlip = 0;
              
    }

    /**
    * Add picture to the Jpanel
    */
    @Override
    public void addPicture() {
        super.getPic().setImage(file);
        super.getPic().setBackground(Color.white);
        getPanel().setOpaque(true);
        getPanel().setBackground(super.getColor());
        getPanel().add(super.getPic(), BorderLayout.CENTER);
        
    }

    @Override
    public JPanel getPanel() {
        return super.getPanel();
    }

    /**
     * Returns An instance of Animal that Dragon Card hold
     *
     * @return An instance of Animal that Dragon Card hold
     *
     */
    @Override
    public Animal getAnimal() {
        return super.getAnimal();
    }

     /**
     * Set a stirng that represents the the path of the file
     * @param file A string represents the path of the file
     */
    public void flip() {

        isFlip = 1;
        this.file = getAnimal().getFilename();
    }

    public String getFile(){
        return file;
    }


    /**
    * Set file name of picture with logo picture
    */
    public void unFlip(){
        isFlip = 0;
        this.file = "Pictures/dragon_logo.png";
    }

    public HashMap<String, Object> getSaveItem(){
        saveItem.put("animal", super.getAnimal().getClass().toString().substring(6));
        saveItem.put("number", super.getAnimal().getAnimalNumber());
        saveItem.put("status", isFlip);
        
        return saveItem;
    }


}
