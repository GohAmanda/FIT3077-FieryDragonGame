public class Bat extends Animal implements AnimalType, Visitor{


/**
 * Concrete class for Bat animal type.
 * It contains all attributes and function required to define the Bat animal that need to put in the dragon cards, squares and caves include file path and file size.
 *
 * @author Ng Yu Mei 32423454
 * @version 1.0.0
 */
    
    /**
     * Constructor for Bat.
     * @param animalNumber the number of Bat animal will in the picture
     * @param width the width of the picture
     * @param height the height of the picture
     */
    public Bat(int animalNumber, int width, int height){
        super(animalNumber, width, height);
        this.setAnimalType();
        this.setFilename();

    }

    public AnimalTypeTesting getAnimalType(){
        return super.getAnimalType();
    }
    /**
     * Returns a String shows the file name of the Bat
     *
     * @return A String shows the file name of the Bat.
     */
    @Override
    public String getFilename(){
        return super.getFilename();
    }

    public void setAnimalType(){
        super.setAnimalType(AnimalTypeTesting.BAT);
    }
    
    /**
     * Set the file name of the picture that contains Bat animal
     */
    public void setFilename(){
        super.setFilename("Pictures/bat"+super.getAnimalNumber()+".png");
        
    }

    /**
     * To validate and accept the animal who want to visit
     * @param animal An instance of animal who implements Visitor interface will visit this Bat class
     * @return 
     */
    @Override
    public int validate(Visitor animal) {
       
       return animal.meetBat(this);
    }

    /**
     * Returns An integer represents the number of steps the dragon token will move when the bat meet BabyDragon
     *@param babyDragon An instance of Baby Dragon
     * @return An integer represents the number of steps the dragon token will move
     */
    @Override
    public int meetBabyDragon(BabyDragon babyDragon) {
        System.out.println("no successful");
        return 0;
    }

     /**
     * Returns An integer represents the number of steps the dragon token will move when the Bat meet Bat
     *@param bat An instance of Bat
     * @return An integer represents the number of steps the dragon token will move
     */
    @Override
    public int meetBat(Bat bat) {
        System.out.println("successful");
        return this.getAnimalNumber();
    }

    /**
     * Returns An integer represents the number of steps the dragon token will move when the Bat meet Salamander
     *@param salamander An instance of salamander
     * @return An integer represents the number of steps the dragon token will move
     */
    @Override
    public int meetSalamander(Salamander salamander) {
        System.out.println("no successful");
        return 0;
    }

     /**
     * Returns An integer represents the number of steps the dragon token will move when the Bat meet Spider
     *@param spider An instance of Spider
     * @return An integer represents the number of steps the dragon token will move
     */
    @Override
    public int meetSpider(Spider spider) {
        System.out.println("no successful");
        return 0;
    }




}
