package com.pes.healthforum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class trainer_login extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;
    ViewGroup progressView;
    protected boolean isProgressShowing = false;

    EditText edidText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_login);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_tusername);
        mTextPassword = (EditText)findViewById(R.id.edittext_tpassword);
        mButtonLogin = (Button)findViewById(R.id.button_tlogin);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();;
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkTrainer(user, pwd);
                if(res == true)
                {
                    Intent trainerLandingPage = new Intent(trainer_login.this,trainerLanding.class);
                    startActivity(trainerLandingPage);
                }
                else
                {
                    Toast.makeText(trainer_login.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
