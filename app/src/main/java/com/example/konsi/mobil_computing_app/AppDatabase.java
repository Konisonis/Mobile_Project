package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ListPatient.class,ListDoctor.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PatientDao patientDao();
    public abstract DoctorDao docterDao();
}
