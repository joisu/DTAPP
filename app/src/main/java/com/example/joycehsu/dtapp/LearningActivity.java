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

public class LearningActivity extends BaseActivity implements View.OnClickListener {

    private CardView card1, card2, card3, card4;

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

        //link cards
        card1 = (CardView) findViewById(R.id.card1);
        card2 = (CardView) findViewById(R.id.card2);
        card3 = (CardView) findViewById(R.id.card3);
        card4 = (CardView) findViewById(R.id.card4);

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
            case R.id.card1: i = new Intent(this,NewsActivity.class);
                startActivity(i);
                break;

            case R.id.card2: i = new Intent(this,LearningActivity.class);
                startActivity(i);
                break;

            case R.id.card3: i = new Intent(this,NewsActivity.class);
                startActivity(i);
                break;

            case R.id.card4: i = new Intent(this,SettingsActivity.class);
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
