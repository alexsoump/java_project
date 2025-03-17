package src.view;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import src.model.Board.Board;
import src.model.Card.Palaces;
import src.model.Finding.*;
import src.model.Pawn.Pawn;
import src.model.Player.Player;



public class GraphicUI extends JFrame {
    private int deckSize = -1;
    private String turn = "Player 1";
    private int P1Frescoes = 0;
    private int P2Frescoes = 0;
    private int P1Points = 0, P2Points = 0, P1SnakeGoddessCount = 0, P2SnakeGoddessCount = 0;

    private boolean P1hasRing = false, P1hasRuto = false, P1hasKosmima = false, P1hasDiskos = false;
    private boolean P2hasRing = false, P2hasRuto = false, P2hasKosmima = false, P2hasDiskos = false;




    // Menu bar and menu items
    private JMenuBar menuBar;
    private JMenu menuGame;
    private JMenuItem newGame, saveGame, loadGame, exitGame;

    // Panels for layout
    private JPanel player1Panel, player2Panel, boardPanel, controlPanel;
    private JPanel player1CardPanel, player1RightPanel, player1SpecialsPanel;
    private JPanel player2CardPanel, player2RightPanel, player2SpecialsPanel;

    // Labels for player info
    private JLabel player1Info, player2Info;
    private JLabel deckInfo = new JLabel(), turnInfo, deckLabel;
    private JLabel diskosLabel, ringLabel, kosmimaLabel, rutoLabel;
    private JLabel diskosLabel2, ringLabel2, kosmimaLabel2, rutoLabel2;
    private JLabel P1SnakeGoddess, P2SnakeGoddess;
    private JLabel[][] pawnLabels; // For displaying pawn images on paths

    private JLayeredPane layeredPane;
    // Buttons for actions
    private JButton[] P1Cards;
    private JButton[] P2Cards;
    private JButton viewFrescoesP1, viewFrescoesP2;

    /**
     * Constructs the GraphicUI object which initializes and sets up the main graphical user interface
     * for the game.
     * The constructor sets up the frame's properties such as title, size, layout, and default close
     * operation. It also initializes the menu bar and panels and makes the frame visible.
     *
     */
    public GraphicUI() {
        try{
            this.deckSize = 100;
            setTitle("Αναζητώντας τα Χαμένα Μινωικά Ανάκτορα");
            setSize(1000, 700);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            // Initialize Components
            initMenuBar();
            initPanels();



            // Set visibility
            setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading resources: " + e.getMessage(),
                    "Initialization Error", JOptionPane.ERROR_MESSAGE);
            exit(1); // Exit the application if critical resources cannot load
        }
    }

    /**
     * Initializes the menu bar for the GUI.
     * The menu bar is then added to the main frame of the application.
     */
    private void initMenuBar() {
        menuBar = new JMenuBar();
        menuGame = new JMenu("Game");

        newGame = new JMenuItem("New Game");
        saveGame = new JMenuItem("Save Game");
        loadGame = new JMenuItem("Continue Saved Game");
        exitGame = new JMenuItem("Exit Game");

        // Add actions
        exitGame.addActionListener(e -> exit(0));

        // Add menu items
        menuGame.add(newGame);
        menuGame.add(saveGame);
        menuGame.add(loadGame);
        menuGame.add(exitGame);

        menuBar.add(menuGame);
        setJMenuBar(menuBar);
    }

    /**
     * Initializes and configures the main panels and UI components for the game interface.
     * The method sets up the following UI sections:
     * This method configures the layout, styles, and event-handling elements needed for the
     * main visual components of the game. It also integrates all the panels into the frame's
     * primary regions (North, Center, South, and East).
     *
     * @throws IOException if there is an error loading image resources for components.
     */
    private void initPanels() throws IOException{
        // Top: Player 1 Information
        player1Panel = new JPanel();
        player1CardPanel = new JPanel();
        player1RightPanel = new JPanel();
        player1SpecialsPanel = new JPanel();

        player1RightPanel.setLayout(new GridLayout(2, 1));
        player1CardPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Horizontal gap of 5px, no vertical gap
        player1SpecialsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        player1Panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        player1Panel.setBorder(BorderFactory.createTitledBorder("Παίκτης 1"));

        // Add player 1 cards
        P1Cards = new JButton[8];
        for (int i = 0; i < 8; i++) {
            P1Cards[i] = new JButton(); // Initialize button
            P1Cards[i].setBorderPainted(false);
            P1Cards[i].setContentAreaFilled(false);
            P1Cards[i].setFocusPainted(false);
            P1Cards[i].setOpaque(false);
            ImageIcon cardIcon = scaleImageIcon("project_assets/images/cards/zakros" + (i+1) + ".jpg", 50, 70);
            P1Cards[i].setIcon(cardIcon);
            player1CardPanel.add(P1Cards[i]);
        }

        // Player 1 info and button
        player1Info = new JLabel("Το σκορ μου: " + P1Points);
        viewFrescoesP1 = new JButton("Οι Τοιχογραφίες μου");
        player1RightPanel.add(player1Info);
        player1RightPanel.add(viewFrescoesP1);

        JPanel P1Diskos = new JPanel();
        JPanel P1Kosmima = new JPanel();
        JPanel P1Ring = new JPanel();
        JPanel P1Ruto = new JPanel();

        P1Diskos.setBorder(BorderFactory.createLineBorder(Color.RED));
        P1Kosmima.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        P1Ring.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        P1Ruto.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        diskosLabel = new JLabel("Δίσκος");
        kosmimaLabel = new JLabel("Κόσμημα");
        ringLabel = new JLabel("Δαχτυλίδι");
        rutoLabel = new JLabel("Ρύτο");

        P1Diskos.add(diskosLabel);
        P1Kosmima.add(kosmimaLabel);
        P1Ring.add(ringLabel);
        P1Ruto.add(rutoLabel);

        player1SpecialsPanel.add(P1Diskos);
        player1SpecialsPanel.add(P1Kosmima);
        player1SpecialsPanel.add(P1Ring);
        player1SpecialsPanel.add(P1Ruto);

        player1Panel.add(player1CardPanel); // finally add the three panels
        player1Panel.add(player1RightPanel);
        player1Panel.add(player1SpecialsPanel);
        P1SnakeGoddess = new JLabel();
        P1SnakeGoddess.setText(" Αγαλματάκια: " + P1SnakeGoddessCount);
        player1SpecialsPanel.add(P1SnakeGoddess);


        // Bottom: Player 2 information- same as Player 1
        player2Panel = new JPanel();
        player2CardPanel = new JPanel();
        player2RightPanel = new JPanel();
        player2SpecialsPanel = new JPanel(); // Add specials panel

        player2RightPanel.setLayout(new GridLayout(2, 1));
        player2CardPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0)); // Horizontal gap of 5px, no vertical gap
        player2SpecialsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0)); // Layout for specials

        player2Panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        player2Panel.setBorder(BorderFactory.createTitledBorder("Παίκτης 2"));

        // Add player 2 cards
        P2Cards = new JButton[8];
        for (int i = 0; i < 8; i++) {
            P2Cards[i] = new JButton(); // Initialize button
            P2Cards[i].setBorderPainted(false);
            P2Cards[i].setContentAreaFilled(false);
            P2Cards[i].setFocusPainted(false);
            P2Cards[i].setOpaque(false);
            ImageIcon cardIcon = scaleImageIcon("project_assets/images/cards/zakros" + (i+1) + ".jpg", 50, 70);
            P2Cards[i].setIcon(cardIcon);
            player2CardPanel.add(P2Cards[i]);
        }

        // Player 2 info and button
        player2Info = new JLabel("Το σκορ μου: " + P2Points);
        viewFrescoesP2 = new JButton("Οι Τοιχογραφίες μου");
        player2RightPanel.add(player2Info);
        player2RightPanel.add(viewFrescoesP2);

        // Add special panels for Player 2
        JPanel P2Diskos = new JPanel();
        JPanel P2Kosmima = new JPanel();
        JPanel P2Ring = new JPanel();
        JPanel P2Ruto = new JPanel();

        P2Diskos.setBorder(BorderFactory.createLineBorder(Color.RED));
        P2Kosmima.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        P2Ring.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        P2Ruto.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        diskosLabel2 = new JLabel("Δίσκος");
        kosmimaLabel2 = new JLabel("Κόσμημα");
        ringLabel2 = new JLabel("Δαχτυλίδι");
        rutoLabel2 = new JLabel("Ρύτο");

        P2Diskos.add(diskosLabel2);
        P2Kosmima.add(kosmimaLabel2);
        P2Ring.add(ringLabel2);
        P2Ruto.add(rutoLabel2);

        player2SpecialsPanel.add(P2Diskos);
        player2SpecialsPanel.add(P2Kosmima);
        player2SpecialsPanel.add(P2Ring);
        player2SpecialsPanel.add(P2Ruto);
        P2SnakeGoddess = new JLabel();
        P2SnakeGoddess.setText(" Αγαλματάκια: " + P2SnakeGoddessCount);
        player2SpecialsPanel.add(P2SnakeGoddess);

        // Assemble Player 2 panel
        player2Panel.add(player2CardPanel);
        player2Panel.add(player2RightPanel);
        player2Panel.add(player2SpecialsPanel);


        // Center: Board Area
        boardPanel = new BackgroundPanel("project_assets/images/background.jpg");
        boardPanel.setLayout(new GridLayout(4, 10)); // Maintain the grid layout
        boardPanel.setBorder(BorderFactory.createTitledBorder("Game Board"));

        // Bottom Control Panel
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(0, 1));
        deckInfo.setText("Available Cards: " + String.valueOf(deckSize));
        turnInfo = new JLabel("Turn: " + turn);
        deckLabel = new JLabel();
        ImageIcon deckIcon = scaleImageIcon("project_assets/images/cards/backCard.jpg", 100, 150); // Replace with your image path
        deckLabel.setIcon(deckIcon);
        deckLabel.setHorizontalAlignment(JLabel.CENTER); // Center the image horizontally
        controlPanel.add(deckInfo);
        controlPanel.add(turnInfo);
        controlPanel.add(deckLabel);

        // Add panels to frame
        add(player1Panel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(player2Panel, BorderLayout.SOUTH);
        add(controlPanel, BorderLayout.EAST);

        populateBoard();

    }

    /**
     * Populates the game board with visual elements including path icons, palace icons,
     * and score labels.
     */
    private void populateBoard() {
        // Path scores
        int[] scores = {-20, -15, -10, 5, 10, 15, 30, 35, 50};

        // Preload and scale path icons
        ImageIcon knossosIcon = scaleImageIcon("project_assets/images/paths/knossos.jpg", 65, 60);
        ImageIcon maliaIcon = scaleImageIcon("project_assets/images/paths/malia.jpg", 65, 60);
        ImageIcon phaistosIcon = scaleImageIcon("project_assets/images/paths/phaistos.jpg", 65, 60);
        ImageIcon zakrosIcon = scaleImageIcon("project_assets/images/paths/zakros.jpg", 65, 60);

        // Preload palace icons
        ImageIcon palaceKnossos = scaleImageIcon("project_assets/images/paths/knossosPalace.jpg", 85, 80);
        ImageIcon palaceMalia = scaleImageIcon("project_assets/images/paths/maliaPalace.jpg", 85, 80);
        ImageIcon palacePhaistos = scaleImageIcon("project_assets/images/paths/phaistosPalace.jpg", 85, 80);
        ImageIcon palaceZakros = scaleImageIcon("project_assets/images/paths/zakrosPalace.jpg", 85, 80);

        // Use absolute positioning
        boardPanel.setLayout(null);

        // Layered pane for visual layering
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 800, 800); // Adjust size based on board dimensions
        boardPanel.add(layeredPane);

        // Pawn labels array
        pawnLabels = new JLabel[4][2];


        // **Offsets to fix alignment**
        int xOffset = 50; // Adjust horizontal starting point
        int yOffset = 35; // Adjust vertical starting point

        // Loop through paths
        for (int path = 0; path < 4; path++) {
            ImageIcon defaultIcon = null;
            ImageIcon palaceIcon = null;

            // Select icons based on path
            switch (path) {
                case 0:
                    defaultIcon = knossosIcon;
                    palaceIcon = palaceKnossos;
                    break;
                case 1:
                    defaultIcon = maliaIcon;
                    palaceIcon = palaceMalia;
                    break;
                case 2:
                    defaultIcon = phaistosIcon;
                    palaceIcon = palacePhaistos;
                    break;
                case 3:
                    defaultIcon = zakrosIcon;
                    palaceIcon = palaceZakros;
                    break;
            }

            // Add cells
            for (int i = 0; i < 8; i++) {
                JLabel cellLabel = new JLabel(defaultIcon);
                // Apply offsets
                cellLabel.setBounds(xOffset + 70 * i, yOffset + 100 * path, 65, 60);
                layeredPane.add(cellLabel, JLayeredPane.DEFAULT_LAYER);

                // Add score labels
                JLabel scoreLabel = new JLabel(scores[i] + " pts", SwingConstants.CENTER);
                scoreLabel.setFont(new Font("Arial", Font.BOLD, 10));
                scoreLabel.setBounds(xOffset + 70 * i, yOffset + 100 * path + 60, 65, 15);
                layeredPane.add(scoreLabel, JLayeredPane.DEFAULT_LAYER);
            }

            // Add palace
            JLabel palaceLabel = new JLabel(palaceIcon);
            // Apply offsets
            palaceLabel.setBounds(xOffset + 70 * 8, yOffset + 100 * path, 85, 80);
            layeredPane.add(palaceLabel, JLayeredPane.DEFAULT_LAYER);
        }

        // Refresh GUI
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    /**
     * Sets the pawn's graphical representation for a player on a specific path and position.
     * If the pawn's JLabel has not been initialized, it creates a new JLabel with an icon
     * and adds it to the layeredPane.
     *
     * @param player   The index of the player (e.g., 0 or 1).
     * @param path     The palace path, represented by the Palaces enum.
     * @param position The position along the specified palace path where the pawn should be placed.
     */
    public void setPawn(int player, Palaces path, int position) {
        // Check if the pawn label already exists, initialize if not
        if (pawnLabels[path.ordinal()][player] == null) {
            pawnLabels[path.ordinal()][player] = new JLabel();
            ImageIcon pawnIcon;
            if(player == 0) {
                pawnIcon = scaleImageIcon("project_assets/images/pionia/arch_blue.png", 30, 30);
            }else{
                pawnIcon = scaleImageIcon("project_assets/images/pionia/arch_red.png", 30, 30);
            }
            pawnLabels[path.ordinal()][player].setIcon(pawnIcon);

            // Add pawn to layeredPane
            layeredPane.add(pawnLabels[path.ordinal()][player], JLayeredPane.PALETTE_LAYER);
        }

        // Calculate pawn position based on grid
        int xOffset = 70 * position; // Horizontal offset for the position
        int yOffset = 100 * path.ordinal() + 10 + (player * 15); // Vertical offset for the path and player

        // Update pawn position and visibility
        pawnLabels[path.ordinal()][player].setBounds(xOffset, yOffset, 30, 30);
        pawnLabels[path.ordinal()][player].setVisible(true);
    }


    /**
     * Updates the game board and interface to reflect the current state of the game.
     * This method updates the players' special findings, pawn positions, pawn visibility,
     * player cards, snake goddesses, and scores on the board.
     *
     * @param players            An array of Player objects representing the players in the game.
     * @param board              A Board object representing the current state of the game board.
     * @param currentPlayerIndex The index of the player whose turn it currently is.
     */
    public void updateBoard(Player[] players, Board board, int currentPlayerIndex) {
        // Update Player 1's Specials
        updateSpecialFindings(players[0], 0);

        // Update Player 2's Specials
        updateSpecialFindings(players[1], 1);

        // Update Pawns on the Board
        updatePawnPositions(players, board);

        // Toggle Pawn Visibility
        togglePawnVisibility(currentPlayerIndex);

        // Update the cards
        updatePlayerCards(0, players);
        updatePlayerCards(1, players);


        // Update Snake Goddesses
        updateSnakeGoddesses(players);

        // Update Scores
        updatePoints(players);
    }

    /**
     * Updates the player's card icons in the GUI based on their current hand.
     *
     * @param playerIndex The index of the player (0 or 1).
     * @param players The array of players in the game.
     */
    public void updatePlayerCards(int playerIndex, Player[] players) {
        // Select the correct player and card buttons
        Player player = players[playerIndex];
        JButton[] cardButtons = (playerIndex == 0) ? P1Cards : P2Cards;

        // Loop through the player's hand and update card icons
        for (int i = 0; i < 8; i++) {
            if (player.chooseCard(i) != null) {
                // Get the card name and create its image path
                String cardName = player.chooseCard(i).toString(); // toString() returns the card's name
                String imagePath = "project_assets/images/cards/" + cardName + ".jpg";

                // Update the button icon
                ImageIcon cardIcon = scaleImageIcon(imagePath, 50, 70);
                cardButtons[i].setIcon(cardIcon);
                cardButtons[i].setVisible(true); // Make sure it's visible
            } else {
                // If no card, clear the icon and hide the button
                cardButtons[i].setIcon(null);
                cardButtons[i].setVisible(false);
            }
        }

        // Refresh the GUI panel to reflect changes
        if (playerIndex == 0) {
            player1CardPanel.revalidate();
            player1CardPanel.repaint();
        } else {
            player2CardPanel.revalidate();
            player2CardPanel.repaint();
        }
    }

    /**
     * Updates the scores and display information for both players in the game.
     *
     * @param players An array of Player objects representing the two players in the game.
     */
    private void updatePoints(Player[] players) {
        players[0].calculateScore();
        players[1].calculateScore();
        this.P1Points = players[0].getScore();
        this.P2Points = players[1].getScore();

        // Player 1 Points
        System.out.println("P1 points: " + P1Points);
        player1Info.setText("Το σκορ μου: " + P1Points);

        // Player 2 Points
        System.out.println("P2 points: " + P2Points);
        player2Info.setText("Το σκορ μου: " + P2Points);
    }

    /**
     * Updates the display of the snake goddess counts for both players in the game.
     *
     * @param players An array of Player objects containing the details of the players,
     *                where players[0] represents player 1 and players[1] represents player 2.
     */
    private void updateSnakeGoddesses(Player[] players) {
        P1SnakeGoddess.setText("Αγαλματάκια: " +  players[0].getSnakeGoddessCount());
        P2SnakeGoddess.setText("Αγαλματάκια: " +  players[1].getSnakeGoddessCount());
    }

    /**
     * Scales an image icon to the specified width and height.
     *
     * @param path   The file path to the image.
     * @param width  The desired width for the scaled image.
     * @param height The desired height for the scaled image.
     * @return A new ImageIcon object with the scaled image.
     */
    private ImageIcon scaleImageIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    /**
     * Retrieves the array of buttons associated with Player 1's cards.
     *
     * @return An array of AbstractButton objects representing Player 1's card buttons.
     */
    public AbstractButton[] getP1Cards() {
        return P1Cards; // Return the array of Player 1's card buttons
    }

    /**
     * Retrieves the array of buttons associated with Player 2's cards.
     *
     * @return An array of AbstractButton objects representing Player 2's card buttons.
     */
    public AbstractButton[] getP2Cards() {
        return P2Cards; // Return the array of Player 2's card buttons
    }

    /**
     * Updates the visual representation of special findings for a specific player
     * in the game's graphical user interface, based on the player's currently obtained special items.
     *
     * @param player       The*/
    private void updateSpecialFindings(Player player, int playerPrefix) {// playerPrefix: 0 or 1
        if(playerPrefix == 0) {
            // update Diskos
            if (player.hasSpecial(new SpecialFinding(SpecialFindings.DiskosFaistou))) {
                diskosLabel.setBackground(Color.RED);
            }
            // update Kosmima
            if (player.hasSpecial(new SpecialFinding(SpecialFindings.KosmhmaMaliwn))) {
                kosmimaLabel.setBackground(Color.YELLOW);
            }
            // update Ring
            if (player.hasSpecial(new SpecialFinding(SpecialFindings.DaxtylidiMinwa))) {
                ringLabel.setBackground(Color.WHITE);
            }
            // update Ruto
            if (player.hasSpecial(new SpecialFinding(SpecialFindings.RytoZakrou))) {
                rutoLabel.setBackground(Color.BLUE);
            }
        }else if(playerPrefix == 1) {
            // update Diskos
            if (player.hasSpecial(new SpecialFinding(SpecialFindings.DiskosFaistou))) {
                diskosLabel2.setBackground(Color.RED);
            }
            // update Kosmima
            if (player.hasSpecial(new SpecialFinding(SpecialFindings.KosmhmaMaliwn))) {
                kosmimaLabel2.setBackground(Color.YELLOW);
            }
            // update Ring
            if (player.hasSpecial(new SpecialFinding(SpecialFindings.DaxtylidiMinwa))) {
                ringLabel2.setBackground(Color.WHITE);
            }
            // update Ruto
            if (player.hasSpecial(new SpecialFinding(SpecialFindings.RytoZakrou))) {
                rutoLabel2.setBackground(Color.BLUE);
            }
        }else{
            // Debug print
            System.out.println("updateSpecialFindings: playerPrefix parameter must either be 1 or 2");
        }

    }

    /**
     * Updates the visual positions and visibility of pawns for all players on the game board.
     *
     * @param players An array of Player objects representing the players in the game. Each player's
     *                pawns and their positions are used to update the graphical interface.
     * @param board   The Board object representing the game's current state, which provides additional
     *                context for updating the pawns.
     */
    private void updatePawnPositions(Player[] players, Board board) {
        for (int path = 0; path < 4; path++) { // Loop through paths
            for (int playerIndex = 0; playerIndex < 2; playerIndex++) { // Loop through players
                Player player = players[playerIndex];
                Pawn pawn = player.getPawnForPath(path);

                if (pawn != null) { // Ensure pawn exists
                    boolean isVisible = (pawn.getPosition() >= 0); // Make pawn visible based on position

                    // Null check for pawn labels
                    if (pawnLabels[path][playerIndex] == null) {
                        System.out.println("Label is null for path " + path + " and player " + playerIndex);
                        continue;
                    }

                    pawnLabels[path][playerIndex].setVisible(isVisible); // Show or hide the pawn

                    // Calculate position based on the pawn's position
                    int pos = pawn.getPosition();

                    // Bounds check and dynamic positioning
                    int xOffset = 70 * pos; // Horizontal offset
                    int yOffset = 100 * path + 10 + (playerIndex * 15); // Vertical offset

                    // Update visual position
                    pawnLabels[path][playerIndex].setBounds(xOffset, yOffset, 30, 30); // Absolute position
                } else {
                    System.out.println("Pawn is null at path " + path + " for player " + playerIndex);
                }
            }
        }
        // Refresh UI
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    /**
     * Toggles the visibility of pawns for players on the game board.
     * Hides the pawns for players who are not the current player, making
     * only the current player's pawns visible.
     *
     * @param currentPlayerIndex The index of the player whose turn it currently is.
     */
    public void togglePawnVisibility(int currentPlayerIndex) {
        for (int path = 0; path < 4; path++) { // Loop through paths
            for (int playerIndex = 0; playerIndex < 2; playerIndex++) { // Loop through both players
                if (pawnLabels[path][playerIndex] != null) {
                    // Show pawns for the current player, hide for the other
                    pawnLabels[path][playerIndex].setVisible(playerIndex != currentPlayerIndex);
                }
            }
        }

        // Refresh UI
        layeredPane.revalidate();
        layeredPane.repaint();
    }


    /**
     * Disables the buttons associated with card selections for a specific player.
     * All card buttons linked to the specified player will be disabled, preventing interaction.
     *
     * @param playerIndex The index of the player whose card buttons should be disabled (0 for Player 1, 1 for Player 2).
     */
    public void disableButtons(int playerIndex){
        for(int i = 0; i < 8; i++){
            if(playerIndex == 0) P1Cards[i].setEnabled(false);
            else if(playerIndex == 1) P2Cards[i].setEnabled(false);
        }

    }

    /**
     * Enables the card buttons for a specific player, allowing interaction with their cards.
     *
     * @param playerIndex The index of the player whose card buttons should be enabled (0 for Player 1, 1 for Player 2).
     */
    public void enableButtons(int playerIndex){
        for(int i = 0; i < 8; i++){
            if(playerIndex == 0) P1Cards[i].setEnabled(true);
            else if(playerIndex == 1) P2Cards[i].setEnabled(true);
        }
    }

    /**
     * A specialized JPanel that supports rendering a scaled background image to fill the panel area.
     * This panel provides a visual enhancement by displaying a background image that
     * dynamically scales to fit its size. It overrides the paintComponent method of the
     * JPanel class to ensure that the image is rendered correctly.
     */
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        // Constructor to load the image
        public BackgroundPanel(String fileName) throws IOException {
            backgroundImage = ImageIO.read(new File("project_assets/images/background.jpg"));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Draw image scaled to fit panel
        }
    }

    // Main method to start the GUI
    public static void main(String[] args){
        SwingUtilities.invokeLater(GraphicUI::new);
    }

    /**
     * Retrieves the JButton associated with viewing Player 1's frescoes.
     *
     * @return A JButton object that provides access to the interface for viewing Player 1's frescoes.
     */
    public JButton getViewFrescoesP1(){
        return viewFrescoesP1;
    }

    /**
     * Retrieves the JButton associated with viewing Player 2's frescoes.
     *
     * @return A JButton object that provides access to the interface for viewing Player 2's frescoes.
     */
    public JButton getViewFrescoesP2(){
        return viewFrescoesP2;
    }

    /**
     * Updates the deck information label with the current deck size.
     *
     * @param size The number of cards remaining in the deck.
     */
    public void updateDeckInfo(int size) {
        this.deckSize = size; // Update internal deck size variable

        // Update the label with the new deck size
        deckInfo.setText("Available Cards: " + size); // Dynamically updates the GUI label
    }

    /**
     * Updates the turn information label with the current player's name.
     *
     * @param turn The name of the player whose turn it is.
     */
    public void updateTurnInfo(String turn) {
        this.turn = turn; // Update internal turn variable

        // Update the label with the current player's name
        turnInfo.setText("Turn: " + turn); // Dynamically updates the GUI label
    }

    /**
     * Prompts the users to input their names through input dialogs.
     * If no name is provided or the input is empty, default names
     * ("Player 1" and "Player 2") are assigned.
     *
     * @param parent The parent Component for the input dialogs,
     *               typically used to position the dialogs relative
     *               to the parent.
     * @return An array of Strings containing the names of two players.
     *         The first element represents Player 1's name, and the
     *         second element represents Player 2's name.
     */
    public static String[] selectNames(Component parent) {
        // Prompt players for their names
        String player1 = JOptionPane.showInputDialog(parent, "Enter Player 1 Name:", "Player Name", JOptionPane.PLAIN_MESSAGE);
        String player2 = JOptionPane.showInputDialog(parent, "Enter Player 2 Name:", "Player Name", JOptionPane.PLAIN_MESSAGE);

        // Ensure no empty names
        if (player1 == null || player1.trim().isEmpty()) {
            player1 = "Player 1"; // Default name
        }
        if (player2 == null || player2.trim().isEmpty()) {
            player2 = "Player 2"; // Default name
        }

        // Return names as an array
        return new String[]{player1, player2};
    }



}
