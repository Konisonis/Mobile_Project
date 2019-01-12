package com.example.konsi.mobil_computing_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Represents the main activity for the doctor user
 */
public class Doctor_Main_Page extends AppCompatActivity {

    private EditText search_patient_edittext;
    private Button search_button;
    private ImageButton addpatient_button;
    private ImageButton sorttable_button;
    private ImageButton filtertable_button;
    private ListView patients_list;
    private ArrayList<Integer> images;
    private ArrayList<String> ids;
    private ArrayList<String> fullnames;
    protected ArrayList<Patient> patients;

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) return;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0) view.setLayoutParams(new
                    ViewGroup.LayoutParams(desiredWidth,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() *
                (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    /**
     * Gets called when the activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__main__page);


        search_patient_edittext = findViewById(R.id.search_patient_edittext);
        search_button = findViewById(R.id.search_button);
        addpatient_button = findViewById(R.id.addpatient_button);
        sorttable_button = findViewById(R.id.sorttable_button);
        filtertable_button = findViewById(R.id.filtertable_button);
        patients_list = findViewById(R.id.patients_list);
        initializeListOptionsClicks();
        createDummyListData();
    }


    /**
     * Creates some dummy patient_list data till database functionality is implemented
     */
    private void createDummyListData() {
        Patient pat1 = new Patient("12345", R.drawable.ravell, "Ravell", "Heerdegen", "15.02.1993","ravell@heerdegen.com", "ravell123", "00000");
        Patient pat2 = new Patient("98765", R.drawable.trang,"Trang", "Le", "23.04.1995","trang@le.com", "trang123", "00000");
        Patient pat3 = new Patient("34567", R.drawable.konstantin,"Konstantin", "Rosenberg", "23.04.1995","konstantin@rosenberg.com", "konst123", "00000");
        Patient pat4 = new Patient("76543", R.drawable.jan,"Jan", "Pohl", "23.04.1990","jan@pohl.com", "jan123", "00000");
        Patient pat5 = new Patient("23456", R.drawable.robin,"Robin", "Schramm", "23.04.1992","robin@schramm.com", "robin123", "00000");
        Patient pat6 = new Patient("54673", R.drawable.ioan,"Ioan", "Maftei", "23.04.1991","ioan@maftei.com", "ioan123", "00000");
        Patient pat7 = new Patient("12345", R.drawable.maximilian,"Maximilian", "Waiblinger", "23.04.1994","maximilian@waiblinger.com", "max123", "00000");

        // Create the dummy patients which are smaller datasets for display
        this.patients = new ArrayList<Patient>();
        patients.add(pat1);
        patients.add(pat2);
        patients.add(pat3);
        patients.add(pat4);
        patients.add(pat5);
        patients.add(pat6);
        patients.add(pat7);
        images = new ArrayList<>();
        ids = new ArrayList<>();
        fullnames = new ArrayList<>();
        for(Patient patient : patients) {
            images.add(patient.getImg());
            ids.add(patient.getId());
            fullnames.add(patient.getFullname());
        }

        // Set the adapter for the patients data
        PatientsAdapter patientsAdapter = new PatientsAdapter(this, images, fullnames, ids);
        patients_list.setAdapter(patientsAdapter);
        Doctor_Main_Page.setListViewHeightBasedOnChildren(patients_list);
    }

    /**
     * Initializes the click-event handling for the clickable layout views
     */
    private void initializeListOptionsClicks() {
        // Add a new patient
        addpatient_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctor_Main_Page.this, Doctor_Add_Patient.class);
                startActivityForResult(intent, 0);
            }
        });
        // Search for one patient or multiple
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchtext = search_patient_edittext.getText().toString();
                // Check for five numbers in a row
                if (searchtext.matches("[0-9]{5}")) {
                    // its the ID
                } else if(searchtext.matches("[a-zA-Z]{2,} ?[a-zA-Z]*")) {
                    // its the name
                } else {
                    // its the date of birth
                }
            }
        });
        // Sort the table by criteria
        sorttable_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // Filter the table by criteria
        filtertable_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * Handles the finished Subactivities callbacks
     * @param requestcode the intent code receiving back to differ activities
     * @param resultcode the resultcode determining if an activity finished successful
     * @param intent the finished Intent callback with all his Extras
     */
    @Override
    public void onActivityResult(int requestcode, int resultcode, Intent intent) {
        if (resultcode == RESULT_OK) {
            switch (requestcode) {
                case 0:
                    // Add the new patient
                    String id = intent.getStringExtra("id");
                    String forname = intent.getStringExtra("forname");
                    String lastname = intent.getStringExtra("lastname");
                    String birthdate = intent.getStringExtra("birthdate");
                    String password = intent.getStringExtra("password");
                    Patient patty = new Patient(id, R.drawable.jan, forname, lastname, birthdate, forname.toLowerCase() +
                            "@" + lastname.toLowerCase() + ".com", password, "00000");
                    patients.add(patty);
                    images.add(patty.getImg());
                    fullnames.add(patty.getFullname());
                    ids.add(patty.getId());
                    break;
            }
        }
    }

    /**
     * Patients Adapter class for specific display feature for patients list of a doctors main page
     */
    class PatientsAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<Integer> images;
        ArrayList<String>  fullnames;
        ArrayList<String>  ids;

        /**
         * Constructs a simple patients adapter for a patients_list
         * @param context the context running in
         * @param images the images to handle
         * @param fullnames the names for the fullname view
         * @param ids the ids for the ids view
         */
        PatientsAdapter(Context context, ArrayList<Integer> images, ArrayList<String>  fullnames, ArrayList<String>  ids) {
            super(context, R.layout.patient_row, R.id.patient_id, ids);
            this.context = context;
            this.images = images;
            this.fullnames = fullnames;
            this.ids = ids;
        }

        /**
         * Decides which data of the patients data arrays is to use for one displayed view
         * @param position the position in the data arrays
         * @param convertView the view to convert
         * @param parent the parent object the view is displayed in
         * @return the displayable view
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutinflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View patient_row = layoutinflater.inflate(R.layout.patient_row, parent, false);
            ImageView profile_picture = findViewById(R.id.patient_picture);
            final TextView patient_id = findViewById(R.id.patient_id);
            final TextView patient_fullname = findViewById(R.id.patient_fullname);
            profile_picture.setImageResource(images.get(position));
            patient_id.setText(ids.get(position));
            patient_fullname.setText(fullnames.get(position));
            patient_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // if clicked, route to detailed patient view, listing the devices of a user to choose for details
                    Intent intent = new Intent(Doctor_Main_Page.this, Doctor_Detailed_Patient.class);
                    intent.putExtra("patient_id", ids.get(position));
                    intent.putExtra("patient_name", patient_fullname.getText().toString());
                    for(Patient p : patients) {
                        if (p.getId().equals(patient_id.getText().toString())) {
                            intent.putExtra("patient_devices", p.getDevices());
                            break;
                        }
                    }
                    startActivity(intent);
                }
            });
            return patient_row;
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


