package src.controller;

import src.model.Board.Board;
import src.model.Card.*;
import src.model.Finding.*;
import src.model.Player.Player;
import src.model.Round.Round;
import src.view.*;

import javax.swing.*;
import java.util.ArrayList;
import javax.sound.sampled.*;


public class Controller {
    private GraphicUI view;         // GUI View
    private FrescoesWindow frescoesFrame; // the Frescoes frame
    private Board board;            // Board Model
    private Player[] players;       // Two Players
    private Round round;            // Current Round
    private int currentPlayerIndex; // is 0 or 1
    private Clip clip;              // for music

    /**
     * Initializes a new instance of the Controller class, setting up the game board,
     * players, and the initial round. This includes initializing the graphical user
     * interface, creating player objects, dealing initial cards, and configuring
     * event listeners for user interactions.
     */
    public Controller() {
        view = new GraphicUI();
        this.board = new Board(); // Initialize Board
        this.players = createPlayers(); // Prompt for players' names
        players[0].playerIndex = 0;
        players[1].playerIndex = 1;
        this.round = new Round(players, board); // Initialize round
        this.currentPlayerIndex = 0;

        // Deal initial cards and set up event listeners
        shareCards();
        setupListeners();
        updateUI();

        // Enable Player 1's buttons and disable Player 2's buttons
        view.enableButtons(0); // Player 1
        view.disableButtons(1); // Player 2
    }

    /**
     * Prompts players to enter names and creates Player objects.
     */
    private Player[] createPlayers() {
        // Pass the current JFrame instance as the parent component
        String[] names = GraphicUI.selectNames(view); // 'view' is the GUI instance
        Player p1 = new Player(names[0]);
        Player p2 = new Player(names[1]);
        return new Player[]{p1, p2};
    }


    /**
     * Shares 8 cards with each player from the deck.
     * <p>
     *     Preconditions: the deck has cards
     * </p>
     * <p>
     *     Post conditions: the players have cards on their hands
     * </p>
     */
    private void shareCards() {
        for (int i = 0; i < 8; i++) {
            if(board.deck.isDeckEmpty()){
                determineWinner();
            }
            players[0].addCardToHand(board.deck.popDeck());
            players[1].addCardToHand(board.deck.popDeck());
        }
    }

    /**
     * Sets up listeners for UI elements.
     */
    private void setupListeners() {
        // Frescoes buttons
        view.getViewFrescoesP1().addActionListener(e -> {
            if (frescoesFrame == null) { // Initialize only once
                frescoesFrame = new FrescoesWindow(players[0].getFrescoes());
            } else {
                frescoesFrame.updateFrescoes(players[0].getFrescoes()); // Update existing frame
            }
            frescoesFrame.setVisible(true);
        });



        view.getViewFrescoesP2().addActionListener(e -> {
            ArrayList<Mural> frescoes = players[1].getFrescoes(); // Fetch frescoes for Player 2
            new FrescoesWindow(frescoes); // Open the frescoes window
        });



        // Cards listeners
        setupCardListeners();
    }

    /**
     * Sets up action listeners for player cards.
     */
    private void setupCardListeners() {
        for (int i = 0; i < 8; i++) {
            int cardIndex = i;

            // Player 1 cards
            view.getP1Cards()[i].addActionListener(e -> handleCardClick(0, cardIndex)); // Left-click action
            view.getP1Cards()[i].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {
                    if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) { // Right click
                        handleRightClick(0, cardIndex); // Handle right-click action
                    }
                }
            });

            // Player 2 cards
            view.getP2Cards()[i].addActionListener(e -> handleCardClick(1, cardIndex)); // Left-click action
            view.getP2Cards()[i].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {
                    if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) { // Right click
                        handleRightClick(1, cardIndex); // Handle right-click action
                    }
                }
            });
        }
    }

    /**
     * Handles card click events.
     * <p>
     *     Preconditions: the buttons have action listeners
     * </p>
     * <p>
     *     Post conditions: actions to move the pawn, discard the card, etc. are performed
     * </p>
     * @param playerIndex Player index (0 or 1).
     * @param cardIndex Card index.
     */
    private void handleCardClick(int playerIndex, int cardIndex) {
        // initialize variables
        int activePlayerIndex = currentPlayerIndex; // store the active player
        int nextPlayerIndex = (playerIndex == 0) ? 1 : 0;
        Player player = players[playerIndex];
        Card cardSelected = player.chooseCard(cardIndex);

        if (cardSelected == null) { // Check if card is already removed
            JOptionPane.showMessageDialog(view, "Card already used!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean playCard = true;
        round.playTurn(player, cardSelected, playCard);
        if(cardSelected instanceof NumberCard){ // update pawn position it the GUI
            if(((NumberCard) cardSelected).getValue() < player.getLastCard(((NumberCard) cardSelected).getPalace()).getValue()){
                JOptionPane.showMessageDialog(view, "Card value smaller than last card played!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            view.setPawn(activePlayerIndex, cardSelected.getPalace(), ((NumberCard) cardSelected).getValue());
            player.setLastCardPlayed(cardSelected.getPalace(), ((NumberCard) cardSelected));
        }else if(cardSelected instanceof Ariadne){
            if(player.getPawnForPath(cardSelected.getPalace().ordinal()) == null){
                JOptionPane.showMessageDialog(view, "Cannot start a pawn with Ariadne!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            view.setPawn(activePlayerIndex, cardSelected.getPalace(), ((Ariadne) cardSelected).getValue());
        }
        view.enableButtons(nextPlayerIndex); // enable next player's buttons
        //1:
        player.rejectCard(cardIndex); // reject the card
        //2:
        round.completeHand(board.deck, players[playerIndex]); // complete the player's hand
        //3:
        view.updatePlayerCards(playerIndex, players); // update GUI
        // Update GUI
        updateUI();
        round.changeTurn();
        currentPlayerIndex = (currentPlayerIndex + 1) % 2; // Toggle between 0 and 1
        // Check for game over
        if (round.isGameOver()) {
            determineWinner();
        }
        view.disableButtons(playerIndex);
    }

    /**
     * handles the discard option(right click)
     * <p>
     *     Preconditions: the button has a listener
     * </p>
     * <p>
     *     Post conditions: the card is discarded
     * </p>
     * @param playerIndex 0 or 1
     * @param cardIndex 0 to 8
     */
    private void handleRightClick(int playerIndex, int cardIndex){
        boolean playCard = false;
        int nextPlayerIndex = (playerIndex == 0) ? 1 : 0;
        Player player = players[playerIndex];
        Card cardSelected = player.chooseCard(cardIndex);
        view.enableButtons(nextPlayerIndex); // enable next player's buttons

        if (cardSelected == null) { // Check if card is already removed
            JOptionPane.showMessageDialog(view, "Card already used!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cardPalace = cardSelected.getPalace().toString();
        view.togglePawnVisibility(playerIndex);
        round.playTurn(player, cardSelected, playCard);

        player.rejectCard(cardIndex); // reject the card
        round.completeHand(board.deck, players[playerIndex]); // complete the player's hand
        view.updatePlayerCards(playerIndex, players); // update GUI

        round.changeTurn();
        currentPlayerIndex = (currentPlayerIndex + 1) % 2; // Toggle between 0 and 1
        // Update GUI
        updateUI();


        // Check for game over
        if (round.isGameOver()) {

            determineWinner();
        }
        view.disableButtons(playerIndex);

    }


    /**
     * Displays frescoes collected by the specified player.
     * @param playerIndex The index of the player (0 or 1).
     */
    private void showFrescoes(int playerIndex) {
        Player player = players[playerIndex];
        frescoesFrame.updateFrescoes(players[playerIndex].getFrescoes());
        frescoesFrame.setVisible(true);

    }

    /* Music:                       (not working)*/

    private void playMusic(String filePath) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop(); // stop current audio if playing
            }
            // Load the new audio file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start(); // play the clip
        } catch (Exception e) {
            System.err.println("Error playing music: " + e.getMessage());
        }
        }


    /**
     * Determines and displays the winner.
     */
    private void determineWinner() {
        // stop the music
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }

        players[0].calculateScore();
        int score1 = players[0].getScore();
        players[1].calculateScore();
        int score2 = players[1].getScore();

        String winner;
        if (score1 > score2) {
            winner = players[0].getName();
        } else if (score2 > score1) {
            winner = players[1].getName();
        } else {
            winner = "It's a tie!";
        }

        JOptionPane.showMessageDialog(view, "Game Over! Winner: " + winner,
                "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Updates the GUI with the latest game state.
     */
    private void updateUI() {
        view.updateDeckInfo(board.deck.getCardNumber());
        view.updateTurnInfo(round.getCurrentPlayer().getName());
        view.updateBoard(players, board, currentPlayerIndex); // Update pawn positions, findings, etc.

        // play the right music
        if(currentPlayerIndex == 0) {
            playMusic("project_assets/music/Player1.wav");
        }else if(currentPlayerIndex == 1) {
            playMusic("project_assets/music/Player2.wav");
        }
    }



}
