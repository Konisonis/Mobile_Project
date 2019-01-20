package com.example.konsi.mobil_computing_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Patient_MasterActivity extends AppCompatActivity {

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
}
