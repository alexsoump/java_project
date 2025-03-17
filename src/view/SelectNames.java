package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectNames {
    private JFrame frame;
    private JTextField player1Field, player2Field;
    private String[] playerNames;

    public SelectNames() {
        frame = new JFrame("Player Name Selection");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        frame.setLayout(new GridLayout(3, 2, 10, 10)); // 3 rows, 2 columns with spacing

        // Labels and text fields for player names
        JLabel player1Label = new JLabel("Player 1 Name:");
        JLabel player2Label = new JLabel("Player 2 Name:");
        player1Field = new JTextField();
        player2Field = new JTextField();

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitListener());

        // Add components to the frame
        frame.add(player1Label);
        frame.add(player1Field);
        frame.add(player2Label);
        frame.add(player2Field);
        frame.add(new JLabel()); // Empty placeholder
        frame.add(submitButton);

        // Make the window visible
        frame.setVisible(true);
    }

    // ActionListener for the Submit button
    private class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name1 = player1Field.getText().trim();
            String name2 = player2Field.getText().trim();

            // Validate input
            if (name1.isEmpty() || name2.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Both players must enter a name!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                playerNames = new String[]{name1, name2}; // Store names in array
                frame.dispose(); // Close the window
            }
        }
    }

    // Method to return player names
    public String[] getPlayerNames() {
        while (playerNames == null) { // Wait until names are submitted
            try {
                Thread.sleep(100); // Check every 100ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return playerNames; // Return the names
    }

    // Main method for testing
    public static void main(String[] args) {
        SelectNames selectNames = new SelectNames();
        String[] names = selectNames.getPlayerNames(); // Retrieve names after input
        System.out.println("Player 1: " + names[0]);
        System.out.println("Player 2: " + names[1]);
    }
}
