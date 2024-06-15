
public class Square extends Path{

    private Dragon currentDragon;
    public Square(Animal newAnimal) {
        super(newAnimal);
        
    }


    public void setCurretDragon(Dragon dragon){
        this.currentDragon = dragon;
    }


    public Dragon getCurrentDragon(){
        return currentDragon;
    }

    

    
}
