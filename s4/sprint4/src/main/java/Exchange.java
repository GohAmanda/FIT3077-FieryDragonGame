public class Exchange extends Animal implements Visitor{

    /**
     * Concrete class for Dragon Pirate animal type.
     * It contains all attributes and function required to define the Dragon Pirate animal that need to put in the dragon cards, squares and caves include file path and file size.
     *
     * @author Ng Yu Mei 32423454, Tan Yi Jin 33263213
     * @version 1.0.0
     */
        
        /**
         * Constructor for DragonPirate.
         * @param animalNumber the number of Dragon Pirate animal will in the picture
         * @param width the width of the picture
         * @param height the height of the picture
         */
        public Exchange(int animalNumber, int width, int height){
            super(animalNumber, width, height);
            setAnimalType();
            setFilename();
    
        }
    
        public AnimalTypeTesting getAnimalType(){
            return super.getAnimalType();
        }
    
        /**
         * Returns a String shows the file name of the DragonPirate
         *
         * @return A String shows the file name of the DragonPirate.
         */
        @Override
        public String getFilename(){
            return super.getFilename();
        }
    
        public void setAnimalType(){
            super.setAnimalType(AnimalTypeTesting.EXCHANGE);
        }
    
        /**
         * Set the file name of the picture that contains Dragon Pirate animal
         */
        public void setFilename(){
            super.setFilename("Pictures/exchange.png");
            
        }
    
      
    
        /**
         * Returns An integer represents the number of steps the dragon token will move when the Exchange meet BabyDragon
         * @param babyDragon An instance of Baby Dragon
         * @return An integer represents the number of steps the dragon token will move
         */
        @Override
        public int meetBabyDragon(BabyDragon babyDragon) {
    
            return (5);
        }
    
         /**
         * Returns An integer represents the number of steps the dragon token will move when the Exchange meet Bat
         * @param bat An instance of Bat
         * @return An integer represents the number of steps the dragon token will move
         */
        @Override
        public int meetBat(Bat bat) {
            return (5);
        }
    
    
         /**
         * Returns An integer represents the number of steps the dragon token will move when the Exchange meet Salamander
         * @param salamander An instance of salamander
         * @return An integer represents the number of steps the dragon token will move
         */
        @Override
        public int meetSalamander(Salamander salamander) {
            return (5);
        }
    
         /**
         * Returns An integer represents the number of steps the dragon token will move when the Exchange meet Spider
         * @param spider An instance of Spider
         * @return An integer represents the number of steps the dragon token will move
         */
        @Override
        public int meetSpider(Spider spider) {
            return (5);
        }
    
        
    
    }
    