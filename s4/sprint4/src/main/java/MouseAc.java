import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Concrete class that implement MouseListener to customize action to handle mouse events.
 * It contains all functions that is required to support MouseListener
 *
 * @author Ng Yu Mei 32423454, Amanda Goh 32694113
 * @version 1.0.0
 */
public class MouseAc implements MouseListener{

    private GameMainBoard board;
    private DragonCard dragonCard;
    private Movement movement;


    public MouseAc(GameMainBoard board, DragonCard dragonCard){
        this.board = board;
        this.dragonCard = dragonCard;
       
    
    }

    /**
     *Handle click action, which is flip the dragon card and trigger the movement action
     * 
     * @param event A target that registered with mouse Listener
     */
    @Override
    public void mouseClicked(MouseEvent event) {
       
        dragonCard.getPanel().removeMouseListener(this);
        dragonCard.flip();

        // Trigger the restart of the countdown timer
        board.restartCountdown();

        dragonCard.addPicture();
        dragonCard.getPanel();
        board.addFlipppedCards(dragonCard);

        
        movement = new Movement(board, dragonCard);
        movement.move();
       
        
    }

    @Override
    public void mousePressed(MouseEvent event) {
       
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        
    }

    @Override
    public void mouseExited(MouseEvent event) {
       
    }

  

}
