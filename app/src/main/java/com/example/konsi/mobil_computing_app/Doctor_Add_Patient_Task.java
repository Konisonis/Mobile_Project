package com.example.konsi.mobil_computing_app;

import android.app.DatePickerDialog;
import android.content.Intent;
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

public class Doctor_Add_Patient_Task extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private EditText from_date;
    private EditText to_date;
    private EditText start_date;
    private int positionClicked;
    private EditText[] date_fields;
    private Button addtask_button;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__add__patient__task);

        from_date = findViewById(R.id.from_date);
        to_date = findViewById(R.id.to_date);
        start_date = findViewById(R.id.start_date);
        addtask_button = findViewById(R.id.addtask_button);
        date_fields = new EditText[3];
        date_fields[0] = from_date;
        date_fields[1] = to_date;
        date_fields[2] = start_date;

        String[] list = getResources().getStringArray(R.array.taskoptions);

        spinner = (Spinner) findViewById(R.id.task_spinner);
        // Create an ArrayAdapter using the string array and a spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, list);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        // Set default selected value
        spinner.setSelection(0);

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
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(calendar.getTime());

        // Choose on which edittext to show
        date_fields[positionClicked].setText(currentDateString);
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
     * Initializes the on click handling for all date fields and buttons
     */
    private void initializeOnClickHandling() {
//        from_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                positionClicked = 0;
//                DialogFragment dialog = new MyDatePickerDialog();
//                dialog.show(getSupportFragmentManager(), "from");
//            }
//        });
//        to_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                positionClicked = 1;
//                DialogFragment dialog = new MyDatePickerDialog();
//                dialog.show(getSupportFragmentManager(), "to");
//            }
//        });
//        start_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                positionClicked = 2;
//                DialogFragment dialog = new MyDatePickerDialog();
//                dialog.show(getSupportFragmentManager(), "start");
//            }
//        });
//        addtask_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create new task
//                // Task task = new Task ...
//                // Go back to Patient_Overview
//            }
//        });
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

        Intent profileIntent = new Intent(this, Doctor_Profile.class);
        Intent infoIntent = new Intent(this, Doctor_App_Info.class);
        Intent logoutIntent = new Intent(this, LoginActivity.class);
        //logout ?

        // Handle item selection
        switch (item.getItemId()) {
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
}
