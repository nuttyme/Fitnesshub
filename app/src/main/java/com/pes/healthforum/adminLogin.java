package com.pes.healthforum;

import android.content.Context;
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

public class adminLogin extends AppCompatActivity {

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
        setContentView(R.layout.activity_admin_login);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_adminusername);
        mTextPassword = (EditText)findViewById(R.id.edittext_adminpassword);
        mButtonLogin = (Button)findViewById(R.id.button_adminlogin);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = "admin";
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if(res == true)
                {
                   Intent adminLandingPage = new Intent(adminLogin.this,adminLanding.class);
                    startActivity(adminLandingPage);
                }
                else
                {
                    Toast.makeText(adminLogin.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
