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

public class askQuestion extends AppCompatActivity {

    EditText mTextqtitle;
    EditText mTextquestion;
    Button mButtonSave;
    TextView mTextViewRegister;
    DatabaseHelper db;
    ViewGroup progressView;
    protected boolean isProgressShowing = false;

    EditText edidText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

        db = new DatabaseHelper(this);
        mTextqtitle = (EditText) findViewById(R.id.et_qtitle);
        mTextquestion = (EditText) findViewById(R.id.et_question);
        mButtonSave = (Button) findViewById(R.id.bt_qsave);

//        mTextViewRegister = (TextView)findViewById(R.id.textview_register);
//        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
//                startActivity(registerIntent);
//            }
//        });

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qtitle = mTextqtitle.getText().toString().trim();
                String question = mTextquestion.getText().toString().trim();
                SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                String value = result.getString("Value", "Data not found");

                long val = db.addQuestion(value, qtitle, question);
                if (val > 0) {
                    Toast.makeText(askQuestion.this, "You have registered", Toast.LENGTH_SHORT).show();
                    Intent moveToMemberHome = new Intent(askQuestion.this, memberHome.class);
                    startActivity(moveToMemberHome);
                } else {
                    Toast.makeText(askQuestion.this, "Registeration Error", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}
