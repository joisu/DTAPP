package com.example.joycehsu.dtapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joycehsu.dtapp.Activities.CustomAdapter;
import com.example.joycehsu.dtapp.Activities.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesActivity extends BaseActivity {


    int preSelectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_activities, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_activities);


        ListView listView = (ListView) findViewById(R.id.listview);
        Button instructions = (Button) findViewById(R.id.instructButton);

        //ADD tasks
        final List<UserModel> users = new ArrayList<>();
        users.add(new UserModel(false, "Think Divergently and Creatively"));
        users.add(new UserModel(false, "Increase Your Creative Output"));
        users.add(new UserModel(false, "Jump Start An Ideation Session"));
        users.add(new UserModel(false, "Learn From Observing Human Behaviour"));
        users.add(new UserModel(false, "Encourage and Accept Constructive Feedback"));
        users.add(new UserModel(false, "Warm Up A Group"));
        users.add(new UserModel(false, "Eliminate Hierarchy To Improve Idea Flow"));
        users.add(new UserModel(false, "Empathize With End Users"));
        users.add(new UserModel(false, "Define A Problem To Work On"));
        users.add(new UserModel(false, "Help Your Group Understand Innovation Thinking"));
        final CustomAdapter adapter = new CustomAdapter(this, users);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                UserModel model = users.get(i);

                if (model.isSelected())
                    model.setSelected(false);

                else
                    model.setSelected(true);

                users.set(i, model);

                //now update adapter
                adapter.updateRecords(users);
            }
        });

        //links to webview for instructions on tasks
        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivitiesActivity.this, DTWebView.class);
                startActivity(i);
            }
        });

    }

    //top right hand menu for settings
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(ActivitiesActivity.this, LoginActivity.class);
//                            i.putExtra("Username", name);
        startActivity(i);
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu_home) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
