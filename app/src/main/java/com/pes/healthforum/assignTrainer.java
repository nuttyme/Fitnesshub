package com.pes.healthforum;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class assignTrainer extends AppCompatActivity {

    DatabaseHelper db, db1, db2;
    private int member_id,trainer_id;
    private ListView memberslv;
    private ListView trainerslv;
    private static final String TAG = "assignTrainer";
    Button btn_save;
    EditText et_member, et_trainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_trainer);
        et_member = (EditText)findViewById(R.id.et_at_member);
        et_trainer = (EditText)findViewById(R.id.et_at_trainer);
        memberslv = (ListView) findViewById(R.id.lv_at_members);
        trainerslv = (ListView) findViewById(R.id.lv_at_trainers);
        db = new DatabaseHelper(this);
        db1 = new DatabaseHelper(this);
        db2 = new DatabaseHelper(this);

        populateMembersListView();
        populateTrainerListView();

        btn_save= (Button) findViewById(R.id.btn_at_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String member_name = et_member.getText().toString();
                String trainer_name = et_trainer.getText().toString();
                db2.assignTrainer(member_id,member_name,trainer_id,trainer_name);

                Toast.makeText(assignTrainer.this, "Saved", Toast.LENGTH_SHORT).show();
                Intent moveToMemberHome = new Intent(assignTrainer.this, adminLanding.class);
                startActivity(moveToMemberHome);
            }
        });

    }


    private void populateMembersListView()
    {

        Cursor data = db.getApprovedMembers();
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
                    member_id = itemID;
                }
                if(itemID > -1){
                    Log.d(TAG,"onItem clicked, ID is " + itemID);
                    et_member = (EditText) findViewById(R.id.et_at_member);
                    et_member.setText( member);
                }
                else {
                    Log.d(TAG," onItem clicked - no item found");
                }
            }
        });

    }

    private void populateTrainerListView()
    {

        Cursor data = db1.getTrainers();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
            //       listData.add(data.getString(1)+" - "+data.getString(2) +" - "+data.getString(3));
        }

        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        trainerslv.setAdapter(adapter);

        trainerslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String trainer = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG,"memberlist onItem clicked" + trainer);

                Cursor data = db1.getTrainerID(trainer);
                int itemID = -1;
//                String question;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                    //                   question = data.getString(1);
                    trainer_id = itemID;
                }
                if(itemID > -1){
                    Log.d(TAG,"onItem clicked, ID trainer is " + itemID);
                    et_trainer = (EditText) findViewById(R.id.et_at_trainer);
                    et_trainer.setText( trainer);
                }
                else {
                    Log.d(TAG," onItem clicked - no item found");
                }
            }
        });

    }

}
