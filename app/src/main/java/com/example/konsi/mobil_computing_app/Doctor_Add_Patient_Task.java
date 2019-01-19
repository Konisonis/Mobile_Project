package com.example.konsi.mobil_computing_app;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Doctor_Add_Patient_Task extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private EditText from_date;
    private EditText to_date;
//    private EditText start_date;
    private int positionClicked;
    private EditText[] date_fields;
    private Button addtask_button;

    private int _day;
    private int _month;
    private int _year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__add__patient__task);

        from_date = findViewById(R.id.from_date);
        to_date = findViewById(R.id.to_date);
//        start_date = findViewById(R.id.start_date);
        addtask_button = findViewById(R.id.addtask_button);
        date_fields = new EditText[3];
        date_fields[0] = from_date;
        date_fields[1] = to_date;

        String[] list = getResources().getStringArray(R.array.taskoptions);


        initializeOnClickHandling();


    }

    /**
     * Sets the text of an editText-field after a date has been chosen from the datepicker
     * @param view the view that got clicked
     * @param year the chosen year
     * @param month the chosen month
     * @param dayOfMonth the chosen day
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        _year = year;
        _month = month;
        _day = dayOfMonth;

        // Choose on which edittext to show
        date_fields[positionClicked].setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(_day).append(".").append(_month + 1).append(".").append(_year).append(""));
    }

    /**
     * Handles the event when an item is selected
     * @param parent the Dropdown-Items Holder
     * @param view the selected view
     * @param position the position of the view
     * @param id the id of the view
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        View selectedView = (View) parent.getItemAtPosition(position);
        Toast.makeText(this, "Selected:" + selectedView.toString(), Toast.LENGTH_LONG).show();
    }

    /**
     * If nothing is selected this is called
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Handles the click events for the date-fields as well as for the add-task button
     */
    public void initializeOnClickHandling() {
        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionClicked = 0;
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                DatePickerDialog dialog = new DatePickerDialog(Doctor_Add_Patient_Task.this, Doctor_Add_Patient_Task.this,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionClicked = 1;
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                DatePickerDialog dialog = new DatePickerDialog(Doctor_Add_Patient_Task.this, Doctor_Add_Patient_Task.this,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
//        start_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                positionClicked = 2;
//                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
//
//                DatePickerDialog dialog = new DatePickerDialog(Doctor_Add_Patient_Task.this, Doctor_Add_Patient_Task.this,
//                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
//                        calendar.get(Calendar.DAY_OF_MONTH));
//                dialog.show();
//            }
//        });
        addtask_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add a new task to the patient and look it up in the Doctor_Detailed_Patient Activity
            }
        });
    }


    //MENU-------------------------------

    /**
     * Decides which inflater handles the menu
     * @param menu the menu to handle
     * @return if event was successful
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.doctor_menu, menu);
        return true;
    }

    /**
     * Decides what to do if an option item got selected
     * @param item the selected item
     * @return if event was successful
     */
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
                //Remove all user data and the stack before starting new intent
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear();
                editor.apply();
                startActivity(logoutIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
