package com.example.cj.fightyourfriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        final TextView textView7 = (TextView) findViewById(R.id.textView7);
        final TextView textView8 = (TextView) findViewById(R.id.textView8);
        final TextView textView9 = (TextView) findViewById(R.id.textView9);

        final Button button = findViewById(R.id.button);

        game = new Game();

        // Display current health and role
        game.player1.stats();
        textView8.setText(String.valueOf(game.player1.getHealth()));
        textView9.setText(String.valueOf(game.player2.getHealth()));

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, intermediate.class);
                    i.putExtra("attack", game.player1.attacker);
                    startActivityForResult(i, SECOND_ACTIVITY_REQUEST_CODE);
                }
            });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                game.player1.choices = data.getIntArrayExtra("choices");

                gameCompare();
            }
        }
    }

    public void gameCompare() {
        // Player one in attack phase
        if (game.player1.isAttacker()) {

            // Player two guess attack/block order
            game.player2.choices = game.chooseOrderBot();

            game.executePhase(game.player1, game.player2);

            // Player two in attack phase
        } else {

            // Player two guess attack/block order
            game.player2.choices = game.chooseOrderBot();

            game.executePhase(game.player2, game.player1);
        }

        final TextView textView8 = (TextView) findViewById(R.id.textView8);
        final TextView textView9 = (TextView) findViewById(R.id.textView9);

        game.player1.attacker = !game.player1.attacker;
        game.player2.attacker = !game.player2.attacker;
        final TextView textView6 = (TextView) findViewById(R.id.textView6);

        if (game.player1.health <= 0) {
            textView6.setText("YOU LOSE");
            textView8.setText("0");

        } else if (game.player2.health <= 0){
            textView6.setText("YOU WIN");
            textView9.setText("0");
        } else {
            textView8.setText(String.valueOf(game.player1.getHealth()));
            textView9.setText(String.valueOf(game.player2.getHealth()));
        }

    }
}