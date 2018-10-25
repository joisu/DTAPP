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
        Button buttonStopQuiz = findViewById(R.id.button_stopquiz);
        //functionality for home button
        buttonStopQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopQuiz();
            }
        });

        //linking highscore textview to XML
        textViewHighScore = findViewById(R.id.textview_highscore);
        loadHighScore();
    }

    //calls finish() on this activity
    private void stopQuiz() {
        Toast.makeText(this, "This should link back to the ReviewActivity", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    private void startQuiz() {
        Intent intent = new Intent(QuizStartActivity.this, QuizActivity.class);
        //it's startActivity() but it accepts a result back.
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

                //stores the score from QuizReviewActivity
                int scoreQuizReview = data.getIntExtra(QuizReviewActivity.TRANSFER_REVIEWSCORE, 0);

                //compares it to the current highscore
                if (scoreQuizReview > highScore) {
                    updateHighScore(scoreQuizReview);
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
