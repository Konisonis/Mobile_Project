package com.example.konsi.mobil_computing_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Doctor_Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__profile);

        SharedPreferences settings = getSharedPreferences("sharedPref", 0);
        TextView userName = findViewById(R.id.accountName);
        TextView userId = findViewById(R.id.accountId);
        TextView dob = findViewById(R.id.dob);
        if(settings != null){
            String doctor = settings.getString("Doctor", "Not Found");
            try{
                JSONObject doctorJson = new JSONObject(doctor);
                userId.setText(doctorJson.getString("id"));
                userName.setText(doctorJson.getString("forname") + " " +doctorJson.getString("lastname") );
                dob.setText(doctorJson.getString("birthdate"));

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
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
