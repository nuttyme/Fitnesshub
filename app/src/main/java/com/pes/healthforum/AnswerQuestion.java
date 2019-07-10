package com.pes.healthforum;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AnswerQuestion extends AppCompatActivity {

    DatabaseHelper db;
    private ListView questionslv;
    private static final String TAG = "healthforum";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);
        questionslv = (ListView) findViewById(R.id.lvquestions);
        db = new DatabaseHelper(this);

        populateQuestionsListView();
    }

    private void populateQuestionsListView() {

        Cursor data = db.getQuestions();
        ArrayList<String> listData = new ArrayList<>();
        final ArrayList<String> questionDescriptions = new ArrayList<>();
        final ArrayList<String> QuestionIDs = new ArrayList<>();
        while(data.moveToNext()){
            QuestionIDs.add(data.getString(0));
            questionDescriptions.add(data.getString(3));
            listData.add("Member name: " + data.getString(1)+"\r\nQuestion Title - "+data.getString(2) +"\r\nDescription - "+data.getString(3));
        }

        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        questionslv.setAdapter(adapter);

        questionslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String questionID = QuestionIDs.get(position);
                String question = questionDescriptions.get(position);
                Log.d(TAG,"onItem clicked, ID is " + questionID);
                Intent editQuestionIntent = new Intent(AnswerQuestion.this, editQuestion.class );
                editQuestionIntent.putExtra("id", questionID);
                editQuestionIntent.putExtra("question",question);
                startActivity(editQuestionIntent);
            }
        });
    }
}
