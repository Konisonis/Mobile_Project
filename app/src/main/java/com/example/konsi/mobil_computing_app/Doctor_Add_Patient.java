package com.example.konsi.mobil_computing_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Random;

/**
 * Activity called when a new patient is to add to the patients list
 */
public class Doctor_Add_Patient extends AppCompatActivity {

    private LinearLayout addpatient;
    private EditText forname_view;
    private EditText lastname_view;
    private EditText birthdate_view;

    /**
     * Gets called when the activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__add__patient);

        addpatient = findViewById(R.id.addpatient_view);
        forname_view = findViewById(R.id.forname_view);
        lastname_view = findViewById(R.id.lastname_view);
        birthdate_view = findViewById(R.id.birthdate_view);

        initializeAddPatientOnClick();
    }

    /**
     * initializes the on click event, builds the patients data needed
     * for creating a new patient in the mainpage and returning an intent
     */
    private void initializeAddPatientOnClick() {
        addpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                // Build ID out of five random numbers
                int num1 = new Random().nextInt(10);
                int num2 = new Random().nextInt(10);
                int num3 = new Random().nextInt(10);
                int num4 = new Random().nextInt(10);
                int num5 = new Random().nextInt(10);
                String id = "" + num1 + num2 + num3 + num4 + num5;
                String forname = forname_view.getText().toString();
                String lastname = lastname_view.getText().toString();
                String birthdate = birthdate_view.getText().toString();
                returnIntent.putExtra("id", id);
                returnIntent.putExtra("forname", forname);
                returnIntent.putExtra("lastname", lastname);
                returnIntent.putExtra("birthdate", birthdate);
                // Build Password with forname and ID
                String pwname = forname.toLowerCase();
                String password = pwname + id;
                returnIntent.putExtra("password", password);

                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }


}
