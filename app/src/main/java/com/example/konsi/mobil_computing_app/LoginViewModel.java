package com.example.konsi.mobil_computing_app;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

public class LoginViewModel extends AndroidViewModel {

    private AppDatabase db;

    public LoginViewModel(@NonNull Application application){
        super(application);
         db = AppDatabase.getDatabase(application);
    }


    public Doctor getDoctor(String eMail){
        return db.doctorDao().findByEMail(eMail);
    }

    public Patient getPatient(String eMail){
        return db.patientDao().findByEmail(eMail);
    }

}
