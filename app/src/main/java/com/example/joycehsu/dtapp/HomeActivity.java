package com.example.joycehsu.dtapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends BaseActivity implements View.OnClickListener{

    private CardView newsCard, learningCard, activitiesCard, settingsCard, quizCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_home, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello Second Activity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //link cards
        newsCard = (CardView) findViewById(R.id.newscard);
        learningCard = (CardView) findViewById(R.id.learningcard);
        activitiesCard = (CardView) findViewById(R.id.activitiescard);
        settingsCard = (CardView) findViewById(R.id.settingscard);
        quizCard = (CardView) findViewById(R.id.quizcard);

        //add on click listeners
        newsCard.setOnClickListener(this);
        learningCard.setOnClickListener(this);
        activitiesCard.setOnClickListener(this);
        settingsCard.setOnClickListener(this);
        quizCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.newscard: i = new Intent(this,NewsActivity.class);
                startActivity(i);
                break;

            case R.id.learningcard: i = new Intent(this,LearningActivity.class);
                startActivity(i);
                break;

            case R.id.activitiescard: i = new Intent(this,NewsActivity.class);
                startActivity(i);
                break;

            case R.id.settingscard: i = new Intent(this,SettingsActivity.class);
                startActivity(i);
                break;

            case R.id.quizcard: i = new Intent(this,ReviewActivity.class);
                startActivity(i);
                break;

            default:break;
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
