package com.pes.healthforum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class memberHome extends AppCompatActivity {
    Button mButtonQuestion;
    Button mButtonProfile;
    Button profile;

    DatabaseHelper db;
    int memberID = -1;
    String userName, firstName, lastName, gender, mobile, email, weight, height, bmi, approved, assigned_trainer;

    SharedPreferences sharedPreferences1;

    TextView tvHome, tvfirstname, tvlastname, tvusername,tvgender, tvmobile, tvemail, tvweight, tvheight, txtBmi, txtApproved, txtTrainer;
    private static final String TAG = "memberHome";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_home);

        db = new DatabaseHelper(this);
        tvHome = (TextView) findViewById(R.id.tvHome);
        SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String memberName = result.getString("Value", "Data not found");
        String value = "Welcome " + result.getString("Value", "Data not found");
//        String value1 = result.getString("Value", "Data not found");
        tvHome.setText(value);

        //sqLiteDatabase.execSQL("CREATE TABLE registeruser
        // ( 0 ID INTEGER PRIMARY  KEY AUTOINCREMENT, 1 username TEXT, 2 password TEXT, 3 firstname TEXT,
        // 4 lastname TEXT, 5 height integer,  6 weight integer,  7 email TEXT, 8 mobile TEXT, 9 BMI TEXT, 10 APPROVED TEXT)");


        Cursor data = db.getMemberDetails(memberName);

        while(data.moveToNext()){
            memberID = data.getInt(0);
            userName = "Username: "+ data.getString(1);
            firstName = "First Name: "+ data.getString(3);
            lastName = "Last Name: "+ data.getString(4);
            height = "Height (in Cms): "+ data.getString(5);
            weight = "Weight (in Kgs): "+ data.getString(6);
            email = "Email: "+data.getString(7);
            mobile = "Mobile: "+ data.getString(8);
            bmi = "BMI: "+data.getString(9);
            String approve_value = data.getString(10);
            txtApproved = (TextView) findViewById(R.id.tvApproved);
            if(approve_value == null){
                approved  = "Account is not approved";
                txtApproved.setText(approved);
                txtApproved.setTextColor(Color.parseColor("#ff0000"));
            }
            else {
                approved = "Account is approved";
                txtApproved.setText(approved);
                txtApproved.setTextColor(Color.parseColor("#228B22"));
            }

        }

        Cursor data2 = db.getAssignedTrainer(memberName);
        txtTrainer = (TextView) findViewById(R.id.tvTrainer);
        while(data2.moveToNext()){
            assigned_trainer = data2.getString(4);
            if(assigned_trainer != null) {
                String temp = "Assigned to Trainer: " + assigned_trainer;
                txtTrainer.setText(temp);
            }
        }

        tvusername = (TextView) findViewById(R.id.tvUserName);
        tvfirstname = (TextView) findViewById(R.id.tvFirstName);
        tvlastname = (TextView) findViewById(R.id.tvLastName);
//        tvgender = (TextView) findViewById(R.id.tvGender);
        tvmobile = (TextView) findViewById(R.id.tvMobile);
        tvemail = (TextView) findViewById(R.id.tvEmail);
        tvweight = (TextView) findViewById(R.id.tvWeight);
        tvheight = (TextView) findViewById(R.id.tvHeight);
        txtBmi = (TextView) findViewById(R.id.tvBmi);
        tvusername.setText(userName);
        tvfirstname.setText(firstName);
        tvlastname.setText(lastName);
//        tvgender.setText(lastName);
        tvmobile.setText(mobile);
        tvemail.setText(email);
        tvweight.setText(weight);
        tvheight.setText(height);
        txtBmi.setText(bmi);

        mButtonQuestion = (Button)findViewById(R.id.bt_cquestions);
        mButtonQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questionPage = new Intent(memberHome.this,askQuestion.class);
                startActivity(questionPage);
            }
        });

        profile = (Button) findViewById(R.id.bt_profile_update);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences1 = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                editor.putString("Value", result.getString("Value", "Data not found"));
                editor.apply();



                Intent signupIntent = new Intent(memberHome.this,updateProfile.class);
                signupIntent.putExtra("id",memberID);
                signupIntent.putExtra("username",userName.split(":")[1]);
                startActivity(signupIntent);
            }
        });

/*        mButtonProfile = (Button)findViewById(R.id.bt_uprofile);
        mButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateProfilePage = new Intent(memberHome.this,updateProfile.class);
                startActivity(updateProfilePage);
            }
        });*/

    }
}
