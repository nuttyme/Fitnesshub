package com.pes.healthforum;

import android.app.ListActivity;
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

public class healthforum extends AppCompatActivity {

    DatabaseHelper db;
    private ListView questionslv;
    private static final String TAG = "healthforum";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthforum);
        questionslv = (ListView) findViewById(R.id.lvquestions);
        db = new DatabaseHelper(this);

        populateQuestionsListView();
    }

    private void populateQuestionsListView() {

        Cursor data = db.getQuestions();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add("Member name: " + data.getString(1)+"\r\nQuestion Title - "+data.getString(2) +"\r\nDescription - "+data.getString(3) +"\r\nAnswer : " + data.getString(4));
        }

        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        questionslv.setAdapter(adapter);

        questionslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String question = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG,"onItem clicked" + question.substring(14,20));

                Cursor data = db.getQuestionID(question);
                int itemID = -1;
//                String question;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
 //                   question = data.getString(1);
                }
                if(itemID > -1){
                    Log.d(TAG,"onItem clicked, ID is " + itemID);
                    Intent editQuestionIntent = new Intent(healthforum.this, editQuestion.class );
                    editQuestionIntent.putExtra("id",itemID);
                    editQuestionIntent.putExtra("question",question);
                    startActivity(editQuestionIntent);
                }
                else {
                    Log.d(TAG," onItem clicked - no item found");
                }
            }
        });
    }
}

