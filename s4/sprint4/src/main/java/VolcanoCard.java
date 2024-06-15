
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;



/**
 * Concrete class that implement the set up of the Volcano card
 *
 * @author Ng Yu Mei 32423454
 * @version 1.0.0
 */
public class VolcanoCard {

    
    
    private JPanel panel;
    private ArrayList<Square> squareList;
    private ArrayList<String> squareAnimal;
    private int hasIndention;
    private HashMap<String, Object> saveItem;
   
  
    public VolcanoCard(Square animal1, Square animal2, Square animal3){
        
        
        squareList =new ArrayList<>();
        squareAnimal =new ArrayList<>();
        animal1.addPicture();
        squareList.add(animal1);
        animal2.addPicture();
        squareList.add(animal2);
        animal3.addPicture();
        squareList.add(animal3);
        this.hasIndention = 0;
        saveItem = new HashMap<String, Object>();       
       

        panel = new JPanel();
    }

    public JPanel getPanel() {
        this.panel.setPreferredSize(new Dimension((((int)squareList.get(0).getPanel().getPreferredSize().getWidth()))*squareList.size()+10,((int)squareList.get(0).getPanel().getPreferredSize().getHeight())+10));
        return panel;
    }

     /**
     * Add player to the current square
     * @param newSquare The Square instance will be added in the Volcano card
     */
    public void addSquare(Square newSquare){
        squareList.add(newSquare);
    }

    
    public ArrayList<Square> getSquareList() {
        return squareList;
    }

    public HashMap<String, Object> getSaveItem(){

        for(Square square: squareList){
            squareAnimal.add(square.getAnimal().getClass().toString().substring(6));
        }
        saveItem.put("volcano", squareAnimal);
        saveItem.put("hasIndention", hasIndention);
        return saveItem;
    }

    public int getHasIndention(){
        return hasIndention;
    }

    public void setHasIndention(){
        this.hasIndention=1;
    }


    public void setVolcanoCard(){
        panel.setOpaque(true);

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        for(int square=0; square < squareList.size(); square++){
            panel.add(squareList.get(square).getPanel());
        }
        
    }
    
    

}
