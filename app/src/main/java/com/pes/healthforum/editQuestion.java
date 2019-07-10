package com.pes.healthforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editQuestion extends AppCompatActivity {

    Button btnSave;
    EditText et_question, et_answer;

    DatabaseHelper db;

    private String selectedId;
    private String selectedQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);


        btnSave = (Button) findViewById(R.id.btn_asave);
        et_question = (EditText) findViewById(R.id.editText_question);
        et_answer = (EditText) findViewById(R.id.editText_answer);

        db = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();


        selectedId = receivedIntent.getStringExtra("id");
        selectedQuestion = receivedIntent.getStringExtra("question");


        et_question.setText(selectedQuestion);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = et_answer.getText().toString();
                db.updateAnswer(Integer.parseInt(selectedId),answer);

                Toast.makeText(editQuestion.this, "Saved", Toast.LENGTH_SHORT).show();
                Intent moveToMemberHome = new Intent(editQuestion.this, AnswerQuestion.class);
                startActivity(moveToMemberHome);
            }
        });
    }
}
