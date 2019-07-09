package com.pes.healthforum;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class listPackages extends AppCompatActivity {

    DatabaseHelper db;
    private ListView mlv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_packages);
        mlv = (ListView) findViewById(R.id.list_packages);
        db = new DatabaseHelper(this);
        populateListView();
    }


    public void populateListView() {

        Cursor data = db.getPackages();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(1)+"-"+data.getString(2));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mlv.setAdapter(adapter);
    }

}