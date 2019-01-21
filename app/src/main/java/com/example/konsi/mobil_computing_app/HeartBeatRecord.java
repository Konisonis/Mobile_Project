package com.example.konsi.mobil_computing_app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class HeartBeatRecord extends ViewModel {
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
        dummyMeasurement.add("Measure date: 14/01/2019 10:20 - Value: 85 BPM");
        measurementRecord.setValue(dummyMeasurement);
    }

    public void setMeasurementRecord(String date, String measurement){
        dummyMeasurement.add("Measure date: "+ date + " - Value: "+ measurement + " BPM");
        measurementRecord.setValue(dummyMeasurement);
    }
}
