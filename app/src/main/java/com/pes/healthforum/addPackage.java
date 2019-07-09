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

public class addPackage extends AppCompatActivity {

    EditText mTextpackagename;
    EditText mTextdescription;
    Button mButtonSave;
    DatabaseHelper db;

    EditText edidText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_package);

        db = new DatabaseHelper(this);
        mTextpackagename = (EditText) findViewById(R.id.et_packagename);
        mTextdescription = (EditText) findViewById(R.id.et_pdescription);
        mButtonSave = (Button) findViewById(R.id.bt_packagesave);

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String packagename = mTextpackagename.getText().toString().trim();
                String description = mTextdescription.getText().toString().trim();
                SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                String value = result.getString("Value", "Data not found");

                long val = db.addPackage(packagename, description);
                if (val > 0) {
                    Toast.makeText(addPackage.this, "You have registered", Toast.LENGTH_SHORT).show();
//                    Intent moveToMemberHome = new Intent(addPackage.this, adminLanding.class);
//                    startActivity(moveToMemberHome);
                } else {
                    Toast.makeText(addPackage.this, "Registeration Error", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
