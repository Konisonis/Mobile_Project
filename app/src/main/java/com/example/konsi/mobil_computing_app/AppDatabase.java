package com.example.konsi.mobil_computing_app;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Date;

@Database(entities = {Patient.class,Doctor.class,Message.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PatientDao patientDao();
    public abstract DoctorDao doctorDao();
    public abstract MessageDao messageDao();

    private static AppDatabase INSTANCE;


    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "AppDatabase")
                            .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.d("MoviesDatabase", "populating with data...");
                                    new PopulateDbAsync(INSTANCE).execute();
                                }
                            })
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    //Dump Database and create new


    public static void destroyInstance() {
        INSTANCE = null;
    }


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final PatientDao patientDao;
        private final DoctorDao doctorDao;
        private final MessageDao messageDao;

        public PopulateDbAsync(AppDatabase instance) {
            patientDao = instance.patientDao();
            doctorDao = instance.doctorDao();
            messageDao = instance.messageDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            patientDao.deleteAll();
            patientDao.insert(new Patient("123",1,"Hanns","Ibal","01.01.2019","hanns@patient.de","admin123","321"));
            patientDao.insert(new Patient("456",1,"Peter","Pan","01.01.2019","peter@patient.de","admin123","321"));
            patientDao.insert(new Patient("789",1,"Konsi","Nuss","01.01.2019","konsi@patient.de","admin123","321"));
            patientDao.insert(new Patient("109",1,"Ravell","Heerdegen","01.01.2019","ravell@patient.de","admin123","321"));
            patientDao.insert(new Patient("192",1,"Admin","Admin","01.01.2019","admin@patient.de","admin123","321"));

            //add dummy doctors

            doctorDao.deleteAll();
            doctorDao.insert(new Doctor("321","Admin","Superdoctor","admin@doctor.de","01.01.19","admin123"));
            doctorDao.insert(new Doctor("098","Dr. Chiro","Praqtica","admin@doctor1.de","01.01.19","admin123"));
            doctorDao.insert(new Doctor("876","Admin","Admin","admin@doctor2.de","01.01.19","admin123"));

            //add dummy messages

            messageDao.deleteAll();
            messageDao.insert(new Message("111", "1.1.1", "This is a test message", "098", "109"));

            return null;
        }
    }
}



