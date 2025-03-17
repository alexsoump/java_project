package src.view;

import javax.swing.*;

public class FindingNotify extends JDialog {
    private boolean playerChoice; // keep or ignore/destroy
    private final String message = "There is a finding in this position!\\nWould you like to add it to your collection?";
    private final String destroyMessage = "Would you like to destroy the finding?";

    /** Constructor
     *
     * @param parent parent
     * @param isArchaeologist archeologist or theseus
     */
    public FindingNotify(JFrame parent, boolean isArchaeologist) {
        super(parent, "Finding Notification", true);
    }

    /**
     * getter for playerChoice
     * @return true if player wants to pick up the finding, false to destroy/ignore
     */
    public boolean getPlayerChoice() {
        return playerChoice;
    }
}
