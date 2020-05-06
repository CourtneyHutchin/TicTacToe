package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Fields
    private Button[] buttons;    // Array of buttons
    private TextView playerTurn; // The TextView to display who's turn it is
    private Player x;            // The player who is using "X"
    private Player o;            // The player who is using "O"
    private Player currPlayer;   // The current player during that round



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerTurn = findViewById(R.id.playerTurn);

        createButtons();
        startGame();
    }

    private void startGame() {
        // Set players name to be X and O
        o = new Player("O");
        x = new Player("X");

        currPlayer = o;

        // Put player name into text box
        currPlayerText();
    }

    /*
    Create an array of buttons
     */
    private void createButtons() {
        buttons = new Button[] {
                findViewById(R.id.boxOne),
                findViewById(R.id.boxTwo),
                findViewById(R.id.boxThree),
                findViewById(R.id.boxFour),
                findViewById(R.id.boxFive),
                findViewById(R.id.boxSix),
                findViewById(R.id.boxSeven),
                findViewById(R.id.boxEight),
                findViewById(R.id.boxNine)
        };
    }

    /*
    When a player clicks a button, add their name to the button
     */
    public void onClick(View view) {
        for (Button button : buttons) {
            CharSequence empty = "";
            if (view == button && button.getText() == empty) {    // Check if spot is empty
                button.setText(currPlayer.playerName);                  // Add player name (X or O)
                switchPlayer();
            }
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

    public void newGame(View view) {
        CharSequence empty = "";
        for (Button button : buttons) {
            button.setText(empty);
        }
    }
}