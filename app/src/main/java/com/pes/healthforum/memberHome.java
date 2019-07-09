package com.pes.healthforum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
    String userName, firstName, lastName, gender, mobile, email, weight, height;

    SharedPreferences sharedPreferences1;

    TextView tvHome, tvfirstname, tvlastname, tvusername,tvgender, tvmobile, tvemail, tvweight, tvheight;
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
        // 4 lastname TEXT, 5 height integer,  6 weight integer,  7 email TEXT, 8 mobile TEXT, BMI INTEGER, APPROVED TEXT)");


        Cursor data = db.getMemberDetails(memberName);

        while(data.moveToNext()){
            memberID = data.getInt(0);
            userName = data.getString(1);
            firstName = data.getString(3);
            lastName = data.getString(4);
            height = data.getString(5);
            weight = data.getString(6);
            email = data.getString(7);
            mobile = data.getString(8);

        }
        tvusername = (TextView) findViewById(R.id.tvUserName);
        tvfirstname = (TextView) findViewById(R.id.tvFirstName);
        tvlastname = (TextView) findViewById(R.id.tvLastName);
//        tvgender = (TextView) findViewById(R.id.tvGender);
        tvmobile = (TextView) findViewById(R.id.tvMobile);
        tvemail = (TextView) findViewById(R.id.tvEmail);
        tvweight = (TextView) findViewById(R.id.tvWeight);
        tvheight = (TextView) findViewById(R.id.tvHeight);
        tvusername.setText(userName);
        tvfirstname.setText(firstName);
        tvlastname.setText(lastName);
//        tvgender.setText(lastName);
        tvmobile.setText(mobile);
        tvemail.setText(email);
        tvweight.setText(weight);
        tvheight.setText(height);

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
                signupIntent.putExtra("username",userName);
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
