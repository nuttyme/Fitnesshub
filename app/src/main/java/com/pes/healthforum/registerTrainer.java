package com.pes.healthforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class registerTrainer extends AppCompatActivity {

    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_trainer);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_tusername);
        mTextPassword = (EditText)findViewById(R.id.edittext_tpassword);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_tcnf_password);
        mButtonRegister = (Button)findViewById(R.id.button_tregister);
        mTextViewLogin = (TextView)findViewById(R.id.textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(registerTrainer.this,trainer_login.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();

                if(pwd.equals(cnf_pwd)){
                    long val = db.addTrainer(user,pwd);
                    if(val > 0){
                        Toast.makeText(registerTrainer.this,"You have registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(registerTrainer.this,trainer_login.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(registerTrainer.this,"Registeration Error",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(registerTrainer.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
