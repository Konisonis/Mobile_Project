package com.example.konsi.mobil_computing_app;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Patient_Messages extends Patient_MasterActivity {

    private ListView messageListView;
    private PatientAndDoctorViewModel patdocmodel;
    private ArrayList<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__messages);

        // Initialize ViewModel for database access
        patdocmodel = ViewModelProviders.of(this).get(PatientAndDoctorViewModel.class);

        //get all messages; TODO get message by patient id
        this.messages = (ArrayList) patdocmodel.getAllMessages();

        messageListView = (ListView)findViewById(R.id.messagesListView);

        ArrayAdapter<Message> arrayAdapter = new ArrayAdapter<Message>(this, android.R.layout.simple_list_item_1, messages);

        messageListView.setAdapter(arrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.patient_menu, menu);
        return true;
    }


}
