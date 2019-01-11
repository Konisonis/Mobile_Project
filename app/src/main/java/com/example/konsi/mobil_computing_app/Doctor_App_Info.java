package com.example.konsi.mobil_computing_app;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

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
