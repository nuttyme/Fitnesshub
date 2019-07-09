package com.pes.healthforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminLanding extends AppCompatActivity {

    Button mButtonAddTrainer, mButtonRemoveTrainer;
    Button mButtonAddPackage, healthforum,approveMember, assignTrainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing);

        mButtonAddTrainer = (Button) findViewById(R.id.btnaddtrainer);


        mButtonAddTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(adminLanding.this, registerTrainer.class);
                startActivity(registerIntent);
            }
        });

        mButtonRemoveTrainer = (Button) findViewById(R.id.btnremovetrainer);


        mButtonRemoveTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent removeTrainerIntent = new Intent(adminLanding.this, removeTrainer.class);
                startActivity(removeTrainerIntent);
            }
        });

        mButtonAddPackage = (Button) findViewById(R.id.btnaddpackage);


        mButtonAddPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(adminLanding.this, addPackage.class);
                startActivity(registerIntent);
            }
        });


        healthforum = (Button) findViewById(R.id.btnhealthforum);

        healthforum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(adminLanding.this,healthforum.class);
                startActivity(signupIntent);
            }
        });

        approveMember = (Button) findViewById(R.id.btnapprovecustomer);

        approveMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(adminLanding.this,approveMember.class);
                startActivity(signupIntent);
            }
        });

        assignTrainer = (Button) findViewById(R.id.btnassigntrainer);

        assignTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(adminLanding.this,assignTrainer.class);
                startActivity(signupIntent);
            }
        });
    }
}
