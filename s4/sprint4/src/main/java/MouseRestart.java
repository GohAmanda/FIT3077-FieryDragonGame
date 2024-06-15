import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class MouseRestart implements MouseListener{

    private GameMainBoard mainBoard;

    public MouseRestart(GameMainBoard mainBoard){
        this.mainBoard = mainBoard;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            GameMainBoard newBoard = new GameMainBoard(mainBoard.getPlayerArr().size(), false);
            Container contentPane = e.getComponent().getParent().getParent().getParent().getParent();
            contentPane.remove(e.getComponent().getParent().getParent().getParent().getParent());
            for(Dragon dragon : mainBoard.getPlayerArr()){
                Cave cave = new Cave(dragon.getCave().getAnimal());
                cave.setColor(dragon.getCave().getColor());
                Dragon dragon1 = new Dragon(dragon.getPlayerNumber());
                dragon1.setCave(cave);
                cave.addPicture();
                dragon1.setLabel();
                cave.setUpPlayer(dragon1);
                newBoard.addPlayer(dragon1);
            }
            newBoard.initialzeSaveButton();
            newBoard.readGetConfiguration();
            newBoard.setUpPlayerTurnLabel();
            contentPane.add(newBoard.getPanel());
        } catch (FileNotFoundException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (NoSuchMethodException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (InvocationTargetException ex) {
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
