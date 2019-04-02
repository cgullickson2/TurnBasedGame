package com.example.cj.fightyourfriends;

        import android.content.Intent;
        import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class intermediate extends AppCompatActivity {

    int[] choices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);

        final TextView textView1 = findViewById(R.id.textView1);
        final TextView textView2 = findViewById(R.id.textView2);
        final TextView textView3 = findViewById(R.id.textView3);
        final TextView textView4 = findViewById(R.id.textView4);
        final TextView textView5 = findViewById(R.id.textView5);

        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final Button button4 = findViewById(R.id.button4);
        final Button button5 = findViewById(R.id.button5);
        final Button button6 = findViewById(R.id.button6);
        final Button button7 = findViewById(R.id.button7);

        choices = new int[3];
        for (int i = 0; i < 3; i++)
            choices[i] = 0;

        // Determine current players role
        final boolean attacking = this.getIntent().getBooleanExtra("attack", false);

        // Initialize choice texts to "choice x"
        textView1.setText(R.string.choice1);
        textView2.setText(R.string.choice2);
        textView3.setText(R.string.choice3);

        // Initialize buttons to attacking or blocking
        if (attacking) {
            button1.setText(R.string.attack_high);
            button2.setText(R.string.attack_high);
            button3.setText(R.string.attack_high);
            button4.setText(R.string.attack_low);
            button5.setText(R.string.attack_low);
            button6.setText(R.string.attack_low);
        } else {
            button1.setText(R.string.block_high);
            button2.setText(R.string.block_high);
            button3.setText(R.string.block_high);
            button4.setText(R.string.block_low);
            button5.setText(R.string.block_low);
            button6.setText(R.string.block_low);
        }

        // Display if attacking or defending with dialogue
        if (attacking) {
            textView4.setText(getString(R.string.attacking));
            textView5.setText(getString(R.string.attack_dialogue));
        } else {
            textView4.setText(R.string.defending);
            textView5.setText(getString(R.string.defend_dialogue));
        }
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                choices[0] = 1;
                if (attacking) {
                    textView1.setText(R.string.attack_high);
                } else {
                    textView1.setText(R.string.block_high);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                choices[1] = 1;
                if (attacking) {
                    textView2.setText(R.string.attack_high);
                } else {
                    textView2.setText(R.string.block_high);
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                choices[2] = 1;
                if (attacking) {
                    textView3.setText(R.string.attack_high);
                } else {
                    textView3.setText(R.string.block_high);
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                choices[0] = 0;
                if (attacking) {
                    textView1.setText(R.string.attack_low);
                } else {
                    textView1.setText(R.string.block_low);
                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                choices[1] = 0;
                if (attacking) {
                    textView2.setText(R.string.attack_low);
                } else {
                    textView2.setText(R.string.block_low);
                }
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                choices[2] = 0;
                if (attacking) {
                    textView3.setText(R.string.attack_low);
                } else {
                    textView3.setText(R.string.block_low);
                }
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("choices", choices);
                setResult(RESULT_OK, i);
                finish();
                return;
            }
        });
    }
}
