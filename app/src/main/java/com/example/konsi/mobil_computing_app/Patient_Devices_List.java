package com.example.konsi.mobil_computing_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Patient_Devices_List extends Patient_MasterActivity {

    public static final String selected_device = "SELECTED_DEVICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Test List Elements
        final String[] items = new String[] {"Device1", "Device2", "Device3", "Device4"};
        final Intent intent = new Intent(this, Patient_Main_Page.class);


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


}
