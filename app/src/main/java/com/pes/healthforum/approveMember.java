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

public class approveMember extends AppCompatActivity {


        DatabaseHelper db;
        private ListView memberslv;
        private static final String TAG = "healthforum";

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_approve_member);
            memberslv = (ListView) findViewById(R.id.lvMembers);
            db = new DatabaseHelper(this);

            populateMembersListView();
        }


    private void populateMembersListView()
    {

        Cursor data = db.getMembers();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
     //       listData.add(data.getString(1)+" - "+data.getString(2) +" - "+data.getString(3));
        }

        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        memberslv.setAdapter(adapter);

        memberslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String member = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG,"memberlist onItem clicked" + member);

                Cursor data = db.getMemberID(member);
                int itemID = -1;
//                String question;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                    //                   question = data.getString(1);
                }
                if(itemID > -1){
                    Log.d(TAG,"onItem clicked, ID is " + itemID);
                    Intent editQuestionIntent = new Intent(approveMember.this, editUser.class );
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

