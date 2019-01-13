package com.example.konsi.mobil_computing_app;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;

import com.google.gson.Gson;

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
    protected ArrayList<Patient> filteredSortedPatients;

    private PatientAndDoctorViewModel patdocmodel;

//    public static void setListViewHeightBasedOnChildren(ListView listView) {
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) return;
//        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
//                View.MeasureSpec.UNSPECIFIED);
//        int totalHeight = 0;
//        View view = null;
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            view = listAdapter.getView(i, view, listView);
//            if (i == 0) view.setLayoutParams(new
//                    ViewGroup.LayoutParams(desiredWidth,
//                    ViewGroup.LayoutParams.WRAP_CONTENT));
//
//            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
//            totalHeight += view.getMeasuredHeight();
//        }
//
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//
//        params.height = totalHeight + (listView.getDividerHeight() *
//                (listAdapter.getCount() - 1));
//
//        listView.setLayoutParams(params);
//        listView.requestLayout();
//    }

    /**
     * Gets called when the activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__main__page);

        // Initialize ViewModel for database access
        patdocmodel = ViewModelProviders.of(this).get(PatientAndDoctorViewModel.class);

        // SearchField
        search_patient_edittext = findViewById(R.id.search_patient_edittext);

        // Buttons
        search_button = findViewById(R.id.search_button);
        addpatient_button = findViewById(R.id.addpatient_button);
        sorttable_button = findViewById(R.id.sorttable_button);
        filtertable_button = findViewById(R.id.filtertable_button);

        // Patients_lists for display
        patients_list = findViewById(R.id.patients_list);

        initializeListOptionsClicks();
        createPatientListData();
    }


    /**
     * Creates some dummy patient_list data till database functionality is implemented
     */
    private void createPatientListData() {


        this.patients = new ArrayList<Patient>();
        filteredSortedPatients = new ArrayList<Patient>();

        // Get the dummy patients for display
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString("Doctor", "");
        Doctor doc = gson.fromJson(json, Doctor.class);
        if (doc == null) {
            Log.e("DOC ERROR:", "Doctor is null");
        }

        this.patients = (ArrayList) patdocmodel.getAllPatientsByDoctorId(doc.getId());

        Log.d("READ_PATIENTS_FROM_DB", "Everything okay");

        images = new ArrayList<>();
        ids = new ArrayList<>();
        fullnames = new ArrayList<>();
        for(Patient patient : this.patients) {
            int imageReference;
            try {
                Log.e("IMAGEREFERENCE:", patient.getForname().toLowerCase());
                imageReference = getResources().
                        getIdentifier(patient.getForname().
                                toLowerCase(),"drawable", getPackageName());
                Log.e("IMAGEREFERENCE:", ""+imageReference);
                if (imageReference != 0) {
                } else {
                    imageReference = R.drawable.jan;
                }
            } catch (Resources.NotFoundException e) {
                imageReference = R.drawable.jan;
            }
            images.add(imageReference);
            ids.add(patient.getId());
            fullnames.add(patient.getFullname());
        }

        Log.e("WICHTIG WICHTIG", ""+images.size());
        Log.e("WICHTIG WICHTIG", images.toString());

        // Set the adapter for the patients data
        PatientsAdapter patientsAdapter = new PatientsAdapter(this, images, fullnames, ids);
        patients_list.setAdapter(patientsAdapter);
        // Doctor_Main_Page.setListViewHeightBasedOnChildren(patients_list);
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
                    // its the ID - look in the patients_list
                } else if(searchtext.matches("[a-zA-Z]{2,} ?[a-zA-Z]*")) {
                    // its the name - look in the patients_list
                } else {
                    // its the date of birth - look in the patients_list
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
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString("Doctor", "");
        Doctor doc = gson.fromJson(json, Doctor.class);
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
                            "@" + lastname.toLowerCase() + ".com", password, doc.getId());

                    try {
                        patdocmodel.insertPatientToDb(patty);
                        Log.d("DATABASE_LOG:", "Saved Patient to Database successfully");
                        patients.add(patty);
                        images.add(patty.getImg());
                        fullnames.add(patty.getFullname());
                        ids.add(patty.getId());
                        Toast.makeText(Doctor_Main_Page.this, "Successfully added patient to list", Toast.LENGTH_LONG).show();

                        createPatientListData();
                    } catch(Exception e) {
                        Log.e("InsertToDB", "Error saving patient to Database");
                        e.printStackTrace();
                        Toast.makeText(Doctor_Main_Page.this, "Error at adding patient to list", Toast.LENGTH_LONG).show();
                    }
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
            final ImageView profile_picture = patient_row.findViewById(R.id.patient_picture);
            final TextView patient_id = patient_row.findViewById(R.id.patient_id);
            final TextView patient_fullname = patient_row.findViewById(R.id.patient_fullname);

            profile_picture.setImageResource(images.get(position));

            patient_id.setText(ids.get(position));
            patient_fullname.setText(fullnames.get(position));
            patient_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // if clicked, route to detailed patient view, listing the devices of a user to choose for details
                    Intent intent = new Intent(Doctor_Main_Page.this, Doctor_Detailed_Patient.class);
                    intent.putExtra("patient_id", ids.get(position));
                    intent.putExtra("patient_name", fullnames.get(position));
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


