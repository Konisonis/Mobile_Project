package com.example.konsi.mobil_computing_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Doctor_Detailed_Patient extends AppCompatActivity {

    private TextView patient_name;
    private ListView patient_devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__detailed__patient);

        // Initialize views
        patient_name = findViewById(R.id.patient_name);
        patient_devices = findViewById(R.id.patient_devices);

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
