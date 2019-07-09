package com.pes.healthforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class updateProfile extends AppCompatActivity {

    Button btnSave;
    EditText et_firstname, et_lastname, et_gender, et_mobile, et_email, et_height, et_weight,et_username;

    DatabaseHelper db;

    private int selectedId;
    private String selectedMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        btnSave = (Button) findViewById(R.id.btnUpdateProfile);
        et_username = (EditText) findViewById(R.id.etUPUserName);
        et_firstname = (EditText) findViewById(R.id.etUPFirstName);
        et_lastname = (EditText) findViewById(R.id.etUPLastName);
        et_gender = (EditText) findViewById(R.id.etUPGender);
        et_mobile = (EditText) findViewById(R.id.etUPMobile);
        et_height = (EditText) findViewById(R.id.etUPHeight);
        et_weight = (EditText) findViewById(R.id.etUPWeight);
        et_email = (EditText) findViewById(R.id.etUPEmail);

        db = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();


        selectedId = receivedIntent.getIntExtra("id", -1);
        selectedMember = receivedIntent.getStringExtra("username");


        et_username.setText(selectedMember);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String l_et_firstname = et_firstname.getText().toString();
                String l_et_lastname = et_lastname.getText().toString();
                String l_et_mobile = et_mobile.getText().toString();
                String l_et_height = et_height.getText().toString();
                String l_et_weight = et_weight.getText().toString();
                String l_et_email = et_email.getText().toString();

                db.updateUser(selectedId,l_et_firstname,l_et_lastname,l_et_mobile,l_et_height,l_et_weight ,l_et_email);

                Toast.makeText(updateProfile.this,"Profile Updated",Toast.LENGTH_SHORT).show();
                Intent moveToLogin = new Intent(updateProfile.this,memberHome.class);
                startActivity(moveToLogin);
            }
        });

    }
}
