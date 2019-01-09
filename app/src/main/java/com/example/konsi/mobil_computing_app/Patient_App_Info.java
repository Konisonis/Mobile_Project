package com.example.konsi.mobil_computing_app;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import static android.content.pm.PackageManager.GET_META_DATA;

public class Patient_App_Info extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__app__info);

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
        Intent logoutIntent = new Intent(this, Patient_Devices_List.class);
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
                //TODO Logout
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View view) {
        Log.d(TAG, "App Updated ");
    }
}
