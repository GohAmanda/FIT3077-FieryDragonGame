
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import org.yaml.snakeyaml.Yaml;

public class MouseRecover implements MouseListener{

   
    private final int SIZE = 90;
    private final int CARD_SIZE = 80;
    private ArrayList<Integer> playerIndexList = new ArrayList<>();;
    private ArrayList<Integer> playerStepsList = new ArrayList<>();;
    private JPanel parentPanel;
    public MouseRecover(){

    }

    public MouseRecover(JPanel parent){
        this.parentPanel = parent;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        try {
            //read configuration file
            InputStream input = new FileInputStream(new File("s4\\sprint4\\src\\main\\java\\database.yml"));
            Yaml yaml = new Yaml();
            // Load all data stored in configuration file in hashmap format
            HashMap<String, Object> response = yaml.load(input);

           if(response != null){

            //recover player information
            HashMap<String, Object> playerList = (HashMap<String, Object>) response.get("playerList");
            int numPlayer = playerList.size();
           
            int currentPlayerIndex = (int)response.get("currentPlayerIndex");
            GameMainBoard mainBoard = new GameMainBoard(numPlayer, true);
            mainBoard.initialzeSaveButton();
            for(int i=0; i<numPlayer; i++){
                HashMap<String, Object> playerMap = (HashMap<String, Object>) playerList.get(i);
                
                Dragon dragon = new Dragon((int) playerMap.get("number"));
                int caveIndex = (int) playerMap.get("caveIndex");
                int pathIndex = (int) playerMap.get("currentIndex");
                Class animalClass = Class.forName(playerMap.get("cave").toString());
                Class[] paramType = {Integer.TYPE, Integer.TYPE, Integer.TYPE};
                Constructor constructor = animalClass.getConstructor(paramType);
                int playerIndex = pathIndex;
                int playerStep = (int) playerMap.get("totalSteps");

                Object[] params = {1, SIZE, SIZE};
                Animal instance = (Animal) constructor.newInstance(params);
                Cave cave = new Cave(instance);
                HashMap<Integer, Color> playerColor = new HashMap<Integer, Color>();
                playerColor.put(1, Color.orange);
                playerColor.put(2, Color.blue);
                playerColor.put(3, Color.white);
                playerColor.put(4, Color.green);
                cave.setColor(playerColor.get(dragon.getPlayerNumber()));
                dragon.setCave(cave);
                dragon.setLabel();
                dragon.setTotalPathSteps(playerStep);
                dragon.setTotalPathIndex(playerIndex);
                dragon.setCaveIndex(caveIndex);
                playerIndexList.add(playerIndex);
                playerStepsList.add(playerStep);
                
                if(pathIndex !=0){
                    dragon.setStartMovement();
                }
                

                mainBoard.addPlayer(dragon);
                System.out.println("player info" + dragon.getTotalPathIndex());
           }
           mainBoard.setCurrentPlayerIndex(currentPlayerIndex);

           //Recover volcano card and path information
           HashMap<String, Object> totalList = (HashMap<String, Object>) response.get("totalList");
           int squareNum = 0;
           int totalListSize = totalList.size();
           ArrayList<VolcanoCard> volcanoCards = new ArrayList<>();
           ArrayList<Animal> tempList = new ArrayList<>();
           for(int j=0; j< totalListSize; j++){

                HashMap<String, Object> volcanoInformation = (HashMap<String, Object>) totalList.get(j);
                ArrayList<String> squares = (ArrayList<String>) volcanoInformation.get("volcano");
                
                for(int k =0; k< squares.size(); k++){
                    Class animalClass = Class.forName(squares.get(k).toString());
                    Class[] paramType = {Integer.TYPE, Integer.TYPE, Integer.TYPE};
                    Constructor constructor = animalClass.getConstructor(paramType);

                    Object[] params = {1, SIZE, SIZE};
                    Animal instance = (Animal) constructor.newInstance(params);
                    tempList.add(instance);
                    squareNum+=1;
                    }

                VolcanoCard volcano = new VolcanoCard(new Square(tempList.get(0)), new Square(tempList.get(1)), new Square(tempList.get(2)));
                int hasIndention = (int) volcanoInformation.get("hasIndention");
                if(hasIndention ==1){
                    volcano.setHasIndention();
                    mainBoard.addIndentionCard(volcano);
                }
                volcanoCards.add(volcano);
                tempList.clear();
           }

           squareNum = (squareNum - 10) / 2;
           mainBoard.setTotalist(volcanoCards);
           mainBoard.setRowCountVolcano(squareNum);

           HashMap<String, Object> dragonCardList = (HashMap<String, Object>) response.get("cardList");
           int dragonListSize = dragonCardList.size();
           ArrayList<DragonCard> dragonCards = new ArrayList<>();

           for(int m =0; m< dragonListSize; m++){
                HashMap<String, Object> dragonCarMap =(HashMap<String, Object>) dragonCardList.get(m);
                System.out.println();
                Class animalClass = Class.forName(dragonCarMap.get("animal").toString());
                Class[] paramType = {Integer.TYPE, Integer.TYPE, Integer.TYPE};
                Constructor constructor = animalClass.getConstructor(paramType);

                int animalNum = (int) dragonCarMap.get("number");
                Object[] params = {animalNum, CARD_SIZE, CARD_SIZE};
                Animal instance = (Animal) constructor.newInstance(params);
                DragonCard dragonCard = new DragonCard(instance);
                int cardStatus = (int) dragonCarMap.get("status");
                if(cardStatus == 0){
                    dragonCard.unFlip();
                    
                }
                else if(cardStatus == 1){
                    dragonCard.flip();
                    mainBoard.addFlipppedCards(dragonCard);
                }

                mainBoard.addDragonCard(dragonCard);

           }

          

            mainBoard.initialzeSaveButton();
            mainBoard.setUpMainBoard();
            mainBoard.setUpPlayerTurnLabel();
            for(Dragon dragon: mainBoard.getPlayerArr()){
                dragon.setTotalPathIndex(playerIndexList.get(dragon.getPlayerNumber()-1));
                dragon.setTotalPathSteps(playerStepsList.get(dragon.getPlayerNumber()-1));
                dragon.getTotalCurrentPlace().getPic().revalidate();
                dragon.getTotalCurrentPlace().getPic().repaint();
                System.out.println(dragon.getSaveItem());
                //System.out.println(dragon.getPlayerNumber() + "now at" + dragon.getTotalPathIndex());
                dragon.getTotalCurrentPlace().getPic().add(dragon.getLabel(),BorderLayout.SOUTH);
                dragon.getTotalCurrentPlace().getPic().revalidate();
                dragon.getTotalCurrentPlace().getPic().repaint();
               }

            Container contentPane = parentPanel.getParent();
            contentPane.remove(parentPanel);
            contentPane.add(mainBoard.getPanel());
            contentPane.revalidate();
            contentPane.repaint();

            }
          
            
        } catch (FileNotFoundException ex) {
         }catch (ClassNotFoundException ex) {
        } catch (NoSuchMethodException ex) {
        } catch (SecurityException ex) {
        } catch (IllegalArgumentException ex) {
        } catch (InvocationTargetException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
     
    }

}
