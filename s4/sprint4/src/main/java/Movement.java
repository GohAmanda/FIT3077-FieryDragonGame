import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;


/**
 * Concrete class for Movement
 *
 * @author Ng Yu Mei 32423454, Amanda Goh 32694113, Tan Yi Jin 33263213
 * @version 1.0.0
 */

public class Movement {

    private Dragon player;
    private Dragon d;
    private final DragonCard dragonCard;
    private int steps;
    private GameMainBoard gameMainBoard;
    private Boolean isMovement;

    public Movement(GameMainBoard board, DragonCard dragonCard) {
        this.gameMainBoard = board;
        this.player = gameMainBoard.getCurrentPlayer();
        this.dragonCard = dragonCard;
        this.steps = 0;

    }

     /**
     * Check if the dragon is going to move and how many steps the dragon token will move
     */
    public void checkMovement() {

        // with new steps
        steps = ((AnimalType) player.getTotalCurrentPlace().getAnimal()).validate((Visitor) dragonCard.getAnimal());
        System.out.println(player.getTotalCurrentPlace().getAnimal() + "        '''''''''''''''''" + player);
        Object obj = player.getTotalCurrentPlace();
        if(steps != 0 && player.getIsStartMovement() && obj instanceof Square)
        {
            ((Square)obj).setCurretDragon(null);
        }
        isMovement = steps > 0;
    }


    /**
     * Displays a winning message and resets the game view to the main screen.
     */
    public void winning() {

        // Create a message indicating which player won
        String message = "Congratulations! Player " + player.getPlayerNumber() + " won the game!";

        // Display the winning message in a dialog box
        JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);

        // Create a new instance of the App to reset the main screen
        App app = new App();

        // Get the content pane of the parent container
        Container contentPane = gameMainBoard.getPanel().getParent();

        // Remove the current view (game panel)
        contentPane.remove(gameMainBoard.getPanel());

        // Add the new view (main screen panel)
        contentPane.add(app.getPanel());

        // Repaint the content pane to reflect the changes
        contentPane.revalidate();
        contentPane.repaint();

    }

    public void move() {

        checkMovement();
        Timer timer = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                if (steps == 0) {
                    ((Timer) e.getSource()).stop();

                    
                    // with new steps
                    if(player.getTotalPathIndex() == player.getCaveIndex() && player.getIsStartMovement() 
                        && player.getTotalPathSteps() == 0)
                    {
                        winning();
                    }
                    else
                    {
                        Object obj = player.getTotalCurrentPlace();
                        
                        if(player.getIsStartMovement() && obj instanceof Square)
                        {
                            ((Square)obj).setCurretDragon(player);       
                        }
                        if(d != null)
                        {
                            Object dLoc = d.getTotalCurrentPlace();
                            if(dLoc instanceof Square && d.getIsStartMovement())
                            {
                                ((Square)dLoc).setCurretDragon(d); 
                            }
                        }
                        if(isMovement == false)
                        {
                            gameMainBoard.setDefaultCurrentPlayerIndex();
                            gameMainBoard.unFlipCard();
                            gameMainBoard.getPanel();
                        }
                    }



                } 
                else {

                    player.getTotalCurrentPlace().getPic().revalidate();
                    player.getTotalCurrentPlace().getPic().repaint();

                    // with new steps
                    // get final index
                    // if index = 24 + 3
                    int finalIndex = player.getTotalPathIndex() + steps;
                    int currIndex = player.getTotalPathIndex();
                    
                    // loop through c is there a cave
                    if(steps > 0)
                    {
                        for(int i = currIndex + 1; i <= finalIndex; i++)
                        {
                            currIndex = i;
                            // if index out of bounds
                            if(i >= player.getTotalPath().size())
                            {
                                currIndex = i - player.getTotalPath().size();
                            }
                            Object loc = player.getTotalPath().get(currIndex);
                            // if cave && not self cave, isCave = true 
                            if(loc instanceof Cave && currIndex != player.getCaveIndex())
                            {
                                finalIndex += 1;
                            }

                            // if detect self cave but not at final loc && totalsteps==0
                            else if(currIndex == player.getCaveIndex() && currIndex != finalIndex && player.getTotalPathSteps()==0)
                            {
                                System.out.println(currIndex +"oo"+ finalIndex);
                                steps = 0;
                                isMovement = false;
                            }

                            // if selfcave but totalstep !=0
                            else if(currIndex == player.getCaveIndex() && player.getTotalPathSteps()!=0)
                            {
                                finalIndex += 1;
                            }
                        }
                    }
                    
                    else if(steps < 0)
                    {
                        // index = 1 - 3 = 24
                        for(int j = player.getTotalPathIndex() - 1; j >= 0; j--)
                        {
                            // if index < 0
                            if(j < 0)
                            {
                                currIndex = j + player.getTotalPath().size();
                            }
                            Object loc = player.getTotalPath().get(currIndex);
                            if(loc instanceof Cave)
                            {
                                finalIndex -= 1;
                            }
                        }
                    }
                    

                    // check if finalindex in range
                    if(finalIndex >= player.getTotalPath().size())
                    {
                        finalIndex -= player.getTotalPath().size();
                    }
                    else if(finalIndex < 0)
                    {
                        finalIndex += (player.getTotalPath().size());
                    }
                    
                    // if player choose exchange
                    if (steps == 5)
                    {
                        // go forward and backward to check if there any player
                        for (int i = 1; i < player.getTotalPath().size(); i++) {
                            // forward
                            int forwardInt = player.getTotalPathIndex() + i;
                            int backwardInt = player.getTotalPathIndex() - i;
                            if(forwardInt >= player.getTotalPath().size())
                            {
                                forwardInt -= player.getTotalPath().size();
                            }
                            if(backwardInt < 0)
                            {
                                backwardInt += player.getTotalPath().size();
                            }
                            Object objForward = player.getTotalPath().get(forwardInt);
                            Object objBackward = player.getTotalPath().get(backwardInt);                        
                            
                            if(steps > 0)
                            {
                                // forward
                                if (objForward instanceof Square && ((Square)objForward).getCurrentDragon()!=null
                                    && ((Square)objForward).getCurrentDragon()!= player) {
                                    Square sForward = (Square) objForward; // Cast to Square
                            
                                    // if other player on the square
                                    if (sForward.getCurrentDragon() != null) {
                                        d = sForward.getCurrentDragon();
                                        // swap path index between players
                                        player.incrementIndex(i);
                                        d.decrementIndex(i);
                                        steps = 0;
                                        isMovement = false;
                                        break;
                                    }
                                }

                                // backward
                                else if(objBackward instanceof Square && ((Square)objBackward).getCurrentDragon()!=null
                                    && ((Square)objBackward).getCurrentDragon()!= player)
                                {
                                    Square sBackward = (Square) objBackward; // Cast to Square
                            
                                    // if other player on the square
                                    if (sBackward.getCurrentDragon() != null) {
                                        d = sBackward.getCurrentDragon();
                                        // swap path index between players
                                        player.decrementIndex(i);
                                        d.incrementIndex(i);
                                        steps = 0;
                                        isMovement = false;
                                        break;
                                    }

                                }
                            }
                            
                        }
                        steps = 0;
                        isMovement = false;
                    }

                    else if(steps > 0)
                    {
                        player.setStartMovement();


                        // set invalid index
                        // if finalIndex has player
                        Object finalLoc = player.getTotalPath().get(finalIndex);
                        if(finalLoc instanceof Square)
                        {
                            Square s = (Square)finalLoc;
                            if(s.getCurrentDragon() != null)
                            {
                                steps = 0;
                                isMovement = false;
                            }
                        }                   
                        
                        // set valid index
                        if(steps > 0 && isMovement == true)
                        {
                            // if next position is cave && not self cave
                            int nextI = player.getTotalPathIndex()+1;
                            if(nextI >= player.getTotalPath().size())
                            {
                                nextI -= player.getTotalPath().size();
                            }
                            Object nextPosition = player.getTotalPath().get(nextI);
                            
                            if(nextPosition instanceof Cave && nextI != player.getCaveIndex())
                            {
                                player.incrementIndex(2);
                            }
                            // if next position is self cave
                            else if(nextPosition instanceof Cave && nextI == player.getCaveIndex() 
                                && player.getTotalPathSteps() - steps != 0)
                            {
                                player.incrementIndex(2);
                            }
                            else
                            {
                                player.incrementIndex(1);
                            }
                            steps -= 1;
                        }
                    }

                    // if pirate dragon
                    else if(steps < 0)
                    {
                        if (player.getIsStartMovement())
                        {
                            // set invalid index
                            // if now in cave
                            Object isCaveNow = player.getTotalCurrentPlace();
                            System.out.println(isCaveNow+"^^^^^^^^^^^^^^^^^");
                            Object finalLoc = player.getTotalPath().get(finalIndex);
                            if(isCaveNow instanceof Cave)
                            {
                                steps = 0;
                                isMovement = false;
                            }
                            // if finalIndex has player
                            else if(finalLoc instanceof Square)
                            {
                                Square s = (Square)finalLoc;
                                System.out.println(finalIndex+ "back-------------" + player.getTotalPathIndex()+finalIndex);
                                if(s.getCurrentDragon() != null)
                                {
                                    steps = 0;
                                    isMovement = false;
                                }
                            }
                            if(steps < 0)
                            {
                                // if next position is cave && not self cave
                                int nextI = player.getTotalPathIndex()-1;
                                if(nextI < 0)
                                {
                                    nextI += player.getTotalPath().size();
                                }
                                Object nextPosition = player.getTotalPath().get(nextI);
                                
                                if(nextPosition instanceof Cave)
                                {
                                    player.decrementIndex(2);
                                }
                                else
                                {
                                    player.decrementIndex(1);
                                }
                                steps += 1;
                                }
                            }
                            else
                            {
                                steps = 0;
                                isMovement = false;
                            }
                    }

                    player.getTotalCurrentPlace().addPlayer(player);

                    if(d != null)
                    {
                        d.getTotalCurrentPlace().addPlayer(d);
                    }

                }

            }

        });

        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
                    
    }
}