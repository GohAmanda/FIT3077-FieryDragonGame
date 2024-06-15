public class BabyDragon extends Animal implements AnimalType, Visitor{

/**
 * Concrete class for BabyDragon animal type.
 * It contains all attributes and function required to define the BabyDragon animal that need to put in the dragon cards, squares and caves include file path and file size.
 *
 * @author Ng Yu Mei 32423454
 * @version 1.0.0
 */

    /**
     * Constructor for BabyDragon.
     * @param animalNumber the number of Baby dragon animal will in the picture
     * @param width the width of the picture
     * @param height the height of the picture
     */
    public BabyDragon(int animalNumber, int width, int height){
        super(animalNumber, width, height);
        setAnimalType();
        setFilename();

    }

    public AnimalTypeTesting getAnimalType(){
        return super.getAnimalType();
    }

    /**
     * Returns a String shows the file name of the Baby dragon
     *
     * @return A String shows the file name of the Baby dragon.
     */
    @Override
    public String getFilename(){
        return super.getFilename();
    }

    public void setAnimalType(){
        super.setAnimalType(AnimalTypeTesting.BABY_DRAGON);
    }

    /**
     * Set the file name of the picture that contains Baby dragon animal
     */
    public void setFilename(){
        super.setFilename("Pictures/baby_dragon"+super.getAnimalNumber()+".png");
    }



    /**
     * To validate and accept the animal who want to visit
     * @param animal An instance of animal who implements Visitor interface will visit this Baby dragon class
     * @return
     */
    @Override
    public int validate(Visitor animal) {

        

        return animal.meetBabyDragon(this);
        
    }


     /**
     * Returns An integer represents the number of steps the dragon token will move when the BabyDragon meet BabyDragon
     *@param babyDragon An instance of Baby Dragon
     * @return An integer represents the number of steps the dragon token will move
     */
    @Override
    public int meetBabyDragon(BabyDragon babyDragon) {
        System.out.println("successful");

        return this.getAnimalNumber();
       
    }

     /**
     * Returns An integer represents the number of steps the dragon token will move when the BabyDragon meet Bat
     *@param bat An instance of Bat
     * @return An integer represents the number of steps the dragon token will move
     */
    @Override
    public int meetBat(Bat bat) {
        System.out.println("no successful");
        return 0;
    }

    /**
     * Returns An integer represents the number of steps the dragon token will move when the BabyDragon meet Salamander
     *@param salamander An instance of salamander
     * @return An integer represents the number of steps the dragon token will move
     */
    @Override
    public int meetSalamander(Salamander salamander) {
        System.out.println("no successful");
        return 0;
    }

     /**
     * Returns An integer represents the number of steps the dragon token will move when the BabyDragon meet Spider
     *@param spider An instance of Spider
     * @return An integer represents the number of steps the dragon token will move
     */
    @Override
    public int meetSpider(Spider spider) {
        System.out.println("no successful");
        return 0;
    }

    
}
