package com.pes.healthforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class trainerLanding extends AppCompatActivity {

     Button healthforum,approveMember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_landing);

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
