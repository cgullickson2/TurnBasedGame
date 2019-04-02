package com.example.cj.fightyourfriends;

import android.content.Intent;
import android.content.res.Resources;
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
        button.setText(R.string.continue_game);

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

        final TextView textView8 = (TextView) findViewById(R.id.textView8);
        final TextView textView9 = (TextView) findViewById(R.id.textView9);
        final TextView textView6 = (TextView) findViewById(R.id.textView6);
        final TextView textView10 = (TextView) findViewById(R.id.textView10);

        int damage;

        // Player one in attack phase
        if (game.player1.isAttacker()) {

            // Player two guess attack/block order
            game.player2.choices = game.chooseOrderBot();

            damage = game.executePhase(game.player1, game.player2);

            // Dialogue for report
            if ( damage >= 45) {
                textView10.setText(R.string.report_gave_damage_perf);
            } else if ( damage <= -15) {
                textView10.setText(R.string.report_took_damage_parry);
            } else {
                Resources res = getResources();
                textView10.setText(String.format(res.getString(R.string.report_gave_damage), damage));
            }

        // Player two in attack phase
        } else {

            // Player two guess attack/block order
            game.player2.choices = game.chooseOrderBot();

            damage = game.executePhase(game.player2, game.player1);

            // Dialogue for report
            if ( damage >= 45) {
                textView10.setText(R.string.report_took_damage_perf);
            } else if ( damage <= -15) {
                textView10.setText(R.string.report_gave_damage_parry);
            } else {
                Resources res = getResources();
                textView10.setText(String.format(res.getString(R.string.report_took_damage), damage));
            }

        }

        game.player1.attacker = !game.player1.attacker;
        game.player2.attacker = !game.player2.attacker;

        if (game.player1.health <= 0) {
            textView6.setVisibility(View.VISIBLE);
            textView6.setText(R.string.lose_dialogue);
            textView8.setText("0");
            textView10.setVisibility(View.INVISIBLE);

        } else if (game.player2.health <= 0){
            textView6.setVisibility(View.VISIBLE);
            textView6.setText(R.string.win_dialogue);
            textView9.setText("0");
            textView10.setVisibility(View.INVISIBLE);
        } else {
            textView8.setText(String.valueOf(game.player1.getHealth()));
            textView9.setText(String.valueOf(game.player2.getHealth()));
        }

//        if (game.isOver(game)) {
//            gameRestart(game);
//        }
    }

//    public void gameRestart( Game game) {
//
//        final Game g = game;
//
//        final Button button = findViewById(R.id.button);
//        final TextView textView6 = findViewById(R.id.textView6);
//        final TextView textView8 = findViewById(R.id.textView8);
//        final TextView textView9 = findViewById(R.id.textView9);
//
//        button.setText(R.string.play_again);
//
//        game.restartGame(game);
//
//        // TODO: need to figure out multiple onclick listeners
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                textView8.setText("100");
//                textView9.setText("100");
//                textView6.setVisibility(View.INVISIBLE);
//                Intent i = new Intent(MainActivity.this, intermediate.class);
//                i.putExtra("attack", g.player1.attacker);
//                startActivityForResult(i, SECOND_ACTIVITY_REQUEST_CODE);
//            }
//        });
//    }
}