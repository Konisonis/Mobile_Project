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
        String[] patientDevices = extras.getStringArray("patient_devices");

        // Create adapter for array items
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, patientDevices);
        patient_devices.setAdapter(itemsAdapter);
        // Give every item a click listener to route to detailsview
        patient_devices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // go to the detailed device view with graphs and history data
                // take the patientID for database query
            }
        });

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
