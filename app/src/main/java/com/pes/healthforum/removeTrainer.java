package com.pes.healthforum;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class removeTrainer extends AppCompatActivity {

    DatabaseHelper db;
    private ListView memberslv;
    private static final String TAG = "healthforum";

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_trainer);
        memberslv = (ListView) findViewById(R.id.lvMembers);
        populateMembersListView();
    }


    private void populateMembersListView()
    {
        db = new DatabaseHelper(this);
        Cursor data = db.getTrainers();
        ArrayList<String> listData = new ArrayList<>();
        final ArrayList<String> membersIDs = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1)+" : "+data.getString(3) +" "+data.getString(4));
            membersIDs.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        memberslv.setAdapter(adapter);

        memberslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String member = membersIDs.get(position).toString();
                Log.d(TAG,"memberlist onItem clicked" + member);

                Cursor data = db.getTrainerID(member);
                int itemID = -1;
                //                String question;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                    //                   question = data.getString(1);
                }
                if(itemID > -1){
                    Log.d(TAG,"onItem clicked, ID is " + itemID);
                    Intent editQuestionIntent = new Intent(removeTrainer.this, confirmRemoveTrainer.class );
                    editQuestionIntent.putExtra("id",itemID);
                    editQuestionIntent.putExtra("question",member);
                    startActivity(editQuestionIntent);
                }
                else {
                    Log.d(TAG," onItem clicked - no item found");
                }
            }
        });
    }
}
