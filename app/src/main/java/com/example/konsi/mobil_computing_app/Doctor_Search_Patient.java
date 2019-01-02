package com.example.konsi.mobil_computing_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Activity called when a patient is searched for in the patients list
 */
public class Doctor_Search_Patient extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private LinearLayout searchpatient;
    private EditText option_decision_value_view;
    private Spinner spinner;

    /**
     * Gets called when the activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__search__patient);

        searchpatient = findViewById(R.id.searchpatient_view);
        option_decision_value_view = findViewById(R.id.option_decision_value_view);
        // Get the search options
        String[] list = getResources().getStringArray(R.array.searchoptions);

        spinner = (Spinner) findViewById(R.id.search_spinner);
        // Create an ArrayAdapter using the string array and a spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, list);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        // Set default selected value
        spinner.setSelection(0);

        initializeSearchOnClick();
    }

    /**
     * initializes the on click feature, building a new intent with selected option and value for callback to mainpage
     */
    private void initializeSearchOnClick() {
        searchpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                String searchvalue = option_decision_value_view.getText().toString();
                String selectedoption = (String) spinner.getSelectedItem();
                returnIntent.putExtra("searchvalue", searchvalue);
                returnIntent.putExtra("searchoption", selectedoption);
                Toast.makeText(Doctor_Search_Patient.this, selectedoption + ": " + searchvalue, Toast.LENGTH_LONG).show();

                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    /**
     * Handles the event when an item is selected
     * @param parent the Dropdown-Items Holder
     * @param view the selected view
     * @param position the position of the view
     * @param id the id of the view
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        View selectedView = (View) parent.getItemAtPosition(position);
        Toast.makeText(this, "Selected:" + selectedView.toString(), Toast.LENGTH_LONG).show();
    }

    /**
     * If nothing is selected this is called
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
