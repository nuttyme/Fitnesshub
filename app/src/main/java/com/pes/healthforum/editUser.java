package com.pes.healthforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editUser extends AppCompatActivity {

    Button btnSave;
    EditText et_question, et_answer;

    DatabaseHelper db;

    private int selectedId;
    private String selectedQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        btnSave = (Button) findViewById(R.id.EUbtn_asave);
        et_question = (EditText) findViewById(R.id.EUeditText_question);
        et_answer = (EditText) findViewById(R.id.EUeditText_answer);

        db = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();


        selectedId = receivedIntent.getIntExtra("id", -1);
        selectedQuestion = receivedIntent.getStringExtra("question");


        et_question.setText(selectedQuestion);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = et_answer.getText().toString();
                db.updateUser(selectedId,answer);
            }
        });
    }
}
