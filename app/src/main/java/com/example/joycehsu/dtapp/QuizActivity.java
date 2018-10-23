package com.example.joycehsu.dtapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {
    private TextView textViewScore;
    private TextView textViewQuestion;
    private TextView textViewTimer;
    private TextView textViewQuestionCount;
    private RadioGroup radGroup;
    private RadioButton radButton1;
    private RadioButton radButton2;
    private RadioButton radButton3;
    private Button buttonNext;
    private List<Question> questionList;
    private ColorStateList textColorRadioButton;
    private ColorStateList textColorCountDown;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private int score;
    private boolean attempted;
    private long backPressedTimer;

    private static final long COUNTDOWN_MS = 25000;
    private CountDownTimer countDownTimer;
    private long countDownTimeLeft_MS;

    public static final String TRANSFER_SCORE = "transferScore";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Linking to XML
        textViewScore = findViewById(R.id.textview_score);
        textViewQuestion = findViewById(R.id.textview_question);
        textViewTimer = findViewById(R.id.textview_timer);
        textViewQuestionCount = findViewById(R.id.textview_questioncount);
        radGroup = findViewById(R.id.radiogroup);
        radButton1 = findViewById(R.id.radiobutton1);
        radButton2 = findViewById(R.id.radiobutton2);
        radButton3 = findViewById(R.id.radiobutton3);
        buttonNext = findViewById(R.id.button_next);
        //Storing the colour of the radio button to modify it later and back
        textColorRadioButton = radButton1.getTextColors();
        textColorCountDown = textViewTimer.getTextColors();

        //initialise dbHelper
        QuizDbHelper dbHelper = new QuizDbHelper(this);

        //fills our questionList with questions!
        questionList = dbHelper.getAllQuestions();
        //determines the total number of questions
        questionCountTotal = questionList.size();

        //randomizes questionList for a more viable quiz
        Collections.shuffle(questionList);

        //Sets up the first question of the quiz
        nextQuestion();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!attempted) {
                    if (radButton1.isChecked() || radButton2.isChecked() || radButton3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    nextQuestion();
                }
            }
        });

    }

    //Initiates setup for next question (also sets up the quiz on its first call)
    private void nextQuestion() {
        //reset text colours of radio buttons
        radButton1.setTextColor(textColorRadioButton);
        radButton2.setTextColor(textColorRadioButton);
        radButton3.setTextColor(textColorRadioButton);
        //unchecks radio buttons (because new questions should not have checked buttons)
        radGroup.clearCheck();

        //If the question was not the last question of the quiz, then sets up for the next question
        //Keeps track of how many questions (as opposed to what question it is, because we randomize)
        if (questionCounter < questionCountTotal) {

            currentQuestion = questionList.get(questionCounter);

            //sets the new question
            textViewQuestion.setText(currentQuestion.getQuestion());
            //sets the new radio buttons (multiple choice options)
            radButton1.setText(currentQuestion.getOption1());
            radButton2.setText(currentQuestion.getOption2());
            radButton3.setText(currentQuestion.getOption3());

            //increments the question counter
            questionCounter++;

            //sets the question number view up the top left
            textViewQuestionCount.setText("Q: " + questionCounter + "/" + questionCountTotal);

            //tracks whether the question was attempted
            //if it has, buttonNext will become a 'Next' button instead of a Confirm button
            attempted = false;
            buttonNext.setText("Click to Confirm");

            //starts the timer as soon as a question appears
            countDownTimeLeft_MS = COUNTDOWN_MS;
            startTimer();
        } else {
            finishQuiz();
        }
    }

    //defines the method for every tick and also when the timer lapses
    private void startTimer() {
        countDownTimer = new CountDownTimer(countDownTimeLeft_MS, 1000) {
            @Override
            public void onTick(long l) {
                countDownTimeLeft_MS = l;
                updateTextViewTimer();
            }

            @Override
            public void onFinish() {
                //explicitly setting timer to 0 visibly when the timer lapses
                //just hard-coding this for visibility-continuity purposes
                //because without it the countdown can get stuck at 1 second remaining
                //This shouldn't cause any material difference in functionality
                countDownTimeLeft_MS = 0;
                updateTextViewTimer();

                //If you haven't selected an answer in time, it will treat it as a did-not-attempt
                checkAnswer();
            }
        }
                .start();
    }

    //this method formats the locale time and also sets the colour for the countdown
    private void updateTextViewTimer() {
        int minutes = (int) (countDownTimeLeft_MS / 1000) / 60;
        int seconds = (int) (countDownTimeLeft_MS / 1000) % 60;

        //will update the shown Timer every 1000ms
        String liveTextViewTimer = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewTimer.setText(liveTextViewTimer);

        //changes timer colour to an alarming red when 10000ms is left
        if (countDownTimeLeft_MS < 10000) {
            textViewTimer.setTextColor(Color.RED);
        } else {
            textViewTimer.setTextColor(textColorCountDown);
        }
    }

    //After clicking to confirm a selected answer...
    private void checkAnswer() {
        //Recognising that an attempt has been made
        attempted = true;

        //stop the timer
        countDownTimer.cancel();

        //Compare attempted (checked radiobutton) with respective database answer
        RadioButton radChecked = findViewById(radGroup.getCheckedRadioButtonId());
        int answerNo = radGroup.indexOfChild(radChecked) + 1;
        if (answerNo == currentQuestion.getAnswerNo()) {
            score++;
            textViewScore.setText("Score: " + score);
        }

        showSolution();

    }

    //part of checkAnswer(); visual changes to Views

    private void showSolution() {
        //change all solutions to red
        radButton1.setTextColor(Color.RED);
        radButton2.setTextColor(Color.RED);
        radButton3.setTextColor(Color.RED);

        //change colour of solution to green (so wrong answers are still in red)
        switch (currentQuestion.getAnswerNo()) {
            case 1:
                radButton1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Option A was correct.");
                break;
            case 2:
                radButton2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Option B was correct.");
                break;
            case 3:
                radButton3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Option C was correct.");
                break;
        }

        //changes 'Next' button to a finish button if there are no more questions
        if (questionCounter < questionCountTotal) {
            buttonNext.setText("Next");
        } else {
            buttonNext.setText("Click to Finish!");
        }

    }

    //executes onDestroy(), ends the quiz
    private void finishQuiz() {
        //also passes the score back (to the QuizStartActivity)
        Intent resultIntent = new Intent();
        resultIntent.putExtra(TRANSFER_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    //security feature in case you mis-click the back button
    //only activates onBackPressed() if you tap twice in a short time
    @Override
    public void onBackPressed() {
        if (backPressedTimer + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish early", Toast.LENGTH_SHORT).show();
        }
        backPressedTimer = System.currentTimeMillis();
    }

    //timer typically persists even if the app is closed
    //need to make sure that the timer is cancelled when onDestroy()
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
