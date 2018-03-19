package playconnect4;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Connect4GUI {
    //declaration of Connect4GUI objects

    private JFrame frame;
    private JLabel[][] slots;
    private JButton[] btn;
    //variables used in PlayingBoard
    private int xsize = 7;
    private int ysize = 6;
    private int currentPlayer = 1;                      //Initial player will be Player 1
    
    //Game variables to communicate with top program
    private boolean hasWon = false;
    private boolean isDraw = false;
    private boolean quit = false;
    private boolean newGame = false;
    
    //making of playingBoard and Game
    PlayingBoard playBoard = new PlayingBoard();
    Game game = new Game(playBoard); //create game Game

    public Connect4GUI() {

        frame = new JFrame("CONNECT 4 GAME");

        JPanel panel = (JPanel) frame.getContentPane();
        //USing grid layout to display tiles
        panel.setLayout(new GridLayout(xsize, ysize + 1));

        slots = new JLabel[xsize][ysize];
        btn = new JButton[xsize];

        for (int i = 0; i < xsize; i++) 
        {
            btn[i] = new JButton("" + (i + 1));
            btn[i].setActionCommand("" + i);
            btn[i].addActionListener(
                    new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            
                            int a = Integer.parseInt(e.getActionCommand());
                            int y = playBoard.find_y(a);        //Check for free space in clicked column
                            if (y != -1) {
                                //sets a place to current player
                                if (game.set_and_check(a, y, currentPlayer)) 
                                {
                                    hasWon = true;
                                } 
                                else if (game.draw_game()) 
                                {//checks for drawgame
                                    isDraw = true;
                                } 
                                else 
                                {
                                    //change player
                                    currentPlayer = playBoard.changeplayer(currentPlayer, 2);
                                    frame.setTitle("CONNECT 4 GAME - PLAYER " + currentPlayer + "'s Turn");
                                }
                            } 
                            else 
                            {
                                JOptionPane.showMessageDialog(null, "Choose other column", "THIS COLUMN IS OCCUPIED", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    });
            panel.add(btn[i]);
        }
        for (int column = 0; column < ysize; column++) {
            for (int row = 0; row < xsize; row++) {
                slots[row][column] = new JLabel();
                slots[row][column].setHorizontalAlignment(SwingConstants.CENTER);
                slots[row][column].setBorder(new LineBorder(Color.black));
                panel.add(slots[row][column]);
            }
        }

        //jframe stuff
        frame.setContentPane(panel);
        frame.setSize(
                700, 600);
        frame.setVisible(
                true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateBoard() {//keeps the Connect4GUI in sync with the logggggtjiic and grid
        for (int row = 0; row < xsize; row++) {
            for (int column = 0; column < ysize; column++) {
                if (playBoard.matrix_equals(row, column, 1)) {
                    slots[row][column].setOpaque(true);
                    slots[row][column].setBackground(Color.black);
                    slots[row][column].setIcon(new ImageIcon(Connect4GUI.class.getResource("/playconnect4/supergirl.jpg")));
                }
                if (playBoard.matrix_equals(row, column, 2)) {
                    slots[row][column].setOpaque(true);
                    slots[row][column].setBackground(Color.green);
                    slots[row][column].setIcon(new ImageIcon(Connect4GUI.class.getResource("/playconnect4/superman.jpg")));
                }
            }
        }
    }

    public void showWon() {
        String winner = "PLAYER " + currentPlayer + " WINS";
        int n = JOptionPane.showConfirmDialog(
                frame,
                winner+"\nDo you want to play a new game?",
                winner,
                JOptionPane.YES_NO_OPTION);
        if (n < 1) {
            frame.dispose();
            newGame = true;
        } else {
            frame.dispose();
            quit = true;
        }
    }

    public void showDraw() {
        String winner = "DRAW GAME!!";
        int n = JOptionPane.showConfirmDialog(
                frame,
                "Do you want to play a new game?",
                winner,
                JOptionPane.YES_NO_OPTION);
        if (n < 1) {
            frame.dispose();
            newGame = true;
        } else {
            frame.dispose();
            quit = true;
        }
    }

    public boolean getHasWon() {
        return hasWon;
    }

    public boolean getHasDraw() {
        return isDraw;
    }

    public boolean getQuit() {
        return quit;
    }

    public boolean getNewGame() {
        return newGame;
    }

    public static void main(String[] args) {
        Connect4GUI Gui = new Connect4GUI();
    }
}