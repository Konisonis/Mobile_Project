package com.example.konsi.mobil_computing_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Doctor_Add_Patient_Message extends AppCompatActivity {
    Button save;
    ArrayList<String> addArray = new ArrayList<String>();
    EditText txt;
    ListView show;
    TextView patientname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__add__patient__message);

        patientname =(TextView) findViewById(R.id.patient_nam);

        /*Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String patientID = extras.getString("patient_id");
        String patiNam = extras.getString("patient_name");*/

        //patientname.setText(patiNam);
        //set the current time
        setTime();
        txt = (EditText) findViewById(R.id.edit_text);
        show=(ListView)findViewById(R.id.listView);
        save=(Button)findViewById(R.id.send_messagebutton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput=txt.getText().toString();
                if(addArray.contains(getInput)){
                    Toast.makeText(getBaseContext(), "Item already added", Toast.LENGTH_LONG).show();
                }
                else if(getInput==null||getInput.trim().equals("")){
                    Toast.makeText(getBaseContext(),"Input field is empty", Toast.LENGTH_LONG).show();

                }
                else{
                    addArray.add(getInput);
                    ArrayAdapter<String> adapter= new ArrayAdapter<String>(Doctor_Add_Patient_Message.this,android.R.layout.simple_list_item_1,addArray);
                    show.setAdapter(adapter);
                    ((EditText)findViewById(R.id.edit_text)).setText(" ");
                }
            }

        });
    }



    public void setTime()
    {
        final Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format= new SimpleDateFormat("dd/MM/yy HH:mm");
        String time=format.format(calendar.getTime());
        final TextView textViewDate=findViewById(R.id.current_date);
        textViewDate.setText(time);
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
            case R.id.app_info:
                startActivity(infoIntent);
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

}
