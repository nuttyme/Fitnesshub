package com.pes.healthforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.app.Dialog;

import java.util.Calendar;

public class updateProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button btnSave;
    EditText et_firstname, et_lastname, et_gender, et_mobile, et_email, et_height, et_weight,et_username, et_doj;
    TextView txt_bmi;
    private RadioGroup genderGroup;
    private RadioButton genderButton;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private Spinner slots, packages;

    DatabaseHelper db;
    String[] packs = { "Select a package", "Package A", "Package B", "Package C"};
    String[] slot = { "Select a slot", "11:00 - 12:00", "13:00 - 14:00", "14:00 - 15:00"};

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
        txt_bmi = (TextView) findViewById(R.id.etUPBMI);
        et_doj = (EditText) findViewById(R.id.etDOJ);

        packages = (Spinner) findViewById(R.id.spinner_packages);
        slots = (Spinner) findViewById(R.id.spinner_slots);
        packages.setOnItemSelectedListener(this);
        slots.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, packs);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        packages.setAdapter(aa);
        packages.setSelection(0);

        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, slot);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        slots.setAdapter(aa2);
        slots.setSelection(0);

        db = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();


        selectedId = receivedIntent.getIntExtra("id", -1);
        selectedMember = receivedIntent.getStringExtra("username");
        et_username.setText(selectedMember);
        et_firstname.setText(receivedIntent.getStringExtra("firstname"));
        et_lastname.setText(receivedIntent.getStringExtra("lastname"));
        et_mobile.setText(receivedIntent.getStringExtra("mobile"));
        et_email.setText(receivedIntent.getStringExtra("email"));
        et_height.setText(receivedIntent.getStringExtra("height"));
        et_weight.setText(receivedIntent.getStringExtra("weight"));
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String l_et_firstname = et_firstname.getText().toString();
                String l_et_lastname = et_lastname.getText().toString();
                String l_et_mobile = et_mobile.getText().toString();
                String l_et_height = et_height.getText().toString();
                String l_et_weight = et_weight.getText().toString();
                String l_et_email = et_email.getText().toString();

                genderGroup = (RadioGroup) findViewById(R.id.gender);
                int selectedGenderId = genderGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                genderButton = (RadioButton) findViewById(selectedGenderId);
                final String gender = genderButton.getText().toString();
                String bmi_value = "null";

                if(!l_et_height.equals("null") &&  !l_et_weight.equals("null")){
                    bmi_value = Float.toString(calculateBMI());
                }

                db.updateUser(selectedId,l_et_firstname,l_et_lastname,l_et_mobile,l_et_height,l_et_weight ,l_et_email, bmi_value, gender);

                Toast.makeText(updateProfile.this,"Profile Updated",Toast.LENGTH_SHORT).show();
                Intent moveToLogin = new Intent(updateProfile.this,memberHome.class);
                startActivity(moveToLogin);
            }
        });

        et_height.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if((!et_weight.getText().toString().equals("") || !et_weight.getText().toString().equals("null")) && (!et_height.getText().toString().equals("") ||  !et_height.getText().toString().equals("null"))){
                        String bmi_text = "BMI: "+ Float.toString(calculateBMI());
                        txt_bmi.setText(bmi_text);
                    }
                }
            }
        });

        et_doj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setDate(v);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private float calculateBMI() {
        float height = (Float.parseFloat(et_height.getText().toString())/100) * (Float.parseFloat(et_height.getText().toString())/100);
        Log.d("weight", Float.toString(height));
        float bmi = Integer.parseInt(et_weight.getText().toString()) / height;
        float bmi_rounded = (float)(Math.round(bmi * 100.0) / 100.0);
        return bmi_rounded;
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
    };

    private void showDate(int year, int month, int day) {
        et_doj.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
