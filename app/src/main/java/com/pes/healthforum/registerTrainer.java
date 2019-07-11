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
    EditText mTextCnfPassword, mTextFirstName, mTextLastName, mTextMobile, mTextAge, mTextSlot;
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
        mTextFirstName = (EditText)findViewById(R.id.edittext_firstname);
        mTextLastName = (EditText)findViewById(R.id.edittext_lastname);
        mTextMobile = (EditText)findViewById(R.id.edittext_mobile);
        mTextAge = (EditText)findViewById(R.id.edittext_age);
        mTextSlot = (EditText)findViewById(R.id.edittext_slot);
        mButtonRegister = (Button)findViewById(R.id.button_tregister);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();
                String first_name = mTextFirstName.getText().toString().trim();
                String last_name = mTextLastName.getText().toString().trim();
                String mobile = mTextMobile.getText().toString().trim();
                String age = mTextAge.getText().toString().trim();
                String slot = mTextSlot.getText().toString().trim();

                if(pwd.equals(cnf_pwd)){
                    try{
                        long val = db.addTrainer(user,pwd, first_name, last_name, mobile, age, slot);
                        if(val > 0){
                            Toast.makeText(registerTrainer.this,"You have registered",Toast.LENGTH_SHORT).show();
                            Intent moveTohome = new Intent(registerTrainer.this,adminLanding.class);
                            startActivity(moveTohome);
                        }
                        else{
                            Toast.makeText(registerTrainer.this,"Registeration Error or User already exists",Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e) {
                        Toast.makeText(registerTrainer.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(registerTrainer.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
