/**
 * Abstract class for all animal type.
 * It contains all attributes and function required to define the animal need to put in the dragon cards, squares and caves include file path and file size.
 *
 * @author Ng Yu Mei 32423454
 * @version 1.0.0
 */
public abstract class Animal{


    private  AnimalTypeTesting animalType;

    /**
    * the file name of the picture of the animal
    */
    private String filename;
    /**
    * the number of animal will in the picture
    */
    private int animalNumber;

    /**
    * the width of the picture of the animal
    */
    private int width;

    /**
    * the height of the picture of the animal
    */
    private int height;

    /**
     * Constructor for Animal.
     */

    public Animal(int animalNumber, int width, int height){
        this.animalNumber =animalNumber;
        this.width = width;
        this.height = height;
    }

    public AnimalTypeTesting getAnimalType(){

        return animalType;
    }

    /**
     * Returns a String shows the file name of the animal
     *
     * @return A String shows the file name of the animal.
     */

    public String getFilename(){
        return filename;
    }

    public void setAnimalType(AnimalTypeTesting type){
        animalType = type;
    }
    
     /**
     * Set the file name of the picture
     * @param path the file name of the picture
     */
    public void setFilename(String path){
        filename = path;
    }

    /**
     * Returns an Integer shows the number of animal in the picture
     *
     * @return An Integer shows the number of animal in the picture
     */
    public int getAnimalNumber(){

        return animalNumber;
    }

     /**
     * Set the number of the animal that will in the picture
     * @param num the number of the animal the picture
     */
    public void setAnimalNumberr(int num){
        animalNumber = num;
    }

    /**
     * Returns an Integer shows the width of the picture set
     *
     * @return An Integer shows the width of the picture set
     */
    public int getWidth(){
        return width;
    }
    /**
     * Set the width of the picture
     * @param width the width of the picture
     */
    public void setWidth(int width){
        this.width = width;
    }

    /**
     * Returns an Integer shows the height of the picture set
     *
     * @return An Integer shows the height of the picture set
     */
    public int getHeight(){
        return height;
    }

     /**
     * Set the height of the picture
     * @param height the height of the picture
     */
    public void setHeight(int height){
        this.height = height;
    }

    
}
