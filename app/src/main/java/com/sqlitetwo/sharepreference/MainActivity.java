package com.sqlitetwo.sharepreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtHighscore, txtScore;
    Button btnPlay, btnReset;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    private final String PREF_NAME = "com.sqlitetwo.sharepreference.Score";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHighscore = findViewById(R.id.txt_high_score);
        txtScore = findViewById(R.id.txt_score);

        btnPlay = findViewById(R.id.btn_play);
        btnReset = findViewById(R.id.btn_reset);

        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = sp.edit();

        int highScore = sp.getInt("high_score", 0);
        txtHighscore.setText("High Score:" + highScore);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int score = random.nextInt(5000);
                txtScore.setText("" + score);

                int getSavedScore = sp.getInt("high_score", 0);
                if (score > getSavedScore) {
                    txtHighscore.setText("High Score" + score);
                    editor.putInt("high_score", score);
                    editor.apply();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putInt("high_score", 0);
                editor.apply();
                txtHighscore.setText("high score:0");
                txtScore.setText("0");
            }
        });


    }
}
