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
    private Player x;
    private Player o;
    private Player currPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createButtons();
    }


    private void createButtons() {
        // Hold Buttons in an Array
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

    private void OnClick(View playerTurn) {
        for (Button button : buttons) {
            CharSequence empty = "";
            if (playerTurn == button && button.getText() == empty) {    // Check if spot is empty
                button.setText(currPlayer.playerName);                  // Add player name (X or O)
            }
        }
    }
}
