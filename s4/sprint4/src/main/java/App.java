import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Concrete class for App.
 * The starting page showing the start and exit
 *
 * @author Ng Yu Mei 32423454, Amanda Goh 32694113, Yi Jin Tan 33263213
 * @version 1.0.0
 */
public class App {

    // Declare GUI components
    private JPanel panel;
    private JButton startButton;
    private JButton exitButton;
    private JButton contButton;

    /**
     * Main method to run the application.
     *
     * @param args Command-line arguments
     * @throws Exception if an error occurs during the execution
     */
    public static void main(String[] args) throws Exception {

        // Schedule a job for the event-dispatching thread to create and show the GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                // Create an instance of the App
                App app = new App();
                // Create the main application window (JFrame)
                JFrame f = new JFrame("The Fiery Dragon Game");
                // Add a JScrollPane containing the main panel of the app
                JScrollPane scroll = new JScrollPane(app.getPanel());
                f.getContentPane().add(scroll);
                // Set the default close operation
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // Pack the frame to fit the components
                f.pack();
                // Maximize the frame to full screen
                f.setExtendedState(JFrame.MAXIMIZED_BOTH);
                // Make the frame visible
                f.setVisible(true);
            }
        });

    }

    /**
     * Constructor to set up the GUI components.
     */
    public App() {

        // Initialize the main panel
        panel = new JPanel();
        panel.setBackground(new Color(247, 243, 158));
        panel.setLayout(new BorderLayout());

        // Create and set up the title label
        JLabel titleLabel = new JLabel("Welcome to the Fiery Game");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 45));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Image panel holding the image
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(new Color(247, 243, 158));

        // Load the image icon
        String imagePath = "Pictures/startImg.png"; // Shorter filename
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(imagePath));
        JLabel imageLabel = new JLabel(imageIcon);
        imagePanel.add(imageLabel);

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(247, 243, 158));

        // Create and set up the start button
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        startButton.setFocusable(false);
        buttonPanel.add(startButton);

        // Create and set up the exit button
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        exitButton.setFocusable(false);
        buttonPanel.add(exitButton);

        contButton = new JButton("Recover Last Game");
        contButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        contButton.setFocusable(false);
        buttonPanel.add(contButton);
        contButton.addMouseListener(new MouseRecover(panel));

        // Create a center panel with GridBagLayout
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(imagePanel, new GridBagConstraints());
        centerPanel.setBackground(new Color(247, 243, 158));
        centerPanel.add(buttonPanel);

        // Add the center panel to the main panel
        panel.add(centerPanel, BorderLayout.CENTER);

        // Add action listener for the start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create an instance of NumOfPlayer class
                NumOfPlayer num = new NumOfPlayer();

                // Get the content pane of the parent container
                Container contentPane = panel.getParent();

                // Remove the current view (StartPage panel)
                contentPane.remove(panel);

                // Add the new view (cave panel)
                contentPane.add(num.getPanel());

                // Repaint the content pane to reflect the changes
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        // Add action listener for the exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

    }

    /**
     * Returns the main panel of the application.
     *
     * @return A JPanel that represents the main panel of the application
     */
    public JPanel getPanel() {
        return panel;
    }

}
