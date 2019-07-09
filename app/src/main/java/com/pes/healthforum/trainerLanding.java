package com.pes.healthforum;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class trainerLanding extends AppCompatActivity {

     Button healthforum,approveMember;

     String trainer_username, userName, firstName, lastName, mobile, age;

     TextView tvfirstname, tvlastname, tvmobile, tvage, tvusername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_landing);

        Intent receivedIntent = getIntent();
        trainer_username = receivedIntent.getStringExtra("trainer_username");
        Log.d("trainer username: " , trainer_username);
        DatabaseHelper db;
        db = new DatabaseHelper(this);
        Cursor data = db.getTrainerDetails(trainer_username);

        while(data.moveToNext()){
            userName = "Username: "+ data.getString(1);
            firstName = "First Name: "+ data.getString(3);
            lastName = "Last Name: "+ data.getString(4);
            mobile = "Mobile: "+ data.getString(5);
            age = "Age: "+data.getString(6);
        }

        tvfirstname = (TextView) findViewById(R.id.tvFirstName);
        tvlastname = (TextView) findViewById(R.id.tvLastName);
        tvmobile = (TextView) findViewById(R.id.tvMobile);
        tvage = (TextView) findViewById(R.id.tvAge);
        tvusername = (TextView) findViewById(R.id.tvUserName);

        tvusername.setText(userName);
        tvfirstname.setText(firstName);
        tvlastname.setText(lastName);
        tvmobile.setText(mobile);
        tvage.setText(age);

        healthforum = (Button) findViewById(R.id.bt_aquestions);

        healthforum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(trainerLanding.this,healthforum.class);
                startActivity(signupIntent);
            }
        });

        approveMember = (Button) findViewById(R.id.bt_members);

        approveMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(trainerLanding.this,trainerMembers.class);
                startActivity(signupIntent);
            }
        });

    }
}
