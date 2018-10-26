package com.example.joycehsu.dtapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static java.sql.Types.NULL;

public class LearningActivity extends BaseActivity implements View.OnClickListener {

    public static final String SHARED_PREFERENCES = "sharedPreferences";
    public static final String CARD1LEARNED_KEY = "card1LearnedKey";
    public static final String CARD2LEARNED_KEY = "card2LearnedKey";
    public static final String CARD3LEARNED_KEY = "card3LearnedKey";
    public static final String CARD4LEARNED_KEY = "card4LearnedKey";

    private int card1Learned, card2Learned, card3Learned, card4Learned;

    private static final int REQUESTCODE_CARD1 = 1;
    private static final int REQUESTCODE_CARD2 = 2;
    private static final int REQUESTCODE_CARD3 = 3;
    private static final int REQUESTCODE_CARD4 = 4;

    private TextView card1title, card2title, card3title, card4title;

    private CardView card1, card2, card3, card4, card5, card6, card7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_learning, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_learning);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello Second Activity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //link card titles
        card1title = (TextView) findViewById(R.id.card1title);
        card2title = (TextView) findViewById(R.id.card2title);
        card3title = (TextView) findViewById(R.id.card3title);
        card4title = (TextView) findViewById(R.id.card4title);


        //link cards
        card1 = (CardView) findViewById(R.id.card1);
        card2 = (CardView) findViewById(R.id.card2);
        card3 = (CardView) findViewById(R.id.card3);
        card4 = (CardView) findViewById(R.id.card4);

        //load any saved changes to whether you've starting/finished reading the topics
        loadLearned();

        //add on click listeners
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.card1:
                i = new Intent(this, LearningLesson1.class);
                startActivityForResult(i, REQUESTCODE_CARD1);
                break;

            case R.id.card2:
                i = new Intent(this, LearningLesson2.class);
                startActivityForResult(i, REQUESTCODE_CARD2);
                break;

            case R.id.card3:
                i = new Intent(this, LearningLesson3.class);
                startActivityForResult(i, REQUESTCODE_CARD3);
                break;

            case R.id.card4:
                i = new Intent(this, LearningLesson4.class);
                startActivityForResult(i, REQUESTCODE_CARD4);
                break;


            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //checks the requestcode to make sure we're handling the result we think we're handling
        //needed in the case where we have multiple results being sent over

        if (requestCode == REQUESTCODE_CARD1) {
            //checks if the request and result was successful
            if (resultCode == RESULT_OK) {


                int card1Learned = data.getIntExtra(LearningLesson1.TRANSFER_LEARNED, 0);

                if (card1Learned == 2) {
                    card1title.setText("Topic 1\n" + "(Finished)");
                }else if (card1Learned == 1){
                    card1title.setText("Topic 1\n" + "(In progress)");
                }else if (card1Learned == NULL){

                }

                //SharedPreferences interface can store values
                SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                //editor is needed to modify the values in SharedPreferences
                SharedPreferences.Editor editor = preferences.edit();
                //key-value pair
                editor.putInt(CARD1LEARNED_KEY, card1Learned);
                editor.apply();
            }
        }

        if (requestCode == REQUESTCODE_CARD2) {
            //checks if the request and result was successful
            if (resultCode == RESULT_OK) {


                int card2Learned = data.getIntExtra(LearningLesson2.TRANSFER_LEARNED, 0);

                if (card2Learned == 2) {
                    card2title.setText("Topic 2\n" + "(Finished)");
                }else if (card2Learned == 1){
                    card2title.setText("Topic 2\n" + "(In progress)");
                }else if (card2Learned == NULL){

                }

                //SharedPreferences interface can store values
                SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                //editor is needed to modify the values in SharedPreferences
                SharedPreferences.Editor editor = preferences.edit();
                //key-value pair
                editor.putInt(CARD2LEARNED_KEY, card2Learned);
                editor.apply();
            }
        }

        if (requestCode == REQUESTCODE_CARD3) {
            //checks if the request and result was successful
            if (resultCode == RESULT_OK) {


                int card3Learned = data.getIntExtra(LearningLesson3.TRANSFER_LEARNED, 0);

                if (card3Learned == 2) {
                    card3title.setText("Topic 1\n" + "(Finished)");
                }else if (card3Learned == 1){
                    card3title.setText("Topic 1\n" + "(In progress)");
                }else if (card3Learned == NULL){

                }

                //SharedPreferences interface can store values
                SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                //editor is needed to modify the values in SharedPreferences
                SharedPreferences.Editor editor = preferences.edit();
                //key-value pair
                editor.putInt(CARD3LEARNED_KEY, card3Learned);
                editor.apply();
            }
        }

        if (requestCode == REQUESTCODE_CARD4) {
            //checks if the request and result was successful
            if (resultCode == RESULT_OK) {


                int card4Learned = data.getIntExtra(LearningLesson4.TRANSFER_LEARNED, 0);

                if (card4Learned == 2) {
                    card4title.setText("Topic 1\n" + "(Finished)");
                }else if (card4Learned == 1){
                    card4title.setText("Topic 1\n" + "(In progress)");
                }else if (card4Learned == NULL){

                }

                //SharedPreferences interface can store values
                SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                //editor is needed to modify the values in SharedPreferences
                SharedPreferences.Editor editor = preferences.edit();
                //key-value pair
                editor.putInt(CARD4LEARNED_KEY, card4Learned);
                editor.apply();

                }
            }
        }

    private void loadLearned() {

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        //sets variable with the key-value pair

        card1Learned = preferences.getInt(CARD1LEARNED_KEY, 0);
        card2Learned = preferences.getInt(CARD2LEARNED_KEY, 0);
        card3Learned = preferences.getInt(CARD3LEARNED_KEY, 0);
        card4Learned = preferences.getInt(CARD4LEARNED_KEY, 0);

        setCardTitles();

    }

    private void setCardTitles(){
        if (card1Learned == 2) {
            card1title.setText("Topic 1\n" + "(Finished)");
        }else if (card1Learned == 1){
            card1title.setText("Topic 1\n" + "(In progress)");
        }else if (card1Learned == NULL){

        }

        if (card2Learned == 2) {
            card2title.setText("Topic 2\n" + "(Finished)");
        }else if (card2Learned == 1){
            card2title.setText("Topic 2\n" + "(In progress)");
        }else if (card2Learned == NULL){

        }

        if (card3Learned == 2) {
            card3title.setText("Topic 1\n" + "(Finished)");
        }else if (card3Learned == 1){
            card3title.setText("Topic 1\n" + "(In progress)");
        }else if (card3Learned == NULL){

        }

        if (card4Learned == 2) {
            card4title.setText("Topic 1\n" + "(Finished)");
        }else if (card4Learned == 1){
            card4title.setText("Topic 1\n" + "(In progress)");
        }else if (card4Learned == NULL){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learning, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu_learning) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}