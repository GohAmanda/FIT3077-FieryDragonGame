import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

/**
 * An abstract class for implementation of the Picture Holder that will hold and display the picture in Cave, Square and Dragon Card and Dragon Token
 *
 * @author Ng Yu Mei 32423454
 * @version 1.0.0
 */
public abstract class PictureHolder {
    
    private JPanel panel;   
    private PictureComponent pic;    
    private Animal animal;
    private Color color;


    public PictureHolder(Animal newAnimal){
        this.animal = newAnimal; 
        this.color = new Color(181, 121, 43);
        pic = new PictureComponent(animal.getWidth(), animal.getHeight());
        pic.setOpaque(true);
        pic.setBackground(new Color(209, 152, 77));
        initialzePlace();
        // picContainer.setOpaque(true);
        // picContainer.setBackground(new Color(209, 152, 77));
        
    }

    /**
     * Initialize a picture holder
     */
    public void initialzePlace(){
        panel= new JPanel();  
        panel.setSize(new Dimension(animal.getWidth()+10,  animal.getHeight()+10));
    }

     /**
     * Set an Animal instance that the the square, dragon card, and cave will belongs to
     * @param animal An instance of Animal class
     */
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

     /**
     * Set  A color instance for the background of the JPanel
     * @param color A color instance for the background of the JPanel
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns An instance of Animal that Dragon Card hold
     *
     * @return An instance of Animal that Dragon Card hold
     *
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Returns An instance of Color of the backgroud of the Picture Holder
     *
     * @return An instance of Color of the backgroud of the Picture Holder
     *
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns An instance of Picture of Components that draw Picture on the JPanel
     *
     * @return An instance of Picture of Components that draw Picture on the JPanel
     *
     */
    public PictureComponent getPic() {
        return pic;
    }

    

    /**
     * Add picture to the JPanel for Cave, Square or Dragon Card
     */
    public void addPicture(){
        pic.setImage(animal.getFilename());
        panel.setOpaque(true);
        panel.setBackground(color);
        panel.add(pic, BorderLayout.CENTER);

    }

   
    public JPanel getPanel(){   
       panel.setPreferredSize(new Dimension(animal.getWidth()+10,  animal.getHeight()+10));
        return panel;

    }
}
