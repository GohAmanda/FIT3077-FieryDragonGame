import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Concrete class for Cave place.
 * It is the place where the dragon token or player stay initially. It is also considered as player house. The player will win the game when the player come back to the cave 
 * after player walk all squares in clockwise direction
 *
 * @author Ng Yu Mei 32423454
 * @version 1.0.0
 */

public class Cave extends Path{

    /**
    * An instance of Dragon(player)
    */
    private Dragon player;

    /**
     * Constructor for Cave.
     * @param newAnimal An instance of animal with any animal type
     */
    public Cave(Animal newAnimal) {
        super(newAnimal);
        
    }
    

    /**
     * Add player to the cave, add jLabel as dragon token in jpanel so that dragon token will on the jpanel that represent the cave
     * @param dragon An instance of Dragon
     */
    public void setUpPlayer(Dragon dragon) {
        this.player = dragon;
        this.getPic().add(dragon.getLabel(),BorderLayout.CENTER);
        this.getPic().revalidate();
        this.getPic().repaint();
    
        
    }



    /**
     * Change the size of the picture
     * @param width the width of the picture
     * @param height the height of the picture
     */
    public void changeSize(int width, int height){
        this.getAnimal().setWidth(width);
        this.getAnimal().setHeight(height);
        this.getPic().setWidth(width);
        this.getPic().setHeight(height);
        this.addPicture();
    }



    /**
     * Returns a JPanel that represents the cave, contains all components the cave need
     *
     * @return A JPanel that represents the cave, contains all components the cave need
     */
    @Override
    public JPanel getPanel() {
        
        return super.getPanel();
    }

    public boolean canEnter(Dragon player) {
        // Implement the logic to determine if the player can enter the cave
        // This could involve checking the player's status, items, etc.
        return true; // Replace with actual logic
    }
    
    public Dragon getDragon()
    {
        return this.player;
    }
    
}
