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

        if (this.getIntent().getBooleanExtra("attack", false)) {
            textView4.setText("You are Attacking");
        } else {
            textView4.setText("You are Defending");
        }
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView1.setText("Attack Chosen");
                choices[0] = 1;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView2.setText("Attack Chosen");
                choices[1] = 1;
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView3.setText("Attack Chosen");
                choices[2] = 1;
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView1.setText("Defense Chosen");
                choices[0] = 0;
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView2.setText("Defense Chosen");
                choices[1] = 0;
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView3.setText("Defense Chosen");
                choices[2] = 0;
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
