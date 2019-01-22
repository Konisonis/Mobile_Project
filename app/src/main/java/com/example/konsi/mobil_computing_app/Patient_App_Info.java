package com.example.konsi.mobil_computing_app;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class Patient_App_Info extends Patient_MasterActivity {
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


    public void onClick(View view) {
        Toast.makeText (getApplicationContext(), "App updated!", Toast.LENGTH_LONG).show();
    }
}
