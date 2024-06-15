
import java.awt.BorderLayout;


/**
 * An abstract class for implementation of the path that can be stepped on by the dragon token such as Cave and Square
 * @author Ng Yu Mei 32423454, Tan Yi Jin 33263213
 * @version 1.0.0
 */
public abstract class Path extends PictureHolder implements Holdable{

    /**
     * Constructor for Path.
     * @param newAnimal An instace of Animal 
    
     */
    public Path(Animal newAnimal) {
        super(newAnimal);
        
    }

    /**
     * Add player to the current square
     * @param dragon The dragon token that need to put on the current Aquare
     */
    @Override
    public void addPlayer(Dragon dragon) {
        Object obj = dragon.getTotalCurrentPlace();
        // with new steps
        if(dragon.getIsStartMovement() && dragon.getTotalPathIndex() != dragon.getTotalPath().size()-1)
        {
            if(obj instanceof Square)
            {
                Square s = (Square) obj;
                if(s.getCurrentDragon() != null)
                {
                    System.out.println("added player to new squareeee " + dragon.getTotalPathIndex());
                    dragon.getTotalCurrentPlace().getPic().add(s.getCurrentDragon().getLabel(), BorderLayout.NORTH);
                }
            }
        }
      
        dragon.getTotalCurrentPlace().getPic().remove(dragon.getLabel());
        dragon.getTotalCurrentPlace().getPic().add(dragon.getLabel(),BorderLayout.SOUTH);
        dragon.getTotalCurrentPlace().getPic().revalidate();
        dragon.getTotalCurrentPlace().getPic().repaint();

        
    }



}
