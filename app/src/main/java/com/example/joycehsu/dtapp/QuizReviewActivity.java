package com.example.joycehsu.dtapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class QuizReviewActivity extends AppCompatActivity {
    public static final String TRANSFER_REVIEWSCORE = "transferReviewScore";
    private TextView textViewQuizReviewScore;
    private RatingBar ratingBar;
    private TextView textViewQuizReviewFeedback;
    private Button buttonQuizReviewFinish;
    private long backPressedTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_review);

        //receive intent
        Intent intent = getIntent();

        final int reviewscore = intent.getIntExtra("score", 0);
        int questionCountTotal = intent.getIntExtra("questioncounttotal", 0);
        float scoreratio = (float) reviewscore / (float) questionCountTotal;

        textViewQuizReviewScore = (TextView) findViewById(R.id.textview_quizreviewscore);
        //show score
        textViewQuizReviewScore.setText("You scored: " + reviewscore + "/" + questionCountTotal);

        ratingBar = (RatingBar) findViewById(R.id.ratingbar_quizreviewratingbar);
        //just in case (also done in XML)
        ratingBar.setStepSize(1);
        ratingBar.setNumStars(5);
        //show relevant number of stars
        ratingBar.setRating(scoreratio * 5);

        textViewQuizReviewFeedback = (TextView) findViewById(R.id.textview_quizreviewfeedback);
        //show relevant feedback based on score
        if (scoreratio <= 0.21) {
            textViewQuizReviewFeedback.setText("You should definitely review ALL the content again...");
        } else if (scoreratio > 0.21 &&
                scoreratio <= 0.41) {
            textViewQuizReviewFeedback.setText("You should probably review the content again.");
        } else if (scoreratio > 0.41 &&
                scoreratio <= 0.61) {
            textViewQuizReviewFeedback.setText("Passable, but brush up on your knowledge!");
        } else if (scoreratio > 0.61 &&
                scoreratio <= 0.81) {
            textViewQuizReviewFeedback.setText("Nice effort. Remember follow-up on your knowledge gaps.");
        } else if (scoreratio > 0.81) {
            textViewQuizReviewFeedback.setText("High distinction worthy. Ensure that you maintain your knowledge!");
        } else {
            textViewQuizReviewFeedback.setText("You broke the feedback giver!");
        }

        buttonQuizReviewFinish = (Button) findViewById(R.id.button_quizreviewfinish);
        buttonQuizReviewFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //also packs the score in the intent
                Intent resultIntent = new Intent(QuizReviewActivity.this, QuizActivity.class);
                resultIntent.putExtra(TRANSFER_REVIEWSCORE, reviewscore);
                setResult(RESULT_OK, resultIntent);

                finish();
            }
        });

    }

    //same override method as in QuizActivity
    @Override
    public void onBackPressed() {
        if (backPressedTimer + 2000 > System.currentTimeMillis()) {
            finish();
        } else {
            Toast.makeText(this, "WARNING: Press back again to finish WITHOUT saving score!", Toast.LENGTH_SHORT).show();
        }
        backPressedTimer = System.currentTimeMillis();
    }
}
