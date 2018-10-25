package com.example.joycehsu.dtapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ReviewActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_review, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_review);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Review", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button buttonGoToQuiz = findViewById(R.id.button_goToQuiz);
        buttonGoToQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToQuiz();
            }
        });
    }

    // intent method to go to the quiz from review
    private void goToQuiz() {
        Intent intent = new Intent(ReviewActivity.this, QuizStartActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu_review) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
