package com.example.joycehsu.dtapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizStartActivity extends AppCompatActivity {
    //request code helps to identify the result as it is passed through activities
    private static final int REQUESTCODE_QUIZ = 1;
    public static final String SHARED_PREFERENCES = "sharedPreferences";
    public static final String HIGHSCORE_KEY = "highScoreKey";
    private TextView textViewHighScore;
    private int highScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz_start);

        //linking quiz start button to XML
        Button buttonStartQuiz = findViewById(R.id.button_startquiz);
        //functionality for quiz start button
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        //linking home button to XML
        Button buttonHome = findViewById(R.id.button_home);
        //functionality for home button
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome();
            }
        });

        //linking highscore textview to XML
        textViewHighScore = findViewById(R.id.textview_highscore);
        loadHighScore();
    }

    private void goHome() {
        Toast.makeText(this, "This should link back to the ReviewActivity", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(QuizStartActivity.this, ReviewActivity.class);
        startActivity(intent);
    }

    private void startQuiz() {
        Intent intent = new Intent(QuizStartActivity.this, QuizActivity.class);
        //startActivity() but it also returns a result back.
        startActivityForResult(intent, REQUESTCODE_QUIZ);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //checks the requestcode to make sure we're handling the result we think we're handling (the score)
        //needed in the case where we have multiple results being sent over
        if (requestCode == REQUESTCODE_QUIZ) {
            //checks if the request and result was successful
            if (resultCode == RESULT_OK) {
                //stores the score from the QuizActivity as 'score' in this QuizStartActivity
                int score = data.getIntExtra(QuizActivity.TRANSFER_SCORE, 0);
                //compares it to current highScore
                if (score > highScore) {
                    updateHighScore(score);
                }
            }
        }

    }

    private void updateHighScore(int newHighScore) {
        highScore = newHighScore;
        textViewHighScore.setText("Highscore: " + highScore);

        //SharedPreferences interface can store values
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        //editor is needed to modify the values in SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        //key-value pair
        editor.putInt(HIGHSCORE_KEY, highScore);
        editor.apply();
    }

    private void loadHighScore() {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        //sets highScore variable with the key-value pair
        highScore = preferences.getInt(HIGHSCORE_KEY, 0);
        textViewHighScore.setText("Highscore: " + highScore);
    }
}
