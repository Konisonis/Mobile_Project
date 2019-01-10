package com.example.konsi.mobil_computing_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Patient_Devices_List extends AppCompatActivity {

    public static final String selected_device = "SELECTED_DEVICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Test List Elements
        final String[] items = new String[] {"Device1", "Device2", "Device3", "Device4"};
        final Intent intent = new Intent(this, Patient_Main_Page.class);

        startActivity(new Intent(this,LoginActivity.class));


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__devices__list);

        ListView listView = (ListView) findViewById(R.id.ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra(selected_device, items[position]);
                startActivity(intent);
            }
        });
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

}
