package com.example.konsi.mobil_computing_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Patient_Profile extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    Button but;
    boolean click = true;

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

        but = findViewById(R.id.changePassword);
        but.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (click) {
                    Toast.makeText (getApplicationContext(), "Password changed!", Toast.LENGTH_LONG).show();
                    click = false;
                } else {
                    click = true;
                }
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.patient_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent profileIntent = new Intent(this, Patient_Profile.class);
        Intent devicesIntent = new Intent(this, Patient_Devices_List.class);
        Intent infoIntent = new Intent(this, Patient_App_Info.class);
        Intent logoutIntent = new Intent(this, LoginActivity.class);
        Intent messageIntent = new Intent(this,Patient_Messages.class);


        // Handle item selection
        switch (item.getItemId()) {
            case R.id.profile:
                startActivity(profileIntent);
                return true;
            case R.id.message:
                startActivity(messageIntent);
                return true;
            case R.id.devices:
                startActivity(devicesIntent);
                return true;
            case R.id.info:
                startActivity(infoIntent);
                return true;
            case R.id.logout:
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
    public void onClick(View view){
        Log.d(TAG, "Password changed ");
    }
}
