package com.pes.healthforum;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class trainerMembers extends AppCompatActivity {
    DatabaseHelper db;
    private ListView mlv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_members);

        mlv = (ListView) findViewById(R.id.lv_tm_members);
        db = new DatabaseHelper(this);
        populateMListView();
    }


    public void populateMListView() {

        Cursor data = db.get_assign_trainer();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add("Member - " + data.getString(2)+" assigned to Trainer -"+data.getString(4));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mlv.setAdapter(adapter);
    }
}
