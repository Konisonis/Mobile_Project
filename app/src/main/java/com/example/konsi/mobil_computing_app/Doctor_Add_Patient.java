package com.example.konsi.mobil_computing_app;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

/**
 * Activity called when a new patient is to add to the patients list
 */
public class Doctor_Add_Patient extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private LinearLayout addpatient;
    private EditText forname_view;
    private EditText lastname_view;
    private EditText birthdate_view;

    private int _day;
    private int _month;
    private int _birthYear;

    /**
     * Gets called when the activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__add__patient);

        addpatient = findViewById(R.id.addpatient_view);
        forname_view = findViewById(R.id.forname_view);
        lastname_view = findViewById(R.id.lastname_view);
        birthdate_view = findViewById(R.id.birthdate_view);
        birthdate_view.setOnClickListener(this);

        initializeAddPatientOnClick();
    }

    //MENU-------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.doctor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent mainpageIntent = new Intent(this, Doctor_Main_Page.class);
        Intent profileIntent = new Intent(this, Doctor_Profile.class);
        Intent infoIntent = new Intent(this, Doctor_App_Info.class);
        Intent logoutIntent = new Intent(this, LoginActivity.class);
        //logout ?

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.doctor_main_page:
                startActivity(mainpageIntent);
                return true;
            case R.id.profile_doctor:
                startActivity(profileIntent);
                return true;
            case R.id.app_info:
                startActivity(infoIntent);
                return true;
            case R.id.log_out:
                finish();
                startActivity(logoutIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        _birthYear = year;
        _month = month;
        _day = dayOfMonth;
        updateDisplay();
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }


    /**
     * initializes the on click event, builds the patients data needed
     * for creating a new patient in the mainpage and returning an intent
     */
    private void initializeAddPatientOnClick() {
        addpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                // Build ID out of five random numbers
                int num1 = new Random().nextInt(10);
                int num2 = new Random().nextInt(10);
                int num3 = new Random().nextInt(10);
                String id = "" + num1 + num2 + num3;
                String forname = forname_view.getText().toString();
                String lastname = lastname_view.getText().toString();
                String birthdate = birthdate_view.getText().toString();
                returnIntent.putExtra("id", id);
                returnIntent.putExtra("forname", forname);
                returnIntent.putExtra("lastname", lastname);
                returnIntent.putExtra("birthdate", birthdate);
                // Build Password with forname and ID
                String pwname = forname.toLowerCase();
                String password = pwname + id;
                returnIntent.putExtra("password", password);

                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    private void updateDisplay() {

        birthdate_view.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(_day).append(".").append(_month + 1).append(".").append(_birthYear).append(""));
    }
}
