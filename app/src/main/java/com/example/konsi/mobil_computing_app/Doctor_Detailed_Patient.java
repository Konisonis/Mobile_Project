package com.example.konsi.mobil_computing_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Doctor_Detailed_Patient extends AppCompatActivity {

    private TextView patient_name;
    private ListView patient_devices;

    private Button addtask_button;
    private Button addmessage_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__detailed__patient);

        // Initialize views
        patient_name = findViewById(R.id.patient_name);
        patient_devices = findViewById(R.id.patient_devices);
        addtask_button = findViewById(R.id.addtask_button);
        addmessage_button = findViewById(R.id.addmessage_button);

        // Get extras from the intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String patientID = extras.getString("patient_id");
        String patientName = extras.getString("patient_name");

        // Add devices of a patient to display
        String[] devices = new String[] {
                "Heartratio",
                "Bloodpressure",
                "Steps"
        };

        // Create a List from String Array elements
        final List<String> devices_list = new ArrayList<String>(Arrays.asList(devices));

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, devices_list);

        // DataBind ListView with items from ArrayAdapter
        patient_devices.setAdapter(arrayAdapter);

        patient_name.setText(patientName);

        //Initialize button event click handling
        initializeButtonEventHandling();
    }

    private void initializeButtonEventHandling() {
        addtask_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctor_Detailed_Patient.this, Doctor_Add_Patient_Task.class);
                startActivity(intent);
            }
        });
        addmessage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctor_Detailed_Patient.this, Doctor_Add_Patient_Message.class);
                startActivity(intent);
            }
        });
        patient_devices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String devicename = (String) patient_devices.getAdapter().getItem(position);
                    Intent intent = new Intent(Doctor_Detailed_Patient.this, Doctor_Patient_Measurements.class);
                    intent.putExtra("devicename", devicename);
                    startActivity(intent);
                } catch( Exception e) {
                    Log.e("DETAILED_PATIENT_ERROR:", "Devicename failure in starting patient measurement view");
                }
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
