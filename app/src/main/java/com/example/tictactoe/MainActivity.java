package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Fields
    private Button[][] buttons;    // 2D Array of buttons to traverse to find a winner
    private TextView playerTurn;   // The TextView to display who's turn it is
    private Player x;              // The player who is using "X"
    private Player o;              // The player who is using "O"
    private Player currPlayer;     // The current player during that round



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerTurn = findViewById(R.id.playerTurn);

        createButtons();
        startGame();
    }

    /*
    Set the current player and set the text field
     */
    private void startGame() {
        // Set players name to be X and O
        o = new Player("O");
        x = new Player("X");

        // Set the starting player
        currPlayer = x;

        // Put player name into text box
        currPlayerText();
    }

    /*
    Create an array of buttons
     */
    public void createButtons() {
        buttons = new Button[][] {
                {findViewById(R.id.boxOne), findViewById(R.id.boxTwo), findViewById(R.id.boxThree)},
                {findViewById(R.id.boxFour), findViewById(R.id.boxFive), findViewById(R.id.boxSix)},
                {findViewById(R.id.boxSeven), findViewById(R.id.boxEight), findViewById(R.id.boxNine)}
        };
    }

    /*
    When a player clicks a button, add their name to the button and check for a winner
     */
    @SuppressLint("SetTextI18n")
    public void onClick(View view) {
        // Loop through the button array and add the players name to the button they clicked
        for (Button[] button : buttons) {
            for (int cols = 0; cols < buttons[0].length; cols++) {
                CharSequence empty = "";
                if (view == button[cols] && button[cols].getText() == empty) {    // Check if spot is empty
                    button[cols].setText(currPlayer.playerName);                         // Add player name (X or O)
                }
            }
        }

        // Check for a winner or a tie
        if(isWinner("X")) {
            playerTurn.setText("Player X Won!");
            gameOver();
        }
        else if(isWinner("O")) {
            playerTurn.setText("Player O Won!");
            gameOver();
        }
        else if(checkTie()) {
            playerTurn.setText("Tie!");
            gameOver();
        }
        else {
            switchPlayer();
        }
    }

    /*
    Set the PlayerTurn TextView to show the current players turn
     */
    private void currPlayerText() {
        playerTurn.setText("Player " + currPlayer.playerName + "'s Turn");
    }

    /*
    Swap between each player so they each have turns
     */
    private void switchPlayer() {
        if(currPlayer == o) {
            currPlayer = x;
        }
        else {
            currPlayer = o;
        }
        currPlayerText();
    }

    /*
    Reset each button to blank and set text to current player
     */
    public void newGame(View view) {
        // Empty the buttons
        CharSequence empty = "";
        // Loop through and clear all the text from each button
        for (Button[] button : buttons) {
            for (int cols = 0; cols < buttons[0].length; cols++) {
                button[cols].setText(empty);
                button[cols].setEnabled(true);
            }
        }
        // Start a new game
        startGame();
    }

    /*
    Checks vertical, horizontal and diagonal for a winner. If no winner, check for a tie
     */
    private boolean isWinner(String player) {
        if(checkHorizontal(player)) {
            return true;
        }
        if(checkVertical(player)) {
            return true;
        }
        return checkDiagonal(player);
    }

    /*
    Check if every button has been filled
     */
    private boolean checkTie() {
        // Loop through each button and check if they are not empty
        CharSequence empty = "";
        for (Button[] button : buttons) {
            for (int cols = 0; cols < buttons[0].length; cols++) {
                if (button[cols].getText() == empty) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
    Disable every button since the game is over
     */
    private void gameOver() {
        // Loop through and disable every button
        for (Button[] button : buttons) {
            for (int cols = 0; cols < buttons[0].length; cols++) {
                button[cols].setEnabled(false);
            }
        }
    }

    /*
    Check each row to see if there is 3 in a row
     */
    private boolean checkHorizontal(String player) {
        // First [] is column second [] is row
        return buttons[0][0].getText() == player && buttons[0][1].getText() == player && buttons[0][2].getText() == player ||  // Check first row
                buttons[1][0].getText() == player && buttons[1][1].getText() == player && buttons[1][2].getText() == player || // Check second row
                buttons[2][0].getText() == player && buttons[2][1].getText() == player && buttons[2][2].getText() == player;   // Check third row
    }

    /*
    Check each column to see if there is 3 in a row
     */
    private boolean checkVertical(String player) {
        // Check third column
        return buttons[0][0].getText() == player && buttons[1][0].getText() == player && buttons[2][0].getText() == player ||  // Check first column
                buttons[0][1].getText() == player && buttons[1][1].getText() == player && buttons[2][1].getText() == player || // Check second column
                buttons[0][2].getText() == player && buttons[1][2].getText() == player && buttons[2][2].getText() == player;
    }

    /*
    Check left and right diagonally to see if there are 3 in a row
     */
    private boolean checkDiagonal(String player) {
        // Check upward right diagonal
        return buttons[0][0].getText() == player && buttons[1][1].getText() == player && buttons[2][2].getText() == player ||  // Check downward right diagonal
                buttons[0][2].getText() == player && buttons[1][1].getText() == player && buttons[2][0].getText() == player;
    }
}