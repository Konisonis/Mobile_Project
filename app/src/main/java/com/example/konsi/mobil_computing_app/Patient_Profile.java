package com.example.konsi.mobil_computing_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Patient_Profile extends Patient_MasterActivity {
    private static final String TAG = "MyActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__profile);

        SharedPreferences settings = getSharedPreferences("sharedPref", 0);
        TextView userName = findViewById(R.id.accountName);
        TextView userId = findViewById(R.id.accountId);
        TextView dob = findViewById(R.id.dob);
        if(settings != null){
            String patient = settings.getString("Patient", "Not Found");
            Log.d(TAG, patient);
            try{
                JSONObject patientJson = new JSONObject(patient);
                userId.setText(patientJson.getString("id"));
                userName.setText(patientJson.getString("fullname"));
                dob.setText(patientJson.getString("birthdate")); 

            } catch (JSONException e) {
                       throw new RuntimeException(e);
            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.patient_menu, menu);
        return true;
    }
    public void onClick(View view) {
        Toast.makeText (getApplicationContext(), "Password changed!", Toast.LENGTH_LONG).show();
    }


}
