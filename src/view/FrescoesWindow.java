package src.view;

import src.model.Finding.Mural;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FrescoesWindow extends JFrame {

    /**
     * <b>Constructor:</b> Displays the frescoes collected by a player in a scrollable window.<br>
     * <b>Post conditions:</b> Frescoes are shown as images with descriptions.
     *
     * @param frescoes An ArrayList containing the frescoes collected by the player.
     */
    public FrescoesWindow(ArrayList<Mural> frescoes) {
        // Window setup
        setTitle("Player's Frescoes");
        setSize(400, 300); // Small window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this window, not the main game

        // Create panel with vertical layout
        JPanel frescoPanel = new JPanel();
        frescoPanel.setLayout(new BoxLayout(frescoPanel, BoxLayout.Y_AXIS)); // Vertical stacking

        // Scroll pane for the frescoes
        JScrollPane scrollPane = new JScrollPane(frescoPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Add each fresco to the panel
        for (Mural fresco : frescoes) {
            if (fresco != null) { // Ensure fresco is not null
                // Create label with fresco name and points
                JLabel frescoLabel = new JLabel(fresco.toString() + " - " + fresco.getPoints() + " points");

                // Load and scale image
                ImageIcon frescoImage = new ImageIcon("project_assets/images/frescoes/" + fresco.toString() + ".jpg");
                frescoLabel.setIcon(scaleImageIcon(frescoImage, 80, 80)); // Scale image to 80x80

                // Add fresco label to the panel
                frescoPanel.add(frescoLabel);
            }
        }

        // Add the scroll pane to the frame and make it visible
        add(scrollPane);
        setVisible(true);
    }

    public void updateFrescoes(ArrayList<Mural> frescoes) {
        // Get the content pane and clear existing components
        getContentPane().removeAll(); // Clear old frescoes

        // Create a new panel for frescoes
        JPanel frescoPanel = new JPanel();
        frescoPanel.setLayout(new BoxLayout(frescoPanel, BoxLayout.Y_AXIS)); // Vertical stacking

        // Scroll pane for the frescoes
        JScrollPane scrollPane = new JScrollPane(frescoPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Add each fresco to the panel
        for (Mural fresco : frescoes) {
            if (fresco != null) { // Ensure fresco is not null
                // Create label with fresco name and points
                JLabel frescoLabel = new JLabel(fresco.toString() + " - " + fresco.getPoints() + " points");

                // Load and scale image
                ImageIcon frescoImage = new ImageIcon("project_assets/images/frescoes/" + fresco.toString() + ".jpg");
                frescoLabel.setIcon(scaleImageIcon(frescoImage, 80, 80)); // Scale image to 80x80

                // Add fresco label to the panel
                frescoPanel.add(frescoLabel);
            }
        }

        // Add the scroll pane to the frame
        add(scrollPane);

        // Refresh the window
        revalidate(); // Recalculate layout
        repaint();    // Repaint the frame
    }


    /**
     * Utility method to scale images.
     *
     * @param icon   The original icon.
     * @param width  Desired width.
     * @param height Desired height.
     * @return Scaled ImageIcon.
     */
    private ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
}
