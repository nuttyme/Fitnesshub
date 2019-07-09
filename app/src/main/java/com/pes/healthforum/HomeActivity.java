package com.pes.healthforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button login;
    Button about;
    Button admin;
    Button trainer;
    Button healthforum, packages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(signupIntent);
            }
        });


        about = (Button) findViewById(R.id.btnabout);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(HomeActivity.this,about.class);
                startActivity(signupIntent);
            }
        });

        admin = (Button) findViewById(R.id.adminlogin);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(HomeActivity.this,adminLogin.class);
                startActivity(signupIntent);
            }
        });

        trainer = (Button) findViewById(R.id.trainerlogin);

        trainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(HomeActivity.this,trainer_login.class);
                startActivity(signupIntent);
            }
        });

        healthforum = (Button) findViewById(R.id.healthforum);

        healthforum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(HomeActivity.this,healthforum.class);
                startActivity(signupIntent);
            }
        });

        packages = (Button) findViewById(R.id.packages);

        packages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(HomeActivity.this,listPackages.class);
                startActivity(signupIntent);
            }
        });

    }
}
