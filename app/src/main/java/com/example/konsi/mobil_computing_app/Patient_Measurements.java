package com.example.konsi.mobil_computing_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;

public class Patient_Measurements extends Patient_MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurements);

        //Check which fragment to inflate by extra in intent, case 1 footstep, case 2 heartbeat
        Intent intent = getIntent();
        int fragmentNumber = intent.getIntExtra("whichFragment", 2);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        switch (fragmentNumber) {
            case 1:
                RealtimeFootStepUpdates fragment = new RealtimeFootStepUpdates();
                if (fragment != null) {
                    // Replace current fragment by this new one
                    ft.replace(R.id.fragmentHolder, fragment);
                    ft.commit();}
                    break;
            case 2:
                RealtimeHeartBeatUpdates fragment2 = new RealtimeHeartBeatUpdates();
                if (fragment2 != null) {
                    // Replace current fragment by this new one
                    ft.replace(R.id.fragmentHolder, fragment2);
                    ft.commit();

                }
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.patient_menu, menu);
        return true;
    }


}
