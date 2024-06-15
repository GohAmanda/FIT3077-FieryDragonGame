import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * To let player choose number of players
 * @author Yi Jin Tan 33263213
 * @version 1.0.0
 */
public class NumOfPlayer {

    private JPanel panel;
    private JLabel titleLabel;
    private JPanel playerChoicePanel;
    private JLabel[] playerLabels;

    /**
     * Constructor of the class
     * Setup the page of the class
     */
    public NumOfPlayer() {
        panel = new JPanel();
        panel.setBackground(new Color(247, 243, 158));
        //panel.setLayout(new BorderLayout());

        titleLabel = new JLabel("Choose Number of Players:", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        playerChoicePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 300)); // Display 3 choices horizontally
        playerChoicePanel.setBackground(new Color(247, 243, 158));

        panel.add(playerChoicePanel, BorderLayout.CENTER);

        initializePlayerChoices();
    }

    /**
     * Added the choices to the page
     */
    public void initializePlayerChoices() {
        int[] numPlayersChoices = {2, 3, 4};
        playerLabels = new JLabel[numPlayersChoices.length];

        for (int i = 0; i < 3; i++) {
            final int numPlayers = numPlayersChoices[i];
            JPanel jp = new JPanel();
            jp.setPreferredSize(new Dimension(150, 150));
            JLabel label = new JLabel(numPlayers + "");
            label.setFont(new Font("SansSerif", Font.PLAIN, 75));
            label.setForeground(Color.black);
            jp.add(label, BorderLayout.CENTER);
            jp.setBackground(Color.white);

            Border border = BorderFactory.createLineBorder(Color.black, 5);
            jp.setBorder(border);


            jp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle mouse click event
                    // For demonstration, let's print the selected number of players
                    System.out.println("Selected number of players: " + numPlayers);
                
                    // You can perform any action here, such as starting the cave selection process
                    CaveSelection cave = new CaveSelection(numPlayers);
                
                    cave.setTitleLayout();
                    cave.setCaveLayout();
                    cave.startPage();

                    // Get the content pane of the current window
                    Container contentPane = panel.getParent();
                    
                    // Remove the current view (NumOfPlayer panel)
                    contentPane.remove(panel);
                    
                    // Add the new view (CaveSelection panel)
                    contentPane.add(cave.getPanel());
                    
                    // Repaint the content pane to reflect the changes
                    contentPane.revalidate();
                    contentPane.repaint();
                }
            });

            playerLabels[i] = label;
            playerChoicePanel.add(jp);
        }
    }

    /**
     * Getter to get the panel of this class
     * @return
     */
    public JPanel getPanel() {
        return panel;
    }

}
