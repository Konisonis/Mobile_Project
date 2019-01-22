package com.example.konsi.mobil_computing_app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class Patient_Measurements extends Patient_MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurements);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.patient_menu, menu);
        return true;
    }


}
