package com.example.konsi.mobil_computing_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class Doctor_App_Info extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__app__info);

        final PackageManager pm = getPackageManager();

        try {
            ApplicationInfo app = this.getPackageManager().getApplicationInfo("com.example.konsi.mobil_computing_app", 0);
            PackageInfo packageInfo = pm.getPackageInfo(app.packageName, PackageManager.GET_PERMISSIONS);
            Date installTime = new Date( packageInfo.firstInstallTime );
            Date updateTime = new Date( packageInfo.lastUpdateTime );

            String appInfoText = "Name: " + pm.getApplicationLabel(app) + "\nVersion: " + packageInfo.versionCode + "\nInstalled: " + installTime.toString() + "\nUpdated: " + updateTime.toString();
            TextView textView = findViewById(R.id.appInfo);
            textView.setText(appInfoText);
        } catch ( PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
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
    public void onClick(View view) {
        Toast.makeText (getApplicationContext(), "App updated!", Toast.LENGTH_LONG).show();
    }
}
