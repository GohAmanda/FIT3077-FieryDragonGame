
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.yaml.snakeyaml.Yaml;


public class MouseSave implements MouseListener{

    private GameMainBoard gameMainBoard;
    private PrintWriter writer;
    private Yaml yaml;
    private HashMap<String, Object> saveDict = new HashMap<String, Object>();
    private HashMap<Integer, HashMap<String, Object>> volcanoListDict = new HashMap<Integer, HashMap<String, Object>>();
    private HashMap<Integer, HashMap<String, Object>> cardDict = new HashMap<Integer, HashMap<String, Object>>();
    private HashMap<Integer, HashMap<String, Object>> playerDict = new HashMap<Integer, HashMap<String, Object>>();
    public MouseSave(GameMainBoard board) throws FileNotFoundException{
        writer = new PrintWriter("s4\\sprint4\\src\\main\\java\\database.yml");
        yaml = new Yaml();
        this.gameMainBoard = board;
        
       
    
    }
    @Override
    public void mouseClicked(MouseEvent e) {

        ArrayList<VolcanoCard> totalList = gameMainBoard.getTotalList();
        ArrayList<DragonCard> cardList = gameMainBoard.getDragonCardList();
        ArrayList<Dragon> playerList = gameMainBoard.getPlayerArr();
        App app = new App();
        for(int i =0; i < totalList.size(); i++){
           volcanoListDict.put(i, totalList.get(i).getSaveItem());
        }
        for(int j = 0; j< cardList.size(); j++){
            cardDict.put(j, cardList.get(j).getSaveItem());

        }
        for(int k=0; k<playerList.size(); k++){

            playerDict.put(k, playerList.get(k).getSaveItem());
        }

        saveDict.put("totalList", volcanoListDict);
        saveDict.put("cardList", cardDict);
        saveDict.put("playerList", playerDict);
        saveDict.put("currentPlayerIndex", gameMainBoard.getCurrentPlayerIndex());
        yaml.dump(saveDict, writer);


        Container contentPane = gameMainBoard.getPanel().getParent();
        contentPane.remove(gameMainBoard.getPanel());
        contentPane.add(app.getPanel());
        contentPane.revalidate();
        contentPane.repaint();

        
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
