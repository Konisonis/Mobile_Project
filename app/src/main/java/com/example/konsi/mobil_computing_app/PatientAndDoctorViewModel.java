package com.example.konsi.mobil_computing_app;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;


public class PatientAndDoctorViewModel extends AndroidViewModel {

    private AppDatabase db;

    public PatientAndDoctorViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getDatabase(application);
    }

    public void insertPatientToDb(Patient patient) {
            db.patientDao().insert(patient);
    }

    public List<Patient> getAllPatientsByDoctorId(String doctorId){
            return db.patientDao().findAllByDoctor(doctorId);
    }

    public Patient getPatientById(String id){
        return db.patientDao().getById(id);
    }

    public Doctor getDoctorByEmail(String eMail){
        return db.doctorDao().findByEMail(eMail);
    }

    public Patient getPatientByEmail(String eMail){
        return db.patientDao().findByEmail(eMail);
    }


}
