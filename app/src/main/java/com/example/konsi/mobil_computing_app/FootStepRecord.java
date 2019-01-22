package com.example.konsi.mobil_computing_app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.ArrayList;

public class FootStepRecord extends ViewModel {
    private MutableLiveData<ArrayList<String>> measurementRecord;
    private ArrayList<String> dummyMeasurement;
    public LiveData<ArrayList<String>> getMeasurement(){
        if(measurementRecord== null){
            measurementRecord = new MutableLiveData<ArrayList<String>>();
            loadMeasurment();
        }
        return  measurementRecord;
    }
    private void loadMeasurment(){
        dummyMeasurement = new ArrayList<>();
        dummyMeasurement.add("Measure date: 14/01/2019 22:11 - Value: 6540 steps");
        measurementRecord.setValue(dummyMeasurement);
    }

    public void setMeasurementRecord(String date, String measurement){
        dummyMeasurement.add("Measure date: "+ date + " - Value: "+ measurement + " steps");
        measurementRecord.setValue(dummyMeasurement);
        Log.d("MyActivity", dummyMeasurement.toString());
    }
}
